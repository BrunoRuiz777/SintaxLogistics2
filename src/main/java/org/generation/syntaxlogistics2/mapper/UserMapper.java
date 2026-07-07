package org.generation.syntaxlogistics2.mapper;

import org.generation.syntaxlogistics2.dto.request.RegisterUserRequest;
import org.generation.syntaxlogistics2.dto.response.UserResponse;
import org.generation.syntaxlogistics2.model.Users;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserMapper {

    // Convierte lo que llega del Frontend (Request) hacia la Base de Datos (Entity)
    public Users toEntity(RegisterUserRequest request) {
        Users user = new Users();
        user.setName(request.name());
        user.setLastName(request.lastName());
        user.setEmail(request.email());
        user.setPhoneNumber(request.phone());
        user.setPassword(request.password());

        return user;
    }

    // Convierte lo que sale de la Base de Datos (Entity) hacia el Frontend (Response)
    public UserResponse toResponse(Users user) {
        return new UserResponse(
                user.getId(),
                user.getName(),
                user.getLastName(),
                user.getEmail(),
                user.getPhoneNumber(),
                user.getRol().name()
        );
    }

    // Convierte una lista de entidades a una lista de responses (para el GET /api/users)
    public List<UserResponse> toResponseList(List<Users> users) {
        return users.stream()
                .map(this::toResponse)
                .toList();
    }
}