package com.example.event_service.dao;

import com.example.event_service.model.InstanceDefaultPrice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.NativeQuery;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InstanceDefaultPriceDao extends JpaRepository<InstanceDefaultPrice,Integer> {

    @NativeQuery(
            value = "SELECT * FROM t_instance_default_price WHERE eventInstance_id = ?1")
    Optional<List<InstanceDefaultPrice>> findByInstanceId(Long instanceId);
}
