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

@Service
public class TaskService {

    private final TaskRepository repository;

    public TaskService(TaskRepository repository) {
        this.repository = repository;
    }

    public List<Task> getAll(StatoTask stato, String search) {
        return repository.findAll()
                .stream()
                .sorted(
                        Comparator.comparingInt(Task::getPriorita)
                                .thenComparing(Task::getTitolo))
                .filter(t -> stato == null || t.getStato() == stato)
                .filter(t -> search == null || t.getTitolo().toLowerCase().contains(search.toLowerCase()))
                .collect(Collectors.toList());
    }

    public Optional<Task> getById(Long id) {
        return repository.findById(id);
    }

    public Task crea(Task task) {
        task.setStato(StatoTask.TODO);
        return repository.save(task);
    }

    public Task aggiorna(Long id, StatoTask nuovoStato) {
        Task task = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task non trovato con id: " + id));

        if (!transazioneConsentita(task.getStato(), nuovoStato)) {
            throw new IllegalStateException(
                    "Transizione di stato non consentita: da " + task.getStato() + " a " + nuovoStato);
        }

        task.setStato(nuovoStato);
        return repository.save(task);
    }

    public void elimina(Long id) {
        repository.deleteById(id);
    }


    public List<Task> getScaduti() {
        return repository.findAll()
                .stream()
                .filter(t -> t.getScadenza() != null)
                .filter(t -> t.getScadenza().isBefore(LocalDate.now()))
                .filter(t -> t.getStato() != StatoTask.DONE && t.getStato() != StatoTask.CANCELLED)
                .collect(Collectors.toList());
    }

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

    private boolean transazioneConsentita(StatoTask attuale, StatoTask nuovo) {
        return switch (attuale) {
            case TODO -> nuovo == StatoTask.IN_PROGRESS || nuovo == StatoTask.CANCELLED;
            case IN_PROGRESS -> nuovo == StatoTask.DONE || nuovo == StatoTask.CANCELLED;
            case DONE, CANCELLED -> false;
        };
    }
}