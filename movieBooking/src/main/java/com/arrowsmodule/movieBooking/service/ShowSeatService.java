package com.arrowsmodule.movieBooking.service;

import com.arrowsmodule.movieBooking.dto.ShowSeatDto;

import java.util.List;

public interface ShowSeatService {
    public String add(ShowSeatDto showSeatDto);
    public List<ShowSeatDto> findAll();
    public ShowSeatDto findById(long showSeatId);
}
