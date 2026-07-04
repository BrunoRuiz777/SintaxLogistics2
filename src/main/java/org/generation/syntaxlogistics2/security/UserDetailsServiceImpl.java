package org.generation.syntaxlogistics2.security;

import org.generation.syntaxlogistics2.model.Users;
import org.generation.syntaxlogistics2.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UsersRepository usersRepository;

    // Spring Security llama este método SOLO cuando alguien intenta loguearse
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Users user = usersRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado: " + email));

        // Convierte TU entidad Users al formato que Spring Security entiende
        return User.builder()
                .username(user.getEmail())
                .password(user.getPassword()) // ya debe estar hasheada
                .roles(user.getRol().name())
                .build();
    }
}