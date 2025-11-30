package com.restaurant.restaurant_api.config;

import com.restaurant.restaurant_api.services.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {
    private final AuthenticationService authenticationService;

    @Autowired
    public DataInitializer(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @Override
    public void run(String... args) {
        // Crear usuario admin por defecto
        authenticationService.createDefaultAdminIfNotExists();
    }
}
