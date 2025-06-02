package com.example.venue_service.enums;

public enum VenueStatus {
    ACTIVE("Active"),
    INACTIVE("Inactive"),
    UNDER_MAINTENANCE("Under Maintenance");

    private final String status;

    VenueStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
