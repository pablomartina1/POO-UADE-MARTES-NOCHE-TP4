package model;

import java.util.*;

public class Funcion {

    private final int funcionID;
    private final String horario;
    private final Date fecha;
    private final List<Entrada> entradas;
    private final Sala sala;
    private final Pelicula pelicula;

    public Funcion(Date fecha, int funcionID, String horario, List<Entrada> entradas, Sala sala, Pelicula pelicula) {
        this.fecha = fecha;
        this.funcionID = funcionID;
        this.horario = horario;
        this.entradas = entradas;
        this.sala = sala;
        this.pelicula = pelicula;
    }

    public Pelicula getPelicula() {
        return pelicula;
    }

    public int getFuncionID() {
        return funcionID;
    }

    public String getHorario() {
        return horario;
    }

    public Date getFecha() {
        return fecha;
    }

    public List<Entrada> getEntradas() {
        return entradas;
    }

    public Sala getSala() {
        return sala;
    }

    public int getSalaID() {
        return sala != null ? sala.getSalaID() : -1;
    }

    public int getSucursalID() {
        return sala != null ? sala.getSucursalID() : -1;
    }

    public int getPeliculaID() {
        return pelicula != null ? pelicula.getPeliculaID() : -1;
    }

    public int getCantidadAsientosDisponibles() {
        if (sala == null) return 0;
        int total = sala.getCapacidad();
        int vendidos = entradas != null ? entradas.size() : 0;
        return total - vendidos;
    }

    public float calcularMontoPorEntradaDeLaPelicula() {
        float total = 0.0f;
        float descuento = 0f;
        if (pelicula.getCondicionesDescuento() != null) {
            descuento = pelicula.getCondicionesDescuento().getDescuento();
        }
        for (Entrada entrada : entradas) {
            total += entrada.getPrecio() * (1 - descuento);
        }
        return total;
    }
}
