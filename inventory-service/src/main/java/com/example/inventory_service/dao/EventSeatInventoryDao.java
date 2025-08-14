package com.example.inventory_service.dao;

import com.example.inventory_service.model.EventSeatInventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EventSeatInventoryDao extends JpaRepository<EventSeatInventory, Long> {
    // Additional query methods can be defined here if needed
    List<EventSeatInventory> findByEventInstanceId(Long eventInstanceId);
    List<EventSeatInventory> findByEventId(Long eventId);
    List<EventSeatInventory> findBySeatIds(List<Long> seatId);
}
