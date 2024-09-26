package com.arrowsmodule.movieBooking.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SeatDto {
    private long seatId;
    private String seatNumber;
    private String type;
    private long cinemaHallId;
}
