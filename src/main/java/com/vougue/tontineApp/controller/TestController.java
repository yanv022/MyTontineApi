package com.vougue.tontineApp.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/test-db")
    public String testConnection() {
        return "Erfolgreiche PostgreSQL-Verbindung!🎉";
    }
}
