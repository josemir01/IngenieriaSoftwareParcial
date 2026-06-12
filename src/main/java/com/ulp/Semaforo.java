package com.ulp;

import com.ulp.Denuncia
import com.ulp.Luz;
import java.util.ArrayList;
import java.util.List;


public class Semaforo {

    private String nroSerie;
    private String estado;
    private String ubicacion;
    private String tipoCorriente;
    private String empresaFabricadora;
    private String tipo;
    private String color;

    private List<Luz> luces;
    private List<Denuncia> historicoDenuncias;

    public Semaforo(String nroSerie, String estado, String ubicacion,
                    String tipoCorriente, String empresaFabricadora,
                    String tipo, String color) {

        this.nroSerie = nroSerie;
        this.estado = estado;
        this.ubicacion = ubicacion;
        this.tipoCorriente = tipoCorriente;
        this.empresaFabricadora = empresaFabricadora;
        this.tipo = tipo;
        this.color = color;

        this.luces = new ArrayList<>();
        this.historicoDenuncias = new ArrayList<>();

        crearLucesIniciales();
    }

    private void crearLucesIniciales() {
        luces.add(new Luz(1, "LED", "Rojo"));
        luces.add(new Luz(2, "LED", "Amarillo"));
        luces.add(new Luz(3, "LED", "Verde"));
    }

    public void agregarLuz(Luz luz) {
        if (!luces.contains(luz)) {
            luces.add(luz);
        }
    }

    public void agregarDenuncia(Denuncia denuncia) {
        historicoDenuncias.add(denuncia);
    }

    public List<Luz> getLuces() {
        return luces;
    }

    public List<Denuncia> getHistoricoDenuncias() {
        return historicoDenuncias;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
