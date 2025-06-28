package java;

import controller.PeliculasController;
import controller.VentasController;
import java.model.*;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class UnitariosTest {

    @Test
    public void testRegistrarPeliculaPorGenero_OK() {
        PeliculasController controller = PeliculasController.getInstance();
        controller.agregarPelicula(
                TipoGenero.DRAMA,
                "DramaTest",
                110,
                "Director X",
                TipoProyeccion.DOSD,
                Arrays.asList("Actor X", "Actriz Y")
        );
        Optional<Pelicula> pelicula = controller.getPeliculas()
                .stream()
                .filter(p -> p.getNombre().equals("DramaTest"))
                .findFirst();

        assertTrue(pelicula.isPresent(), "La película debería haber sido registrada.");
        assertEquals(TipoGenero.DRAMA, pelicula.get().getGenero());
    }

    @Test
    public void testRegistrarPeliculaPorGenero_NombreInexistente() {
        PeliculasController controller = PeliculasController.getInstance();

        boolean existe = controller.getPeliculas()
                .stream()
                .anyMatch(p -> p.getNombre().equals("NombreFalso"));

        assertFalse(existe, "La película no debería existir.");
    }

    @Test
    public void testConsultarPeliculasPorGenero_OK() {
        PeliculasController controller = PeliculasController.getInstance();
        controller.agregarPelicula(
                TipoGenero.ROMANCE,
                "RomanceTest",
                100,
                "Directora Z",
                TipoProyeccion.TRESD,
                Collections.singletonList("Actor Amoroso")
        );

        List<String> resumen = controller.getResumenPeliculasPorGenero("ROMANCE");
        assertEquals(1, resumen.size());
        assertTrue(resumen.getFirst().contains("RomanceTest"));
    }

    @Test
    public void testConsultarPeliculasPorGenero_Vacio() {
        PeliculasController controller = PeliculasController.getInstance();
        List<String> resumen = controller.getResumenPeliculasPorGenero("BIOGRAFICA");
        assertTrue(resumen.isEmpty(), "No debería haber películas del género BIOGRAFICA.");
    }

    @Test
    public void testEmitirReportePeliculasMayorRecaudacion_conVenta() {
        Pelicula peli = new Pelicula();
        peli.setGenero(TipoGenero.DRAMA);
        peli.setNombre("Recaudadora");
        peli.setDuracionMin(130);
        peli.setDirector("Director R");
        peli.setTipoProyeccion(TipoProyeccion.DOSD);
        peli.setActores(new ArrayList<>());
        peli.setCondicionesDescuento(new CondicionesDescuento(null, null, 0, 0f, null, new ArrayList<>()));

        Sala sala = new Sala(10, "Sala Prueba", 80);

        // Creo entradas con precio > 0 para que haya recaudación
        List<Entrada> entradas = new ArrayList<>();
        entradas.add(new Entrada(1, null, 100f));
        entradas.add(new Entrada(2, null, 50f));

        // Creo la función asignando las entradas (aún con entradas que tienen funcion=null)
        Funcion funcion = new Funcion(new Date(), 999, "20:00", entradas, sala, peli);

        // Ahora asigno la función correctamente en cada entrada de la lista
        for (int i = 0; i < entradas.size(); i++) {
            Entrada vieja = entradas.get(i);
            entradas.set(i, new Entrada(vieja.getNroAsiento(), funcion, vieja.getPrecio()));
        }

        // Creo la venta con la función
        Venta venta = new Venta(999, new Date(), new ArrayList<>(), funcion);

        // Limpio ventas previas para evitar interferencias en el test
        VentasController.getInstance().getVentas().clear();

        // Agrego esta venta al controlador
        VentasController.getInstance().getVentas().add(venta);

        // Ahora obtengo recaudación por película
        Map<Pelicula, Float> recaudacion = Reportes.recaudacionPorPelicula();

        assertTrue(recaudacion.containsKey(peli), "La película debería existir en la recaudación.");
        assertTrue(recaudacion.get(peli) > 0, "La recaudación debería ser mayor a 0.");
    }

}