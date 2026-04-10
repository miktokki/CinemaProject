package backend.cinema.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import backend.cinema.model.Cinema;
import backend.cinema.model.CinemaRepository;
import backend.cinema.model.MovieRepository;

@Controller

public class CinemaController {

    private final MovieRepository movieRepo;
    private final CinemaRepository cinemaRepo;

    public CinemaController(MovieRepository movieRepo, CinemaRepository cinemaRepo) {
        this.movieRepo = movieRepo;
        this.cinemaRepo = cinemaRepo;
    }

    @GetMapping("/")
    public String getHomePage() {
        return "index";
    }

    @GetMapping("/cinema/{city}")
    public String getCity(@PathVariable String city, Model model) {
        // model.addAttribute("city", city.toUpperCase());
        model.addAttribute("cinemas", cinemaRepo.findByCityIgnoreCase(city).get(0));
        model.addAttribute("movies", movieRepo.findByCinema_CityIgnoreCase(city));
        return "cinema";
    }

}
