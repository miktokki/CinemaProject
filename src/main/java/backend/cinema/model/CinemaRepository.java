package backend.cinema.model;

import org.springframework.data.repository.CrudRepository;

public interface CinemaRepository extends CrudRepository<Cinema, Long> {
    Cinema findFirstByCityIgnoreCase(String city);
}
