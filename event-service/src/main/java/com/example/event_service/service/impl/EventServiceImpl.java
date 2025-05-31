package com.example.event_service.service.impl;

import com.example.event_service.dao.EventDao;
import com.example.event_service.dto.EventNewRequest;
import com.example.event_service.dto.EventResponse;
import com.example.event_service.model.Event;
import com.example.event_service.service.EventService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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
//        eventRequest.setCreatedAt(LocalDateTime.now());
//        eventRequest.setUpdatedAt(LocalDateTime.now());
        BeanUtils.copyProperties(eventRequest,event);
        eventDao.save(event);
        return "New Event is created.";
    }

    @Override
    public EventResponse getEventById(int id) {
        return eventDao.findById(id).stream().map(
                e -> {
                   EventResponse response = new EventResponse();
                   BeanUtils.copyProperties(e,response);
                   return response;
                }).toList().get(0);
    }

    @Override
    public List<EventResponse> getEventByTitle(String title) {
        log.info(eventDao.findByTitle(title).toString());
        return eventDao.findByTitle(title).orElseThrow().stream().map(
                e -> {
                    log.info(e.toString());
                    EventResponse response = new EventResponse();
                    BeanUtils.copyProperties(e, response);
                    log.info(response.toString());
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
