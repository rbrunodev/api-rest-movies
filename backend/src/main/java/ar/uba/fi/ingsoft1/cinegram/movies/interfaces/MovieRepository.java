package ar.uba.fi.ingsoft1.cinegram.movies.interfaces;

import ar.uba.fi.ingsoft1.cinegram.movies.models.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface MovieRepository extends JpaRepository<Movie, Long>, JpaSpecificationExecutor<Movie> {
}
