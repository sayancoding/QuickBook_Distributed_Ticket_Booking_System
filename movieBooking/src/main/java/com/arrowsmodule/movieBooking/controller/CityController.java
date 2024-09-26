//package com.arrowsmodule.movieBooking.controller;
//
//import com.arrowsmodule.movieBooking.dto.ResponseDto;
//import com.arrowsmodule.movieBooking.service.CityService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/api/city")
//public class CityController {
//    @Autowired
//    private CityService cityService;
//    @PostMapping
//    public ResponseEntity<ResponseDto> post(@RequestBody
//                                            CityDto cityDto){
//        ResponseEntity<ResponseDto> response;
//        String res = cityService.add(cityDto);
//
//        response = new ResponseEntity<>(
//                ResponseDto.builder().data(res).build(),
//                HttpStatus.CREATED);
//
//        return response;
//    }
//
//    @GetMapping
//    public ResponseEntity<ResponseDto> findAll(
//            @RequestParam(name = "page",defaultValue = "0",required = false) Integer page,
//            @RequestParam(name = "pageSize",defaultValue = "50",required = false) Integer pageSize
//    ){
//        ResponseEntity<ResponseDto> response;
//        List<CityDto> cityDtos = cityService.findAll();
//
//        response = new ResponseEntity<>(
//                ResponseDto.builder().data(cityDtos).build(),
//                HttpStatus.OK);
//
//        return response;
//    }
//    @GetMapping("/{cityId}")
//    public ResponseEntity<ResponseDto> findById(@PathVariable long cityId){
//        ResponseEntity<ResponseDto> response;
//        CityDto cityDto = cityService.findById(cityId);
//
//        response = new ResponseEntity<>(
//                ResponseDto.builder().data(cityDto).build(),
//                HttpStatus.OK);
//
//        return response;
//    }
//}
