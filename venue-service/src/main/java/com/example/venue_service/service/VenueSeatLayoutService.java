package com.example.venue_service.service;


import com.example.venue_service.dto.LayoutConfig;
import com.example.venue_service.dto.LayoutDto;

import java.util.List;

public interface VenueSeatLayoutService {
    public void generateSeatLayout(LayoutConfig config, Long venueId);

    public List<LayoutDto> getSeatLayoutByVenueId(Long venueId);
}
