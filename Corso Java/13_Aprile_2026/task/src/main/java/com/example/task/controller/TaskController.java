package com.example.task.controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.task.model.StatoTask;
import com.example.task.model.Task;
import com.example.task.service.TaskService;

import java.util.List;

// Indica che questa classe è un controller REST:
@RestController
// Tutti gli endpoint di questa classe iniziano con /tasks
@RequestMapping("/tasks")
public class TaskController {

    // Il service contiene la logica di business
    // "final" significa che non può essere riassegnato dopo l'inizializzazione
    private final TaskService service;

    // Iniezione delle dipendenze tramite costruttore (consigliata rispetto a
    // @Autowired)
    public TaskController(TaskService service) {
        this.service = service;
    }

    // GET /tasks restituisce tutti i task
    // GET /tasks?stato=TODO filtra per stato
    // GET /tasks?search=spesa filtra per parola nel titolo
    // GET /tasks?stato=TODO&search=spesa entrambi i filtri insieme
    // "required = false" significa che i parametri sono opzionali:
    // se non vengono passati, arrivano come null al service
    @GetMapping
    public List<Task> getAll(
            @RequestParam(required = false) StatoTask stato,
            @RequestParam(required = false) String search) {
        return service.getAll(stato, search);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> getById(@PathVariable Long id) {
        return service.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // POST /tasks crea un nuovo task
    // @Valid attiva la validazione dei campi dell'oggetto Task (es. @NotNull,
    // @NotBlank)
    // Se la validazione fallisce, Spring risponde automaticamente con 400 Bad
    // Request
    // @RequestBody deserializza il JSON ricevuto in un oggetto Task
    @PostMapping
    public Task crea(@Valid @RequestBody Task task) {
        return service.crea(task);
    }

    // PUT /tasks/{id}?stato=IN_PROGRESS aggiorna lo stato di un task
    // @PathVariable legge l'id dall'URL
    // @RequestParam legge il nuovo stato dalla query string
    // Se la transizione non è consentita (es. TODO DONE) 400 Bad Request
    // Se il task non esiste 404 Not Found
    @PutMapping("/{id}")
    public ResponseEntity<?> aggiorna(@PathVariable Long id,
            @RequestParam StatoTask stato) {
        try {
            Task aggiornato = service.aggiorna(id, stato);
            return ResponseEntity.ok(aggiornato);
        } catch (IllegalStateException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // DELETE /tasks/{id} elimina un task per id
    // Restituisce 204 No Content (operazione riuscita, nessun dato da restituire)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> elimina(@PathVariable Long id) {
        service.elimina(id);
        return ResponseEntity.noContent().build();
    }

    // GET /tasks/scaduti restituisce i task con scadenza passata
    // e stato diverso da DONE o CANCELLED
    @GetMapping("/scaduti")
    public List<Task> getScaduti() {
        return service.getScaduti();
    }

    // GET /tasks/in-scadenza restituisce i task in scadenza entro N giorni
    // Se non si specifica il parametro, il valore di default è 7 giorni
    // Esempio: GET /tasks/in-scadenza?giorni=3
    @GetMapping("/in-scadenza")
    public List<Task> getInScadenza(
            @RequestParam(defaultValue = "7") int giorni) {
        return service.getInScadenza(giorni);
    }
}