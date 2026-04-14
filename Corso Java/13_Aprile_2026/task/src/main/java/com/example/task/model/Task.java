package com.example.task.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

// @Data genera automaticamente getter, setter, toString, equals e hashCode (Lombok)
// @AllArgsConstructor genera un costruttore con tutti i campi
// @NoArgsConstructor genera il costruttore vuoto, obbligatorio per JPA
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titolo;
    // La descrizione non può essere una stringa vuota o composta solo da spazi
    // Se la validazione fallisce, il messaggio di errore sarà quello indicato
    @NotBlank(message = "La descrizione non può essere vuota")

    // La descrizione deve avere una lunghezza compresa tra 3 e 20 caratteri
    @Size(min = 3, max = 20, message = "La descrizione deve essere tra 3 e 20 caratteri")

    // Mappa questo campo sulla colonna "descrizione" nel database
    // nullable = false significa che il campo non può essere NULL nel DB
    @Column(name = "descrizione", nullable = false)
    private String descrizione;

    private int priorita;
    private LocalDate scadenza;

    // Salva il valore dell'enum come stringa nel DB (es. "TODO", "IN_PROGRESS")
    // Senza questa annotazione verrebbe salvato come numero intero (0, 1, 2...)
    // il che renderebbe i dati illeggibili nel database
    @Enumerated(EnumType.STRING)
    private StatoTask stato;

}