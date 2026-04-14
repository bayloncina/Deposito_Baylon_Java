package com.example.test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.test.service.MessaggioService;

@RestController
public class DemoController {

    private final MessaggioService messaggioService;


    // constructor Injection
    @Autowired
    public DemoController(MessaggioService messaggioService) {
        this.messaggioService = messaggioService;
    }

    @GetMapping("/saluta")
    public String saluta() {
        messaggioService.saluta();
        return "Saluto inviato";
    }

}
