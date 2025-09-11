package com.springLataleLauncher.demo.controllers;

import com.springLataleLauncher.demo.interfaces.AuthRequest;
import com.springLataleLauncher.demo.interfaces.UserView;
import com.springLataleLauncher.demo.entity.User;

import com.springLataleLauncher.demo.interfaces.UserViewMapper;
import com.springLataleLauncher.demo.security.JwtTokenFilter;
import com.springLataleLauncher.demo.utils.JwtTokenUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

// @Api(tags = "Authentication")
@RestController 
@RequestMapping(path = "api/public")
public class AuthApi {
    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil jwtTokenUtil;
    private final UserViewMapper userView;
    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    public AuthApi(AuthenticationManager authenticationManager,
                   JwtTokenUtil jwtTokenUtil,
                   UserViewMapper userView) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtil = jwtTokenUtil;
        this.userView = userView;
        }

    @PostMapping("/login")
    public ResponseEntity<UserView> login(@Valid @RequestBody AuthRequest request) {
        try {
            Authentication authenticate = authenticationManager
                .authenticate(
                    new UsernamePasswordAuthenticationToken(
                        request.getUsername(), request.getPassword()
                    )
                );

            User user = (User) authenticate.getPrincipal();
						//send kafka notification about authenticated user logged in
            if (authenticate.isAuthenticated()){
                kafkaTemplate.send("user.events", user.getUsername() + " logged in");
            }


            return ResponseEntity.ok()
                .header(
                    HttpHeaders.AUTHORIZATION,
                    jwtTokenUtil.generateAccessToken(user)
                )
                .body(userView.toUserview(user));
        } catch (BadCredentialsException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}
