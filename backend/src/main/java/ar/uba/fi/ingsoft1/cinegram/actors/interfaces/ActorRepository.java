package ar.uba.fi.ingsoft1.cinegram.actors.interfaces;

import ar.uba.fi.ingsoft1.cinegram.actors.models.Actor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActorRepository extends JpaRepository<Actor, Long> {
}
