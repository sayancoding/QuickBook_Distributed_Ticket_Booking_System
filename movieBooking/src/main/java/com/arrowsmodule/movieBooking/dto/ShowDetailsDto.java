package com.arrowsmodule.movieBooking.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShowDetailsDto {
    private long showId;
    private LocalDate showDate;
    private LocalTime startAt;
    private LocalTime endAt;
    private MovieDto movie;
    private CinemaHallDto cinemaHall;
}
