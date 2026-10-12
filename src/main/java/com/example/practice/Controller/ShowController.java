package com.example.practice.Controller;

import com.example.practice.Show;
import com.example.practice.service.ShowService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/shows")
public class ShowController {
    private final ShowService showService;
    public ShowController(ShowService showService) {
        this.showService = showService;
    }
    @PostMapping
    public Show createShow(@RequestParam Long movieId, @RequestBody Show show) {
        return showService.createShow(movieId, show);
    }
    @GetMapping
    public List<Show> getShows() {
        return showService.getAllShows();
    }
    @GetMapping("/{id}")
    public Show getShow(@PathVariable Long id){
        return showService.getShowById(id);
    }

}
