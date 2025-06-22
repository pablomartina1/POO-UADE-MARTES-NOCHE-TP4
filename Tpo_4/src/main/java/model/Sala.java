package model;

public class Sala {

    private int salaID;
    private String denominacion;
    private int asientos;
    private int sucursalID; // se puede agregar si querés vincularlo con una sucursal

    public Sala(int salaID, String denominacion, int asientos) {
        this.salaID = salaID;
        this.denominacion = denominacion;
        this.asientos = asientos;
    }

    public Sala() {
        // Constructor vacío para cuando se usa setSalaID desde el controlador
    }

    public int getSalaID() {
        return salaID;
    }

    public void setSalaID(int salaID) {
        this.salaID = salaID;
    }

    public String getDenominacion() {
        return denominacion;
    }

    public void setDenominacion(String denominacion) {
        this.denominacion = denominacion;
    }

    public int getCapacidad() {
        return asientos;
    }

    public void setAsientos(int asientos) {
        this.asientos = asientos;
    }

    public int getSucursalID() {
        return sucursalID;
    }

    public void setSucursalID(int sucursalID) {
        this.sucursalID = sucursalID;
    }
}
