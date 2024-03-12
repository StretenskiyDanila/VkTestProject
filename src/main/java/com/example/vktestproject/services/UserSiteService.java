package com.example.vktestproject.services;

import com.example.vktestproject.models.Album;
import com.example.vktestproject.models.Post;
import com.example.vktestproject.models.ResponseTodosUser;
import com.example.vktestproject.models.UserSite;

import java.util.Map;

public interface UserSiteService {

    UserSite[] getAllUsers(Map<String, String> map);
    UserSite getUserById(Long userId);
    Album[] getUsersWithAlbums(Long userId);
    Post[] getUsersWithPosts(Long userId);
    ResponseTodosUser[] getUserWithTodos(Long userId);
    UserSite create(UserSite userSite);
    UserSite update(Long userId, UserSite userSite);
    UserSite patching(Long userId, UserSite userSite);
    UserSite delete(Long userId);

}
