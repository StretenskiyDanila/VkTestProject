package com.example.vktestproject.controllers;

import com.example.vktestproject.payload.request.SignInRequest;
import com.example.vktestproject.payload.request.SignUpRequest;
import com.example.vktestproject.payload.response.JwtAuthenticationResponse;
import com.example.vktestproject.payload.response.MessageResponse;
import com.example.vktestproject.services.impl.AuthenticationService;
import com.example.vktestproject.validations.ResponseErrorValidation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationService authenticationService;
    private final ResponseErrorValidation responseErrorValidation;

    @PostMapping("/signin")
    public ResponseEntity<Object> signUn(@Valid @RequestBody SignInRequest request,
                                                            BindingResult bindingResult) {
        ResponseEntity<Object> errors = responseErrorValidation.mapValidationService(bindingResult);
        if (!ObjectUtils.isEmpty(errors)) {
            return errors;
        }

        JwtAuthenticationResponse response = authenticationService.signIn(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/signup")
    public ResponseEntity<Object> signUp(@Valid @RequestBody SignUpRequest request,
                                                            BindingResult bindingResult) {
        ResponseEntity<Object> errors = responseErrorValidation.mapValidationService(bindingResult);
        if (!ObjectUtils.isEmpty(errors)) return errors;

        MessageResponse messageResponse = authenticationService.signUp(request);
        return ResponseEntity.ok(messageResponse);
    }

}
