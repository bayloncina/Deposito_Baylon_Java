package com.example.test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.test.service.CalcolatriceService;

@RestController
public class CalcolatriceController {

    private final CalcolatriceService cService;

    @Autowired
    public CalcolatriceController(CalcolatriceService cService) {
        this.cService = cService;
    }

    @GetMapping("/somma")
    public int somma(@RequestParam int a, @RequestParam int b) {
    return cService.somma(a, b);
    }

}
