package com.arrowsmodule.movieBooking.domain.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@Table(name = "t_movie")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long movieId;

    private String movieName;
    private String movieDes;
    private long durationInMin;
    private String language;
    private LocalDate releasedAt;
    private String genre;

}
