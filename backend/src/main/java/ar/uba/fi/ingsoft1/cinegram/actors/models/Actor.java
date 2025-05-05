package ar.uba.fi.ingsoft1.cinegram.actors.models;

import ar.uba.fi.ingsoft1.cinegram.movies.models.Movie;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Actor {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column
    private String description;

    @ManyToMany(mappedBy = "actors")
    private Set<Movie> movies = new HashSet<>();

    public Actor(Long id, String title, String description, Set<Movie> movies) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.movies = movies != null ? movies : new HashSet<>();
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

    public Set<Movie> getMovies() {
        return movies;
    }

    public void setMovies(Set<Movie> movies) {
        this.movies = movies;
    }
}
