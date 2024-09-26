package com.arrowsmodule.movieBooking.domain.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CurrentTimestamp;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "t_booking")
public class Booking extends Metadata {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long bookingId;
    private String status;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "booking_id")
    private Set<ShowSeat> showSeats = new HashSet<>();

    @CurrentTimestamp
    private LocalDateTime timeStamp;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "show_id",referencedColumnName = "showId")
    private Show show;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id",referencedColumnName = "userId")
    private User user;
}
