package view;

import controller.PeliculasController;
import model.TipoGenero;
import model.TipoProyeccion;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class RegistrarPeliculaxGenero extends JFrame {

    private final JTextField txtNombre;
    private final JTextField txtDuracion;
    private final JTextField txtDirector;
    private final JTextField txtProyeccion;
    private final JTextField txtGenero;
    private final JTextField txtActores;
    private final JButton btnRegistrar;
    private final JButton btnCancelar;

    public RegistrarPeliculaxGenero() {
        setTitle("Registrar Película por Género");
        setSize(400, 350);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(7, 2, 5, 5));

        add(new JLabel("Nombre:"));
        txtNombre = new JTextField();
        add(txtNombre);

        add(new JLabel("Duración (minutos):"));
        txtDuracion = new JTextField();
        add(txtDuracion);

        add(new JLabel("Director:"));
        txtDirector = new JTextField();
        add(txtDirector);

        add(new JLabel("Tipo de Proyección (DOSD, TRESD, TRESDMAX, CUATROD):"));
        txtProyeccion = new JTextField();
        add(txtProyeccion);

        add(new JLabel("Género (DRAMA, ROMANCE, TERROR, BIOGRAFICA, SUSPENSO):"));
        txtGenero = new JTextField();
        add(txtGenero);

        add(new JLabel("Actores (separados por coma):"));
        txtActores = new JTextField();
        add(txtActores);

        btnRegistrar = new JButton("Registrar Película");
        btnRegistrar.addActionListener(this::registrarPelicula);
        add(btnRegistrar);

        btnCancelar = new JButton("Cancelar");
        btnCancelar.addActionListener(e -> {
            this.dispose();
        });
        add(btnCancelar);

        setVisible(true);
    }

    private void registrarPelicula(ActionEvent e) {
        try {
            String nombre = txtNombre.getText().trim();
            if (nombre.isEmpty()) throw new IllegalArgumentException("Nombre vacío");

            int duracion = Integer.parseInt(txtDuracion.getText().trim());
            if (duracion <= 0) throw new IllegalArgumentException("Duración debe ser positiva");

            String director = txtDirector.getText().trim();
            if (director.isEmpty()) throw new IllegalArgumentException("Director vacío");

            String proyeccionStr = txtProyeccion.getText().trim().toUpperCase();
            TipoProyeccion proyeccion = TipoProyeccion.valueOf(proyeccionStr);

            String generoStr = txtGenero.getText().trim().toUpperCase();
            TipoGenero genero = TipoGenero.valueOf(generoStr);

            String actoresRaw = txtActores.getText().trim();
            java.util.List<String> actores;
            if (actoresRaw.isEmpty()) {
                actores = java.util.Collections.emptyList();
            } else {
                actores = java.util.Arrays.asList(actoresRaw.split("\\s*,\\s*"));
            }

            // Llamar al controller para agregar
            PeliculasController.getInstance().agregarPelicula(genero, nombre, duracion, director, proyeccion, actores);

            JOptionPane.showMessageDialog(this, "Película registrada con éxito.");
            this.dispose();

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Duración debe ser un número entero válido.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error inesperado: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
