package com.example.vktestproject.controllers;

import com.example.vktestproject.entity.User;
import com.example.vktestproject.entity.enums.ERole;
import com.example.vktestproject.payload.response.MessageResponse;
import com.example.vktestproject.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/*
*
* Тестовый контроллер для показа, что при смене ролей у пользователя становятся доступны установленные URL
*
* */

@RestController
@RequestMapping("/api/role")
@RequiredArgsConstructor
public class RoleController {

    private final UserService userService;

    @GetMapping()
    public ResponseEntity<MessageResponse> currentRole() {
        User user = userService.getCurrentUser();
        return ResponseEntity.ok(new MessageResponse("Текущая роль пользователя '" + user.getUsername() + "' - " + user.getRole().name()));
    }

    @GetMapping("/{role_name}")
    public ResponseEntity<MessageResponse> changingRole(@PathVariable(name = "role_name") String role_name) {
        try {
            ERole role = ERole.valueOf("ROLE_" + role_name);
            User user = userService.getCurrentUser();
            user.setRole(role);
            userService.save(user);
            return ResponseEntity.ok(new MessageResponse("Текущая роль пользователя изменена на " + role.name()));
        } catch (IllegalArgumentException ex) {
            return new ResponseEntity<>(new MessageResponse("Такой роли не существует"), HttpStatus.BAD_REQUEST);
        }
    }

}
