package ar.uba.fi.ingsoft1.cinegram.movies.services;

import ar.uba.fi.ingsoft1.cinegram.movies.interfaces.MovieRepository;
import ar.uba.fi.ingsoft1.cinegram.movies.dto.MovieCreateDTO;
import ar.uba.fi.ingsoft1.cinegram.movies.dto.MovieDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class MovieService {

    private final MovieRepository movieRepository;

    @Autowired
    MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public Page<MovieDTO> getMovies(Pageable pageable) {
        return movieRepository.findAll(pageable).map(MovieDTO::new);
    }

    public Optional<MovieDTO> getMovie(long id) {
        return movieRepository.findById(id).map(MovieDTO::new);
    }

    public MovieDTO createMovie(MovieCreateDTO movieCreate) {
        return new MovieDTO(movieRepository.save(movieCreate.asMovie()));
    }

    public Optional<MovieDTO> updateMovie(long id, MovieCreateDTO movieCreate) {
        if (!movieRepository.existsById(id)) {
            return Optional.empty();
        }
        var saved = movieRepository.save(movieCreate.asMovie(id));
        return Optional.of(new MovieDTO(saved));
    }

    public void deleteMovie(long id) {
        movieRepository.deleteById(id);
    }
}
