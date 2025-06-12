package com.example.inventory_service.enums;

public enum SeatStatus {
    AVAILABLE("Available"),
    BOOKED("Booked"),
    RESERVED("Reserved"),
    UNAVAILABLE("Unavailable");

    private final String status;

    SeatStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
