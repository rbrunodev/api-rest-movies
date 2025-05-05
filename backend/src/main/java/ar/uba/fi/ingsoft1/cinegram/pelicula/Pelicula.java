package ar.uba.fi.ingsoft1.cinegram.pelicula;



import ar.uba.fi.ingsoft1.cinegram.pelicula.Categoria;
import jakarta.persistence.*;

import javax.management.AttributeList;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Pelicula {

//    @Id
//    @GeneratedValue
//    private Long idPelicula;
//
//    @Column(nullable = false)
//    private String tituloPelicula;
//
//
//    @ManyToMany
//    @JoinTable(
//            name = "categoria",
//            joinColumns = @JoinColumn(name = "idCategoria"),
//            inverseJoinColumns = @JoinColumn(name = "idPelicula"))
//    private List<Categoria> categorias;
//
//    @ManyToMany
//    @JoinTable(
//            name = "actor",
//            joinColumns = @JoinColumn(name = "idActor"),
//            inverseJoinColumns = @JoinColumn(name = "idPelicula"))
//    private List<Actor> actores;
//
//    @Column
//    private List<Integer> puntaje;
//
//
//    public Pelicula(Long idPelicula, String tituloPelicula, String categoria) {
//
//        this.idPelicula = idPelicula;
//        this.tituloPelicula = tituloPelicula;
//        categorias = new ArrayList<>();
//        puntaje = new ArrayList<>();
//    }
//
//    public Pelicula() {
//
//    }
//    public void agregarActor(Actor actor) {
//        actores.add(actor);
//        actor.getPeliculasQueActuan().add(this);
//    }
//    public void agregarCategoria(Categoria categoria) {
//        categorias.add(categoria);
//        categoria.getPeliculas().add(this);
//    }
//    public void puntuar(int unPuntaje){
//        puntaje.add(unPuntaje);
//    }
//    public Integer obtenerPromedio(){
//        return puntaje.stream()
//                .mapToInt(Integer::intValue)
//                .average()
//                .orElse(0);
//    }
//
//    public Long getIdPelicula(){
//        return idPelicula;
//    }
//
//    public String getTituloPelicula(){
//        return tituloPelicula;
//
//    }
//
//    public List<Actor> getActores() {
//        return actores;
//    }
//    public List<Categoria> getCategorias(){
//        return categorias;
//    };

}