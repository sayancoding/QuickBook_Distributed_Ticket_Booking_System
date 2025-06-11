package com.example.inventory_service.service;

import com.example.inventory_service.dao.EventSeatInventoryDao;
import com.example.inventory_service.dto.EventInstanceCreate;
import com.example.inventory_service.dto.EventSeatInventoryDto;
import com.example.inventory_service.model.EventSeatInventory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class EventSeatInvServiceImpl implements EventSeatInvService{

    @Autowired
    private EventSeatInventoryDao eventSeatInventoryDao;


    @Override
    public void createInventoryForEvent(EventInstanceCreate eventInstanceCreate) {
//        List<EventSeatInventory> inventoryList = eventInstanceCreate.getDefaultPrices().stream().map(priceDto -> {
//            EventSeatInventory inventory = new EventSeatInventory();
//            BeanUtils.copyProperties(priceDto, inventory);
//            inventory.setEventInstanceId(eventInstanceCreate.getEventInstanceId());
//            inventory.setEventId(eventInstanceCreate.getEventId());
//            return inventory;
//        }).toList();

//        eventSeatInventoryDao.saveAll(inventoryList);
        log.info(eventInstanceCreate.toString());
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
