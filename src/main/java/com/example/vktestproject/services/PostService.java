package com.example.vktestproject.services;

import com.example.vktestproject.models.Post;
import com.example.vktestproject.models.ResponseCommentPost;

import java.util.Map;

public interface PostService {

    Post[] getAllPosts(Map<String, String> map);
    Post getPostById(Long postId);
    ResponseCommentPost[] getPostWithComment(Long postId);
    Post create(Post post);
    Post update(Long postId, Post post);
    Post patching(Long postId, Post post);
    Post delete(Long postId);

}
