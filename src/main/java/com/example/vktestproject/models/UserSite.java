package com.example.vktestproject.models;

import lombok.Data;

@Data
public class UserSite {

    private Long id;
    private String username;
    private String email;
    private Address address;
    private String phone;
    private String website;
    private Company company;

}
