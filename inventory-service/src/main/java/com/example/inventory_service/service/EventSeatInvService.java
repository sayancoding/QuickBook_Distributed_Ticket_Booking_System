package com.example.inventory_service.service;

import com.example.inventory_service.dto.EventInstanceCreate;
import com.example.inventory_service.dto.EventSeatInventoryDto;

import java.util.List;

public interface EventSeatInvService {
    public void createInventoryForEvent(EventInstanceCreate eventInstanceCreate);
    public List<EventSeatInventoryDto> getInventoryByInstanceId(Long eventInstanceId);
    public List<EventSeatInventoryDto> getInventoryByEventId(Long eventId);
}
