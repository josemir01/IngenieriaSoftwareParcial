
package com.ulp;


public class Persona {

    private String dni;
    private String nombre;
    private String mail;

    public Persona(String dni, String nombre, String mail) {
        this.dni = dni;
        this.nombre = nombre;
        this.mail = mail;
    }

    public String getDni() {
        return dni;
    }
}
