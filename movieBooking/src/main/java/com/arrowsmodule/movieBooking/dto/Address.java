package com.arrowsmodule.movieBooking.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Address {
    private long addressId;
    private String streetNo;
    private String city;
    private String state;
    private String zipcode;
    private String country;
    private String landmark;
}
