package com.example.practice.Controller;

import com.example.practice.dto.BookingResponse;
import com.example.practice.service.BookingService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bookings")
public class BookingController {

    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping
    public BookingResponse booking(@RequestParam Long showId) {
        return bookingService.CreateBooking(showId);
    }

    @GetMapping
    public List<BookingResponse> getBookings() {
        return bookingService.getBookingsByUser();
    }

    @DeleteMapping("/{id}")
    public void deleteBooking(@PathVariable Long id) {
        bookingService.cancelBooking(id);
    }
}