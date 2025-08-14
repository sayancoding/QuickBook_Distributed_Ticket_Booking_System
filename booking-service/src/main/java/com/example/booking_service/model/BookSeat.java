package com.example.booking_service.model;

import com.example.booking_service.enums.SeatStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "t_book_seat")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookSeat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long bookingId;
    private Long seatId;
    private Long price;
    @Enumerated(EnumType.STRING)
    private SeatStatus status;
}
