package com.example.gestionalePizzeria.repository;

import com.example.gestionalePizzeria.model.OrdineItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrdineItemRepository extends JpaRepository<OrdineItem, Long> {
}
