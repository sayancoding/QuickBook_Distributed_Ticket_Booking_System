package com.arrowsmodule.movieBooking.dao;

import com.arrowsmodule.movieBooking.domain.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingDao extends JpaRepository<Booking,Long> {
}
