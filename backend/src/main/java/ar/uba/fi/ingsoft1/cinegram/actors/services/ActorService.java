package ar.uba.fi.ingsoft1.cinegram.actors.services;

import ar.uba.fi.ingsoft1.cinegram.actors.interfaces.ActorRepository;
import ar.uba.fi.ingsoft1.cinegram.movies.interfaces.MovieRepository;
import ar.uba.fi.ingsoft1.cinegram.actors.dto.ActorCreateDTO;
import ar.uba.fi.ingsoft1.cinegram.actors.dto.ActorDTO;
import ar.uba.fi.ingsoft1.todo_template.common.exception.ItemNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
public class ActorService {

    private final ActorRepository actorRepository;
    private final MovieRepository movieRepository;

    ActorService(ActorRepository actorRepository, MovieRepository movieRepository) {
        this.actorRepository = actorRepository;
        this.movieRepository = movieRepository;
    }

    public Page<ActorDTO> getActors(Pageable pageable) {
        return actorRepository.findAll(pageable).map(ActorDTO::new);
    }

    public ActorDTO getActor(long id) throws ItemNotFoundException {
        var actor = actorRepository.findById(id).orElseThrow(() -> new ItemNotFoundException("actor", id));
        return new ActorDTO(actor);
    }

    public ActorDTO createActor(ActorCreateDTO actorCreate) throws ItemNotFoundException {
        return new ActorDTO(actorRepository.save(actorCreate.asActor(movieRepository::findById)));
    }

    public Optional<ActorDTO> updateActor(long id, ActorCreateDTO actorCreate) throws ItemNotFoundException {
        if (!actorRepository.existsById(id)) {
            return Optional.empty();
        }
        var saved = actorRepository.save(actorCreate.asActor(id, movieRepository::findById));
        return Optional.of(new ActorDTO(saved));
    }

    public void deleteActor(long id) {
        actorRepository.deleteById(id);
    }
}
