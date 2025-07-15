package com.example.venue_service.component;

import com.example.venue_service.dto.SeatLayoutEventDto;
import com.example.venue_service.service.VenueSeatLayoutService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class EventConsumer {
    @Value("${kafka.topics.venue-seat-layout}")
    private String VENUE_LAYOUT_TOPIC;
    @Value("${kafka.consumer.venue-service-group}")
    private String VENUE_KAFKA_GROUP;
    @Autowired
    private VenueSeatLayoutService seatLayoutService;

    @KafkaListener(topics = "${kafka.topics.venue-seat-layout}", groupId = "${kafka.consumer.venue-service-group}")
    public void listen(String message) throws JsonProcessingException {
        log.info("Received message: " + message);
        ObjectMapper om = new ObjectMapper();
        SeatLayoutEventDto eventDto = om.readValue(message, SeatLayoutEventDto.class);
        seatLayoutService.generateSeatLayout(eventDto.getLayoutConfig(), eventDto.getVenueId());
    }
}
