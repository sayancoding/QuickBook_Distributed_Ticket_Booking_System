package com.example.venue_service.dto;

import lombok.Data;

@Data
public class SeatLayoutEventDto {
    private Long venueId;
    private LayoutConfig layoutConfig;
}
