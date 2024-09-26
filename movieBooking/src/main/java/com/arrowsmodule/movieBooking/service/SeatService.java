package com.arrowsmodule.movieBooking.service;

import com.arrowsmodule.movieBooking.dto.SeatDto;

import java.util.List;

public interface SeatService {
    public String add(SeatDto seatDto);
    public List<SeatDto> findAll();
    public SeatDto findById(long seatId);
}
