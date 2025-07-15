package com.example.inventory_service.component;

import com.example.inventory_service.dto.EventInstanceCreate;
import com.example.inventory_service.service.EventSeatInvService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class EventConsumer {
    @Value("${kafka.topic.event-instance-create}")
    private String EVENT_LAYOUT_TOPIC;
    @Value("${kafka.consumer.inv-service-group}")
    private String INVENTORY_KAFKA_GROUP;
    @Autowired
    private EventSeatInvService eventSeatInvService;

    private final Logger logger = LoggerFactory.getLogger(EventConsumer.class);

    @KafkaListener(topics = "#{EVENT_LAYOUT_TOPIC}", groupId = "#{INVENTORY_KAFKA_GROUP}")
    public void listen(String message) throws JsonProcessingException {
        logger.info("Received message: " + message);
        ObjectMapper om = new ObjectMapper();
        EventInstanceCreate eventDto = om.readValue(message, EventInstanceCreate.class);
        eventSeatInvService.createInventoryForEvent(eventDto);
    }
}
