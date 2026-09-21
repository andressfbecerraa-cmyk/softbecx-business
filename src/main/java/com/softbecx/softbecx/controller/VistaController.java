package com.softbecx.softbecx.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class VistaController {

    @GetMapping("/login")
    public String mostrarLogin() {
        return "login";
    }

    @GetMapping("/dashboard")
    public String mostrarDashboard() {
        return "dashboard";
    }

    @GetMapping("/productos")
    public String mostrarProductos() {
        return "productos";
    }

    @GetMapping("/clientes")
    public String mostrarClientes() {
        return "clientes";
    }
}