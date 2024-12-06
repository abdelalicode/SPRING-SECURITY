package com.secure.secureAPP.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/secure")
public class BasicSecurityController {

    @GetMapping("/public")
    public String publicEndPoint() {
        return "This is a public endpoint";
    }


    @GetMapping("/admin")
    public String adminEndPoint() {
        return "This is a admin endpoint";
    }

    @GetMapping("/client")
    public String clientsEndPoint() {
        return "This is a client endpoint";
    }
}