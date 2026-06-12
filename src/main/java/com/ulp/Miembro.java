/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ulp;

public class Miembro {

    private int id;
    private String nombre;
    private String puesto;
    private String especialidad;
    private boolean libre;

    public Miembro(int id, String nombre, String puesto,
                   String especialidad, boolean libre) {

        this.id = id;
        this.nombre = nombre;
        this.puesto = puesto;
        this.especialidad = especialidad;
        this.libre = libre;
    }

    public boolean isLibre() {
        return libre;
    }

    public void setLibre(boolean libre) {
        this.libre = libre;
    }
}