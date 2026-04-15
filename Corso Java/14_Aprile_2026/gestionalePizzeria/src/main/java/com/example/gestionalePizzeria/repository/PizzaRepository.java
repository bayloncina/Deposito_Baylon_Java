package com.example.gestionalePizzeria.repository;

import com.example.gestionalePizzeria.model.Pizza;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PizzaRepository extends JpaRepository<Pizza, Long> {
}
