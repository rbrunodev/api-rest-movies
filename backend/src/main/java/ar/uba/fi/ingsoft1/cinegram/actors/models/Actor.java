package ar.uba.fi.ingsoft1.cinegram.actors.models;

import ar.uba.fi.ingsoft1.cinegram.movies.models.Movie;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Actor {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column
    private String description;

    @ManyToOne
    private Movie movie;

    public Actor(Long id, String title, String description, Movie movie) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.movie = movie;
    }

    public Actor() {}

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Movie getProject() {
        return movie;
    }
}
