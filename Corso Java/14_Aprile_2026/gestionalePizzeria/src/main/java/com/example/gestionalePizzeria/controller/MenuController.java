package com.example.gestionalePizzeria.controller;

import com.example.gestionalePizzeria.model.PizzaMenu;
import com.example.gestionalePizzeria.service.MenuService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * Controller REST che espone le operazioni sui menu delle pizze.
 * Gestisce le richieste HTTP per leggere, creare, aggiornare e cancellare voci di menu.
 */
@RestController
@RequestMapping("/menu")
public class MenuController {

    private final MenuService menuService;

    public MenuController(MenuService menuService) {
        this.menuService = menuService;
    }

    /**
     * Restituisce le pizze di menu attualmente disponibili.
     */
    @GetMapping
    public ResponseEntity<List<PizzaMenu>> getDisponibili() {
        return ResponseEntity.ok(menuService.getDisponibili());
    }

    /**
     * Restituisce tutte le voci del menu, incluse quelle non disponibili.
     */
    @GetMapping("/tutte")
    public ResponseEntity<List<PizzaMenu>> getAll() {
        return ResponseEntity.ok(menuService.getAll());
    }

    /**
     * Crea una nuova voce di menu.
     * Richiede l'header X-User-Id per identificare l'utente che effettua l'operazione.
     */
    @PostMapping
    public ResponseEntity<PizzaMenu> create(@RequestBody PizzaMenu pizzaMenu,
            @RequestHeader("X-User-Id") Long utenteId) {
        return ResponseEntity.ok(menuService.save(pizzaMenu, utenteId));
    }

    /**
     * Aggiorna una voce di menu esistente identificata dall'id.
     * L'utente che richiede l'aggiornamento è passato con l'header X-User-Id.
     */
    @PutMapping("/{id}")
    public ResponseEntity<PizzaMenu> update(@PathVariable Long id,
            @RequestBody PizzaMenu pizzaMenu,
            @RequestHeader("X-User-Id") Long utenteId) {
        return ResponseEntity.ok(menuService.update(id, pizzaMenu, utenteId));
    }

    /**
     * Rimuove una voce di menu esistente.
     * Restituisce 204 No Content in caso di successo.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id,
            @RequestHeader("X-User-Id") Long utenteId) {
        menuService.delete(id, utenteId);
        return ResponseEntity.noContent().build();
    }
}
