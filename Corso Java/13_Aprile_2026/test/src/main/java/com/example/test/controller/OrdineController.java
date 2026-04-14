package com.example.test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.test.service.OrdineService;

@RestController
public class OrdineController {

    private final OrdineService ordineService;

    @Autowired
    public OrdineController(OrdineService ordineService) {
        this.ordineService = ordineService;
    }

    @PostMapping("/ordina")
    public String ordina(@RequestBody String ordine) {
        ordineService.creaOrdine();
        return "Ordine effettuatodi "+ ordine + " effettuato";
    }
}