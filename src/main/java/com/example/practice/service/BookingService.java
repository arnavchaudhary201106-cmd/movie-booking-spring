package com.example.practice.service;

import com.example.practice.Booking;

import com.example.practice.Repo.BookingRepo;

import com.example.practice.Repo.ShowRepo;
import com.example.practice.Repo.UserRepository;
import com.example.practice.Show;
import com.example.practice.User;
import com.example.practice.dto.BookingResponse;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingService {
    private final BookingRepo bookingRepo;
    private final UserRepository userRepo;
    private final ShowRepo showRepo;
    public BookingService(BookingRepo bookingRepo, ShowRepo showRepo, UserRepository userRepo) {
        this.bookingRepo = bookingRepo;
        this.showRepo = showRepo;
        this.userRepo = userRepo;
    }
    @Transactional
    public BookingResponse CreateBooking(Long showId) {
        Show show = showRepo.findById(showId)
                .orElseThrow(() ->
                        new RuntimeException("Show not found"));

        String username = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();

        User user = userRepo.findByUsername(username)
                .orElseThrow(() ->
                        new RuntimeException("User not found"));

        Booking booking = new Booking();
        if(show.getAvailableSeats()<=0){
            throw new RuntimeException("No available seats");
        }
        show.setAvailableSeats(show.getAvailableSeats()-1);
        showRepo.save(show);
        booking.setShow(show);
        booking.setUser(user);

        Booking savedBooking = bookingRepo.save(booking);

        BookingResponse response = new BookingResponse();
        response.setBookingId(savedBooking.getId());
        response.setUsername(user.getUsername());
        response.setShow(show);



        return response;
    }
    public List<BookingResponse> getBookingsByUser(){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepo.findByUsername(username).orElseThrow(()-> new RuntimeException("User not found"));
      List<Booking> bookings = bookingRepo.findByUser(user);
    return bookings.stream()
            .map(booking -> {
                BookingResponse response = new BookingResponse();
                response.setBookingId(booking.getId());
                response.setUsername(user.getUsername());
                response.setShow(booking.getShow());
                return response;
            })
            .toList();
    }
    @Transactional
    public void cancelBooking(Long bookingId){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        Booking booking  = bookingRepo.findById(bookingId).orElseThrow(()-> new RuntimeException("Booking not found"));
        if(!booking.getUser().getUsername().equals(username)){
            throw new RuntimeException("cannot cancel this");
        }
        Show show = booking.getShow();
        show.setAvailableSeats(show.getAvailableSeats() + 1);
        bookingRepo.delete(booking);

    }
}
