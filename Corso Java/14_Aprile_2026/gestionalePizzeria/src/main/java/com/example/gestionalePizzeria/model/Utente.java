package com.example.gestionalePizzeria.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Data 
@NoArgsConstructor 
@AllArgsConstructor
public class Utente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String email;

    @JsonProperty("isAdmin")
    private boolean isAdmin;

    private int puntiBonus;

    @JsonIgnore
    @OneToMany(mappedBy = "utente", cascade = CascadeType.ALL)
    private List<Ordine> ordini;
}
