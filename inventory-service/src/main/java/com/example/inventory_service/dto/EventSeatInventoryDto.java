package com.example.inventory_service.dto;

import lombok.Data;

@Data
public class EventSeatInventoryDto {
    private Long inventoryId;

    private Long eventInstanceId;
    private Long eventId;
    private Long venueId;
    private Long seatId;
    private String seatType;
    private String status; // e.g., "available", "booked", "reserved"
    private int basePrice;
}
