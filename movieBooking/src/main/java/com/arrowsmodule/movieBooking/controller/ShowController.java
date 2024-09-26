package com.arrowsmodule.movieBooking.controller;

import com.arrowsmodule.movieBooking.dto.ResponseDto;
import com.arrowsmodule.movieBooking.dto.ShowDto;
import com.arrowsmodule.movieBooking.service.ShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/show")
public class ShowController {
    @Autowired
    private ShowService showService;

    @PostMapping
    public ResponseEntity<ResponseDto> post(@RequestBody
                                            ShowDto showDto){
        ResponseEntity<ResponseDto> response;
        String res = showService.add(showDto);

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
        List<ShowDto> showDtos = showService.findAll();

        response = new ResponseEntity<>(
                ResponseDto.builder().data(showDtos).build(),
                HttpStatus.OK);

        return response;
    }
    @GetMapping("/{showId}")
    public ResponseEntity<ResponseDto> findById(@PathVariable long showId){
        ResponseEntity<ResponseDto> response;
        ShowDto showDto = showService.findById(showId);

        response = new ResponseEntity<>(
                ResponseDto.builder().data(showDto).build(),
                HttpStatus.OK);

        return response;
    }
}
