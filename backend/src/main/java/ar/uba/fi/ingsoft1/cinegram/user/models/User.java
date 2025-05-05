package ar.uba.fi.ingsoft1.cinegram.user.models;

import ar.uba.fi.ingsoft1.cinegram.pelicula.Pelicula;
import ar.uba.fi.ingsoft1.cinegram.user.interfaces.CredencialesDeUsuario;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

public class User implements CredencialesDeUsuario {
//                   implements CredencialesDeUsuario,UserDetails{
    @Id
    @GeneratedValue
    private Long idUsuario;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String apellido;
    @Column(nullable = false)
    private String nombre;
    @Column(nullable = false)
    private String fechaDeNacimiento;
    @Column(nullable = false)
    private String genero;

    @Column(nullable = false)
    private String fotoDePerfil;
//Dudo que sea un string pero supongamos que es el link



    @Column(nullable = false)
    private String contrasenia;

    @Column(nullable = false)
    private String role;

    public User() {}

    public User(String email, String contrasenia, String nombre, String apellido, String fechaDeNacimiento, String genero, String fotoDePerfil) {
        this.email = email;
        this.apellido = apellido;
        this.fechaDeNacimiento = fechaDeNacimiento;
        this.genero = genero;
        this.fotoDePerfil = fotoDePerfil;
        this.nombre = nombre;
        this.contrasenia = contrasenia;
        this.role = "USER";
    }

    public void puntuarPelicula(Pelicula unaPelicula, int unPuntaje) {
        unaPelicula.puntuar(unPuntaje);
    }


    @Override
    public String nombre() {
        return this.nombre;
    }

    @Override
    public String contrasenia() {
        return this.contrasenia;
    }


    public String getNombre() {
        return this.nombre;
    }

    public String getContrasenia() {
        return this.contrasenia;
    }

    public String getRole() {
        return role;
    }

//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return List.of(new SimpleGrantedAuthority(role));
//    }
}
