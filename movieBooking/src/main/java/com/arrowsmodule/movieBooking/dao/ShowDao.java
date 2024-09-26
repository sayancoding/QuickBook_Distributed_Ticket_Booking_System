package com.arrowsmodule.movieBooking.dao;

import com.arrowsmodule.movieBooking.domain.entity.Show;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShowDao extends JpaRepository<Show,Long> {
}
