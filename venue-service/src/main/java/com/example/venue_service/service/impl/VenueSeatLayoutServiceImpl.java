package com.example.venue_service.service.impl;

import com.example.venue_service.dao.SeatLayoutDao;
import com.example.venue_service.dto.LayoutConfig;
import com.example.venue_service.dto.LayoutDto;
import com.example.venue_service.exception.NotFoundException;
import com.example.venue_service.model.VenueSeatLayout;
import com.example.venue_service.service.VenueSeatLayoutService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class VenueSeatLayoutServiceImpl implements VenueSeatLayoutService {
    @Autowired
    private SeatLayoutDao seatLayoutDao;

    @Override
    public void generateSeatLayout(LayoutConfig config, Long venueId) {
        List<VenueSeatLayout> seatLayouts = new ArrayList<>();

        config.getSeatTypeMap().forEach((row,type)->{
            for(int i = 1; i <= config.getColumn(); i++) {
                VenueSeatLayout layout = new VenueSeatLayout();
                layout.setRowLabel(row);
                layout.setColumnNumber(i);
                layout.setVenueId(venueId);
                layout.setSeatType(type);
                seatLayouts.add(layout);
            }
        });
        seatLayoutDao.saveAll(seatLayouts);
        log.info("Seat layout generated for venue ID: {}", venueId);
    }

    @Override
    public List<LayoutDto> getSeatLayoutByVenueId(Long venueId){
        List<VenueSeatLayout> seatLayouts = seatLayoutDao.findByVenueId(venueId)
                .orElseThrow(() -> new NotFoundException("No seat layout found for the given venue ID : " + venueId));

        List<LayoutDto> layoutDtos = new ArrayList<>();
        for (VenueSeatLayout layout : seatLayouts) {
            LayoutDto dto = new LayoutDto();
            dto.setLayoutId(layout.getLayoutId());
            dto.setRowLabel(layout.getRowLabel());
            dto.setColumnNumber(layout.getColumnNumber());
            dto.setSeatType(layout.getSeatType());
            dto.setVenueId(layout.getVenueId());
            layoutDtos.add(dto);
        }
        return layoutDtos;
    }
}
