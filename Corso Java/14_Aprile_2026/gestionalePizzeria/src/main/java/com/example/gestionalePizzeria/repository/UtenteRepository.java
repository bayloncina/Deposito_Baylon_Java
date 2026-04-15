package com.example.gestionalePizzeria.repository;

import com.example.gestionalePizzeria.model.Utente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UtenteRepository extends JpaRepository<Utente, Long> {
}
