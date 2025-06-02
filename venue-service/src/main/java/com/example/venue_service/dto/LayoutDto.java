package com.example.venue_service.dto;

import lombok.Data;

@Data
public class LayoutDto {
    private Long venueId;
    private String rowLabel;
    private int columnNumber;
    private String seatType;
}
