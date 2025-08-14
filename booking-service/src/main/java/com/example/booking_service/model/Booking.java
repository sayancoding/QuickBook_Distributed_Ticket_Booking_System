package com.example.booking_service.model;

import com.example.booking_service.enums.BookingStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "t_bookings")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookingId;
    private Long userId;
    private Long eventInstanceId;

    @Enumerated(EnumType.STRING)
    private BookingStatus status;

    private Long totalAmount;

    private LocalDateTime bookingTime;

    private LocalDateTime confirmedTime;
    private LocalDateTime cancelledTime;

}
