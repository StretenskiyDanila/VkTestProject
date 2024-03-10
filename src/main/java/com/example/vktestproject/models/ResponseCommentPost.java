package com.example.vktestproject.models;

import lombok.Data;

@Data
public class ResponseCommentPost {

    private Long postId;
    private Long id;
    private String name;
    private String email;
    private String body;

}
