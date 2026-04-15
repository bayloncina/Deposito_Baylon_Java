package com.example.gestionalePizzeria.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@NoArgsConstructor 
@AllArgsConstructor
public class Ordine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "utente_id")
    private Utente utente;

    private LocalDateTime dataOrdine;
    private double totale;

    @Enumerated(EnumType.STRING)
    private StatoOrdine stato;

    @JsonIgnore
    @OneToMany(mappedBy = "ordine", cascade = CascadeType.ALL)
    private List<OrdineItem> items;
}
