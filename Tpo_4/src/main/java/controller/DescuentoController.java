package controller;

import model.CondicionesDescuento;
import model.TarjetaDescuento;
import model.TipoTarjeta;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DescuentoController {
	
	
	private static DescuentoController INSTANCE = null;
	
	private final List<CondicionesDescuento> Descuento;

	public static synchronized DescuentoController getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new DescuentoController();
		}
		return INSTANCE;
	}
	
    public DescuentoController() {
    	Descuento = new ArrayList<CondicionesDescuento>();
    	CondicionesDescuento CondicionesDescuento = new CondicionesDescuento(new Date(), new Date(), 5, 50, TipoTarjeta.PAMI, new ArrayList<TarjetaDescuento>());
    	Descuento.add(CondicionesDescuento);
    	
    }

    public void ABM() {
	}
}