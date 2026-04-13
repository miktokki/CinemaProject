package backend.cinema;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import backend.cinema.model.AppUser;
import backend.cinema.model.AppUserRepository;
import backend.cinema.model.Cinema;
import backend.cinema.model.CinemaRepository;
import backend.cinema.model.Genre;
import backend.cinema.model.GenreRepository;
import backend.cinema.model.Movie;
import backend.cinema.model.MovieRepository;

@SpringBootApplication
public class CinemaApplication {

	public static void main(String[] args) {
		SpringApplication.run(CinemaApplication.class, args);
	}

	@Bean
	public CommandLineRunner runner(MovieRepository movieRepo, GenreRepository genreRepo, CinemaRepository cinemaRepo,
			AppUserRepository appuserRepo) {
		return (args) -> {
			if (genreRepo.count() == 0) {
				Genre action = new Genre("Action");
				Genre drama = new Genre("Drama");
				Genre scifi = new Genre("Sci-Fi");
				Genre horror = new Genre("Horror");
				Genre comedy = new Genre("Comedy");

				genreRepo.save(action);
				genreRepo.save(drama);
				genreRepo.save(scifi);
				genreRepo.save(horror);
				genreRepo.save(comedy);

				Cinema helsinki = new Cinema("Tennispalatsi", "Helsinki");
				Cinema tampere = new Cinema("Plevna", "Tampere");
				Cinema turku = new Cinema("Kinopalatsi", "Turku");

				cinemaRepo.save(helsinki);
				cinemaRepo.save(tampere);
				cinemaRepo.save(turku);

				Movie interstellar = new Movie("Interstellar", "Chistopher Nolan", 2014, 144, 14.0, scifi, helsinki, 25,
						50);
				Movie godfather = new Movie("The Godfather", "Francis Coppola", 1972, 175, 10.0, action, helsinki, 60,
						100);
				Movie shawshank = new Movie("The Shawshank Redemption", "Frank Darabont", 1994, 142, 12.0, action,
						tampere, 20, 70);
				Movie forrestgump = new Movie("Forrest Gump", "Robert Zemeckis", 1994, 142, 8.0, comedy, tampere, 15,
						40);
				Movie inception = new Movie("Inception", "Christopher Nolan", 2010, 148, 16.0, scifi, turku, 17, 60);
				Movie seven = new Movie("Seven", "David Fincher", 1995, 127, 13.0, horror, turku, 34, 60);

				movieRepo.save(interstellar);
				movieRepo.save(godfather);
				movieRepo.save(shawshank);
				movieRepo.save(forrestgump);
				movieRepo.save(inception);
				movieRepo.save(seven);

				AppUser user = new AppUser("user", "$2a$12$oZMpg6HZM7guShvIVHaAtOw9TAYcKRZiJ7L2jf.Vu2ZJx.hTlpvC2",
						"USER");
				AppUser admin = new AppUser("admin", "$2a$12$RpOmARKXwlI5M8VpBHuIGeaOCUlU9w7MqpqYW70YU7R.HTlItJ1pu",
						"ADMIN");

				appuserRepo.save(user);
				appuserRepo.save(admin);

			}
		};
	}

}
