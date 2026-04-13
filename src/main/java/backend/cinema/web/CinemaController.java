package backend.cinema.web;

import backend.cinema.model.GenreRepository;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import backend.cinema.model.Cinema;
import backend.cinema.model.CinemaRepository;
import backend.cinema.model.Movie;
import backend.cinema.model.MovieRepository;
import jakarta.validation.Valid;

@Controller

public class CinemaController {

    private final GenreRepository genreRepository;
    private final MovieRepository movieRepo;
    private final CinemaRepository cinemaRepo;

    public CinemaController(MovieRepository movieRepo, CinemaRepository cinemaRepo, GenreRepository genreRepository) {
        this.movieRepo = movieRepo;
        this.cinemaRepo = cinemaRepo;
        this.genreRepository = genreRepository;
    }

    @GetMapping("/")
    public String getHomePage() {
        return "index";
    }

    @GetMapping("/cinema/{city}")
    public String getCity(@PathVariable String city, Model model) {
        Cinema cinema = cinemaRepo.findFirstByCityIgnoreCase(city);
        model.addAttribute("city", city.toUpperCase());

        model.addAttribute("cinema", cinema);
        model.addAttribute("movies", movieRepo.findByCinema_CityIgnoreCase(city));
        return "cinema";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/addmovie")
    public String addMovie(@RequestParam String city, Model model) {
        model.addAttribute("movie", new Movie());
        model.addAttribute("genres", genreRepository.findAll());
        model.addAttribute("city", city);
        return "addmovie";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/editmovie/{id}")
    public String editMovie(@PathVariable Long id, Model model) {
        Movie movie = movieRepo.findById(id).orElseThrow();
        model.addAttribute("movie", movieRepo.findById(id).orElseThrow());
        model.addAttribute("genres", genreRepository.findAll());
        model.addAttribute("city", movie.getCinema().getCity());
        return "editmovie";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/savemovie")
    public String saveMovie(@Valid @ModelAttribute Movie movie, BindingResult bindingResult, @RequestParam String city,
            Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("genres", genreRepository.findAll());
            model.addAttribute("city", city);
            if (movie.getId() == null) {
                return "addmovie";
            } else {
                return "editmovie";
            }
        }
        Cinema cinema = cinemaRepo.findFirstByCityIgnoreCase(city);
        movie.setCinema(cinema);
        movie.setBookedSeats(0);
        movieRepo.save(movie);
        return "redirect:/cinema/" + city;

    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/deletemovie/{id}")
    public String deleteMovie(@PathVariable Long id, @RequestParam String city) {
        movieRepo.deleteById(id);
        return "redirect:/cinema/" + city;
    }

    @PostMapping("/cinema/{movieId}")
    public String bookSeat(@PathVariable Long movieId) {
        Movie movie = movieRepo.findById(movieId).orElseThrow();
        if (movie.getBookedSeats() < movie.getCapacity()) {
            movie.setBookedSeats(movie.getBookedSeats() + 1);
            movieRepo.save(movie);
        }
        // movie.getBookedSeats();
        return "redirect:/cinema/" + movie.getCinema().getCity();
    }

}
