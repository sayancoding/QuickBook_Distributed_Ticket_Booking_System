package com.example.event_service.service.impl;

import com.example.event_service.client.VenueClient;
import com.example.event_service.constants.Message;
import com.example.event_service.dao.EventDao;
import com.example.event_service.dao.EventInstanceDao;
import com.example.event_service.dto.*;
import com.example.event_service.exception.NotFoundException;
import com.example.event_service.model.Event;
import com.example.event_service.model.EventInstance;
import com.example.event_service.model.InstanceDefaultPrice;
import com.example.event_service.service.EventInstanceService;
import com.example.event_service.service.KafkaEventService;
import com.example.event_service.utils.DateTimeUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
public class EventInstanceServiceImpl implements EventInstanceService {
    @Autowired
    private EventInstanceDao eventInstanceDao;
    @Autowired
    private EventDao eventDao;
    @Autowired
    private KafkaEventService kafkaEventService;
    @Autowired
    private VenueClient venueClient;
    @Autowired
    private ObjectMapper om;
    @Value("${kafka.topic.event-instance-created}")
    private String EVENT_INSTANCE_CREATED_TOPIC;

    @Override
    public String createEventInstance(EventInstanceRequest request) {
        if(!eventDao.existsById(request.getEventId())){
            throw new NotFoundException("No event found with ID: "+request.getEventId());
        }
        if(!venueClient.hasVenueById(request.getVenueId())){
            throw new NotFoundException("No venue found with ID: "+request.getVenueId());
        }
        EventInstance eventInstance = new EventInstance();
        BeanUtils.copyProperties(request, eventInstance);

        eventInstance.setStartTime(DateTimeUtil.toDateTime(request.getStartTime()));
        eventInstance.setEndTime(DateTimeUtil.toDateTime(request.getEndTime()));

        eventInstance.setCreatedAt(DateTimeUtil.formatDateTime(LocalDateTime.now()));
        eventInstance.setUpdatedAt(DateTimeUtil.formatDateTime(LocalDateTime.now()));

        EventInstance finalEventInstance = eventInstance;
        List<InstanceDefaultPrice> defaultPrices = request.getDefaultPrices().stream().map(
                priceDto -> {
                    InstanceDefaultPrice instanceDefaultPrice = new InstanceDefaultPrice();
                    BeanUtils.copyProperties(priceDto, instanceDefaultPrice);
                    instanceDefaultPrice.setEventInstance(finalEventInstance); // Set the parent reference
                    return instanceDefaultPrice;
                }
        ).toList();

        eventInstance.setDefaultPrices(defaultPrices);
        eventInstance = eventInstanceDao.save(eventInstance);

        EventInstanceCreate eventInstanceCreate = new EventInstanceCreate();
        eventInstanceCreate.setEventInstanceId(eventInstance.getEventInstanceId());
        eventInstanceCreate.setEventId(eventInstance.getEventId());
        eventInstanceCreate.setVenueId(eventInstance.getVenueId());
        eventInstanceCreate.setDefaultPrices(request.getDefaultPrices());

        try {
            kafkaEventService.sendMessage(EVENT_INSTANCE_CREATED_TOPIC, om.writeValueAsString(eventInstanceCreate));
            log.info("Event instance creation message sent to Kafka topic: {}", EVENT_INSTANCE_CREATED_TOPIC);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("Failed to send event instance creation message to Kafka topic: {}", EVENT_INSTANCE_CREATED_TOPIC, e);
            return "Failed to send event instance creation message to kafka topic";
        }
        log.info("Event instance created with ID: {}", eventInstance.getEventInstanceId());
        return Message.NEW_EVENT_INSTANCE_CREATED;
    }

    @Override
    public EventInstanceResponse findByInstanceId(Long instanceId) {
        EventInstance eventInstance = eventInstanceDao.findById(instanceId)
                .orElseThrow(() -> new NotFoundException("Event instance not found with ID : "+instanceId));

        if (eventInstance != null) {
            EventInstanceResponse response = new EventInstanceResponse();
            BeanUtils.copyProperties(eventInstance, response);
            List<InstanceDefaultPriceDto> defaultPriceDtoList = eventInstance.getDefaultPrices().stream()
                    .map(defaultPrice -> {
                        InstanceDefaultPriceDto defaultPriceDto = new InstanceDefaultPriceDto();
                        defaultPriceDto.setType(defaultPrice.getType());
                        defaultPriceDto.setPrice(defaultPrice.getPrice());
                        return defaultPriceDto;
                    }).toList();
            response.setDefaultPrices(defaultPriceDtoList);
            return response;
        }
        return null;
    }

