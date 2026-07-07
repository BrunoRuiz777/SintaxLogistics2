package org.generation.syntaxlogistics2.controller;

import jakarta.validation.Valid;
import org.generation.syntaxlogistics2.dto.request.LoginRequest;
import org.generation.syntaxlogistics2.dto.response.LoginResponse;
import org.generation.syntaxlogistics2.mapper.UserMapper;
import org.generation.syntaxlogistics2.model.Users;
import org.generation.syntaxlogistics2.repository.UsersRepository;
import org.generation.syntaxlogistics2.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest request) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.email(), request.password())
            );
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Correo o contraseña incorrectos");
        }

        Users user = usersRepository.findByEmail(request.email())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        String token = jwtUtil.generateToken(user.getEmail());
        return ResponseEntity.ok(new LoginResponse(token, userMapper.toResponse(user)));
    }
}