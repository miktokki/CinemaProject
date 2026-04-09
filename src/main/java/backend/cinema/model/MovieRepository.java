package backend.cinema.model;

import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface MovieRepository extends CrudRepository<Movie, Long> {
    List<Movie> findByTitle(String title);

}
