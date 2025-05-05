package ar.uba.fi.ingsoft1.cinegram.movies.services;

import ar.uba.fi.ingsoft1.cinegram.categories.models.Category;
import ar.uba.fi.ingsoft1.cinegram.movies.interfaces.MovieRepository;
import ar.uba.fi.ingsoft1.cinegram.movies.dto.MovieCreateDTO;
import ar.uba.fi.ingsoft1.cinegram.movies.dto.MovieDTO;
import ar.uba.fi.ingsoft1.cinegram.movies.models.FilterMovie;
import ar.uba.fi.ingsoft1.cinegram.movies.models.Movie;
import ar.uba.fi.ingsoft1.cinegram.actors.interfaces.ActorRepository;
import ar.uba.fi.ingsoft1.cinegram.actors.models.Actor;
import ar.uba.fi.ingsoft1.todo_template.common.exception.ItemNotFoundException;
import ar.uba.fi.ingsoft1.cinegram.categories.interfaces.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@Transactional
public class MovieService {

    private final MovieRepository movieRepository;
    private final ActorRepository actorRepository;
    private final CategoryRepository categoryRepository;


    @Autowired
    MovieService(MovieRepository movieRepository,ActorRepository actorRepository, CategoryRepository categoryRepository) {
        this.movieRepository = movieRepository;
        this.actorRepository = actorRepository;
        this.categoryRepository = categoryRepository;
    }

    public Page<MovieDTO> getMovies(Pageable pageable, String title, long actorId, long categoryId) {
        Specification<Movie> filters;
        filters = Specification.where(FilterMovie.byTitle(title))
                .and(FilterMovie.byActor(actorId))
                .and(FilterMovie.byCategory(categoryId));
        return movieRepository.findAll(filters, pageable).map(MovieDTO::new);
    }

    public Optional<MovieDTO> getMovie(long id) {
        return movieRepository.findById(id).map(MovieDTO::new);
    }

    public MovieDTO createMovie(MovieCreateDTO movieCreate) throws ItemNotFoundException {
        Movie movie = movieCreate.asMovie(categoryRepository::findById, actorRepository::findById);
        return new MovieDTO(movieRepository.save(movie));
    }

    public Optional<MovieDTO> updateMovie(long id, MovieCreateDTO movieCreate) throws ItemNotFoundException {
        if (!movieRepository.existsById(id)) {
            return Optional.empty();
        }

        Movie movie = movieRepository.findById(id).orElseThrow(() -> new ItemNotFoundException("movie", id));
        movie.setName(movieCreate.name());
        movie.setDescription(movieCreate.description());

        if (movieCreate.categoryIds() != null && !movieCreate.categoryIds().isEmpty()) {
            Set<Category> categories = new HashSet<>();
            for (Long categoryId : movieCreate.categoryIds()) {
                Category category = categoryRepository.findById(categoryId)
                        .orElseThrow(() -> new ItemNotFoundException("category", categoryId));
                categories.add(category);
            }
            movie.setCategories(categories);
        }

        if (movieCreate.actorIds() != null && !movieCreate.actorIds().isEmpty()) {
            Set<Actor> actors = new HashSet<>();
            for (Long actorId : movieCreate.actorIds()) {
                Actor actor = actorRepository.findById(actorId)
                        .orElseThrow(() -> new ItemNotFoundException("actor", actorId));
                actors.add(actor);
            }
            movie.setActors(actors);
        }

        return Optional.of(new MovieDTO(movieRepository.save(movie)));
    }

    public void deleteMovie(long id) {
        movieRepository.deleteById(id);
    }
}
