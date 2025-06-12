package com.example.inventory_service.client;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "event-service", url = "http://localhost:8081/event")
public interface EventClient {
}
