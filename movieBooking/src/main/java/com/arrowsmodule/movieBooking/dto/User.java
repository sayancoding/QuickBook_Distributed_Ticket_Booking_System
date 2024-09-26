package com.arrowsmodule.movieBooking.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class User {
    private long userId;
    private String username;
    private String password;
    private List<String> roles;
}