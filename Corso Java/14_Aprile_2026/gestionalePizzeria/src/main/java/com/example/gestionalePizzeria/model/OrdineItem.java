package com.example.gestionalePizzeria.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor 
@AllArgsConstructor
public class OrdineItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ordine_id")
    private Ordine ordine;

    @ManyToOne
    @JoinColumn(name = "pizza_menu_id")
    private PizzaMenu pizzaMenu;

    private int quantita;
    private Double prezzoUnitario;
}
