package com.example.vktestproject.models;

import lombok.Data;

@Data
public class Post {

    private Long id;
    private String title;
    private String body;
    private Long userId;

}
