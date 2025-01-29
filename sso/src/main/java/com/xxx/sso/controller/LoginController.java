package com.xxx.sso.controller;

import org.keycloak.authorization.client.AuthzClient;
import org.keycloak.representations.idm.authorization.AuthorizationRequest;
import org.keycloak.representations.idm.authorization.AuthorizationResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestParam(value = "username") String username, @RequestParam("password") String password) {
        AuthzClient authzClient = AuthzClient.create();
        AuthorizationRequest request = new AuthorizationRequest();
        AuthorizationResponse response = authzClient.authorization(username, password).authorize(request);
        return ResponseEntity.ok(response);
    }
}
