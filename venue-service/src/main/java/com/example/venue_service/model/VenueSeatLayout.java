package com.example.venue_service.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "t_venue_seat_layout")
public class VenueSeatLayout {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long layoutId;

    private Long venueId;
    private String rowLabel;
    private int columnNumber;
    private String seatType;
}
