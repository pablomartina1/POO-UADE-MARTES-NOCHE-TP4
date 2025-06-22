package model;

import java.util.*;

public class CondicionesDescuento {

    private final Date fchDesde;
    private final Date fchHasta;
    private final int diaSemana;
    private float porcentaje;
    private final TipoTarjeta tipoTarjeta;
    private final List<TarjetaDescuento> tarjetaDescuento;

    public CondicionesDescuento(Date fchDesde, Date fchHasta, int diaSemana, float porcentaje,
                                TipoTarjeta tipoTarjeta, ArrayList<TarjetaDescuento> tarjetaDescuento) {
        this.fchDesde = fchDesde;
        this.fchHasta = fchHasta;
        this.diaSemana = diaSemana;
        this.porcentaje = porcentaje;
        this.tipoTarjeta = tipoTarjeta;
        this.tarjetaDescuento = tarjetaDescuento;
    }

    // Getters y setters
    public Date getFchDesde() {
        return fchDesde;
    }

    public Date getFchHasta() {
        return fchHasta;
    }

    public int getDiaSemana() {
        return diaSemana;
    }

    public float getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(float porcentaje) {
        this.porcentaje = porcentaje;
    }

    public TipoTarjeta getTipoTarjeta() {
        return tipoTarjeta;
    }

    public List<TarjetaDescuento> getTarjetaDescuento() {
        return tarjetaDescuento;
    }

    // Suma el descuento por tipo + adicionales
    public float getDescuento() {
        float descuento = porcentaje;
        if (tarjetaDescuento != null) {
            for (TarjetaDescuento td : tarjetaDescuento) {
                descuento += getDescuentoPorTarjeta(td.getTipoTarjeta());
            }
        }
        return descuento;
    }

    // Metodo utilitario para cada tipo de tarjeta
    public static float getDescuentoPorTarjeta(TipoTarjeta tipoTarjeta) {
        return switch (tipoTarjeta) {
            case PAMI -> 0.25f;
            case UADE, MOVIECLUB -> 0.15f;
            case LANACION, CLARIN365 -> 0.5f;
            default -> 0.0f;
        };
    }
}
