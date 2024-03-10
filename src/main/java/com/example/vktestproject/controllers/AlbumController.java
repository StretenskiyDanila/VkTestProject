package com.example.vktestproject.controllers;

import com.example.vktestproject.dto.AlbumDTO;
import com.example.vktestproject.facade.AlbumFacade;
import com.example.vktestproject.models.Album;
import com.example.vktestproject.models.ResponsePhotosAlbum;
import com.example.vktestproject.services.AlbumService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/api/albums")
@RequiredArgsConstructor
public class AlbumController {

    private final AlbumService albumService;
    private final AlbumFacade albumFacade;

    @GetMapping()
    public ResponseEntity<AlbumDTO[]> getAlbums(@RequestParam(required = false) Map<String, String> map) {
        Album[] albums = albumService.getAllAlbums(map);
        AlbumDTO[] response = Arrays.stream(albums)
                .map(albumFacade::albumToAlbumDTO)
                .toArray(AlbumDTO[]::new);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{albumId}")
    public ResponseEntity<AlbumDTO> getAlbumById(@PathVariable("albumId") String albumId) {
        Album responseAlbum = albumService.getAlbumById(albumId);
        AlbumDTO response = albumFacade.albumToAlbumDTO(responseAlbum);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{albumId}/photos")
    public ResponseEntity<ResponsePhotosAlbum[]> getAlbumsWithPhotos(@PathVariable("albumId") String albumId) {
        ResponsePhotosAlbum[] response = albumService.getAlbumsWithPhotos(albumId);
        return ResponseEntity.ok(response);
    }

    @PostMapping()
    public ResponseEntity<AlbumDTO> createAlbum(@RequestBody AlbumDTO albumDTO) {
        Album album = albumFacade.albumDTOToAlbum(albumDTO);
        album = albumService.create(album);
        AlbumDTO response = albumFacade.albumToAlbumDTO(album);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{albumId}")
    public ResponseEntity<AlbumDTO> updateAlbum(@PathVariable("albumId") String albumId,
                                              @RequestBody AlbumDTO albumDTO) {
        Album album = albumFacade.albumDTOToAlbum(albumDTO);
        album = albumService.update(albumId, album);
        AlbumDTO response = albumFacade.albumToAlbumDTO(album);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{albumId}")
    public ResponseEntity<AlbumDTO> patchingAlbum(@PathVariable("albumId") String albumId,
                                                @RequestBody AlbumDTO albumDTO) {
        Album album = albumFacade.albumDTOToAlbum(albumDTO);
        album = albumService.patching(albumId, album);
        AlbumDTO response = albumFacade.albumToAlbumDTO(album);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{albumId}")
    public ResponseEntity<AlbumDTO> deleteAlbum(@PathVariable("albumId") String albumId) {
        Album album = albumService.delete(albumId);
        AlbumDTO albumDTO = albumFacade.albumToAlbumDTO(album);
        return ResponseEntity.ok(albumDTO);
    }

}
