package com.example.gestionalePizzeria.service;

import com.example.gestionalePizzeria.model.Utente;
import com.example.gestionalePizzeria.repository.UtenteRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UtenteService {

    private final UtenteRepository utenteRepository;

    public UtenteService(UtenteRepository utenteRepository) {
        this.utenteRepository = utenteRepository;
    }

    public List<Utente> getAll() {
        return utenteRepository.findAll();
    }

    public Utente getById(Long id) {
        return utenteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Utente non trovato con id: " + id));
    }

    public Utente save(Utente utente) {
        return utenteRepository.save(utente);
    }

    public void aggiungiPunti(Long utenteId, int punti) {
        Utente utente = getById(utenteId);
        utente.setPuntiBonus(utente.getPuntiBonus() + punti);
        utenteRepository.save(utente);
    }
}
