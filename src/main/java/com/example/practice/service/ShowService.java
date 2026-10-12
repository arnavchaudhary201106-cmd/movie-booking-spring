package com.example.practice.service;

import com.example.practice.Movie;
import com.example.practice.Repo.BookingRepo;
import com.example.practice.Repo.MovieRepo;
import com.example.practice.Repo.ShowRepo;
import com.example.practice.Show;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ShowService {
    private final ShowRepo showRepo;
    private final MovieRepo movieRepo;
    public ShowService(ShowRepo showRepo, MovieRepo movieRepo) {
        this.showRepo = showRepo;
        this.movieRepo = movieRepo;
    }
    public Show createShow(Long movieId,Show show) {
        Movie movie = movieRepo.findById(movieId).orElseThrow(()-> new RuntimeException("movie not found"));
        show.setMovie(movie);
        return showRepo.save(show);
    }
    public List getAllShows() {
        return showRepo.findAll();
    }
    public Show getShowById(Long showId) {
        return showRepo.findById(showId).orElseThrow(()-> new RuntimeException("show not found"));
    }
}
