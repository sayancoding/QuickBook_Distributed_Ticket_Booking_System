package com.example.event_service.dto;

import lombok.Data;

@Data
public class VenueDetails {
    private Long venueId;
    private String name;
    private String city;
    private String address;
}
