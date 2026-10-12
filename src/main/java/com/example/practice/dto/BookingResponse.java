package com.example.practice.dto;

import com.example.practice.Movie;
import com.example.practice.Show;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookingResponse {
private long bookingId;
private String Username;
private Show show;
}
