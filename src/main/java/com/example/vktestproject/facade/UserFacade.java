package com.example.vktestproject.facade;

import com.example.vktestproject.dto.UserDTO;
import com.example.vktestproject.models.UserSite;
import org.springframework.stereotype.Component;

@Component
public class UserFacade {

    public UserDTO userToUserDTO(UserSite userSite) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(userSite.getId());
        userDTO.setPhone(userSite.getPhone());
        userDTO.setEmail(userSite.getEmail());
        userDTO.setCompany(userSite.getCompany());
        userDTO.setAddress(userSite.getAddress());
        userDTO.setUsername(userSite.getUsername());
        userDTO.setWebsite(userSite.getWebsite());
        return userDTO;
    }

    public UserSite userDTOToUser(UserDTO userDTO) {
        UserSite userSite = new UserSite();
        userSite.setId(userDTO.getId());
        userSite.setPhone(userDTO.getPhone());
        userSite.setEmail(userDTO.getEmail());
        userSite.setCompany(userDTO.getCompany());
        userSite.setAddress(userDTO.getAddress());
        userSite.setUsername(userDTO.getUsername());
        userSite.setWebsite(userDTO.getWebsite());
        return userSite;
    }

}
