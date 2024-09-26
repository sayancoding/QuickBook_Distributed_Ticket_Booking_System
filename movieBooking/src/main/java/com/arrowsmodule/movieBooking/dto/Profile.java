package com.arrowsmodule.movieBooking.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class Profile {
    private long profileId;
    private String firstName;
    private String lastName;
    private LocalDate dob;
    private String contactNo;
    private String alterContactNo;
    private String emailId;
    private Address address;
    private User user;
}
