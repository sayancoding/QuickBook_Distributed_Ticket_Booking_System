package com.arrowsmodule.movieBooking.dto;

import jakarta.persistence.MappedSuperclass;
import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;


@Data
@MappedSuperclass
public class Metadata {
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
}
