package com.example.event_service.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "t_event_instances")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventInstance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long eventInstanceId;

    private Long eventId;
    private Long venueId;
    private String language;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String status;

    @OneToMany(mappedBy = "eventInstance", cascade = CascadeType.ALL)
    private List<InstanceDefaultPrice> defaultPrices = new ArrayList<>();

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;


}
