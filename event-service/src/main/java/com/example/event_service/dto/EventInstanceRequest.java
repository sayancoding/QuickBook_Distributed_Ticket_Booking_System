package com.example.event_service.dto;

import com.example.event_service.model.InstanceDefaultPrice;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventInstanceRequest {
    private Long eventId;
    private Long venueId;
    private String language;
    private String startTime;
    private String endTime;
    private String status;

    private List<InstanceDefaultPriceDto> defaultPrices;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
