package com.example.event_service.dao;

import com.example.event_service.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EventDao extends JpaRepository<Event,Long> {
    Optional<List<Event>> findByTitle(String title);
}
