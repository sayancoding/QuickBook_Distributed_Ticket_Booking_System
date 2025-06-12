package com.example.event_service.constants.enums;

public enum EventInstanceStatus {
    ACTIVE("Active"),
    INACTIVE("Inactive"),
    CANCELLED("Cancelled"),

    COMPLETED("Completed");

    private final String status;
    EventInstanceStatus(String status) {
        this.status = status;
    }
    public String getStatus() {
        return status;
    }

}
