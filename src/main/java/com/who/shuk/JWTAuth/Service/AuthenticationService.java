package com.who.shuk.JWTAuth.Service;

import com.who.shuk.JWTAuth.Entity.RoleEnum;
import com.who.shuk.JWTAuth.Entity.User;
import com.who.shuk.JWTAuth.Repository.RoleRepository;
import com.who.shuk.JWTAuth.Repository.UserRepository;
import com.who.shuk.JWTAuth.Model.AuthenticationRequest;
import com.who.shuk.JWTAuth.Model.AuthenticationResponse;
import com.who.shuk.JWTAuth.Model.RegistrationRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    public void register(RegistrationRequest request) {
        var userRole = roleRepository.findByName("USER")
                //exception handling
                .orElseThrow(()-> new IllegalStateException("Role User was not Initialized"));
        var user=User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword())) //everytime to register a user, we need to encode the password
                .accountLocked(false)
                .enabled(false)
                //.roles(List.of(userRole))
                .build();
        userRepository.save(user);
        //sendValidationEmail(user);

    }


    /*public AuthenticationResponse register(RegistrationRequest request) { //create a user, save to DB and return the generated token
        var user= User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(RoleEnum.USER)
                .build();
        userRepository.save(user);
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse
                .builder()
                .token(jwtToken)
                .build();
    }*/

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
