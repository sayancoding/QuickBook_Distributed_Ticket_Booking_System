package com.example.inventory_service.component;

import com.example.inventory_service.dto.EventInstanceCreate;
import com.example.inventory_service.service.EventSeatInvService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class EventConsumer {
    @Autowired
    private EventSeatInvService eventSeatInvService;

    @KafkaListener(topics = "event-instance-create", groupId = "inv-service-group")
    public void listen(String message) throws JsonProcessingException {
        log.info("Received message: " + message);
        ObjectMapper om = new ObjectMapper();
        EventInstanceCreate eventDto = om.readValue(message, EventInstanceCreate.class);
        eventSeatInvService.createInventoryForEvent(eventDto);
    }
}
