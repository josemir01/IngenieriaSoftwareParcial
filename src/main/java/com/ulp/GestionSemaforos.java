/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ulp;

import java.util.ArrayList;
import java.util.List;

public class GestionSemaforos {
    
    private List<Semaforo> semaforos;
    private List<Denuncia> denuncias;
    private List<OrdenComposicion> ordenes;
    private List<EquipoControl> equipos;

    public GestionSemaforos() {
        this.semaforos = new ArrayList<>();
        this.denuncias = new ArrayList<>();
        this.ordenes = new ArrayList<>();
        this.equipos = new ArrayList<>();
    }

    public void registrarSemaforo(Semaforo semaforo) {
        semaforos.add(semaforo);
    }

    public Denuncia generarDenuncia(Persona denunciante, Semaforo semaforo, String problema, String prioridad) {
        Denuncia denuncia = new Denuncia(denuncias.size() + 1,denunciante,semaforo,problema,prioridad);
        denuncias.add(denuncia);
        semaforo.agregarDenuncia(denuncia);
        return denuncia;
    }

    public void registrarOrden(OrdenComposicion orden) {
        ordenes.add(orden);
    }

    public void registrarEquipo(EquipoControl equipo) {
        equipos.add(equipo);
    }

    public List<Denuncia> denunciasSinComponer() {
        List<Denuncia> resultado = new ArrayList<>();
        for (Denuncia aux : denuncias) {
            if (aux.getOrdenAsignada() == null) {
                resultado.add(aux);
            }
        }
        return resultado;
    }

    public List<Semaforo> semaforosDescompuestos() {
        List<Semaforo> resultado = new ArrayList<>();

        for (Semaforo aux : semaforos) {
            if (aux.getEstado().equalsIgnoreCase("Descompuesto")) {
                resultado.add(aux);
            }
        }

        return resultado;
    }

    public List<OrdenComposicion> ordenesPorEquipo(EquipoControl equipo) {
        List<OrdenComposicion> resultado = new ArrayList<>();
        for (OrdenComposicion aux : ordenes) {
            if (aux.getEquipoControl() == equipo) {
                resultado.add(aux);
            }
        }

        return resultado;
    }

    public int cantidadDenunciasPorSemaforo(Semaforo semaforo) {
        return semaforo.getHistoricoDenuncias().size();
    }
}
