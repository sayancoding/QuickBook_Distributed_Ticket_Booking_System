package com.example.booking_service.enums;

public enum SeatStatus {
    AVAILABLE("Available"),
    BOOKED("Booked"),
    LOCKED("Locked"),
    UNAVAILABLE("Unavailable");

    private final String status;

    SeatStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
