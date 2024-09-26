package com.arrowsmodule.movieBooking.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "t_seat")
@AllArgsConstructor
@NoArgsConstructor
public class Seat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long seatId;

    private String seatNumber;
    private String type;

    @ManyToOne
    @JoinColumn(name = "cinemaHallId")
    private CinemaHall cinemaHall;
}
