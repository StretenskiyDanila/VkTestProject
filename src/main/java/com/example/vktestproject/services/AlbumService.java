package com.example.vktestproject.services;

import com.example.vktestproject.models.Album;
import com.example.vktestproject.models.ResponsePhotosAlbum;

import java.util.Map;

public interface AlbumService {

    Album[] getAllAlbums(Map<String, String> map);
    Album getAlbumById(Long albumId);
    ResponsePhotosAlbum[] getAlbumsWithPhotos(Long albumId);
    Album create(Album album);
    Album update(Long albumId, Album album);
    Album patching(Long albumId, Album album);
    Album delete(Long albumId);

}
