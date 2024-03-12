package com.example.vktestproject.controllers;

import com.example.vktestproject.dto.AlbumDTO;
import com.example.vktestproject.dto.PostDTO;
import com.example.vktestproject.dto.UserDTO;
import com.example.vktestproject.facade.AlbumFacade;
import com.example.vktestproject.facade.PostFacade;
import com.example.vktestproject.facade.UserFacade;
import com.example.vktestproject.models.Album;
import com.example.vktestproject.models.Post;
import com.example.vktestproject.models.ResponseTodosUser;
import com.example.vktestproject.models.UserSite;
import com.example.vktestproject.services.AuditService;
import com.example.vktestproject.services.UserSiteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserSiteController {

    private final UserSiteService userSiteService;
    private final AuditService auditService;
    private final UserFacade userFacade;
    private final PostFacade postFacade;
    private final AlbumFacade albumFacade;

    @GetMapping()
    public ResponseEntity<UserDTO[]> getUsers(@RequestParam(required = false) Map<String, String> map) {
        UserSite[] userSites = userSiteService.getAllUsers(map);
        UserDTO[] response = Arrays.stream(userSites)
                .map(userFacade::userToUserDTO)
                .toArray(UserDTO[]::new);
        auditService.save("GET: '/api/users/", true);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable("userId") String userId) {
        UserSite userSite = userSiteService.getUserById(Long.parseLong(userId));
        UserDTO response = userFacade.userToUserDTO(userSite);
        auditService.save("GET: '/api/users/" + userId + "'", true);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{userId}/albums")
    public ResponseEntity<AlbumDTO[]> getUsersWithAlbums(@PathVariable("userId") String userId) {
        Album[] albums = userSiteService.getUsersWithAlbums(Long.parseLong(userId));
        AlbumDTO[] response = Arrays.stream(albums).map(albumFacade::albumToAlbumDTO).toArray(AlbumDTO[]::new);
        auditService.save("GET: '/api/users/" + userId + "/albums'", true);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{userId}/posts")
    public ResponseEntity<PostDTO[]> getUserWithPosts(@PathVariable("userId") String userId) {
        Post[] posts = userSiteService.getUsersWithPosts(Long.parseLong(userId));
        PostDTO[] response = Arrays.stream(posts).map(postFacade::postToPostDTO).toArray(PostDTO[]::new);
        auditService.save("GET: '/api/users/" + userId + "/posts'", true);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{userId}/todos")
    public ResponseEntity<ResponseTodosUser[]> getUserWithTodos(@PathVariable("userId") String userId) {
        auditService.save("GET: '/api/users/" + userId + "/todos'", true);
        return ResponseEntity.ok(userSiteService.getUserWithTodos(Long.parseLong(userId)));
    }

    @PostMapping()
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO) {
        UserSite[] userSites = userSiteService.getAllUsers(new HashMap<>());
        userDTO.setId(userSites[userSites.length - 1].getId() + 1);
        UserSite userSite = userFacade.userDTOToUser(userDTO);
        userSite = userSiteService.create(userSite);
        UserDTO response = userFacade.userToUserDTO(userSite);
        auditService.save("POST: '/api/users/'", true);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable("userId") String userId,
                                               @RequestBody UserDTO userDTO) {
        UserSite userSite = userFacade.userDTOToUser(userDTO);
        userSite = userSiteService.update(Long.parseLong(userId), userSite);
        UserDTO response = userFacade.userToUserDTO(userSite);
        auditService.save("PUT: '/api/users/" + userId + "'", true);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{userId}")
    public ResponseEntity<UserDTO> patchingUser(@PathVariable("userId") String userId,
                                                 @RequestBody UserDTO userDTO) {
        UserSite userSite =  userFacade.userDTOToUser(userDTO);
        userSite = userSiteService.patching(Long.parseLong(userId), userSite);
        UserDTO response = userFacade.userToUserDTO(userSite);
        auditService.save("PATCH: '/api/users/" + userId + "'", true);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<UserDTO> deleteUser(@PathVariable("userId") String userId) {
        UserSite userSite = userSiteService.delete(Long.parseLong(userId));
        UserDTO userDTO = userFacade.userToUserDTO(userSite);
        auditService.save("DELETE: '/api/users/" + userId + "'", true);
        return ResponseEntity.ok(userDTO);
    }


}
