package com.example.toDoListWithAutentication.todo;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.example.toDoListWithAutentication.user.Utente;
import com.example.toDoListWithAutentication.user.UtenteRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TodoService {

    private final TodoRepository todoRepository;
    private final UtenteRepository utenteRepository;

    public Todo creaTodo(Todo todo, String userUtente) {
        Utente utente = utenteRepository.findByUsername(userUtente)
                .orElseThrow(() -> new RuntimeException("Utente non trovato"));

        todo.setUtente(utente);
        return todoRepository.save(todo);
    }

    public List<Todo> getTodosUtente(String userUtente) {
        Utente utente = utenteRepository.findByUsername(userUtente)
                .orElseThrow(() -> new RuntimeException("Utente non trovato"));

        return todoRepository.findByUtente(utente);
    }

    public Todo aggiornaTodo(Long id, Todo nuovoTodo) {
        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Todo non trovato"));

        todo.setTitolo(nuovoTodo.getTitolo());
        todo.setDescrizione(nuovoTodo.getDescrizione());
        todo.setCompletato(nuovoTodo.isCompletato());

        return todoRepository.save(todo);
    }

    public void eliminaTodo(Long id) {
        todoRepository.deleteById(id);
    }
}
