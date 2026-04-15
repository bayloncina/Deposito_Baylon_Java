package com.example.gestionalePizzeria.service;

import com.example.gestionalePizzeria.model.PizzaMenu;
import com.example.gestionalePizzeria.model.Utente;
import com.example.gestionalePizzeria.repository.PizzaMenuRepository;
import com.example.gestionalePizzeria.repository.UtenteRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class MenuService {

    private final PizzaMenuRepository pizzaMenuRepository;
    private final UtenteRepository utenteRepository;

    public MenuService(PizzaMenuRepository pizzaMenuRepository, UtenteRepository utenteRepository) {
        this.pizzaMenuRepository = pizzaMenuRepository;
        this.utenteRepository = utenteRepository;
    }

    public List<PizzaMenu> getAll() {
        return pizzaMenuRepository.findAll();
    }

    public List<PizzaMenu> getDisponibili() {
        return pizzaMenuRepository.findByDisponibileTrue();
    }

    public PizzaMenu getById(Long id) {
        return pizzaMenuRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Voce di menu non trovata con id: " + id));
    }

    private void verificaAdmin(Long utenteId) {
        Utente utente = utenteRepository.findById(utenteId)
                .orElseThrow(() -> new RuntimeException("Utente non trovato con id: " + utenteId));
        if (!utente.isAdmin()) {
            throw new RuntimeException("Non autorizzato");
        }
    }

    public PizzaMenu save(PizzaMenu pizzaMenu, Long utenteId) {
        verificaAdmin(utenteId);
        return pizzaMenuRepository.save(pizzaMenu);
    }

    public PizzaMenu update(Long id, PizzaMenu nuova, Long utenteId) {
        verificaAdmin(utenteId);
        PizzaMenu pizzaMenu = getById(id);
        pizzaMenu.setPizza(nuova.getPizza());
        pizzaMenu.setPrezzo(nuova.getPrezzo());
        pizzaMenu.setDisponibile(nuova.getDisponibile());
        return pizzaMenuRepository.save(pizzaMenu);
    }

    public void delete(Long id, Long utenteId) {
        verificaAdmin(utenteId);
        pizzaMenuRepository.deleteById(id);
    }
}
