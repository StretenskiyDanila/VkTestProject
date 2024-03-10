package com.example.vktestproject.services.impl;

import com.example.vktestproject.models.Post;
import com.example.vktestproject.models.ResponseAlbumUser;
import com.example.vktestproject.models.ResponseTodosUser;
import com.example.vktestproject.models.UserSite;
import com.example.vktestproject.services.UserSiteService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class UserSiteServiceImpl implements UserSiteService {

    private static final String URI_USERS = "https://jsonplaceholder.typicode.com/users";

    private final RestTemplateWork restTemplateWork;

    @Cacheable("users-get")
    @Override
    public UserSite[] getAllUsers(Map<String, String> map) {
        return restTemplateWork.getObject(URI_USERS, map, UserSite[].class);
    }

    @Cacheable("users-get")
    @Override
    public UserSite getUserById(String userId) {
        return restTemplateWork.getObject(URI_USERS + "/" + userId, UserSite.class);

    }

    @Cacheable("users-albums")
    @Override
    public ResponseAlbumUser[] getUsersWithAlbums(String userId) {
        return restTemplateWork.getObject(URI_USERS + "/" + userId + "/albums", ResponseAlbumUser[].class);

    }

    @Cacheable("users-post")
    @Override
    public Post[] getUsersWithPosts(String userId) {
        return restTemplateWork.getObject(URI_USERS + "/" + userId + "/posts", Post[].class);
    }

    @Cacheable("users-todos")
    @Override
    public ResponseTodosUser[] getUserWithTodos(String userId) {
        return restTemplateWork.getObject(URI_USERS + "/" + userId + "/todos", ResponseTodosUser[].class);

    }

    @Cacheable("users-create")
    @Override
    public UserSite create(UserSite userSite) {
        return restTemplateWork.postForObject(URI_USERS, userSite, UserSite.class);
    }

    @Cacheable("users-update")
    @Override
    public UserSite update(String userId, UserSite userSite) {
        HttpEntity<UserSite> http = new HttpEntity<>(userSite);
        ResponseEntity<UserSite> responseUser = restTemplateWork.exchange(
                URI_USERS + "/" + userId,
                HttpMethod.PUT,
                http,
                UserSite.class
        );
        return responseUser.getBody();
    }

    @Cacheable("users-patching")
    @Override
    public UserSite patching(String userId, UserSite userSite) {
        return restTemplateWork.patchObject(URI_USERS + "/" + userId, userSite, UserSite.class);
    }

    @Cacheable("users-delete")
    @Override
    public UserSite delete(String userId) {
        ResponseEntity<UserSite> responseUser =  restTemplateWork.exchange(
                URI_USERS + "/" + userId,
                HttpMethod.DELETE,
                null,
                UserSite.class
        );
        return responseUser.getBody();
    }

}
