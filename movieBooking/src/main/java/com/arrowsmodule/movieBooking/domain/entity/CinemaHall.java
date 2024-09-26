package com.arrowsmodule.movieBooking.domain.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "t_cinema_hall")
public class CinemaHall {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long cinemaHallId;

    private String name;

    private long capacity;

    private String city;

    private String state;

    @OneToMany(mappedBy = "cinemaHall")
    private List<Seat> seats;
}
