package org.generation.syntaxlogistics2.service;

import org.generation.syntaxlogistics2.model.Users;
import org.generation.syntaxlogistics2.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.generation.syntaxlogistics2.enums.UserRol;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UsersRepository usersRepository;

    //crear usuario
    public Users createUser(Users user) {
        if (usersRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new RuntimeException("El email ya está registrado");
        }

        user.setRol(UserRol.CLIENT);
        return usersRepository.save(user);
    }

    //busca por id
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
    public List<Users> findAll()
    {
        return usersRepository.findAll();
    }
}
