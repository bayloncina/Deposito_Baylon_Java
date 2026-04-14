package com.example.task.controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.task.model.StatoTask;
import com.example.task.model.Task;
import com.example.task.service.TaskService;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService service;

    public TaskController(TaskService service) {
        this.service = service;
    }
    
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

    //se la validazione fallisce spring risponde automaticamente 400

    @PostMapping
    public Task crea(@Valid @RequestBody Task task) {
        return service.crea(task);
    }

    // Traccia 1: aggiornamento stato
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

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> elimina(@PathVariable Long id) {
        service.elimina(id);
        return ResponseEntity.noContent().build();
    }

    // Traccia 3: scadenze
    @GetMapping("/scaduti")
    public List<Task> getScaduti() {
        return service.getScaduti();
    }

    @GetMapping("/in-scadenza")
    public List<Task> getInScadenza(
            @RequestParam(defaultValue = "7") int giorni) {
        return service.getInScadenza(giorni);
    }
}