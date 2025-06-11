package com.example.inventory_service.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "t_event_seat_inventory")
@Data
public class EventSeatInventory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long inventoryId;

    private Long eventInstanceId;
    private Long eventId;
    private Long venueId;
    private Long seatId;
    private String seatType;
    private String status; // e.g., "available", "booked", "reserved"
    private int basePrice;
}
