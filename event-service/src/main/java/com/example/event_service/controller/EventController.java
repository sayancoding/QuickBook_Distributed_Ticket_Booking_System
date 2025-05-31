package com.example.event_service.controller;

import com.example.event_service.dto.EventNewRequest;
import com.example.event_service.dto.EventResponse;
import com.example.event_service.service.EventService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/event")
@Slf4j
public class EventController {
    @Autowired
    private EventService eventService;

    @PostMapping
    public ResponseEntity<String> createEvent(@RequestBody EventNewRequest request){
        log.info(request.toString());
        request.setCreatedAt(LocalDateTime.now());
        request.setUpdatedAt(LocalDateTime.now());
        return new ResponseEntity<>(eventService.createEvent(request), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<EventResponse>> findAllEvents(){
        return new ResponseEntity<>(eventService.getAllEvent(), HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<Object> findEventsByQuery(
            @RequestParam(name = "id",required = false) String id,
            @RequestParam(name = "title", required = false) String title
    ){
        log.info("id:"+ id + " title:" + title);
        if(id !=null){
            return new ResponseEntity<>(eventService.getEventById(Integer.parseInt(id)), HttpStatus.OK);
        }
        else if(title != null){
            return new ResponseEntity<>(eventService.getEventByTitle(title), HttpStatus.OK);
        }
        return new ResponseEntity<>(eventService.getAllEvent(), HttpStatus.OK);
    }
}
