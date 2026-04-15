package com.example.gestionalePizzeria.repository;

import com.example.gestionalePizzeria.model.PizzaMenu;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PizzaMenuRepository extends JpaRepository<PizzaMenu, Long> {
    List<PizzaMenu> findByDisponibileTrue();
}
