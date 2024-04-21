package com.who.shuk.JWTAuth.Controllers;

import com.who.shuk.JWTAuth.Model.AuthenticationRequest;
import com.who.shuk.JWTAuth.Model.AuthenticationResponse;
import com.who.shuk.JWTAuth.Service.AuthenticationService;
import com.who.shuk.JWTAuth.Model.RegistrationRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService service;
    @PostMapping("/register")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<AuthenticationResponse>  register(
            @RequestBody @Valid RegistrationRequest request){
        System.out.println("Inside register method");
        service.register(request);
        return ResponseEntity.accepted().build();
    }
    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse>  authenticate(@RequestBody AuthenticationRequest request){
        System.out.println("Inside authenticate method");
        AuthenticationResponse authenticate = service.authenticate(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(authenticate);

    }
}
