package com.example.venue_service.service.impl;

import com.example.venue_service.dao.VenueDao;
import com.example.venue_service.dto.SeatLayoutEventDto;
import com.example.venue_service.dto.VenueDto;
import com.example.venue_service.dto.VenueRequest;
import com.example.venue_service.exception.NotFoundException;
import com.example.venue_service.model.Venue;
import com.example.venue_service.service.KafkaEventService;
import com.example.venue_service.service.VenueSeatLayoutService;
import com.example.venue_service.service.VenueService;
import com.example.venue_service.utils.DateTimeUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

import static com.example.venue_service.constant.Messages.NEW_VENUE_CREATE;

@Service
public class VenueServiceImpl implements VenueService {

    @Value("${kafka.topics.venue-seat-layout}")
    private String VENUE_LAYOUT_TOPIC;
    @Autowired
    private VenueDao venueDao;
    @Autowired
    private VenueSeatLayoutService seatLayoutService;
    @Autowired
    private KafkaEventService kafkaEventService;

    @Override
    public String addVenue(VenueRequest venueRequest) throws JsonProcessingException {
        Venue venue = new Venue();
        BeanUtils.copyProperties(venueRequest, venue);
        venue.setCreatedAt(DateTimeUtil.formatDateTime(LocalDateTime.now()));
        venue.setUpdatedAt(DateTimeUtil.formatDateTime(LocalDateTime.now()));
        Venue response = venueDao.save(venue);

        SeatLayoutEventDto eventDto = new SeatLayoutEventDto();
        eventDto.setVenueId(response.getVenueId());
        eventDto.setLayoutConfig(venueRequest.getLayoutConfig());
        ObjectMapper om = new ObjectMapper();

        kafkaEventService.sendMessage(VENUE_LAYOUT_TOPIC, om.writeValueAsString(eventDto));

        return NEW_VENUE_CREATE;
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
        Venue venue = venueDao.findById(venueId).orElseThrow(() -> new NotFoundException("Venue not found with id : "+venueId));
        VenueDto venueDto = new VenueDto();
        BeanUtils.copyProperties(venue, venueDto);
        return venueDto;
    }

    @Override
    public boolean hasVenueById(Long venueId) {
        return venueDao.existsById(venueId);
    }
}
