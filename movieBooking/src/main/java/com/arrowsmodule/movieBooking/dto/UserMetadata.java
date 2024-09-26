package com.arrowsmodule.movieBooking.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class UserMetadata {
    private String status;
    private LocalDateTime createdAt;
    private LocalDateTime lastActiveAt;
}
