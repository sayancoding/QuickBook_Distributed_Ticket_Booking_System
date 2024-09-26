package com.arrowsmodule.movieBooking.service;

import com.arrowsmodule.movieBooking.dto.MovieDto;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface MovieService {
    public String post(MovieDto movie);
    public List<MovieDto> getAllMovies(int page, int pageSize);
    public MovieDto getMovieById(long movieId);
    public List<MovieDto> getMoviesByFilter(int page, int pageSize, Map<String, String> requestParams);
}
