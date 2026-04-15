package com.example.gestionalePizzeria.service;

import com.example.gestionalePizzeria.model.*;
import com.example.gestionalePizzeria.repository.*;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrdineService {

    private final OrdineRepository ordineRepository;
    private final UtenteRepository utenteRepository;
    private final PizzaMenuRepository pizzaMenuRepository;

    public OrdineService(OrdineRepository ordineRepository,
            UtenteRepository utenteRepository,
            PizzaMenuRepository pizzaMenuRepository) {
        this.ordineRepository = ordineRepository;
        this.utenteRepository = utenteRepository;
        this.pizzaMenuRepository = pizzaMenuRepository;
    }

    public List<Ordine> getByUtente(Long utenteId) {
        return ordineRepository.findByUtenteId(utenteId);
    }

    public List<Ordine> getAllOrdini() {
        return ordineRepository.findAll();
    }

    public Ordine creaOrdine(Long utenteId, List<OrdineItem> items) {
        Utente utente = utenteRepository.findById(utenteId)
                .orElseThrow(() -> new RuntimeException("Utente non trovato con id: " + utenteId));

        Ordine ordine = new Ordine();
        ordine.setUtente(utente);
        ordine.setDataOrdine(LocalDateTime.now());
        ordine.setStato(StatoOrdine.IN_ATTESA);
        ordine.setItems(items);

        double totale = 0;
        for (OrdineItem item : items) {
            PizzaMenu pizzaMenu = pizzaMenuRepository.findById(item.getPizzaMenu().getId())
                    .orElseThrow(
                            () -> new RuntimeException("PizzaMenu non trovato con id: " + item.getPizzaMenu().getId()));
            item.setOrdine(ordine);
            item.setPizzaMenu(pizzaMenu);
            item.setPrezzoUnitario(pizzaMenu.getPrezzo());
            totale += item.getPrezzoUnitario() * item.getQuantita();
        }

        ordine.setTotale(totale);
        Ordine salvato = ordineRepository.save(ordine);

        utente.setPuntiBonus(utente.getPuntiBonus() + (int) totale);
        utenteRepository.save(utente);

        return salvato;
    }

    public Ordine aggiornaStato(Long ordineId, StatoOrdine nuovoStato, Long utenteId) {
        Utente utente = utenteRepository.findById(utenteId)
                .orElseThrow(() -> new RuntimeException("Utente non trovato con id: " + utenteId));
        if (!utente.isAdmin()) {
            throw new RuntimeException("Non autorizzato");
        }
        Ordine ordine = ordineRepository.findById(ordineId)
                .orElseThrow(() -> new RuntimeException("Ordine non trovato con id: " + ordineId));
        ordine.setStato(nuovoStato);
        return ordineRepository.save(ordine);
    }
}
