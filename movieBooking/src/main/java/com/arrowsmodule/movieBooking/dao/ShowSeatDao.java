package com.arrowsmodule.movieBooking.dao;

import com.arrowsmodule.movieBooking.domain.entity.ShowSeat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShowSeatDao extends JpaRepository<ShowSeat,Long> {
}
