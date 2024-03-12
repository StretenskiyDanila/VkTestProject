package com.example.vktestproject.controllers;

import com.example.vktestproject.dto.PostDTO;
import com.example.vktestproject.facade.PostFacade;
import com.example.vktestproject.models.Post;
import com.example.vktestproject.models.ResponseCommentPost;
import com.example.vktestproject.services.AuditService;
import com.example.vktestproject.services.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;
    private final AuditService auditService;
    private final PostFacade postFacade;


    @GetMapping()
    public ResponseEntity<PostDTO[]> getPosts(@RequestParam(required = false) Map<String, String> map) {
        Post[] posts = postService.getAllPosts(map);
        PostDTO[] response = Arrays.stream(posts)
                .map(postFacade::postToPostDTO)
                .toArray(PostDTO[]::new);
        auditService.save("GET: '/api/posts'", true);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{postId}")
    public ResponseEntity<PostDTO> getPostById(@PathVariable("postId") String postId) {
        Post postResponseEntity = postService.getPostById(Long.parseLong(postId));
        PostDTO response = postFacade.postToPostDTO(postResponseEntity);
        auditService.save("GET: '/api/posts/" + postId + "'", true);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{postId}/comments")
    public ResponseEntity<ResponseCommentPost[]> getPostWithComment(@PathVariable("postId") String postId) {
        ResponseCommentPost[] response = postService.getPostWithComment(Long.parseLong(postId));
        auditService.save("GET: '/api/posts/" + postId + "/comments'", true);
        return ResponseEntity.ok(response);
    }

    @PostMapping()
    public ResponseEntity<PostDTO> createPost(@RequestBody PostDTO postDTO) {
        Post[] posts = postService.getAllPosts(new HashMap<>());
        postDTO.setId(posts[posts.length - 1].getId() + 1);
        Post post = postFacade.postDTOToPost(postDTO);
        post = postService.create(post);
        PostDTO response = postFacade.postToPostDTO(post);
        auditService.save("POST: '/api/posts'", true);
        return ResponseEntity.ok(response);
    }
    
    @PutMapping("/{postId}")
    public ResponseEntity<PostDTO> updatePost(@PathVariable("postId") String postId,
                                              @RequestBody PostDTO postDTO) {
        Post post = postFacade.postDTOToPost(postDTO);
        post = postService.update(Long.parseLong(postId), post);
        PostDTO response = postFacade.postToPostDTO(post);
        auditService.save("PUT: '/api/posts/" + postId + "'", true);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{postId}")
    public ResponseEntity<PostDTO> patchingPost(@PathVariable("postId") String postId,
                                              @RequestBody PostDTO postDTO) {
        Post post = postFacade.postDTOToPost(postDTO);
        post = postService.patching(Long.parseLong(postId), post);
        PostDTO response = postFacade.postToPostDTO(post);
        auditService.save("PATCH: '/api/posts/" + postId + "'", true);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<PostDTO> deletePost(@PathVariable("postId") String postId) {
        Post post = postService.delete(Long.parseLong(postId));
        PostDTO postDTO = postFacade.postToPostDTO(post);
        auditService.save("DELETE: '/api/posts/" + postId + "'", true);
        return ResponseEntity.ok(postDTO);
    }

}
