package com.example.task.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titolo;
    @NotBlank(message = "La descrizione non può essere vuota")
    @Size(min = 3, max = 20, message = "La descrizione deve essere tra 3 e 20 caratteri")
    @Column(name = "descrizione", nullable = false)
    private String descrizione;
    private int priorita;
    private LocalDate scadenza;

    @Enumerated(EnumType.STRING)
    private StatoTask stato;

}