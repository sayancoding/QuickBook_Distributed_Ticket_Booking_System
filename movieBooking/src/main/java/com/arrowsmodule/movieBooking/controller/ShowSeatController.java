package com.arrowsmodule.movieBooking.controller;

import com.arrowsmodule.movieBooking.dto.ResponseDto;
import com.arrowsmodule.movieBooking.dto.ShowSeatDto;
import com.arrowsmodule.movieBooking.service.ShowSeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/showSeat")
public class ShowSeatController {
    @Autowired
    private ShowSeatService showSeatService;

    @PostMapping
    public ResponseEntity<ResponseDto> post(@RequestBody
                                            ShowSeatDto showSeatDto){
        ResponseEntity<ResponseDto> response;
        String res = showSeatService.add(showSeatDto);

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
        List<ShowSeatDto> showSeats = showSeatService.findAll();

        response = new ResponseEntity<>(
                ResponseDto.builder().data(showSeats).build(),
                HttpStatus.OK);

        return response;
    }
    @GetMapping("/{showSeatId}")
    public ResponseEntity<ResponseDto> findById(@PathVariable long showSeatId){
        ResponseEntity<ResponseDto> response;
        ShowSeatDto showSeatDto = showSeatService.findById(showSeatId);

        response = new ResponseEntity<>(
                ResponseDto.builder().data(showSeatDto).build(),
                HttpStatus.OK);

        return response;
    }
}
