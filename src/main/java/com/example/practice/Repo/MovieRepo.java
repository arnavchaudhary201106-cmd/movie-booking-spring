package com.example.practice.Repo;

import com.example.practice.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface MovieRepo extends JpaRepository<Movie,Long> {
}
