package com.example.vktestproject.services.impl;

import com.example.vktestproject.models.Post;
import com.example.vktestproject.models.ResponseCommentPost;
import com.example.vktestproject.services.PostService;
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
@CacheConfig(cacheNames = "post")
public class PostServiceImpl implements PostService {

    private static final String URI_POSTS = "https://jsonplaceholder.typicode.com/posts";

    private final RestTemplateWork restTemplateWork;

    @Cacheable
    @Override
    public Post[] getAllPosts(Map<String, String> map) {
        return restTemplateWork.getObject(URI_POSTS, map, Post[].class);
    }

    @Cacheable(key = "#postId")
    @Override
    public Post getPostById(Long postId) {
        return restTemplateWork.getObject(URI_POSTS + "/" + postId, Post.class);
    }

    @Cacheable(cacheNames = "comment")
    @Override
    public ResponseCommentPost[] getPostWithComment(Long postId) {
        return restTemplateWork.getObject(URI_POSTS + "/" + postId + "/comments", ResponseCommentPost[].class);
    }

    @Cacheable(key = "#post.id")
    @Override
    public Post create(Post post) {
        return restTemplateWork.postForObject(URI_POSTS, post, Post.class);
    }

    @CachePut(key = "#postId")
    @Override
    public Post update(Long postId, Post post) {
        HttpEntity<Post> http = new HttpEntity<>(post);
        ResponseEntity<Post> responsePost = restTemplateWork.exchange(
                URI_POSTS + "/" + postId,
                HttpMethod.PUT,
                http,
                Post.class
        );
        return responsePost.getBody();
    }

    @CachePut(key = "#postId")
    @Override
    public Post patching(Long postId, Post post) {
        return restTemplateWork.patchObject(URI_POSTS + "/" + postId, post, Post.class);
    }

    @CacheEvict(key = "#postId")
    @Override
    public Post delete(Long postId) {
        ResponseEntity<Post> responsePost = restTemplateWork.exchange(
                URI_POSTS + "/" + postId,
                HttpMethod.DELETE,
                null,
                Post.class
        );
        return responsePost.getBody();
    }

}
