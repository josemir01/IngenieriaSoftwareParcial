/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ulp;

import java.util.ArrayList;
import java.util.List;

public class EquipoControl {

    private int codigo;
    private List<Miembro> miembros;

    public EquipoControl(int codigo) {
        this.codigo = codigo;
        this.miembros = new ArrayList<>();
    }

    public void agregarMiembro(Miembro miembro) {
        if (miembros.size() < 4) {
            miembros.add(miembro);
        }
    }

    public void liberarMiembros() {
        for (Miembro m : miembros) {
            m.setLibre(true);
        }
    }

    public List<Miembro> getMiembros() {
        return miembros;
    }
}