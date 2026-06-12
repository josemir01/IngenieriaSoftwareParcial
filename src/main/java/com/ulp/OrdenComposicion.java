
package com.ulp;

import java.time.LocalDate;

public class OrdenComposicion {

    private int nroOrden;
    private LocalDate fechaProgramada;
    private LocalDate fechaEfectiva;
    private String detalle;

    private Denuncia denuncia;
    private EquipoControl equipoControl;
    private boolean finalizada;

    public OrdenComposicion(int nroOrden, LocalDate fechaProgramada,
                            String detalle, Denuncia denuncia) {

        this.nroOrden = nroOrden;
        this.fechaProgramada = fechaProgramada;
        this.detalle = detalle;
        this.denuncia = denuncia;
        this.finalizada = false;

        denuncia.asignarOrden(this);
    }

    public void asignarEquipo(EquipoControl equipoControl) {
        this.equipoControl = equipoControl;
    }

    public void finalizarReparacion(LocalDate fechaEfectiva) {
        this.fechaEfectiva = fechaEfectiva;
        this.finalizada = true;

        denuncia.getSemaforo().setEstado("Operativo");

        if (equipoControl != null) {
            equipoControl.liberarMiembros();
        }
    }

    public boolean estaFinalizada() {
        return finalizada;
    }

    public EquipoControl getEquipoControl() {
        return equipoControl;
    }
}
