package com.example.practice.Repo;

import com.example.practice.Booking;
import com.example.practice.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookingRepo extends JpaRepository<Booking, Long> {
List<Booking> findByUser(User user);
}
