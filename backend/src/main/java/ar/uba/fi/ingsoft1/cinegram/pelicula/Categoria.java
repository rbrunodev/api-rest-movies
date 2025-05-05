package ar.uba.fi.ingsoft1.cinegram.pelicula;

import jakarta.persistence.CascadeType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

import javax.management.AttributeList;
import java.util.ArrayList;
import java.util.List;

public class Categoria {

    @Id
    @GeneratedValue
    private Long idCategoria;

    String name;

    @ManyToMany(mappedBy = "categorias")
    List<Pelicula> peliculas;


    public Categoria(String name) {
        this.name = name;
        peliculas = new ArrayList<Pelicula>();
    }

    public void agregarPelicula(Pelicula pelicula){
        peliculas.add(pelicula);
        pelicula.getCategorias().add(this);
    }
    public List<Pelicula> getPeliculas(){
        return peliculas;
    }

}
