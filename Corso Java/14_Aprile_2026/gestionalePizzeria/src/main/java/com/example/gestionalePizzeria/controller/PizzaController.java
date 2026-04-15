package com.example.gestionalePizzeria.controller;

import com.example.gestionalePizzeria.model.Pizza;
import com.example.gestionalePizzeria.service.PizzaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * Controller REST per la gestione delle pizze.
 * Fornisce operazioni CRUD per le entità Pizza.
 */
@RestController
@RequestMapping("/pizze")
public class PizzaController {

    private final PizzaService pizzaService;

    public PizzaController(PizzaService pizzaService) {
        this.pizzaService = pizzaService;
    }

    /**
     * Restituisce la lista completa delle pizze.
     */
    @GetMapping
    public ResponseEntity<List<Pizza>> getAll() {
        return ResponseEntity.ok(pizzaService.getAll());
    }

    /**
     * Restituisce una singola pizza identificata dall'id.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Pizza> getById(@PathVariable Long id) {
        return ResponseEntity.ok(pizzaService.getById(id));
    }

    /**
     * Crea una nuova pizza nel sistema.
     */
    @PostMapping
    public ResponseEntity<Pizza> create(@RequestBody Pizza pizza) {
        return ResponseEntity.ok(pizzaService.save(pizza));
    }

    /**
     * Aggiorna i dati di una pizza esistente.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Pizza> update(@PathVariable Long id, @RequestBody Pizza pizza) {
        return ResponseEntity.ok(pizzaService.update(id, pizza));
    }

    /**
     * Elimina una pizza dal sistema.
     * Restituisce 204 No Content se l'eliminazione ha successo.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        pizzaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
