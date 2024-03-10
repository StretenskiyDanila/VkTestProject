package com.example.vktestproject.dto;

import com.example.vktestproject.models.Address;
import com.example.vktestproject.models.Company;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDTO {

    private Long id;
    private String username;
    private String email;
    private Address address;
    private String phone;
    private String website;
    private Company company;

}
