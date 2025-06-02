package com.example.venue_service.dao;

import com.example.venue_service.model.Venue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VenueDao extends JpaRepository<Venue,Long> {
}
