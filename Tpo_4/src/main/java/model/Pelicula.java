package model;

import java.util.List;
import java.util.Objects;

public class Pelicula {

    private int peliculaID;
    private TipoGenero genero;
    private String director;
    private int duracionMin;
    private String nombre;
    private TipoProyeccion tipoProyeccion;
    private List<String> actores;
    private CondicionesDescuento condicionesDescuento;

    public Pelicula() {
    }

    public int getPeliculaID() {
        return peliculaID;
    }

    public void setPeliculaID(int peliculaID) {
        this.peliculaID = peliculaID;
    }

    public TipoGenero getGenero() {
        return genero;
    }

    public void setGenero(TipoGenero genero) {
        this.genero = genero;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public int getDuracionMin() {
        return duracionMin;
    }

    public void setDuracionMin(int duracionMin) {
        this.duracionMin = duracionMin;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public TipoProyeccion getTipoProyeccion() {
        return tipoProyeccion;
    }

    public void setTipoProyeccion(TipoProyeccion tipoProyeccion) {
        this.tipoProyeccion = tipoProyeccion;
    }

    public List<String> getActores() {
        return actores;
    }

    public void setActores(List<String> actores) {
        this.actores = actores;
    }

    public CondicionesDescuento getCondicionesDescuento() {
        return condicionesDescuento;
    }

    public void setCondicionesDescuento(CondicionesDescuento condicionesDescuento) {
        this.condicionesDescuento = condicionesDescuento;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pelicula pelicula = (Pelicula) o;
        return Objects.equals(nombre, pelicula.nombre) &&
                Objects.equals(director, pelicula.director) &&
                genero == pelicula.genero &&
                duracionMin == pelicula.duracionMin;
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre, director, genero, duracionMin);
    }
}
