package com.arrowsmodule.movieBooking.dto;

import jakarta.persistence.MappedSuperclass;
import lombok.Builder;
import lombok.Data;

import java.util.HashMap;

@Data
@Builder
@MappedSuperclass
public class MetaResponse {
    private long count;
    private HashMap<String,Object> properties;
}
