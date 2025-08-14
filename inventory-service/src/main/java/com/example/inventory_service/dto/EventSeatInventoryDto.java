package com.example.inventory_service.dto;

import com.example.inventory_service.enums.SeatStatus;
import lombok.Data;

@Data
public class EventSeatInventoryDto {
    private Long inventoryId;

    private Long eventInstanceId;
    private Long eventId;
    private Long venueId;
    private Long seatId;
    private String seatType;
    private SeatStatus status; // e.g., "available", "booked", "reserved"
    private int basePrice;
}
