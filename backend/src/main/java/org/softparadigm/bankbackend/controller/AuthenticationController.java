package org.softparadigm.bankbackend.controller;

import org.softparadigm.bankbackend.configuration.jwt.JwtUtils;
import org.softparadigm.bankbackend.dto.AuthPayload;
import org.softparadigm.bankbackend.dto.AuthResponse;
import org.softparadigm.bankbackend.service.IAgentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private IAgentService agentService;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/signIn")
    public ResponseEntity<AuthResponse> signIn(@RequestBody @Validated AuthPayload payload) {
        // Todo: use providers
        UserDetails userDetails = agentService.loadUserByUsername(payload.getUsername());
        if (passwordEncoder.matches(payload.getPassword(), userDetails.getPassword())) {
            String token = jwtUtils.generateToken(payload.getUsername());

            AuthResponse response = new AuthResponse();
            response.setToken(token);
            response.setUsername(payload.getUsername());
            return ResponseEntity.ok(response);
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
}
