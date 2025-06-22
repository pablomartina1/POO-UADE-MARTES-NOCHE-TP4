package controller;

import dto.FuncionDTO;
import dto.VentaDto;
import model.*;

import java.util.*;

public class VentasController {

    private static VentasController INSTANCE = null;
    private List<Venta> ventas;
    private final FuncionController funcionController = FuncionController.getInstance();

    public static synchronized VentasController getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new VentasController();
        }
        return INSTANCE;
    }

    public VentasController() {
        ventas = new ArrayList<>();
        ventas.add(new Venta(1, new Date(), null, null));
    }

    public List<Venta> getVentas() {
        return ventas;
    }

    public void setVentas(List<Venta> ventas) {
        this.ventas = ventas;
    }

    public String comboMasVendido() {
        Map<Integer, Integer> contador = new HashMap<>();
        Map<Integer, String> descripciones = new HashMap<>();

        for (Venta venta : ventas) {
            if (venta.getListaComboID() != null) {
                for (Combo combo : venta.getListaComboID()) {
                    int id = combo.getComboID();
                    contador.put(id, contador.getOrDefault(id, 0) + 1);
                    descripciones.put(id, combo.getDescripcion());
                }
            }
        }

        if (contador.isEmpty()) {
            return "No se vendieron combos.";
        }

        int comboMasVendidoID = Collections.max(contador.entrySet(), Map.Entry.comparingByValue()).getKey();
        String descripcion = descripciones.get(comboMasVendidoID);
        int cantidad = contador.get(comboMasVendidoID);

        return String.format("Combo ID %d (%s) - Vendido %d veces", comboMasVendidoID, descripcion, cantidad);
    }

    // --- MÉTODOS NO USADOS ACTUALMENTE ---

    public List<VentaDto> getVentasDTO() {
        List<VentaDto> dtos = new ArrayList<>();
        for (Venta venta : ventas) {
            if (venta.getFuncion() != null) {
                dtos.add(new VentaDto(modelFuncionToDto(venta.getFuncion())));
            }
        }
        return dtos;
    }

    private FuncionDTO modelFuncionToDto(Funcion funcion) {
        return new FuncionDTO(funcion);
    }

    private Venta buscarVentaPorFuncion(Funcion funcion) {
        for (Venta venta : ventas) {
            if (Objects.equals(funcion, venta.getFuncion())) {
                return venta;
            }
        }
        return null;
    }
}
