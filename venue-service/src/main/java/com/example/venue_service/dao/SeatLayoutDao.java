package com.example.venue_service.dao;

import com.example.venue_service.model.VenueSeatLayout;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SeatLayoutDao extends JpaRepository<VenueSeatLayout, Long> {
    Optional<List<VenueSeatLayout>> findByVenueId(Long venueId);
}
