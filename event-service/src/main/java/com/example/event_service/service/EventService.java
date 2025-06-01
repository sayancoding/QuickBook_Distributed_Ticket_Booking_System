package com.example.event_service.service;

import com.example.event_service.dto.EventNewRequest;
import com.example.event_service.dto.EventResponse;

import java.util.List;


public interface EventService {
    public String createEvent(EventNewRequest eventRequest);
    public EventResponse getEventById(int id);
    public List<EventResponse> getEventByTitle(String title);
    public List<EventResponse> getAllEvent();
}
