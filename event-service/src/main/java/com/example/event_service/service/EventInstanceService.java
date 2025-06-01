package com.example.event_service.service;

import com.example.event_service.dto.EventInstanceRequest;
import com.example.event_service.dto.EventInstanceResponse;

import java.util.List;

public interface EventInstanceService {
    public String createEventInstance(EventInstanceRequest request);
    public EventInstanceResponse findByInstanceId(Long instanceId);
    public List<EventInstanceResponse> findAllInstances();
}
