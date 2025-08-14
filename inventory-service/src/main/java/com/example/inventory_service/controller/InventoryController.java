package com.example.inventory_service.controller;

import com.example.inventory_service.dto.EventSeatInventoryDto;
import com.example.inventory_service.service.EventSeatInvService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/inventory")
public class InventoryController {
    @Autowired
    private EventSeatInvService eventSeatInvService;

    @GetMapping("/seats")
    public List<EventSeatInventoryDto> findBySeatIds(List<Long> seatIds) {
        return eventSeatInvService.getInventoryBySeatIds(seatIds);
    }
}
