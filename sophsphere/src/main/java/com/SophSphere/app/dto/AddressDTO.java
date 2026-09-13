package com.SophSphere.app.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AddressDTO {

    private String street;
    private String city;
    private String state;
    private String zipCode;
}