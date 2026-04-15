package com.example.gestionalePizzeria.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data 
@NoArgsConstructor 
@AllArgsConstructor
public class PizzaMenu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "pizza_id")
    private Pizza pizza;

    private Double prezzo;
    private Boolean disponibile;
}
