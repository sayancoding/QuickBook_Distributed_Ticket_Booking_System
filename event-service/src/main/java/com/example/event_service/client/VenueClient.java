package com.example.event_service.client;

import com.example.event_service.dto.VenueResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "venue-service", url = "${service.venue.url}/venue")
public interface VenueClient {
    @GetMapping("/has/{venueId}")
    public boolean hasVenueById(@PathVariable Long venueId);
    @GetMapping("/{venueId}")
    public VenueResponse findVenueById(@PathVariable Long venueId);
}
