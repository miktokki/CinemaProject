package backend.cinema.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Write the movie title")
    @Size(min = 3, max = 100, message = "Title length must be between 3 and 100 characters")
    private String title;

    @NotBlank(message = "Write the director's name")
    @Size(min = 1, max = 100, message = "Director's name must be between 1 and 100 characters")
    private String director;

    @NotNull(message = "Enter the release year")
    @Min(value = 1900, message = "Release year between 1900 and 2030")
    @Max(value = 2030, message = "Release year between 1900 and 2030")
    private int publicationYear;

    @NotNull(message = "Enter the duration")
    @Min(value = 1)
    @Max(value = 400, message = "Duration must be less than or equal to 400")
    private int duration;

    @DecimalMin(value = "0.0", inclusive = false, message = "Price must be positive")
    @DecimalMax(value = "100.0", message = "Price is too high, max price 100 €")
    private double price;

    @NotNull(message = "Select a genre")
    @ManyToOne
    private Genre genre;

    @ManyToOne
    @JoinColumn(name = "cinema_id")
    private Cinema cinema;

    private int bookedSeats;

    @Min(value = 1, message = "Capacity must be at least 1")
    private int capacity;

    public Movie() {
    }

    public Movie(
            String title,
            String director,
            int publicationYear,
            int duration,
            double price,
            Genre genre,
            Cinema cinema,
            int bookedSeats,
            int capacity) {
        this.title = title;
        this.director = director;
        this.publicationYear = publicationYear;
        this.duration = duration;
        this.price = price;
        this.genre = genre;
        this.cinema = cinema;
        this.bookedSeats = bookedSeats;
        this.capacity = capacity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public int getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(int publicationYear) {
        this.publicationYear = publicationYear;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Cinema getCinema() {
        return cinema;
    }

    public void setCinema(Cinema cinema) {
        this.cinema = cinema;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public int getBookedSeats() {
        return bookedSeats;
    }

    public void setBookedSeats(int bookedSeats) {
        this.bookedSeats = bookedSeats;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    @Override
    public String toString() {
        return "Movie [id=" + id + ", title=" + title + ", director=" + director + ", publicationYear="
                + publicationYear + ", duration=" + duration + ", price=" + price + ", genre=" + genre + ", cinema="
                + cinema + ", bookedSeats=" + bookedSeats + ", capacity=" + capacity + "]";
    }

}
