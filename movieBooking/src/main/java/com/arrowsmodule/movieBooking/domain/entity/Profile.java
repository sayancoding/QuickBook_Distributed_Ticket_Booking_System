package com.arrowsmodule.movieBooking.domain.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "t_profile")
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long profileId;

    private String firstName;
    private String lastName;
    private LocalDate dob;
    private String contactNo;
    private String alterContactNo;
    private String emailId;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "address_id",referencedColumnName = "addressId")
    private Address address;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id",referencedColumnName = "userId")
    private User user;
}
