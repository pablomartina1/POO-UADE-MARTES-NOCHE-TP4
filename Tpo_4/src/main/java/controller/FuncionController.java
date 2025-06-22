package controller;

import dto.FuncionDTO;
import model.Funcion;
import model.Pelicula;
import model.Sala;
import model.TipoGenero;
import model.TipoProyeccion;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.*;

public class FuncionController {

    private final List<Funcion> funciones = new ArrayList<>();
    private static FuncionController INSTANCE = null;

    public static synchronized FuncionController getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new FuncionController();
        }
        return INSTANCE;
    }


    public void registrarFuncion(FuncionDTO dto) {
        Pelicula pelicula = new Pelicula();
        pelicula.setNombre(dto.getNombrePelicula());
        pelicula.setGenero(TipoGenero.valueOf(dto.getGenero()));

        Sala sala = new Sala();
        sala.setSalaID(dto.getSalaID());

        Funcion nuevaFuncion = new Funcion(
                dto.getFecha(),
                dto.getFuncionID(),
                dto.getHorario(),
                new ArrayList<>(),
                sala,
                pelicula
        );

        funciones.add(nuevaFuncion);
    }

    public List<FuncionDTO> getListaFunciones(Date fchFuncion) {
        List<FuncionDTO> resultado = new ArrayList<>();
        for (Funcion f : funciones) {
            if (mismaFecha(f.getFecha(), fchFuncion)) {
                resultado.add(new FuncionDTO(f));
            }
        }
        return resultado;
    }

    private boolean mismaFecha(Date d1, Date d2) {
        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        cal1.setTime(d1);
        cal2.setTime(d2);
        return cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) &&
                cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR);
    }

    public List<Funcion> buscarPeliculaPorFuncion(int peliculaID) {
        List<Funcion> funcionesDeLaPelicula = new ArrayList<>();
        for (Funcion funcion : funciones) {
            if (funcion.getPeliculaID() == peliculaID) {
                funcionesDeLaPelicula.add(funcion);
            }
        }
        return funcionesDeLaPelicula;
    }

    public List<Funcion> buscarPeliculaPorGenerosFuncion(TipoGenero genero) {
        List<Funcion> funcionesDeLaPelicula = new ArrayList<>();
        for (Funcion funcion : funciones) {
            if (funcion.getPelicula().getGenero() == genero) {
                funcionesDeLaPelicula.add(funcion);
            }
        }
        return funcionesDeLaPelicula;
    }

    public int obtenerAsientosDisponiblePorFuncion(int funcionID) {
        for (Funcion funcion : funciones) {
            if (funcion.getFuncionID() == funcionID) {
                return funcion.getCantidadAsientosDisponibles();
            }
        }
        return -1; // no encontrada
    }

    public int peliculaMasVista() {
        // Simulación simple: contar funciones por ID
        Map<Integer, Integer> conteo = new HashMap<>();
        for (Funcion f : funciones) {
            int id = f.getPeliculaID();
            conteo.put(id, conteo.getOrDefault(id, 0) + f.getEntradas().size());
        }

        int maxID = -1;
        int maxVistas = -1;
        for (Map.Entry<Integer, Integer> entry : conteo.entrySet()) {
            if (entry.getValue() > maxVistas) {
                maxVistas = entry.getValue();
                maxID = entry.getKey();
            }
        }
        return maxID;
    }

    public int diaDeLaSemanaConMenorVentas() {
        int[] ventasPorDia = new int[7]; // 0 = Domingo, 6 = Sábado
        for (Funcion f : funciones) {
            Calendar cal = Calendar.getInstance();
            cal.setTime(f.getFecha());
            int dia = cal.get(Calendar.DAY_OF_WEEK) - 1;
            ventasPorDia[dia] += f.getEntradas().size();
        }

        int menorDia = 0;
        for (int i = 1; i < 7; i++) {
            if (ventasPorDia[i] < ventasPorDia[menorDia]) {
                menorDia = i;
            }
        }
        return menorDia;
    }

    public List<String> getFuncionesDisponiblesSemana() {
        List<String> resultado = new ArrayList<>();
        SimpleDateFormat formatoDia = new SimpleDateFormat("EEEE"); // Nombre del día
        SimpleDateFormat formatoHora = new SimpleDateFormat("HH:mm");

        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        int semanaActual = cal.get(Calendar.WEEK_OF_YEAR);

        for (Funcion f : funciones) {
            cal.setTime(f.getFecha());
            int semanaFuncion = cal.get(Calendar.WEEK_OF_YEAR);

            if (semanaFuncion == semanaActual) {
                String dia = formatoDia.format(f.getFecha());
                String hora = f.getHorario();
                resultado.add("Día: " + dia + " - Hora: " + hora);
            }
        }
        return resultado;
    }

    public List<Funcion> getFunciones() {
        return funciones;
    }
}
