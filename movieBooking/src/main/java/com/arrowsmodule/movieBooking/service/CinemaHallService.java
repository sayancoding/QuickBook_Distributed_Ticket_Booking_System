package com.arrowsmodule.movieBooking.service;

import com.arrowsmodule.movieBooking.dto.CinemaHallDto;

import java.util.List;

public interface CinemaHallService {
    public String add(CinemaHallDto CinemaHallDto);
    public List<CinemaHallDto> findAll();
    public CinemaHallDto findById(long cityId);
}
