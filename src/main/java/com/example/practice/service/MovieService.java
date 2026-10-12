package com.example.practice.service;

import com.example.practice.Movie;
import com.example.practice.Repo.MovieRepo;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class MovieService {
    private final MovieRepo movieRepo;
    public MovieService(MovieRepo movieRepo) {
        this.movieRepo = movieRepo;
    }
    public Movie addMovie(Movie movie) {
        Movie savedMovie = movieRepo.save(movie);

        System.out.println("SAVED ID = " + savedMovie.getId());
        System.out.println("SAVED TITLE = " + savedMovie.getTitle());

        return savedMovie;
    }
    public Movie getMovie(Long id) {
        return movieRepo.findById(id).orElseThrow(()-> new RuntimeException("Movie not found"));
    }
    public List<Movie> getAllMovies(){
        return movieRepo.findAll();
    }
    public void DeleteMovie(Long id) {
        movieRepo.deleteById(id);
    }

    public Movie updateMovie(Long id, Movie movie) {
        Movie existingMovie = movieRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Movie not found"));

        existingMovie.setTitle(movie.getTitle());
        existingMovie.setDescription(movie.getDescription());
        existingMovie.setPrice(movie.getPrice());
        existingMovie.setGenre(movie.getGenre());
        existingMovie.setDuration(movie.getDuration());
        existingMovie.setLanguage(movie.getLanguage());

        return movieRepo.save(existingMovie);
    }
}
