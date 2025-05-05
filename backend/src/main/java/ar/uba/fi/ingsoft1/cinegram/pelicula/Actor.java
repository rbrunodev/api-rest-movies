package ar.uba.fi.ingsoft1.cinegram.pelicula;

import jakarta.persistence.*;


import java.util.ArrayList;
import java.util.List;

public class Actor {
    String name;

    @Id
    @GeneratedValue
    private Long idActor;


    @ManyToMany(mappedBy = "actores")
    List<Pelicula> peliculasQueActuan;

    public Actor(String name) {
        this.name = name;
        peliculasQueActuan = new ArrayList<Pelicula>();
    }
    public List<Pelicula> getPeliculasQueActuan() {
        return peliculasQueActuan;
    }

    public void addPelicula(Pelicula pelicula) {
        peliculasQueActuan.add(pelicula);
        pelicula.getActores().add(this);
    }

}