    @Override
    public List<EventInstanceResponse> findAllInstances() {
    List<EventInstance> eventInstances = eventInstanceDao.findAll();
    if (!eventInstances.isEmpty()) {
        return eventInstances.stream().map(eventInstance -> {
            EventInstanceResponse response = new EventInstanceResponse();
            BeanUtils.copyProperties(eventInstance, response);
            List<InstanceDefaultPriceDto> defaultPriceDtoList = eventInstance.getDefaultPrices().stream()
                    .map(defaultPrice -> {
                InstanceDefaultPriceDto defaultPriceDto = new InstanceDefaultPriceDto();
                defaultPriceDto.setType(defaultPrice.getType());
                defaultPriceDto.setPrice(defaultPrice.getPrice());
                return defaultPriceDto;
            }).toList();
            response.setDefaultPrices(defaultPriceDtoList);
            return response;
        }).toList();
    }
        return null;
    }

    @Override
    public EventInstanceDetails findInstanceDetailsById(Long instanceId) {
        EventInstance instance = eventInstanceDao.findById(instanceId)
                .orElseThrow(() -> new NotFoundException("Event instance details not found with ID : "+instanceId));

        if(instance != null) {
            EventInstanceDetails details = new EventInstanceDetails();
            BeanUtils.copyProperties(instance, details);
            details.setStartTime(instance.getStartTime());
            details.setEndTime(instance.getEndTime());
            details.setCreatedAt(instance.getCreatedAt());
            details.setUpdatedAt(instance.getUpdatedAt());

            //Event details
            Event event = eventDao.findById(instance.getEventId()).orElse(null);
            if(event != null) {
                details.setEventName(event.getTitle());
                details.setCategory(event.getCategory());
                details.setDurationInMin(event.getDurationInMin());
            }

            List<InstanceDefaultPriceDto> defaultPriceDtoList = instance.getDefaultPrices().stream()
                    .map(defaultPrice -> {
                        InstanceDefaultPriceDto defaultPriceDto = new InstanceDefaultPriceDto();
                        defaultPriceDto.setType(defaultPrice.getType());
                        defaultPriceDto.setPrice(defaultPrice.getPrice());
                        return defaultPriceDto;
                    }).toList();
            details.setDefaultPrices(defaultPriceDtoList);

            // Fetch venue details
            VenueResponse venueResponse = venueClient.findVenueById(instance.getVenueId());
            VenueDetails venueDetailsDto = new VenueDetails();
            venueDetailsDto.setVenueId(venueResponse.getVenueId());
            venueDetailsDto.setName(venueResponse.getName());
            venueDetailsDto.setCity(venueResponse.getCity());
            venueDetailsDto.setAddress(venueResponse.getAddress());

            details.setVenueDetails(venueDetailsDto);

            return details;
        }
        return null;
    }

    @Override
    public List<EventInstanceDetails> findAllInstanceDetails() {
        List<EventInstance> instances = eventInstanceDao.findAll();
        if (!instances.isEmpty()) {
            return instances.stream().map(instance -> {
                EventInstanceDetails details = new EventInstanceDetails();
                BeanUtils.copyProperties(instance, details);
                details.setStartTime(instance.getStartTime());
                details.setEndTime(instance.getEndTime());
                details.setCreatedAt(instance.getCreatedAt());
                details.setUpdatedAt(instance.getUpdatedAt());

                // Event details
                Event event = eventDao.findById(instance.getEventId()).orElse(null);
                if (event != null) {
                    details.setEventName(event.getTitle());
                    details.setCategory(event.getCategory());
                    details.setDurationInMin(event.getDurationInMin());
                }

                List<InstanceDefaultPriceDto> defaultPriceDtoList = instance.getDefaultPrices().stream()
                        .map(defaultPrice -> {
                            InstanceDefaultPriceDto defaultPriceDto = new InstanceDefaultPriceDto();
                            defaultPriceDto.setType(defaultPrice.getType());
                            defaultPriceDto.setPrice(defaultPrice.getPrice());
                            return defaultPriceDto;
                        }).toList();
                details.setDefaultPrices(defaultPriceDtoList);

                // Fetch venue details
                VenueResponse venueResponse = venueClient.findVenueById(instance.getVenueId());
                VenueDetails venueDetailsDto = new VenueDetails();
                venueDetailsDto.setVenueId(venueResponse.getVenueId());
                venueDetailsDto.setName(venueResponse.getName());
                venueDetailsDto.setCity(venueResponse.getCity());
                venueDetailsDto.setAddress(venueResponse.getAddress());

                details.setVenueDetails(venueDetailsDto);

                return details;
            }).toList();
        }
        return null;
    }
}
