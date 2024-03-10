package com.example.vktestproject.controllers;

import com.example.vktestproject.dto.PostDTO;
import com.example.vktestproject.dto.UserDTO;
import com.example.vktestproject.facade.PostFacade;
import com.example.vktestproject.facade.UserFacade;
import com.example.vktestproject.models.Post;
import com.example.vktestproject.models.ResponseAlbumUser;
import com.example.vktestproject.models.ResponseTodosUser;
import com.example.vktestproject.models.UserSite;
import com.example.vktestproject.services.UserSiteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserSiteController {

    private final UserSiteService userSiteService;
    private final UserFacade userFacade;
    private final PostFacade postFacade;

    @GetMapping()
    public ResponseEntity<UserDTO[]> getUsers(@RequestParam(required = false) Map<String, String> map) {
        UserSite[] userSites = userSiteService.getAllUsers(map);
        UserDTO[] response = Arrays.stream(userSites)
                .map(userFacade::userToUserDTO)
                .toArray(UserDTO[]::new);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable("userId") String userId) {
        UserSite userSite = userSiteService.getUserById(userId);
        UserDTO response = userFacade.userToUserDTO(userSite);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{userId}/albums")
    public ResponseEntity<ResponseAlbumUser[]> getUsersWithAlbums(@PathVariable("userId") String userId) {
        return ResponseEntity.ok(userSiteService.getUsersWithAlbums(userId));
    }

    @GetMapping("/{userId}/posts")
    public ResponseEntity<PostDTO[]> getUserWithPosts(@PathVariable("userId") String userId) {
        Post[] posts = userSiteService.getUsersWithPosts(userId);
        PostDTO[] response = Arrays.stream(posts).map(postFacade::postToPostDTO).toArray(PostDTO[]::new);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{userId}/todos")
    public ResponseEntity<ResponseTodosUser[]> getUserWithTodos(@PathVariable("userId") String userId) {
        return ResponseEntity.ok(userSiteService.getUserWithTodos(userId));
    }

    @PostMapping()
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO) {
        UserSite userSite = userFacade.userDTOToUser(userDTO);
        userSite = userSiteService.create(userSite);
        UserDTO response = userFacade.userToUserDTO(userSite);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable("userId") String userId,
                                               @RequestBody UserDTO userDTO) {
        UserSite userSite = userFacade.userDTOToUser(userDTO);
        userSite = userSiteService.update(userId, userSite);
        UserDTO response = userFacade.userToUserDTO(userSite);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{userId}")
    public ResponseEntity<UserDTO> patchingUser(@PathVariable("userId") String userId,
                                                 @RequestBody UserDTO userDTO) {
        UserSite userSite =  userFacade.userDTOToUser(userDTO);
        userSite = userSiteService.patching(userId, userSite);
        UserDTO response = userFacade.userToUserDTO(userSite);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<UserDTO> deleteUser(@PathVariable("userId") String userId) {
        UserSite userSite = userSiteService.delete(userId);
        UserDTO userDTO = userFacade.userToUserDTO(userSite);
        return ResponseEntity.ok(userDTO);
    }


}
