package com.menelucas.backend.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/teste")
public class HomeController {

    @GetMapping
    public String home() {
        return "API OK";
    }
}
