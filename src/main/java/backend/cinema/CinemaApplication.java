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
				Movie darkknight = new Movie("The Dark Knight", "Christopher Nolan", 2008, 152, 15.0, action, helsinki,
						40, 80);
				Movie pulpfiction = new Movie("Pulp Fiction", "Quentin Tarantino", 1994, 154, 11.0, action, helsinki,
						35, 70);
				Movie matrix = new Movie("The Matrix", "Wachowski Sisters", 1999, 136, 13.0, scifi, tampere, 22, 60);
				Movie gladiator = new Movie("Gladiator", "Ridley Scott", 2000, 155, 12.0, action, tampere, 30, 75);
				Movie titanic = new Movie("Titanic", "James Cameron", 1997, 195, 9.0, drama, turku, 50, 100);
				Movie avatar = new Movie("Avatar", "James Cameron", 2009, 162, 14.0, scifi, turku, 28, 80);
				Movie joker = new Movie("Joker", "Todd Phillips", 2019, 122, 13.0, drama, helsinki, 18, 50);
				Movie parasite = new Movie("Parasite", "Bong Joon-ho", 2019, 132, 12.0, drama, tampere, 12, 40);
				Movie whiplash = new Movie("Whiplash", "Damien Chazelle", 2014, 107, 10.0, drama, turku, 10, 35);
				Movie avengers = new Movie("Avengers: Endgame", "Russo Brothers", 2019, 181, 16.0, action, helsinki, 45,
						120);

				movieRepo.save(interstellar);
				movieRepo.save(godfather);
				movieRepo.save(shawshank);
				movieRepo.save(forrestgump);
				movieRepo.save(inception);
				movieRepo.save(seven);
				movieRepo.save(darkknight);
				movieRepo.save(pulpfiction);
				movieRepo.save(matrix);
				movieRepo.save(gladiator);
				movieRepo.save(titanic);
				movieRepo.save(avatar);
				movieRepo.save(joker);
				movieRepo.save(parasite);
				movieRepo.save(whiplash);
				movieRepo.save(avengers);

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
