package com.arrowsmodule.movieBooking.service.impl;

import com.arrowsmodule.movieBooking.dao.CinemaHallDao;
import com.arrowsmodule.movieBooking.dao.MovieDao;
import com.arrowsmodule.movieBooking.dao.ShowDao;
import com.arrowsmodule.movieBooking.domain.entity.CinemaHall;
import com.arrowsmodule.movieBooking.domain.entity.Movie;
import com.arrowsmodule.movieBooking.domain.entity.Show;
import com.arrowsmodule.movieBooking.dto.ShowDto;
import com.arrowsmodule.movieBooking.exception.NotFoundException;
import com.arrowsmodule.movieBooking.service.ShowService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ShowServiceImpl implements ShowService {
    @Autowired
    private ShowDao showDao;
    @Autowired
    private MovieDao movieDao;
    @Autowired
    private CinemaHallDao cinemaHallDao;
    @Override
    public String add(ShowDto showDto) {
        Show show = new Show();
        BeanUtils.copyProperties(showDto,show);
        CinemaHall cinemaHall = cinemaHallDao.findById(showDto.getCinemaHallId())
                .orElseThrow(()-> new NotFoundException("No Such cinemaHall with id "+showDto.getCinemaHallId()));
        Movie movie = movieDao.findById(showDto.getMovieId())
                .orElseThrow(()-> new NotFoundException("No Such movie with id "+showDto.getMovieId()));

        show.setCinemaHall(cinemaHall);
        show.setMovie(movie);

        showDao.save(show);
        return "New Show is added";
    }

    @Override
    public List<ShowDto> findAll() {
        List<Show> shows = showDao.findAll();
        List<ShowDto> showDtoList = new ArrayList<>();
        shows.forEach(s->{
            ShowDto showDto = new ShowDto();
            showDto.setCinemaHallId(s.getCinemaHall().getCinemaHallId());
            showDto.setMovieId(s.getMovie().getMovieId());
            BeanUtils.copyProperties(s,showDto);
            showDtoList.add(showDto);
        });
        return showDtoList;
    }

    @Override
    public ShowDto findById(long showId) {
        ShowDto showDto = new ShowDto();
        Show show = showDao.findById(showId)
                .orElseThrow(()-> new NotFoundException("Show not found with ShowId - "+showId));
        BeanUtils.copyProperties(show,showDto);
        showDto.setCinemaHallId(show.getCinemaHall().getCinemaHallId());
        showDto.setMovieId(show.getMovie().getMovieId());
        return showDto;
    }
}
