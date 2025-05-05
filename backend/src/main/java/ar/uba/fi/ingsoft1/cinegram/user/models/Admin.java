package ar.uba.fi.ingsoft1.cinegram.user.models;

public class Admin extends User {
    boolean modoAdmin;
//Probablemente deba usarse un state pero jodete valen, personalmente
    public Admin(String email, String contrasenia, String nombre, String apellido, String fechaDeNacimiento, String genero, String fotoDePerfil) {
        super( email, contrasenia,nombre, apellido, fechaDeNacimiento, genero, fotoDePerfil);
        modoAdmin = false;
    }
    public void entrarComoAdmin(){
        modoAdmin = true;
    }
    public void crearAdmin(String email, String contrasenia, String nombre, String apellido, String fechaDeNacimiento, String genero, String fotoDePerfil){

    }
}
