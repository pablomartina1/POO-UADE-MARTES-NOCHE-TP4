package model;

public class Entrada {

    private int nroAsiento;
    private Funcion funcion;
    private float precio;

    public Entrada(int nroAsiento, Funcion funcion, float precio) {
        this.nroAsiento = nroAsiento;
        this.funcion = funcion;
        this.precio = precio;
    }

    public Entrada() {
        // Constructor vacío
    }

    public float getPrecio() {
        return precio;
    }

    public int getNroAsiento() {
        return nroAsiento;
    }

    public Funcion getFuncion() {
        return funcion;
    }

    public int getFuncionID() {
        return funcion != null ? funcion.getFuncionID() : -1;
    }

    public int getPeliculaID() {
        return funcion != null ? funcion.getPeliculaID() : -1;
    }
}
