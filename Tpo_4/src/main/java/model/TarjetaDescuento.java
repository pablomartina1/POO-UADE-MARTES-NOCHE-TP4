package model;

public class TarjetaDescuento {

    private final int tarjetaID;
    private final TipoTarjeta tipoTarjeta;
    private final String numeroTarjeta;

    public TarjetaDescuento(int tarjetaID, TipoTarjeta tipoTarjeta, String numeroTarjeta) {
        this.tarjetaID = tarjetaID;
        this.tipoTarjeta = tipoTarjeta;
        this.numeroTarjeta = numeroTarjeta;
    }

    public int getTarjetaID() {
        return tarjetaID;
    }

    public TipoTarjeta getTipoTarjeta() {
        return tipoTarjeta;
    }

    public String getNumeroTarjeta() {
        return numeroTarjeta;
    }
}
