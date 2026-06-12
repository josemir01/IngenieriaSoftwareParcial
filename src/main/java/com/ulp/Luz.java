
package com.ulp;

public class Luz {

    private int id;
    private String tipo;
    private String color;

    public Luz(int id, String tipo, String color) {
        this.id = id;
        this.tipo = tipo;
        this.color = color;
    }

    public String getColor() {
        return color;
    }
}