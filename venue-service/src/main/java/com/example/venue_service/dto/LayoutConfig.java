package com.example.venue_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LayoutConfig {
    private int row;
    private int column;
    private Map<String, String> seatTypeMap;
}
