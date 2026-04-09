package backend.cinema.model;

import java.util.List;

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
    @Min(value = 1000)
    @Max(value = 9999)
    private int publicationYear;

    @NotNull(message = "Enter the duration")
    @Min(value = 1)
    @Max(value = 400)
    private int duration;

    @DecimalMin(value = "0.0", inclusive = false, message = "Price must be positive")
    @DecimalMax(value = "100.0", message = "Price is too high")
    private double price;

    @NotNull(message = "Select a genre")
    @ManyToOne
    private Genre genre;

    @ManyToOne
    private Cinema cinema;

    public Movie() {
    }

    public Movie(
            String title,
            String director,
            int publicationYear,
            int duration,
            double price,
            Genre genre,
            Cinema cinema) {
        this.title = title;
        this.director = director;
        this.publicationYear = publicationYear;
        this.duration = duration;
        this.price = price;
        this.genre = genre;
        this.cinema = cinema;
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

    @Override
    public String toString() {
        return "Movie [id=" + id + ", title=" + title + ", director=" + director + ", publicationYear="
                + publicationYear + ", duration=" + duration + ", price=" + price + ", genre=" + genre + ", cinema="
                + cinema + "]";
    }

}
