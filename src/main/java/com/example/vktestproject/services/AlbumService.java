package com.example.vktestproject.services;

import com.example.vktestproject.models.Album;
import com.example.vktestproject.models.ResponsePhotosAlbum;

import java.util.Map;

public interface AlbumService {

    Album[] getAllAlbums(Map<String, String> map);
    Album getAlbumById(String albumId);
    ResponsePhotosAlbum[] getAlbumsWithPhotos(String albumId);
    Album create(Album album);
    Album update(String albumId, Album album);
    Album patching(String albumId, Album album);
    Album delete(String albumId);

}
