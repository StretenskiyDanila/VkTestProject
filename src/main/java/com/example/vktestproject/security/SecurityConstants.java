package com.example.vktestproject.security;

public class SecurityConstants {

    public static final String[] WHITE_LIST_URL_FOR_USERS = {"/api/users/**", "/api/users/{userId}/albums",
            "/api/users/{userId}/posts", "/api/users/{userId}/todos"};
    public static final String[] WHITE_LIST_URL_FOR_ALBUMS = {"/api/albums/**", "/api/albums/{albumId}/photos"};
    public static final String[] WHITE_LIST_URL_FOR_POSTS = {"/api/posts/**", "/api/posts/{postId}/comments"};

    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final String CONTENT_TYPE = "application/json";
    public static final long EXPIRATION_TIME = 600_000; //10 min

}
