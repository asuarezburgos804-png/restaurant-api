package com.restaurant.restaurant_api.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthController {
    
    @GetMapping("/")
    public String home() {
        return "API RESTAURANT FUNCIONANDO";
    }
    
    @GetMapping("/health")
    public String health() {
        return "OK";
    }
}