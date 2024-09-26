package com.arrowsmodule.movieBooking.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShowSeatDto {
    private long showSeatId;
    private String status;
    private long price;
    private long seatId;
    private long showId;
}
