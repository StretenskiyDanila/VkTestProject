package com.example.vktestproject.facade;

import com.example.vktestproject.dto.PostDTO;
import com.example.vktestproject.models.Post;
import org.springframework.stereotype.Component;

@Component
public class PostFacade {

    public PostDTO postToPostDTO(Post post) {
        PostDTO postDTO = new PostDTO();
        postDTO.setId(post.getId());
        postDTO.setBody(post.getBody());
        postDTO.setTitle(post.getTitle());
        postDTO.setUserId(post.getUserId());
        return postDTO;
    }

    public Post postDTOToPost(PostDTO postDTO) {
        Post post = new Post();
        post.setId(postDTO.getId());
        post.setBody(postDTO.getBody());
        post.setTitle(postDTO.getTitle());
        post.setUserId(postDTO.getUserId());
        return post;
    }

}
