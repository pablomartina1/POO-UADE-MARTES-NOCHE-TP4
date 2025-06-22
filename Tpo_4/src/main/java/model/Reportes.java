package model;

import controller.VentasController;

import java.util.*;
import java.util.function.Function;

public class Reportes {

    private static List<Venta> getVentas() {
        return VentasController.getInstance().getVentas();
    }

    private static Map<Integer, Float> generarRecaudacion(Function<Funcion, Integer> agrupador) {
        Map<Integer, Float> resultado = new HashMap<>();
        for (Venta venta : getVentas()) {
            Funcion funcion = venta.getFuncion();
            if (funcion != null) {
                int clave = agrupador.apply(funcion);
                float monto = venta.calcularMontoDeLaVentaPorFuncionCombos();
                resultado.put(clave, resultado.getOrDefault(clave, 0f) + monto);
            }
        }
        return resultado;
    }

    public static Map<Pelicula, Float> recaudacionPorPelicula() {
        Map<Pelicula, Float> resultado = new HashMap<>();
        for (Venta venta : getVentas()) {
            Funcion funcion = venta.getFuncion();
            if (funcion != null && funcion.getPelicula() != null) {
                Pelicula peli = funcion.getPelicula();
                float monto = venta.calcularMontoDeLaVentaPorFuncionCombos();
                resultado.put(peli, resultado.getOrDefault(peli, 0f) + monto);
            }
        }
        return resultado;
    }

    public static Map<Integer, Float> recaudacionPorFuncion() {
        return generarRecaudacion(Funcion::getFuncionID);
    }

    public static float recaudacionPorCombos() {
        float total = 0f;
        for (Venta venta : getVentas()) {
            total += venta.calcularMontoPorComboDeVenta();
        }
        return total;
    }

    public static float recaudacionTotal() {
        float total = 0f;
        for (Venta venta : getVentas()) {
            total += venta.calcularMontoDeLaVentaPorFuncionCombos();
        }
        return total;
    }

    public static String peliculaConMayorRecaudacion() {
        Map<Pelicula, Float> recaudaciones = recaudacionPorPelicula();
        if (recaudaciones.isEmpty()) return "Aún no hay ventas realizadas.";

        return recaudaciones.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(entry -> {
                    Pelicula p = entry.getKey();
                    float monto = entry.getValue();
                    return String.format("%s (ID %d) - Recaudación: $%.2f", p.getNombre(), p.getPeliculaID(), monto);
                })
                .orElse("Película desconocida");
    }
}
