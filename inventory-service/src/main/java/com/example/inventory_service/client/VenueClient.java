package com.example.inventory_service.client;

import com.example.inventory_service.dto.LayoutDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "venue-service", url = "http://localhost:8082/venue")
public interface VenueClient {
    @GetMapping("/layout/{venueId}")
    public List<LayoutDto> getSeatLayoutByVenueId(@PathVariable Long venueId);
}
