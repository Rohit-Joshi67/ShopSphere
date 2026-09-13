package com.SophSphere.app.model;

import jakarta.persistence.*;
import lombok.Data;


@Data
@Entity
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String street;
    private String city;
    private String state;
    private String zipCode;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
