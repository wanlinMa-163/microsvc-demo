package com.xxx.sso.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestParam(value = "username") String username, @RequestParam("password") String password) {
        return ResponseEntity.ok("");
    }
}
