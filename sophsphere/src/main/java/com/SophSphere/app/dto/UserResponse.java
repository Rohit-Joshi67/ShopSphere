package com.SophSphere.app.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class UserResponse {

    private Long id;
    private String username;
    private String email;
    private String password;
    private List<AddressDTO> addresses;
}