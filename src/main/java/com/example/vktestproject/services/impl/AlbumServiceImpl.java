package com.example.vktestproject.services.impl;

import com.example.vktestproject.models.Album;
import com.example.vktestproject.models.ResponsePhotosAlbum;
import com.example.vktestproject.services.AlbumService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
@CacheConfig(cacheNames = "album")
public class AlbumServiceImpl implements AlbumService {

    private static final String URI_ALBUMS = "https://jsonplaceholder.typicode.com/albums";

    private final RestTemplateWork restTemplateWork;

    @Cacheable
    @Override
    public Album[] getAllAlbums(Map<String, String> map) {
        return restTemplateWork.getObject(URI_ALBUMS, map, Album[].class);
    }

    @Cacheable(key = "#albumId")
    @Override
    public Album getAlbumById(Long albumId) {
        return restTemplateWork.getObject(URI_ALBUMS + "/" + albumId, Album.class);
    }

    @Cacheable(cacheNames = "photos", key = "#albumId")
    @Override
    public ResponsePhotosAlbum[] getAlbumsWithPhotos(Long albumId) {
        return restTemplateWork.getObject(URI_ALBUMS + "/" + albumId + "/photos", ResponsePhotosAlbum[].class);
    }

    @Cacheable(key = "#album.id")
    @Override
    public Album create(Album album) {
        return restTemplateWork.postForObject(URI_ALBUMS, album, Album.class);
    }

    @CachePut(key = "#albumId")
    @Override
    public Album update(Long albumId, Album album) {
        HttpEntity<Album> http = new HttpEntity<>(album);
        ResponseEntity<Album> responseAlbum = restTemplateWork.exchange(
                URI_ALBUMS + "/" + albumId,
                HttpMethod.PUT,
                http,
                Album.class
        );
        return responseAlbum.getBody();
    }

    @CachePut(key = "#albumId")
    @Override
    public Album patching(Long albumId, Album album) {
        return restTemplateWork.patchObject(URI_ALBUMS + "/" + albumId, album, Album.class);
    }

    @CacheEvict(key = "#albumId")
    @Override
    public Album delete(Long albumId) {
        ResponseEntity<Album> responseAlbum = restTemplateWork.exchange(
                URI_ALBUMS + "/" + albumId,
                HttpMethod.DELETE,
                null,
                Album.class
        );
        return responseAlbum.getBody();
    }

}
