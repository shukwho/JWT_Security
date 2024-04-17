package com.who.shuk.JWTAuth.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService service;
    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse>  register(@RequestBody RegisterRequest request){
        System.out.println("Inside register method");
        return ResponseEntity.status(HttpStatus.CREATED).body(service.register(request));
    }
    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse>  authenticate(@RequestBody AuthenticationRequest request){
        System.out.println("Inside authenticate method");
        AuthenticationResponse authenticate = service.authenticate(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(authenticate);

    }
}
