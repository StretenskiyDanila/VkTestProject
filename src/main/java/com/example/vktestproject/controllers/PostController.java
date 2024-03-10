package com.example.vktestproject.controllers;

import com.example.vktestproject.dto.PostDTO;
import com.example.vktestproject.facade.PostFacade;
import com.example.vktestproject.models.Post;
import com.example.vktestproject.models.ResponseCommentPost;
import com.example.vktestproject.services.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostFacade postFacade;
    private final PostService postService;


    @GetMapping()
    public ResponseEntity<PostDTO[]> getPosts(@RequestParam(required = false) Map<String, String> map) {
        Post[] posts = postService.getAllPosts(map);
        PostDTO[] response = Arrays.stream(posts)
                .map(postFacade::postToPostDTO)
                .toArray(PostDTO[]::new);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{postId}")
    public ResponseEntity<PostDTO> getPostById(@PathVariable("postId") String postId) {
        Post postResponseEntity = postService.getPostById(postId);
        PostDTO response = postFacade.postToPostDTO(postResponseEntity);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{postId}/comments")
    public ResponseEntity<ResponseCommentPost[]> getPostWithComment(@PathVariable("postId") String postId) {
        ResponseCommentPost[] response = postService.getPostWithComment(postId);
        return ResponseEntity.ok(response);
    }

    @PostMapping()
    public ResponseEntity<PostDTO> createPost(@RequestBody PostDTO postDTO) {
        Post post = postFacade.postDTOToPost(postDTO);
        post = postService.create(post);
        PostDTO response = postFacade.postToPostDTO(post);
        return ResponseEntity.ok(response);
    }
    
    @PutMapping("/{postId}")
    public ResponseEntity<PostDTO> updatePost(@PathVariable("postId") String postId,
                                              @RequestBody PostDTO postDTO) {
        Post post = postFacade.postDTOToPost(postDTO);
        post = postService.update(postId, post);
        PostDTO response = postFacade.postToPostDTO(post);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{postId}")
    public ResponseEntity<PostDTO> patchingPost(@PathVariable("postId") String postId,
                                              @RequestBody PostDTO postDTO) {
        Post post = postFacade.postDTOToPost(postDTO);
        post = postService.patching(postId, post);
        PostDTO response = postFacade.postToPostDTO(post);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<PostDTO> deletePost(@PathVariable("postId") String postId) {
        Post post = postService.delete(postId);
        PostDTO postDTO = postFacade.postToPostDTO(post);
        return ResponseEntity.ok(postDTO);
    }

}
