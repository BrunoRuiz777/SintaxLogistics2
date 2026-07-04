package org.generation.syntaxlogistics2.controller;

import jakarta.validation.Valid;
import org.generation.syntaxlogistics2.dto.request.RegisterUserRequest;
import org.generation.syntaxlogistics2.dto.response.UserResponse;
import org.generation.syntaxlogistics2.mapper.UserMapper;
import org.generation.syntaxlogistics2.model.Users;
import org.generation.syntaxlogistics2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;

    // POST /api/users/register
    @PostMapping("/register")
    public ResponseEntity<UserResponse> register(@Valid @RequestBody RegisterUserRequest request) {
        Users user = userMapper.toEntity(request);       // DTO -> Entity
        Users saved = userService.createUser(user);      // aquí es donde se hashea la password
        return ResponseEntity.ok(userMapper.toResponse(saved)); // Entity -> DTO (sin password)
    }

    // GET /api/users/{id}
    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getById(@PathVariable Long id) {
        Users user = userService.findById(id);
        return ResponseEntity.ok(userMapper.toResponse(user));
    }
}