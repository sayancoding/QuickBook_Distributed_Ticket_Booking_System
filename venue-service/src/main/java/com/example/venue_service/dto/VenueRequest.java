package com.example.venue_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VenueRequest {
    private String name;
    private String city;
    private String address;
    private Integer capacity;
    private String status;
    private LayoutConfig layoutConfig;
    private String createdAt;
    private String updatedAt;
}
