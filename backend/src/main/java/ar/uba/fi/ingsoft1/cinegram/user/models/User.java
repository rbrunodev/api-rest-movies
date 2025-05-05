package ar.uba.fi.ingsoft1.cinegram.user.models;

import ar.uba.fi.ingsoft1.cinegram.pelicula.Pelicula;
import ar.uba.fi.ingsoft1.cinegram.user.interfaces.CredencialesDeUsuario;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

public class User implements CredencialesDeUsuario {
    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String lastName;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String birthdate;
    @Column(nullable = false)
    private String gender;

    @Column(nullable = false)
    private String avatar;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String role;

    public User() {}

    public User(String email, String password, String name, String lastName, String birthdate, String gender, String avatar) {
        this.email = email;
        this.lastName = lastName;
        this.birthdate = birthdate;
        this.gender = gender;
        this.avatar = avatar;
        this.name = name;
        this.password = password;
        this.role = "USER";
    }

    public void puntuarPelicula(Pelicula unaPelicula, int unPuntaje) {
        unaPelicula.puntuar(unPuntaje);
    }


    @Override
    public String name() {
        return this.name;
    }

    @Override
    public String password() {
        return this.password;
    }

    public String getName() {
        return this.name;
    }

    public String getPassword() {
        return this.password;
    }

    public String getRole() {
        return role;
    }

    public String getUsername() {return email;}

}
