package dto;

import model.Funcion;
import model.Pelicula;
import java.util.Date;

public class FuncionDTO {

    private final int funcionID;
    private final Date fecha;
    private final String horario;
    private final int salaID;
    private final String genero;
    private final String nombrePelicula;

    // Constructor desde modelo
    public FuncionDTO(Funcion funcion) {
        this.funcionID = funcion.getFuncionID();
        this.fecha = funcion.getFecha();
        this.horario = funcion.getHorario();
        this.salaID = funcion.getSalaID();
        Pelicula pelicula = funcion.getPelicula();
        this.genero = pelicula.getGenero().name();
        this.nombrePelicula = pelicula.getNombre();
    }

    // Constructor desde formulario
    public FuncionDTO(int funcionID, Date fecha, String horario, int salaID, String genero, String nombrePelicula) {
        this.funcionID = funcionID;
        this.fecha = fecha;
        this.horario = horario;
        this.salaID = salaID;
        this.genero = genero;
        this.nombrePelicula = nombrePelicula;
    }

    public int getFuncionID() {
        return funcionID;
    }

    public Date getFecha() {
        return fecha;
    }

    public String getHorario() {
        return horario;
    }

    public int getSalaID() {
        return salaID;
    }

    public String getGenero() {
        return genero;
    }

    public String getNombrePelicula() {
        return nombrePelicula;
    }
}
