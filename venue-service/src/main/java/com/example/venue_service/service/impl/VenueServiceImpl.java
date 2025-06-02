package com.example.venue_service.service.impl;

import com.example.venue_service.dao.VenueDao;
import com.example.venue_service.dto.VenueDto;
import com.example.venue_service.dto.VenueRequest;
import com.example.venue_service.model.Venue;
import com.example.venue_service.service.VenueSeatLayoutService;
import com.example.venue_service.service.VenueService;
import com.example.venue_service.utils.DateTimeUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class VenueServiceImpl implements VenueService {
    @Autowired
    private VenueDao venueDao;
    @Autowired
    private VenueSeatLayoutService seatLayoutService;

    @Override
    public String addVenue(VenueRequest venueRequest) {
        Venue venue = new Venue();
        BeanUtils.copyProperties(venueRequest, venue);
        venue.setCreatedAt(DateTimeUtil.formatDateTime(LocalDateTime.now()));
        venue.setUpdatedAt(DateTimeUtil.formatDateTime(LocalDateTime.now()));
        Venue response = venueDao.save(venue);

        seatLayoutService.generateSeatLayout(venueRequest.getLayoutConfig(), response.getVenueId());

        return "New Venue Added Successfully";
    }

    @Override
    public List<VenueDto> findAllVenue() {
        return venueDao.findAll().stream().map(venue -> {
            VenueDto venueDto = new VenueDto();
            BeanUtils.copyProperties(venue, venueDto);
            return venueDto;
        }).toList();
    }

    @Override
    public VenueDto findVenueById(Long venueId) {
        Venue venue = venueDao.findById(venueId).orElseThrow(() -> new RuntimeException("Venue not found"));
        VenueDto venueDto = new VenueDto();
        BeanUtils.copyProperties(venue, venueDto);
        return venueDto;
    }
}
