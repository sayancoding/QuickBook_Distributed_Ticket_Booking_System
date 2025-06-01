package com.example.event_service.service;

import com.example.event_service.dto.InstanceDefaultPriceDto;

import java.util.List;


public interface InstanceDefaultPriceService {
    public String create(InstanceDefaultPriceDto instanceDefaultPriceDto);
    public List<InstanceDefaultPriceDto> findByInstanceId(Long instanceId);
}
