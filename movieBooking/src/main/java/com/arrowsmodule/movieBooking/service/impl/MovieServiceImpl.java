package com.arrowsmodule.movieBooking.service.impl;

import com.arrowsmodule.movieBooking.dao.MovieDao;
import com.arrowsmodule.movieBooking.domain.entity.Movie;
import com.arrowsmodule.movieBooking.domain.spec.MovieSpecifications;
import com.arrowsmodule.movieBooking.dto.MovieDto;
import com.arrowsmodule.movieBooking.exception.NotFoundException;
import com.arrowsmodule.movieBooking.service.MovieService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class MovieServiceImpl implements MovieService {
    @Autowired
    private MovieDao movieDao;

    @Override
    public String post(MovieDto movieDto) {
        Movie movie = new Movie();
        BeanUtils.copyProperties(movieDto,movie, "movieId");
        movieDao.save(movie);
        return "New Movie has been posted";
    }

    @Override
    public List<MovieDto> getAllMovies(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page,pageSize);
        List<Movie> movies = movieDao.findAll(pageable).getContent();

        List<MovieDto> movieDtos = movies.stream().map(el->{
            MovieDto movieDto = new MovieDto();
            BeanUtils.copyProperties(el,movieDto);
            return movieDto;
        }).collect(Collectors.toList());

        return movieDtos;
    }

    @Override
    public MovieDto getMovieById(long movieId) {
        Movie movie = movieDao.findById(movieId)
                .orElseThrow(()->new NotFoundException("No Such movie with movieId - " + movieId));

        MovieDto movieDto = new MovieDto();
        BeanUtils.copyProperties(movie,movieDto);
        return movieDto;
    }

    @Override
    public List<MovieDto> getMoviesByFilter(int page, int pageSize, Map<String, String> filters) {
        Pageable pageable = PageRequest.of(page,pageSize);
        Specification<Movie> specification = Specification.where(null);

        if(filters.containsKey("movieName") && !filters.get("movieName").isBlank()){
            specification = specification.and(MovieSpecifications.hasMovieName(filters.get("movieName")));
        }
        if(filters.containsKey("language") && !filters.get("language").isBlank()){
            specification = specification.and(MovieSpecifications.hasLanguage(filters.get("language")));
        }
        if(filters.containsKey("genre") && !filters.get("genre").isBlank()){
            specification = specification.and(MovieSpecifications.hasGenre(filters.get("genre")));
        }

        List<Movie> movies = movieDao.findAll(specification,pageable).getContent();
        List<MovieDto> movieDtos = movies.stream().map(el->{
            MovieDto movieDto = new MovieDto();
            BeanUtils.copyProperties(el,movieDto);
            return movieDto;
        }).collect(Collectors.toList());

        return movieDtos;
    }
}
