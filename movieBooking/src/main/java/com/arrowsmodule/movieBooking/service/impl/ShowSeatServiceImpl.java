package com.arrowsmodule.movieBooking.service.impl;

import com.arrowsmodule.movieBooking.dao.SeatDao;
import com.arrowsmodule.movieBooking.dao.ShowDao;
import com.arrowsmodule.movieBooking.dao.ShowSeatDao;
import com.arrowsmodule.movieBooking.domain.entity.Seat;
import com.arrowsmodule.movieBooking.domain.entity.Show;
import com.arrowsmodule.movieBooking.domain.entity.ShowSeat;
import com.arrowsmodule.movieBooking.dto.ShowSeatDto;
import com.arrowsmodule.movieBooking.exception.NotFoundException;
import com.arrowsmodule.movieBooking.service.ShowSeatService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ShowSeatServiceImpl implements ShowSeatService {
    @Autowired
    private ShowSeatDao showSeatDao;
    @Autowired
    private SeatDao seatDao;
    @Autowired
    private ShowDao showDao;
    @Override
    public String add(ShowSeatDto showSeatDto) {
        ShowSeat showSeat = new ShowSeat();
        BeanUtils.copyProperties(showSeatDto,showSeat);
        Seat seat = seatDao.findById(showSeatDto.getSeatId())
                .orElseThrow(()-> new NotFoundException("No Such seat with id "+showSeatDto.getSeatId()));
        Show show = showDao.findById(showSeatDto.getShowId())
                .orElseThrow(()-> new NotFoundException("No Such show with id "+showSeatDto.getShowId()));

        showSeat.setShow(show);
        showSeat.setSeat(seat);

        showSeatDao.save(showSeat);
        return "New ShowSeat is added";
    }

    @Override
    public List<ShowSeatDto> findAll() {
        List<ShowSeat> showSeats = showSeatDao.findAll();
        List<ShowSeatDto> showSeatDtoList = new ArrayList<>();
        showSeats.forEach(s->{
            ShowSeatDto showSeatDto = new ShowSeatDto();
            showSeatDto.setShowId(s.getShow().getShowId());
            showSeatDto.setSeatId(s.getSeat().getSeatId());
            BeanUtils.copyProperties(s,showSeatDto);
            showSeatDtoList.add(showSeatDto);
        });
        return showSeatDtoList;
    }

    @Override
    public ShowSeatDto findById(long showSeatId) {
        ShowSeatDto showSeatDto = new ShowSeatDto();
        ShowSeat showSeat = showSeatDao.findById(showSeatId)
                .orElseThrow(()-> new NotFoundException("ShowSeat not found with ShowSeatId - "+showSeatId));
        BeanUtils.copyProperties(showSeat,showSeatDto);
        showSeatDto.setShowId(showSeat.getShow().getShowId());
        showSeatDto.setSeatId(showSeat.getSeat().getSeatId());
        return showSeatDto;
    }
}
