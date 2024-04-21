package com.who.shuk.JWTAuth.Service;

import com.who.shuk.JWTAuth.Service.JwtService;
import com.who.shuk.JWTAuth.Entity.Role;
import com.who.shuk.JWTAuth.Entity.User;
import com.who.shuk.JWTAuth.Repository.UserRepository;
import com.who.shuk.JWTAuth.model.AuthenticationRequest;
import com.who.shuk.JWTAuth.model.AuthenticationResponse;
import com.who.shuk.JWTAuth.model.RegistrationRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    public AuthenticationResponse register(RegistrationRequest request) { //create a user, save to DB and return the generated token
        var user= User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();
        userRepository.save(user);
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse
                .builder()
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                                            request.getEmail(),request.getPassword()));
        System.out.println("Above");
        var user = userRepository.findByEmail(request.getEmail())
                .orElseThrow();
        System.out.println("Below");
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse
                .builder()
                .token(jwtToken)
                .build();

    }
}
