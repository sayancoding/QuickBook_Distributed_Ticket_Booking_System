package com.example.inventory_service.dto;

import lombok.Data;

@Data
public class LayoutDto {
    private Long layoutId;
    private Long venueId;
    private String rowLabel;
    private int columnNumber;
    private String seatType;
}
