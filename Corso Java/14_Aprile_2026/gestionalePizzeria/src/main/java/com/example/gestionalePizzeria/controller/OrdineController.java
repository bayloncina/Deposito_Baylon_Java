package com.example.gestionalePizzeria.controller;

import com.example.gestionalePizzeria.model.Ordine;
import com.example.gestionalePizzeria.model.OrdineItem;
import com.example.gestionalePizzeria.model.StatoOrdine;
import com.example.gestionalePizzeria.service.OrdineService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

/**
 * Controller REST per la gestione degli ordini.
 * Espone le operazioni per recuperare ordini, creare un nuovo ordine e aggiornare lo stato.
 */
@RestController
@RequestMapping("/ordini")
public class OrdineController {

    private final OrdineService ordineService;

    public OrdineController(OrdineService ordineService) {
        this.ordineService = ordineService;
    }

    /**
     * Restituisce tutti gli ordini associati a un utente specifico.
     */
    @GetMapping("/utente/{utenteId}")
    public ResponseEntity<List<Ordine>> getByUtente(@PathVariable Long utenteId) {
        return ResponseEntity.ok(ordineService.getByUtente(utenteId));
    }

    /**
     * Restituisce tutti gli ordini presenti nel sistema.
     */
    @GetMapping("/all")
    public ResponseEntity<List<Ordine>> getAll() {
        return ResponseEntity.ok(ordineService.getAllOrdini());
    }

    /**
     * Crea un nuovo ordine utilizzando l'ID utente e la lista di items nel corpo della richiesta.
     */
    @PostMapping
    public ResponseEntity<Ordine> creaOrdine(@RequestBody OrdineRequest request) {
        Ordine ordine = ordineService.creaOrdine(request.utenteId, request.items);
        return ResponseEntity.ok(ordine);
    }

    /**
     * Aggiorna lo stato di un ordine esistente.
     * Richiede l'header X-User-Id per identificare l'utente che effettua l'operazione.
     */
    @PutMapping("/{id}/stato")
    public ResponseEntity<Ordine> aggiornaStato(@PathVariable Long id,
            @RequestBody Map<String, String> body,
            @RequestHeader("X-User-Id") Long utenteId) {
        StatoOrdine nuovoStato = StatoOrdine.valueOf(body.get("stato"));
        return ResponseEntity.ok(ordineService.aggiornaStato(id, nuovoStato, utenteId));
    }

    /**
     * DTO di richiesta per la creazione di un ordine.
     */
    static class OrdineRequest {
        public Long utenteId;
        public List<OrdineItem> items;
    }
}
