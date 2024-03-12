package com.example.vktestproject.models;

import lombok.Data;

@Data
public class ResponsePhotosAlbum {

    private Long id;
    private Long albumId;
    private String title;
    private String url;
    private String thumbnailUrl;

}
