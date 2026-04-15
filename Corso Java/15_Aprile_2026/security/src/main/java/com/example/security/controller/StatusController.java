package com.example.security.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class StatusController {

    // Endpoint pubblico per verificare lo stato di autenticazione corrente.
    // Restituisce JSON con { authenticated, username, roles }
    @GetMapping("/status")
    public Map<String, Object> status() {
        Map<String, Object> response = new HashMap<>();

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        boolean isAuthenticated = auth != null
                && auth.isAuthenticated()
                && !"anonymousUser".equals(auth.getPrincipal());

        response.put("authenticated", isAuthenticated);

        if (isAuthenticated) {
            response.put("username", auth.getName());
            response.put("roles", auth.getAuthorities().stream()
                    .map(a -> a.getAuthority())
                    .toList());
        }

        return response;
    }
}
