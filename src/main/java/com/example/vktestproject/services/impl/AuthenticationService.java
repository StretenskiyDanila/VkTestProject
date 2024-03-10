package com.example.vktestproject.services.impl;

import com.example.vktestproject.entity.User;
import com.example.vktestproject.entity.enums.ERole;
import com.example.vktestproject.payload.request.SignInRequest;
import com.example.vktestproject.payload.request.SignUpRequest;
import com.example.vktestproject.payload.response.JwtAuthenticationResponse;
import com.example.vktestproject.payload.response.MessageResponse;
import com.example.vktestproject.security.JWTTokenProvider;
import com.example.vktestproject.security.SecurityConstants;
import com.example.vktestproject.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserService userService;
    private final JWTTokenProvider jwtTokenProvider;
    private final AuthenticationManager authenticationManager;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public MessageResponse signUp(SignUpRequest request) {

        var user = User.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(bCryptPasswordEncoder.encode(request.getPassword()))
                .role(ERole.ROLE_USERS)
                .build();

        userService.create(user);

        return new MessageResponse("Пользователь успешно зарегистрирован");
    }

    public JwtAuthenticationResponse signIn(SignInRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                request.getUsername(),
                request.getPassword()
        ));

        var user = userService.userDetailsService().loadUserByUsername(request.getUsername());

        var jwt = SecurityConstants.TOKEN_PREFIX + jwtTokenProvider.generateToken(user);
        return new JwtAuthenticationResponse(true, jwt);
    }
}
