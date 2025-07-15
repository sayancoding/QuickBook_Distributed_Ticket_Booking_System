package com.example.inventory_service.client;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "event-service", url = "${service.event.baseUrl}/event")
public interface EventClient {
}
