package org.generation.syntaxlogistics2.service;

import org.generation.syntaxlogistics2.dto.request.UpdateUserRequest;
import org.generation.syntaxlogistics2.enums.UserRol;
import org.generation.syntaxlogistics2.model.Users;
import org.generation.syntaxlogistics2.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // crear usuario
    public Users createUser(Users user) {
        if (usersRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new RuntimeException("El email ya está registrado");
        }

        // Encriptamos la contraseña antes de guardarla — nunca en texto plano
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRol(UserRol.CLIENT);
        return usersRepository.save(user);
    }

    // busca por id
    public Users findById(Long id) {
        return usersRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    }

    // Buscar por email (para login)
    public Users findByEmail(String email) {
        return usersRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    }

    // Listar todos
    public List<Users> findAll() {
        return usersRepository.findAll();
    }

    // Actualizar usuario
    public Users updateUser(Long id, UpdateUserRequest request) {
        Users user = findById(id);

        // Si cambia el email, verificar que no lo tenga otro usuario
        if (!user.getEmail().equalsIgnoreCase(request.email())) {
            usersRepository.findByEmail(request.email()).ifPresent(otro -> {
                throw new RuntimeException("El email ya está registrado por otro usuario");
            });
        }

        user.setName(request.name());
        user.setLastName(request.lastName());
        user.setEmail(request.email());
        user.setPhoneNumber(request.phone());

        // Password opcional: solo se actualiza si viene con contenido
        if (request.password() != null && !request.password().isBlank()) {
            user.setPassword(passwordEncoder.encode(request.password()));
        }

        return usersRepository.save(user);
    }

    // Eliminar usuario
    public void deleteUser(Long id) {
        Users user = findById(id);
        usersRepository.delete(user);
    }
}