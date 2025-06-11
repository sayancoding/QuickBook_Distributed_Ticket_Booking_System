package com.example.event_service.service.impl;

import com.example.event_service.dao.EventInstanceDao;
import com.example.event_service.dto.EventInstanceCreate;
import com.example.event_service.dto.EventInstanceRequest;
import com.example.event_service.dto.EventInstanceResponse;
import com.example.event_service.dto.InstanceDefaultPriceDto;
import com.example.event_service.model.EventInstance;
import com.example.event_service.model.InstanceDefaultPrice;
import com.example.event_service.service.EventInstanceService;
import com.example.event_service.service.KafkaEventService;
import com.example.event_service.utils.DateTimeUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class EventInstanceServiceImpl implements EventInstanceService {
    @Autowired
    private EventInstanceDao eventInstanceDao;
    @Autowired
    private KafkaEventService kafkaEventService;

    private final ObjectMapper om = new ObjectMapper();
    @Override
    public String createEventInstance(EventInstanceRequest request) {
        EventInstance eventInstance = new EventInstance();
        BeanUtils.copyProperties(request, eventInstance);

        eventInstance.setStartTime(DateTimeUtil.toDateTime(request.getStartTime()));
        eventInstance.setEndTime(DateTimeUtil.toDateTime(request.getEndTime()));

        eventInstance.setCreatedAt(DateTimeUtil.formatDateTime(LocalDateTime.now()));
        eventInstance.setUpdatedAt(DateTimeUtil.formatDateTime(LocalDateTime.now()));

        List<InstanceDefaultPrice> defaultPrices = request.getDefaultPrices().stream().map(
                priceDto -> {
                    InstanceDefaultPrice instanceDefaultPrice = new InstanceDefaultPrice();
                    BeanUtils.copyProperties(priceDto, instanceDefaultPrice);
                    instanceDefaultPrice.setEventInstance(eventInstance); // Set the parent reference
                    return instanceDefaultPrice;
                }
        ).toList();

        eventInstance.setDefaultPrices(defaultPrices);
        eventInstanceDao.save(eventInstance);

        EventInstanceCreate eventInstanceCreate = new EventInstanceCreate();
        eventInstanceCreate.setEventInstanceId(eventInstance.getEventInstanceId());
        eventInstanceCreate.setEventId(eventInstance.getEventId());
        eventInstanceCreate.setVenueId(eventInstance.getVenueId());
        eventInstanceCreate.setDefaultPrices(request.getDefaultPrices());

        try {
            kafkaEventService.sendMessage("event-instance-create", om.writeValueAsString(eventInstanceCreate));
        } catch (Exception e) {
            e.printStackTrace();
            return "Failed to send event instance creation message to kafka topic";
        }
        return "New Event instance is created";
    }

    @Override
    public EventInstanceResponse findByInstanceId(Long instanceId) {
        EventInstance eventInstance = eventInstanceDao.findById(instanceId).orElse(null);
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
}
