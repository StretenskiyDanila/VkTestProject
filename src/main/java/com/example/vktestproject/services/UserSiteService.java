package com.example.vktestproject.services;

import com.example.vktestproject.models.Post;
import com.example.vktestproject.models.ResponseAlbumUser;
import com.example.vktestproject.models.ResponseTodosUser;
import com.example.vktestproject.models.UserSite;

import java.util.Map;

public interface UserSiteService {

    UserSite[] getAllUsers(Map<String, String> map);
    UserSite getUserById(String userId);
    ResponseAlbumUser[] getUsersWithAlbums(String userId);
    Post[] getUsersWithPosts(String userId);
    ResponseTodosUser[] getUserWithTodos(String userId);
    UserSite create(UserSite userSite);
    UserSite update(String userId, UserSite userSite);
    UserSite patching(String userId, UserSite userSite);
    UserSite delete(String userId);

}
