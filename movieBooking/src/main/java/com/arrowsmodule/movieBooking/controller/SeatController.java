package com.arrowsmodule.movieBooking.controller;

import com.arrowsmodule.movieBooking.dto.ResponseDto;
import com.arrowsmodule.movieBooking.dto.SeatDto;
import com.arrowsmodule.movieBooking.service.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/seat")
public class SeatController {
    @Autowired
    private SeatService seatService;

    @PostMapping
    public ResponseEntity<ResponseDto> post(@RequestBody
                                            SeatDto seatDto){
        ResponseEntity<ResponseDto> response;
        String res = seatService.add(seatDto);

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
        List<SeatDto> seatDtos = seatService.findAll();

        response = new ResponseEntity<>(
                ResponseDto.builder().data(seatDtos).build(),
                HttpStatus.OK);

        return response;
    }
    @GetMapping("/{seatId}")
    public ResponseEntity<ResponseDto> findById(@PathVariable long seatId){
        ResponseEntity<ResponseDto> response;
        SeatDto seatDto = seatService.findById(seatId);

        response = new ResponseEntity<>(
                ResponseDto.builder().data(seatDto).build(),
                HttpStatus.OK);

        return response;
    }
}
