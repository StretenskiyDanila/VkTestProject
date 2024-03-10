package com.example.vktestproject.services;

import com.example.vktestproject.models.Post;
import com.example.vktestproject.models.ResponseCommentPost;

import java.util.Map;

public interface PostService {

    Post[] getAllPosts(Map<String, String> map);
    Post getPostById(String postId);
    ResponseCommentPost[] getPostWithComment(String postId);
    Post create(Post post);
    Post update(String postId, Post post);
    Post patching(String postId, Post post);
    Post delete(String postId);

}
