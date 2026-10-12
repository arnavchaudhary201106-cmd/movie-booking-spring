package com.example.practice;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Getter
@Setter
@Entity
public class Show {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
   private long id;
    @ManyToOne
    private Movie movie;
    private LocalDateTime showTime;
    private int availableSeats;
    private double price;


}
