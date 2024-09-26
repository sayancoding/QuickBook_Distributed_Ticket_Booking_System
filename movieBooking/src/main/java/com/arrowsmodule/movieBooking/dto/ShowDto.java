package com.arrowsmodule.movieBooking.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShowDto {
    private long showId;
    private LocalDate showDate;
    private LocalTime startAt;
    private LocalTime endAt;
    private long movieId;
    private long cinemaHallId;
}
