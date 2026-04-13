package backend.cinema.model;

import org.springframework.data.repository.CrudRepository;
import java.util.List;
import java.util.Optional;

public interface MovieRepository extends CrudRepository<Movie, Long> {
    // List<Movie> findByTitle(String title);
    Optional<Movie> findById(Long id);

    List<Movie> findByCinema_CityIgnoreCase(String city);

}
