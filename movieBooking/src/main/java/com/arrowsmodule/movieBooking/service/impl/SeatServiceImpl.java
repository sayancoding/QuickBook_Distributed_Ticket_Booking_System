package com.arrowsmodule.movieBooking.service.impl;

import com.arrowsmodule.movieBooking.dao.CinemaHallDao;
import com.arrowsmodule.movieBooking.dao.SeatDao;
import com.arrowsmodule.movieBooking.domain.entity.CinemaHall;
import com.arrowsmodule.movieBooking.domain.entity.Seat;
import com.arrowsmodule.movieBooking.dto.SeatDto;
import com.arrowsmodule.movieBooking.exception.NotFoundException;
import com.arrowsmodule.movieBooking.service.SeatService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SeatServiceImpl implements SeatService {
    @Autowired
    private SeatDao seatDao;
    @Autowired
    private CinemaHallDao cinemaHallDao;
    @Override
    public String add(SeatDto seatDto) {
        Seat seat = new Seat();
        BeanUtils.copyProperties(seatDto,seat);
        CinemaHall cinemaHall = cinemaHallDao.findById(seatDto.getCinemaHallId())
                .orElseThrow(()-> new NotFoundException("No Such cinemaHall with id "+seatDto.getCinemaHallId()));
        seat.setCinemaHall(cinemaHall);
        seatDao.save(seat);
        return "New Seat is added";
    }

    @Override
    public List<SeatDto> findAll() {
        List<Seat> seats = seatDao.findAll();
        List<SeatDto> seatDtoList = new ArrayList<>();
        seats.forEach(s->{
            SeatDto seatDto = new SeatDto();
            seatDto.setCinemaHallId(s.getCinemaHall().getCinemaHallId());
            BeanUtils.copyProperties(s,seatDto);
            seatDtoList.add(seatDto);
        });
        return seatDtoList;
    }

    @Override
    public SeatDto findById(long SeatId) {
        SeatDto seatDto = new SeatDto();
        Seat seat = seatDao.findById(SeatId)
                .orElseThrow(()-> new NotFoundException("Seat not found with SeatId - "+SeatId));
        BeanUtils.copyProperties(seat,seatDto);
        seatDto.setCinemaHallId(seat.getCinemaHall().getCinemaHallId());
        return seatDto;
    }
}
