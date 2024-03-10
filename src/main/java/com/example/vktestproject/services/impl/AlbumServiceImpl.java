package com.example.vktestproject.services.impl;


import com.example.vktestproject.models.Album;
import com.example.vktestproject.models.ResponsePhotosAlbum;
import com.example.vktestproject.services.AlbumService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class AlbumServiceImpl implements AlbumService {

    private static final String URI_ALBUMS = "https://jsonplaceholder.typicode.com/albums";

    private final RestTemplateWork restTemplateWork;

    @Cacheable("album-get")
    @Override
    public Album[] getAllAlbums(Map<String, String> map) {
        return restTemplateWork.getObject(URI_ALBUMS, map, Album[].class);
    }

    @Cacheable("album-get")
    @Override
    public Album getAlbumById(String albumId) {
        return restTemplateWork.getObject(URI_ALBUMS + "/" + albumId, Album.class);
    }

    @Cacheable("album-get")
    @Override
    public ResponsePhotosAlbum[] getAlbumsWithPhotos(String albumId) {
        return restTemplateWork.getObject(URI_ALBUMS + "/" + albumId + "/photos", ResponsePhotosAlbum[].class);
    }

    @Cacheable("album-create")
    @Override
    public Album create(Album album) {
        return restTemplateWork.postForObject(URI_ALBUMS, album, Album.class);
    }

    @Cacheable("album-update")
    @Override
    public Album update(String albumId, Album album) {
        HttpEntity<Album> http = new HttpEntity<>(album);
        ResponseEntity<Album> responseAlbum = restTemplateWork.exchange(
                URI_ALBUMS + "/" + albumId,
                HttpMethod.PUT,
                http,
                Album.class
        );
        return responseAlbum.getBody();
    }

    @Cacheable("album-patching")
    @Override
    public Album patching(String albumId, Album album) {
        return restTemplateWork.patchObject(URI_ALBUMS + "/" + albumId, album, Album.class);
    }

    @Cacheable("album-delete")
    @Override
    public Album delete(String albumId) {
        ResponseEntity<Album> responseAlbum = restTemplateWork.exchange(
                URI_ALBUMS + "/" + albumId,
                HttpMethod.DELETE,
                null,
                Album.class
        );
        return responseAlbum.getBody();
    }

}
