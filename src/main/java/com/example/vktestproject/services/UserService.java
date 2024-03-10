package com.example.vktestproject.services;

import com.example.vktestproject.entity.User;
import com.example.vktestproject.payload.request.SignUpRequest;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService {

    User save(User user);
    User create(User user);
    UserDetailsService userDetailsService();
    User getCurrentUser();

}
