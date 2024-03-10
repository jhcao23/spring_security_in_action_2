package com.example.ssiach16ex2.controllers;

import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home(OAuth2AuthenticationToken authentication) {
        // do something with the authentication
        return "index.html";
    }
}
