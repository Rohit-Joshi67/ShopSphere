package com.SophSphere.app.dto;

import lombok.Data;

import java.util.List;

@Data
public class UserRequest {

    private String username;
    private String email;
    private String password;
    private List<AddressDTO> addresses;
}