package com.arrowsmodule.movieBooking.domain.entity;

import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "t_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long userId;

    private String username;
    private String password;
    private List<String> roles;
}