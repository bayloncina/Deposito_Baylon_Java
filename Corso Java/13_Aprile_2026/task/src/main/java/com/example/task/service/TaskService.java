package com.example.task.service;

import org.springframework.stereotype.Service;

import com.example.task.model.StatoTask;
import com.example.task.model.Task;
import com.example.task.repository.TaskRepository;

import java.util.List;
import java.util.Optional;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.stream.Collectors;

// Indica che questa classe è un componente di servizio:
// contiene tutta la logica di business dell'applicazione
@Service
public class TaskService {
    // Il repository gestisce le operazioni sul database
    private final TaskRepository repository;

    // Iniezione delle dipendenze tramite costruttore
    public TaskService(TaskRepository repository) {
        this.repository = repository;
    }

    // Restituisce tutti i task con ordinamento e filtri opzionali
    public List<Task> getAll(StatoTask stato, String search) {
        return repository.findAll()
                .stream()
                // Ordina prima per priorità crescente (1 prima di 3)
                // a parità di priorità, ordina per titolo in ordine alfabetico
                .sorted(
                        Comparator.comparingInt(Task::getPriorita)
                                .thenComparing(Task::getTitolo))
                // Filtro per stato: se stato è null (non passato), il filtro viene ignorato
                .filter(t -> stato == null || t.getStato() == stato)
                // Filtro per parola nel titolo: case-insensitive
                // se search è null (non passato), il filtro viene ignorato
                .filter(t -> search == null || t.getTitolo().toLowerCase().contains(search.toLowerCase()))
                // Raccoglie i risultati in una List
                .collect(Collectors.toList());
    }

    public Optional<Task> getById(Long id) {
        return repository.findById(id);
    }

    public Task crea(Task task) {
        task.setStato(StatoTask.TODO);
        return repository.save(task);
    }

    // Crea un nuovo task e lo salva nel database
    public Task aggiorna(Long id, StatoTask nuovoStato) {
        // Cerca il task per id, se non esiste lancia un'eccezione
        // orElseThrow evita di gestire manualmente il caso Optional.empty()
        Task task = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task non trovato con id: " + id));
        // Controlla se la transizione di stato è consentita
        // Se non lo è, lancia un'eccezione che il controller trasformerà in 400 Bad Request
        if (!transazioneConsentita(task.getStato(), nuovoStato)) {
            throw new IllegalStateException(
                    "Transizione di stato non consentita: da " + task.getStato() + " a " + nuovoStato);
        }
        // Aggiorna lo stato e salva nel database
        task.setStato(nuovoStato);
        return repository.save(task);
    }

    public void elimina(Long id) {
        repository.deleteById(id);
    }

    // Restituisce i task scaduti:
    // hanno una data di scadenza impostata
    // la scadenza è precedente a oggi
    // non sono già completati (DONE) o annullati (CANCELLED)
    public List<Task> getScaduti() {
        return repository.findAll()
                .stream()
                .filter(t -> t.getScadenza() != null)
                .filter(t -> t.getScadenza().isBefore(LocalDate.now()))
                .filter(t -> t.getStato() != StatoTask.DONE && t.getStato() != StatoTask.CANCELLED)
                .collect(Collectors.toList());
    }

    // Restituisce i task in scadenza entro N giorni
    // "oggi" è il limite inferiore, "limite" è il limite superiore
    public List<Task> getInScadenza(int giorni) {
        LocalDate oggi = LocalDate.now();
        LocalDate limite = oggi.plusDays(giorni);

        return repository.findAll()
                .stream()
                .filter(t -> t.getScadenza() != null)
                .filter(t -> !t.getScadenza().isBefore(oggi))
                .filter(t -> !t.getScadenza().isAfter(limite))
                .collect(Collectors.toList());
    }

    // Metodo privato che definisce le transizioni di stato consentite
    // Da TODO si può andare solo a IN_PROGRESS o CANCELLED
    // Da IN_PROGRESS si può andare solo a DONE o CANCELLED
    // Da DONE o CANCELLED nessuna transizione permessa (stati finali)
    private boolean transazioneConsentita(StatoTask attuale, StatoTask nuovo) {
        return switch (attuale) {
            case TODO -> nuovo == StatoTask.IN_PROGRESS || nuovo == StatoTask.CANCELLED;
            case IN_PROGRESS -> nuovo == StatoTask.DONE || nuovo == StatoTask.CANCELLED;
            case DONE, CANCELLED -> false;
        };
    }
}