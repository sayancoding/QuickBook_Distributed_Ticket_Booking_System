package com.example.booking_service.enums;

public enum BookingStatus {
    PENDING("PENDING"),
    CONFIRMED("CONFIRMED"), // Booking has been confirmed
    CANCELLED("CANCELLED"); // Booking has been cancelled

    private final String status;
    BookingStatus(String status) {
        this.status = status;
    }
    public String getStatus() {
        return status;
    }
}
