package com.example.vktestproject.models;

import lombok.Data;

@Data
public class ResponseTodosUser {

    private Long id;
    private Long userId;
    private String title;
    private Boolean completed;

}
