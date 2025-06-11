package com.example.event_service.dto;

import lombok.Data;

import java.util.List;

@Data
public class EventInstanceCreate {
    private Long eventInstanceId;
    private Long eventId;
    private Long venueId;
    private List<InstanceDefaultPriceDto> defaultPrices;
}
