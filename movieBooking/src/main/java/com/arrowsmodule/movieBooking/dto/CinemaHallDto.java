package com.arrowsmodule.movieBooking.dto;

import com.arrowsmodule.movieBooking.domain.entity.Seat;
import com.arrowsmodule.movieBooking.domain.entity.Show;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CinemaHallDto {
    private long cinemaHallId;
    private String name;
    private long cityId;
    private long capacity;
    private String city;
    private String state;
    private List<Seat> seats;
}
