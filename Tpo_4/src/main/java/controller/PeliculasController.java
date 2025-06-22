package controller;

import model.Pelicula;
import model.TipoGenero;
import model.TipoProyeccion;

import java.util.*;

public class PeliculasController {

    private final List<Pelicula> peliculas;

    private static PeliculasController INSTANCE = null;

    public static synchronized PeliculasController getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new PeliculasController();
        }
        return INSTANCE;
    }

    public List<Pelicula> getPeliculas() {
        return peliculas;
    }

    private PeliculasController() {
        peliculas = new ArrayList<>();
    }

    public void agregarPelicula(TipoGenero genero, String nombre, int duracion, String director,
                                TipoProyeccion proyeccion, List<String> actores) {
        Pelicula p = new Pelicula();
        p.setGenero(genero);
        p.setNombre(nombre);
        p.setDuracionMin(duracion);
        p.setDirector(director);
        p.setTipoProyeccion(proyeccion);
        p.setActores(actores);
        p.setCondicionesDescuento(null); // o lo que corresponda

        peliculas.add(p);
    }


    public List<String> getResumenPeliculasPorGenero(String generoTexto) {
        TipoGenero genero = TipoGenero.valueOf(generoTexto);
        List<String> resumenes = new ArrayList<>();

        for (Pelicula p : peliculas) {
            if (p.getGenero() == genero) {
                String actores = String.join(", ", p.getActores() != null ? p.getActores() : Collections.emptyList());
                String resumen = String.format(
                        "Película: %s\nDirector: %s\nDuración: %d min\nProyección: %s\nActores: %s\n---------------------------",
                        p.getNombre(),
                        p.getDirector(),
                        p.getDuracionMin(),
                        p.getTipoProyeccion().name(),
                        actores
                );
                resumenes.add(resumen);
            }
        }
        return resumenes;
    }

}
