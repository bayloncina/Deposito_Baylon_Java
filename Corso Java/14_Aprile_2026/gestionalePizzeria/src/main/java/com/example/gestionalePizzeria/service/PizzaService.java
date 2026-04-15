package com.example.gestionalePizzeria.service;

import com.example.gestionalePizzeria.model.Pizza;
import com.example.gestionalePizzeria.repository.PizzaRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PizzaService {

    private final PizzaRepository pizzaRepository;

    public PizzaService(PizzaRepository pizzaRepository) {
        this.pizzaRepository = pizzaRepository;
    }

    public List<Pizza> getAll() {
        return pizzaRepository.findAll();
    }

    public Pizza getById(Long id) {
        return pizzaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pizza non trovata con id: " + id));
    }

    public Pizza save(Pizza pizza) {
        return pizzaRepository.save(pizza);
    }

    public Pizza update(Long id, Pizza nuova) {
        Pizza pizza = getById(id);
        pizza.setNome(nuova.getNome());
        pizza.setDescrizione(nuova.getDescrizione());
        pizza.setIngredienti(nuova.getIngredienti());
        return pizzaRepository.save(pizza);
    }

    public void delete(Long id) {
        pizzaRepository.deleteById(id);
    }
}
