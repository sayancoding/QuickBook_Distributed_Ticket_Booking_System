package com.example.booking_service.dto;


import lombok.Data;

import java.util.List;

@Data
public class BookingRequest {
    private Long eventInstanceId; // ID of the event instance being booked
    private Long userId; // ID of the user making the booking
    private List<Long> seatIds; // List of seat IDs being booked
}
