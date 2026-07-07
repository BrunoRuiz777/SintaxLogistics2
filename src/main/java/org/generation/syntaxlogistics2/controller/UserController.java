package org.generation.syntaxlogistics2.controller;

import jakarta.validation.Valid;
import org.generation.syntaxlogistics2.dto.request.RegisterUserRequest;
import org.generation.syntaxlogistics2.dto.request.UpdateUserRequest;
import org.generation.syntaxlogistics2.dto.response.UserResponse;
import org.generation.syntaxlogistics2.mapper.UserMapper;
import org.generation.syntaxlogistics2.model.Users;
import org.generation.syntaxlogistics2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        Users user = userMapper.toEntity(request);
        Users saved = userService.createUser(user);
        return ResponseEntity.ok(userMapper.toResponse(saved));
    }

    // GET /api/users - Listar todos los usuarios
    @GetMapping
    public ResponseEntity<List<UserResponse>> getAll() {
        List<Users> users = userService.findAll();
        return ResponseEntity.ok(userMapper.toResponseList(users));
    }

    // GET /api/users/{id}
    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getById(@PathVariable Long id) {
        Users user = userService.findById(id);
        return ResponseEntity.ok(userMapper.toResponse(user));
    }

    // PUT /api/users/{id} - Actualizar usuario
    @PutMapping("/{id}")
    public ResponseEntity<UserResponse> update(@PathVariable Long id, @Valid @RequestBody UpdateUserRequest request) {
        Users updated = userService.updateUser(id, request);
        return ResponseEntity.ok(userMapper.toResponse(updated));
    }

    // DELETE /api/users/{id} - Eliminar usuario
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}