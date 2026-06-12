package com.ulp;

import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

public class GestionSemaforoTest {

    private GestionSemaforos reporte;
    private Semaforo semaforo;
    private Persona persona;
    
    
    //punto 1
    @BeforeEach
    public void setUp() {
        reporte = new GestionSemaforos();

        semaforo = new Semaforo("0007","Descompuesto","Avenida Illia","Led","Empresa A","Vehicular","Negro");

        persona = new Persona("45122230","Josemir Zaleh","josezaleh@gmail.com");
    }

    

    @AfterEach
    public void tearDown() {
        System.out.println("Prueba finalizada correctamente");
    }

    @Test
    public void testCrearSemaforoConTresLuces() {
        reporte.registrarSemaforo(semaforo);

        assertEquals(3, semaforo.getLuces().size());

        Luz PosicionCero = semaforo.getLuces().get(0);

        assertSame(PosicionCero, semaforo.getLuces().get(0));
    }

    
    //punto 2
    @Test
    public void testNoPermitirLucesDuplicadas() {
        Luz luz = semaforo.getLuces().get(0);

        semaforo.agregarLuz(luz);

        assertEquals(3, semaforo.getLuces().size());

        assertSame(luz, semaforo.getLuces().get(0));
    }
    
    //Punto 3
    @Test
    @Timeout(value = 400, unit = TimeUnit.MILLISECONDS)
    public void testDenunciaConOrdenYaAsignada() {
        Denuncia denuncia = reporte.generarDenuncia(
                persona,
                semaforo,
                "No funciona la luz roja",
                "Alta"
        );

        OrdenComposicion orden1 = new OrdenComposicion(
                1,
                LocalDate.now(),
                "Reparar luz roja",
                denuncia
        );

        assertNotNull(denuncia.getOrdenAsignada());

        assertThrows(OrdenYaAsignadaException.class, () -> {
            OrdenComposicion orden2 = new OrdenComposicion(
                    2,
                    LocalDate.now(),
                    "Nueva orden",
                    denuncia
            );
        });
    }

    
    //Punto 4
    @Test
    public void testFinalizarReparacion() {
        Denuncia denuncia = reporte.generarDenuncia(
                persona,
                semaforo,
                "Semaforo apagado",
                "Alta"
        );

        OrdenComposicion orden = new OrdenComposicion(
                1,
                LocalDate.now(),
                "Reparacion general",
                denuncia
        );

        Miembro m1 = new Miembro(1, "Carlos", "Tecnico", "Electricidad", false);
        Miembro m2 = new Miembro(2, "Luis", "Ayudante", "Cableado", false);
        Miembro m3 = new Miembro(3, "Ana", "Tecnica", "Luces", false);
        Miembro m4 = new Miembro(4, "Pedro", "Responsable", "Control", false);

        EquipoControl equipo = new EquipoControl(1);
        equipo.agregarMiembro(m1);
        equipo.agregarMiembro(m2);
        equipo.agregarMiembro(m3);
        equipo.agregarMiembro(m4);

        orden.asignarEquipo(equipo);
        orden.finalizarReparacion(LocalDate.now());

        assertTrue(orden.estaFinalizada());
        assertEquals("Operativo", semaforo.getEstado());

        assertEquals("libre", equipo.getEstado());

        for (Miembro miembro : equipo.getMiembros()) {
            assertTrue(miembro.isLibre());
        }
    }

    
    //Punto 5
    @ParameterizedTest
    @ValueSource(strings = {"Alta", "Media", "Baja"})
    public void testPrioridadValida(String prioridad) {
        Denuncia denuncia = new Denuncia(1,persona,semaforo,"Problema del semaforo",prioridad);
        assertTrue(denuncia.esPrioridadValida(prioridad));
    }

    
    //punto 6
    @Test
    public void testHistorialDenunciasDeSemaforo() {
        reporte.registrarSemaforo(semaforo);

        reporte.generarDenuncia(persona, semaforo, "No funciona", "Alta");
        reporte.generarDenuncia(persona, semaforo, "Luz amarilla rota", "Media");
        reporte.generarDenuncia(persona, semaforo, "No cambia a verde", "Baja");

        List<Denuncia> historial = semaforo.getHistoricoDenuncias();

        assertEquals(3, historial.size());
        assertEquals(3, reporte.cantidadDenunciasPorSemaforo(semaforo));
    }
}