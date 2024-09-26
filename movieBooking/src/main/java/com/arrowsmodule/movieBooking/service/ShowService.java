package com.arrowsmodule.movieBooking.service;

import com.arrowsmodule.movieBooking.dto.ShowDto;

import java.util.List;

public interface ShowService {
    public String add(ShowDto showDto);
    public List<ShowDto> findAll();
    public ShowDto findById(long showId);
}
