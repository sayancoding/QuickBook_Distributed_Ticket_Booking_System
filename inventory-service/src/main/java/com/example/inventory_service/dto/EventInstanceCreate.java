package com.example.inventory_service.dto;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class EventInstanceCreate {
    private Long eventInstanceId;
    private Long eventId;
    private Long venueId;
    private List<DefaultPriceDto> defaultPrices;
}
