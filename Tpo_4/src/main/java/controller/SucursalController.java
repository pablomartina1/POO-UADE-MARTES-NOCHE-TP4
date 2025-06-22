package controller;

import model.Sucursal;

import java.util.*;


/**
 * 
 */
public class SucursalController {

    private static SucursalController INSTANCE = null;

    public static synchronized SucursalController getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new SucursalController();
        }
        return INSTANCE;
    }
	
	private final List<Sucursal> sucursal;
		
    public SucursalController() {
    	sucursal = new ArrayList<Sucursal>();
    	sucursal.add(new Sucursal(1, "Barracas", "Av Montes de Oca 1100", null));
        sucursal.add(new Sucursal(2, "Monserrat", "Av Independencia 2000", null));
        sucursal.add(new Sucursal(3, "Recoleta", "Av Las Heras 1558", null));
    }

    /**
     * @param id 
     * @param denom 
     * @param dir
     */
    public void agregarSucursal(int id, String denom, String dir) {
        // TODO implement here
    }

    /**
     * @param idSucursal 
     * @param salaID 
     * @param denom 
     * @param nroasientos
     */
    public void agregarSala(int idSucursal, int salaID, String denom, int nroasientos) {
        // TODO implement here
    }

}