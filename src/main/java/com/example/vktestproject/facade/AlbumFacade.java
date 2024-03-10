package com.example.vktestproject.facade;

import com.example.vktestproject.dto.AlbumDTO;
import com.example.vktestproject.models.Album;
import org.springframework.stereotype.Component;

@Component
public class AlbumFacade {

    public AlbumDTO albumToAlbumDTO(Album album) {
        AlbumDTO albumDTO = new AlbumDTO();
        albumDTO.setId(album.getId());
        albumDTO.setUserId(album.getUserId());
        albumDTO.setTitle(album.getTitle());
        return albumDTO;
    }

    public Album albumDTOToAlbum(AlbumDTO albumDTO) {
        Album album = new Album();
        album.setId(albumDTO.getId());
        album.setUserId(albumDTO.getUserId());
        album.setTitle(albumDTO.getTitle());
        return album;
    }

}
