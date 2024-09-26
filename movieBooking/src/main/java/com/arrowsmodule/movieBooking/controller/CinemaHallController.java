package com.arrowsmodule.movieBooking.controller;

import com.arrowsmodule.movieBooking.dto.CinemaHallDto;
import com.arrowsmodule.movieBooking.dto.ResponseDto;
import com.arrowsmodule.movieBooking.service.CinemaHallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cinemaHall")
public class CinemaHallController {
    @Autowired
    private CinemaHallService cinemaHallService;
    @PostMapping
    public ResponseEntity<ResponseDto> post(@RequestBody
                                            CinemaHallDto cinemaHallDto){
        ResponseEntity<ResponseDto> response;
        String res = cinemaHallService.add(cinemaHallDto);

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
        List<CinemaHallDto> cinemaHallDtos = cinemaHallService.findAll();

        response = new ResponseEntity<>(
                ResponseDto.builder().data(cinemaHallDtos).build(),
                HttpStatus.OK);

        return response;
    }
    @GetMapping("/{cinemaHallId}")
    public ResponseEntity<ResponseDto> findById(@PathVariable long cinemaHallId){
        ResponseEntity<ResponseDto> response;
        CinemaHallDto cinemaHallDto = cinemaHallService.findById(cinemaHallId);

        response = new ResponseEntity<>(
                ResponseDto.builder().data(cinemaHallDto).build(),
                HttpStatus.OK);

        return response;
    }
}
