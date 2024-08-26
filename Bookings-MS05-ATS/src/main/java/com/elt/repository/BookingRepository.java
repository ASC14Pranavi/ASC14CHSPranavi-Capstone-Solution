package com.elt.repository;

import com.elt.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking,String> {
    Booking findBySeatNumber(int seatNumber);
}

