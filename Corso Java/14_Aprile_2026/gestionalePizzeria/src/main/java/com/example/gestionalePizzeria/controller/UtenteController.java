package com.example.gestionalePizzeria.controller;

import com.example.gestionalePizzeria.model.Utente;
import com.example.gestionalePizzeria.service.UtenteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

/**
 * Controller REST per la gestione degli utenti.
 * Espone endpoint per leggere, creare utenti e recuperare bonus.
 */
@RestController
@RequestMapping("/utenti")
public class UtenteController {

    private final UtenteService utenteService;

    public UtenteController(UtenteService utenteService) {
        this.utenteService = utenteService;
    }

    /**
     * Restituisce la lista completa degli utenti registrati.
     */
    @GetMapping
    public ResponseEntity<List<Utente>> getAll() {
        return ResponseEntity.ok(utenteService.getAll());
    }

    /**
     * Restituisce un utente specifico identificato dall'id.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Utente> getById(@PathVariable Long id) {
        return ResponseEntity.ok(utenteService.getById(id));
    }

    /**
     * Crea un nuovo utente nel sistema.
     */
    @PostMapping
    public ResponseEntity<Utente> create(@RequestBody Utente utente) {
        return ResponseEntity.ok(utenteService.save(utente));
    }

    /**
     * Restituisce i punti bonus associati a un utente.
     * Il risultato è fornito come mappa con utenteId e puntiBonus.
     */
    @GetMapping("/{id}/bonus")
    public ResponseEntity<Map<String, Object>> getBonus(@PathVariable Long id) {
        Utente utente = utenteService.getById(id);
        return ResponseEntity.ok(Map.of(
                "utenteId", utente.getId(),
                "puntiBonus", utente.getPuntiBonus()));
    }
}
