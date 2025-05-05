package ar.uba.fi.ingsoft1.cinegram.movies.models;

import ar.uba.fi.ingsoft1.cinegram.actors.models.Actor;
import ar.uba.fi.ingsoft1.cinegram.categories.models.Category;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Movie {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column
    private String description;

    @ManyToMany
    @JoinTable(
            name = "movie_actor",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "actor_id")
    )
    private Set<Actor> actors = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "movie_category",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private Set<Category> categories = new HashSet<>();

    public Movie(Long id, String name, String description, Set<Category> categories) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.categories = categories;
    }

    public Movie() {}

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public void setActors(Set<Actor> actors) {
        this.actors = actors;
    }

    public void setCategories(Set<Category> categories){
        this.categories = categories;
    }

    public Set<Actor> getActors() {
        return actors;
    }

    public Set<Category> getCategories() {
        return categories;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
