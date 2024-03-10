package com.example.vktestproject.services.impl;

import com.example.vktestproject.entity.User;
import com.example.vktestproject.entity.enums.ERole;
import com.example.vktestproject.exception.UserExistsException;
import com.example.vktestproject.payload.request.SignUpRequest;
import com.example.vktestproject.repository.UserRepository;
import com.example.vktestproject.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public User create(User user) {

        try {
            return save(user);
        } catch (Exception ex) {
            throw new UserExistsException("Пользователь " + user.getUsername() + " уже существует!");
        }
    }

    @Override
    public UserDetailsService userDetailsService() {
        return this::getByUsername;
    }

    @Override
    public User getCurrentUser() {
        // Получение имени пользователя из контекста Spring Security
        var username = SecurityContextHolder.getContext().getAuthentication().getName();
        return getByUsername(username);
    }

    private User getByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Пользователь с именем '" + username + "'не найден"));
    }

}
