package com.example.event_service.service.impl;

import com.example.event_service.constants.Message;
import com.example.event_service.dao.EventDao;
import com.example.event_service.dto.EventNewRequest;
import com.example.event_service.dto.EventResponse;
import com.example.event_service.exception.NotFoundException;
import com.example.event_service.model.Event;
import com.example.event_service.service.EventService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class EventServiceImpl implements EventService {
    @Autowired
    private EventDao eventDao;
    @Override
    public String createEvent(EventNewRequest eventRequest) {
        Event event = new Event();
        BeanUtils.copyProperties(eventRequest,event);
        event = eventDao.save(event);
        log.info("New Event is created with ID: {}", event.getEventId());
        return Message.NEW_EVENT_CREATED;
    }

    @Override
    public EventResponse getEventById(long id) {
        Event event = eventDao.findById(id)
                .orElseThrow(() -> new NotFoundException("Event not found with id: " + id));

        EventResponse response = new EventResponse();
        BeanUtils.copyProperties(event,response);
        return response;
    }

    @Override
    public List<EventResponse> getEventByTitle(String title) {
        log.info(eventDao.findByTitle(title).toString());
        return eventDao.findByTitle(title)
                .orElseThrow(() -> new NotFoundException("Event not found with title: " + title))
                .stream().map(
                e -> {
                    EventResponse response = new EventResponse();
                    BeanUtils.copyProperties(e, response);
                    return response;
                }).collect(Collectors.toList());
    }

    @Override
    public List<EventResponse> getAllEvent() {
        return eventDao.findAll().stream().map(
                e -> {
                    EventResponse response = new EventResponse();
                    BeanUtils.copyProperties(e,response);
                    return response;
                }).toList();
    }
}
