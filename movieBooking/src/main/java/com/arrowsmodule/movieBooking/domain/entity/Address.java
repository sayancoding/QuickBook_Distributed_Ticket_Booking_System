package com.arrowsmodule.movieBooking.domain.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "t_address")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long addressId;

    private String streetNo;
    private String city;
    private String state;
    private String zipcode;
    private String country;
    private String landmark;
}
