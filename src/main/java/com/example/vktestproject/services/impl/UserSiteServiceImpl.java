package com.example.vktestproject.services.impl;

import com.example.vktestproject.models.Album;
import com.example.vktestproject.models.Post;
import com.example.vktestproject.models.ResponseTodosUser;
import com.example.vktestproject.models.UserSite;
import com.example.vktestproject.services.UserSiteService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
@CacheConfig(cacheNames = "user-site")
public class UserSiteServiceImpl implements UserSiteService {

    private static final String URI_USERS = "https://jsonplaceholder.typicode.com/users";

    private final RestTemplateWork restTemplateWork;

    @Cacheable
    @Override
    public UserSite[] getAllUsers(Map<String, String> map) {
        return restTemplateWork.getObject(URI_USERS, map, UserSite[].class);
    }

    @Cacheable(key = "#userId")
    @Override
    public UserSite getUserById(Long userId) {
        return restTemplateWork.getObject(URI_USERS + "/" + userId, UserSite.class);

    }

    @Cacheable(cacheNames = "album", key = "#userId")
    @Override
    public Album[] getUsersWithAlbums(Long userId) {
        return restTemplateWork.getObject(URI_USERS + "/" + userId + "/albums", Album[].class);
    }

    @Cacheable(cacheNames = "post", key = "#userId")
    @Override
    public Post[] getUsersWithPosts(Long userId) {
        return restTemplateWork.getObject(URI_USERS + "/" + userId + "/posts", Post[].class);
    }

    @Cacheable(cacheNames = "todos", key = "#userId")
    @Override
    public ResponseTodosUser[] getUserWithTodos(Long userId) {
        return restTemplateWork.getObject(URI_USERS + "/" + userId + "/todos", ResponseTodosUser[].class);

    }

    @Cacheable(key = "#userSite.id")
    @Override
    public UserSite create(UserSite userSite) {
        return restTemplateWork.postForObject(URI_USERS, userSite, UserSite.class);
    }

    @CachePut(key = "#userId")
    @Override
    public UserSite update(Long userId, UserSite userSite) {
        HttpEntity<UserSite> http = new HttpEntity<>(userSite);
        ResponseEntity<UserSite> responseUser = restTemplateWork.exchange(
                URI_USERS + "/" + userId,
                HttpMethod.PUT,
                http,
                UserSite.class
        );
        return responseUser.getBody();
    }

    @CachePut(key = "#userId")
    @Override
    public UserSite patching(Long userId, UserSite userSite) {
        return restTemplateWork.patchObject(URI_USERS + "/" + userId, userSite, UserSite.class);
    }

    @CacheEvict
    @Override
    public UserSite delete(Long userId) {
        ResponseEntity<UserSite> responseUser =  restTemplateWork.exchange(
                URI_USERS + "/" + userId,
                HttpMethod.DELETE,
                null,
                UserSite.class
        );
        return responseUser.getBody();
    }

}
