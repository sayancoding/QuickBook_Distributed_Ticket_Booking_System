package com.example.venue_service.service;

import com.example.venue_service.dto.LayoutDto;
import com.example.venue_service.dto.VenueDto;
import com.example.venue_service.dto.VenueRequest;

import java.util.List;

public interface VenueService {
    public String addVenue(VenueRequest venueRequest);
    public List<VenueDto> findAllVenue();
    public VenueDto findVenueById(Long venueId);

}
