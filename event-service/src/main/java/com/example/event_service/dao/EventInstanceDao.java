package com.example.event_service.dao;

import com.example.event_service.model.EventInstance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventInstanceDao extends JpaRepository<EventInstance,Long> {
}
