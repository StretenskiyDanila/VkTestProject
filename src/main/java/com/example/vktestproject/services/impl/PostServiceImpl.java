package com.example.vktestproject.services.impl;

import com.example.vktestproject.models.Post;
import com.example.vktestproject.models.ResponseCommentPost;
import com.example.vktestproject.services.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private static final String URI_POSTS = "https://jsonplaceholder.typicode.com/posts";

    private final RestTemplateWork restTemplateWork;

    @Cacheable("post-get")
    @Override
    public Post[] getAllPosts(Map<String, String> map) {
        return restTemplateWork.getObject(URI_POSTS, map, Post[].class);
    }

    @Cacheable("post-get")
    @Override
    public Post getPostById(String postId) {
        return restTemplateWork.getObject(URI_POSTS + "/" + postId, Post.class);
    }

    @Cacheable("post-get")
    @Override
    public ResponseCommentPost[] getPostWithComment(String postId) {
        return restTemplateWork.getObject(URI_POSTS + "/" + postId + "/comments", ResponseCommentPost[].class);
    }

    @Cacheable("post-create")
    @Override
    public Post create(Post post) {
        return restTemplateWork.postForObject(URI_POSTS, post, Post.class);
    }

    @Cacheable("post-update")
    @Override
    public Post update(String postId, Post post) {
        HttpEntity<Post> http = new HttpEntity<>(post);
        ResponseEntity<Post> responsePost = restTemplateWork.exchange(
                URI_POSTS + "/" + postId,
                HttpMethod.PUT,
                http,
                Post.class
        );
        return responsePost.getBody();
    }

    @Cacheable("post-patching")
    @Override
    public Post patching(String postId, Post post) {
        return restTemplateWork.patchObject(URI_POSTS + "/" + postId, post, Post.class);
    }

    @Cacheable("post-delete")
    @Override
    public Post delete(String postId) {
        ResponseEntity<Post> responsePost = restTemplateWork.exchange(
                URI_POSTS + "/" + postId,
                HttpMethod.DELETE,
                null,
                Post.class
        );
        return responsePost.getBody();
    }

}
