package com.arrowsmodule.movieBooking.controller;

import com.arrowsmodule.movieBooking.dto.MovieDto;
import com.arrowsmodule.movieBooking.dto.ResponseDto;
import com.arrowsmodule.movieBooking.exception.NotFoundException;
import com.arrowsmodule.movieBooking.service.MovieService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/movie")
@Tag(name = "Movie-Controller")
@Slf4j
public class MovieController {
    @Autowired
    private MovieService movieService;
    @PostMapping
    public ResponseEntity<ResponseDto> post(@RequestBody
                                                MovieDto movieDto){
        ResponseEntity<ResponseDto> response;
        String res = movieService.post(movieDto);

        response = new ResponseEntity<>(
                ResponseDto.builder().data(res).build(),
                HttpStatus.CREATED);

        return response;
    }

    @GetMapping
    public ResponseEntity<ResponseDto> findAll(
            @RequestParam(name = "page",defaultValue = "0",required = false) Integer page,
            @RequestParam(name = "pageSize",defaultValue = "50",required = false) Integer pageSize
    ){
        ResponseEntity<ResponseDto> response;
        List<MovieDto> movieDtos = movieService.getAllMovies(page,pageSize);

        response = new ResponseEntity<>(
                ResponseDto.builder().data(movieDtos).build(),
                HttpStatus.OK);

        return response;
    }
    @GetMapping("/{movieId}")
    public ResponseEntity<ResponseDto> findById(@PathVariable long movieId){
        ResponseEntity<ResponseDto> response;
        MovieDto movieDto = movieService.getMovieById(movieId);

        response = new ResponseEntity<>(
                ResponseDto.builder().data(movieDto).build(),
                HttpStatus.OK);

        return response;
    }
    @GetMapping("/filter")
    public ResponseEntity<ResponseDto> findByFilter(
            @RequestParam(name = "page",defaultValue = "0",required = false) Integer page,
            @RequestParam(name = "pageSize",defaultValue = "50",required = false) Integer pageSize,
            @RequestParam Map<String,String> requestParams){
        ResponseEntity<ResponseDto> response;
        List<MovieDto> movieDtos = movieService.getMoviesByFilter(page,pageSize,requestParams);

        response = new ResponseEntity<>(
                ResponseDto.builder().data(movieDtos).build(),
                HttpStatus.OK);
        return response;
    }
}
