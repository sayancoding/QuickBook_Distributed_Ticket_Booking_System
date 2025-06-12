package com.example.venue_service.controller;

import com.example.venue_service.dto.LayoutDto;
import com.example.venue_service.dto.VenueDto;
import com.example.venue_service.dto.VenueRequest;
import com.example.venue_service.service.VenueSeatLayoutService;
import com.example.venue_service.service.VenueService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/venue")
public class VenueController {
    @Autowired
    private VenueService venueService;
    @Autowired
    private VenueSeatLayoutService venueSeatLayoutService;

    @PostMapping
    public String addVenue(@RequestBody VenueRequest venueRequest) throws JsonProcessingException {
        return venueService.addVenue(venueRequest);
    }

    @GetMapping
    public List<VenueDto> findAllVenue() {
        return venueService.findAllVenue();
    }

    @GetMapping("/{venueId}")
    public VenueDto findVenueById(@PathVariable Long venueId) {
        return venueService.findVenueById(venueId);
    }

    @GetMapping("/has/{venueId}")
    public boolean hasVenueById(@PathVariable Long venueId) {
        return venueService.hasVenueById(venueId);
    }


    @GetMapping("/layout/{venueId}")
    public List<LayoutDto> getSeatLayoutByVenueId(@PathVariable Long venueId) {
        return venueSeatLayoutService.getSeatLayoutByVenueId(venueId);
    }
}
