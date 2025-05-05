package ar.uba.fi.ingsoft1.cinegram.movies.interfaces;

import ar.uba.fi.ingsoft1.cinegram.movies.models.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, Long> {
}
