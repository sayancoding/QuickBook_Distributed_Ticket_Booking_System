package com.arrowsmodule.movieBooking.service.impl;

import com.arrowsmodule.movieBooking.dao.CinemaHallDao;
import com.arrowsmodule.movieBooking.domain.entity.CinemaHall;
import com.arrowsmodule.movieBooking.dto.CinemaHallDto;
import com.arrowsmodule.movieBooking.exception.NotFoundException;
import com.arrowsmodule.movieBooking.service.CinemaHallService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CinemaHallServiceImpl implements CinemaHallService {
    @Autowired
    private CinemaHallDao cinemaHallDao;
    @Override
    public String add(CinemaHallDto CinemaHallDto) {
        CinemaHall cinemaHall = new CinemaHall();
        BeanUtils.copyProperties(CinemaHallDto,cinemaHall);
        cinemaHallDao.save(cinemaHall);
        return "New CinemaHall is added";
    }

    @Override
    public List<CinemaHallDto> findAll() {
        List<CinemaHall> cinemaHalls = cinemaHallDao.findAll();
        List<CinemaHallDto> CinemaHallDtoList = new ArrayList<>();
        cinemaHalls.forEach(cm->{
            CinemaHallDto cinemaHallDto = new CinemaHallDto();
            BeanUtils.copyProperties(cm,cinemaHallDto);
            CinemaHallDtoList.add(cinemaHallDto);
        });
        return CinemaHallDtoList;
    }

    @Override
    public CinemaHallDto findById(long cinemaHallId) {
        CinemaHallDto cinemaHallDto = new CinemaHallDto();
        CinemaHall cinemaHall = cinemaHallDao.findById(cinemaHallId).orElseThrow(()-> new NotFoundException("CinemaHall not found with Id - "+cinemaHallId));
        BeanUtils.copyProperties(cinemaHall,cinemaHallDto);
        return cinemaHallDto;
    }
}
