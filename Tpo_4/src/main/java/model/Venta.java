package model;

import java.util.Date;
import java.util.List;

public class Venta {

    private int ventaID;
    private Date fchVenta;
    private List<Combo> combos;
    private Funcion funcion;
    private TarjetaDescuento tarjetaDescuento;

    public Venta(int ventaID, Date fchVenta, List<Combo> combos, Funcion funcion) {
        this.ventaID = ventaID;
        this.fchVenta = fchVenta;
        this.combos = combos;
        this.funcion = funcion;
        this.tarjetaDescuento = null; // se puede setear luego si aplica
    }

    // Getters y setters
    public int getVentaID() { return ventaID; }
    public void setVentaID(int ventaID) { this.ventaID = ventaID; }

    public Date getFchVenta() { return fchVenta; }
    public void setFchVenta(Date fchVenta) { this.fchVenta = fchVenta; }

    public List<Combo> getListaComboID() { return combos; }
    public void setCombos(List<Combo> combos) { this.combos = combos; }

    public Funcion getFuncion() { return funcion; }
    public void setFuncion(Funcion funcion) { this.funcion = funcion; }

    public TarjetaDescuento getTarjetaDescuento() { return tarjetaDescuento; }
    public void setTarjetaDescuento(TarjetaDescuento tarjetaDescuento) { this.tarjetaDescuento = tarjetaDescuento; }

    public int getFuncionID() {
        return funcion != null ? funcion.getFuncionID() : -1;
    }

    public int getPeliculaID() {
        return funcion != null ? funcion.getPeliculaID() : -1;
    }

    public TipoTarjeta getTipoTarjeta() {
        return tarjetaDescuento != null ? tarjetaDescuento.getTipoTarjeta() : null;
    }

    public float getTotal() {
        return calcularMontoDeLaVentaPorFuncionCombos();
    }

    public float calcularMontoPorComboDeVenta() {
        if (combos == null || combos.isEmpty()) return 0.0f;

        float descuento = 0.0f;
        if (tarjetaDescuento != null) {
            descuento = CondicionesDescuento.getDescuentoPorTarjeta(tarjetaDescuento.getTipoTarjeta());
        }

        float total = 0.0f;
        for (Combo combo : combos) {
            total += combo.getPrecio() * (1 - descuento);
        }
        return total;
    }

    public float calcularMontoDeLaVentaPorFuncionCombos() {
        float totalEntradas = funcion != null ? funcion.calcularMontoPorEntradaDeLaPelicula() : 0.0f;
        float totalCombos = calcularMontoPorComboDeVenta();
        return totalEntradas + totalCombos;
    }
}
