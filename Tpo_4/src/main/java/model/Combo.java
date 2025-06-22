package model;

public class Combo {

    private final int comboID;
    private final String descripcion;
    private final float precio;
    private final CondicionesDescuento contiene;

    public Combo(int comboID, String descripcion, float precio, CondicionesDescuento contiene) {
        this.comboID = comboID;
        this.descripcion = descripcion;
        this.precio = precio;
        this.contiene = contiene;
    }

    public int getComboID() {
        return comboID;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public float getPrecio() {
        return precio;
    }

    public CondicionesDescuento getCondicionesDescuento() {
        return contiene;
    }
}
