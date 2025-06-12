package com.example.inventory_service.service;

import com.example.inventory_service.client.VenueClient;
import com.example.inventory_service.dao.EventSeatInventoryDao;
import com.example.inventory_service.dto.DefaultPriceDto;
import com.example.inventory_service.dto.EventInstanceCreate;
import com.example.inventory_service.dto.EventSeatInventoryDto;
import com.example.inventory_service.dto.LayoutDto;
import com.example.inventory_service.enums.SeatStatus;
import com.example.inventory_service.model.EventSeatInventory;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class EventSeatInvServiceImpl implements EventSeatInvService{

    private final Logger logger = LoggerFactory.getLogger(EventSeatInvServiceImpl.class);

    @Autowired
    private EventSeatInventoryDao eventSeatInventoryDao;

    @Autowired
    private VenueClient venueClient;


    @Override
    public void createInventoryForEvent(EventInstanceCreate eventInstanceCreate) {
        List<EventSeatInventory> inventoryList = new ArrayList<>();

        List<LayoutDto> seats = venueClient.getSeatLayoutByVenueId(eventInstanceCreate.getVenueId());
        log.info("Seats fetched for venue ID {}: {}", eventInstanceCreate.getVenueId(), seats);
        seats.forEach(e ->{
             // Default to 0 if no price found
            EventSeatInventory inventory = new EventSeatInventory();
            inventory.setEventInstanceId(eventInstanceCreate.getEventInstanceId());
            inventory.setEventId(eventInstanceCreate.getEventId());
            inventory.setVenueId(eventInstanceCreate.getVenueId());
            inventory.setSeatId(e.getLayoutId());
            inventory.setSeatType(e.getSeatType());
            inventory.setStatus(SeatStatus.AVAILABLE);
            inventory.setBasePrice(eventInstanceCreate.getDefaultPrices().stream()
                    .filter(price ->price.getType().equals(e.getSeatType())).findFirst()
                    .orElseGet(DefaultPriceDto::new).getPrice());// Initially all seats are available

            inventoryList.add(inventory);
        });
        eventSeatInventoryDao.saveAll(inventoryList);
    }

    @Override
    public List<EventSeatInventoryDto> getInventoryByInstanceId(Long eventInstanceId) {
        List<EventSeatInventoryDto> inventoryDtoList = eventSeatInventoryDao.findByEventInstanceId(eventInstanceId).stream().map(inv ->{
            EventSeatInventoryDto dto = new EventSeatInventoryDto();
            BeanUtils.copyProperties(inv, dto);
            return dto;
        }).toList();
        return inventoryDtoList;
    }

    @Override
    public List<EventSeatInventoryDto> getInventoryByEventId(Long eventId) {
        List<EventSeatInventoryDto> inventoryDtoList = eventSeatInventoryDao.findByEventId(eventId).stream().map(inv ->{
            EventSeatInventoryDto dto = new EventSeatInventoryDto();
            BeanUtils.copyProperties(inv, dto);
            return dto;
        }).toList();
        return inventoryDtoList;
    }
}
