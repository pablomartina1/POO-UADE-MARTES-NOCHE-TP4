package model;

import java.util.*;

public class Sucursal {

    private int sucursalID;
    private String denominacion;
    private String direccion;
    private List<Sala> salas;

    public Sucursal(int sucursalID, String denominacion, String direccion, ArrayList<Sala> salas) {
        this.sucursalID = sucursalID;
        this.denominacion = denominacion;
        this.direccion = direccion;
        this.salas = salas;
    }

    public Sucursal() {
        // Constructor vacío para poder instanciar y luego setear
    }

    public int getSucursalID() {
        return sucursalID;
    }

    public void setSucursalID(int sucursalID) {
        this.sucursalID = sucursalID;
    }

    public String getDenominacion() {
        return denominacion;
    }

    public void setDenominacion(String denominacion) {
        this.denominacion = denominacion;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public List<Sala> getSalas() {
        return salas;
    }

    public void setSalas(List<Sala> salas) {
        this.salas = salas;
    }
}
