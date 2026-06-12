package com.ulp;
import com.ulp.Semaforo;
import com.ulp.OrdenComposicion;


public class Denuncia {

    private int codigo;
    private Persona denunciante;
    private Semaforo semaforo;
    private String problema;
    private String prioridad;

    private OrdenComposicion ordenAsignada;

    public Denuncia(int codigo, Persona denunciante, Semaforo semaforo,String problema, String prioridad) {

        if (!esPrioridadValida(prioridad)) {
            throw new IllegalArgumentException("Prioridad inválida");
        }

        this.codigo = codigo;
        this.denunciante = denunciante;
        this.semaforo = semaforo;
        this.problema = problema;
        this.prioridad = prioridad;
        this.ordenAsignada = null;
    }

    public void asignarOrden(OrdenComposicion orden) {
        if (this.ordenAsignada != null) {
            throw new OrdenYaAsignadaException("La denuncia ya tiene una orden asignada");
        }

    }

    public static boolean esPrioridadValida(String prioridad) {
        return prioridad.equalsIgnoreCase("Alta")
                || prioridad.equalsIgnoreCase("Media")
                || prioridad.equalsIgnoreCase("Baja");
    }

    public OrdenComposicion getOrdenAsignada() {
        return ordenAsignada;
    }

    public Semaforo getSemaforo() {
        return semaforo;
    }
}