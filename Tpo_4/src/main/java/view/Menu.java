package main.java.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu extends JFrame implements ActionListener {

    private JButton btRegistrarFuncion;
    private JButton btRegistrarPelicula;
    private JButton btConsultarPelicula;
    private JButton btConsultarRecaudacionxPeliculas;

    public Menu() {
        setTitle("Menú de Opciones");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 400);
        setLocationRelativeTo(null);

        iniciarLayout();
        iniciarBotones();
        agregarBotones();

        setVisible(true);
    }

    private void iniciarLayout() {
        setLayout(new GridLayout(4, 1));
    }

    private void iniciarBotones() {
        btRegistrarFuncion = new JButton("Registrar Función");
        btRegistrarFuncion.addActionListener(this);

        btRegistrarPelicula = new JButton("Registrar Película");
        btRegistrarPelicula.addActionListener(this);

        btConsultarPelicula = new JButton("Consultar Película");
        btConsultarPelicula.addActionListener(this);

        btConsultarRecaudacionxPeliculas = new JButton("Consultar Recaudación por Película");
        btConsultarRecaudacionxPeliculas.addActionListener(this);
    }

    private void agregarBotones() {
        add(btRegistrarFuncion);
        add(btRegistrarPelicula);
        add(btConsultarPelicula);
        add(btConsultarRecaudacionxPeliculas);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object src = e.getSource();
        if (src == btRegistrarFuncion) {
            new view.RegistrarFuncionxGenero();
        } else if (src == btRegistrarPelicula) {
            new view.RegistrarPeliculaxGenero();
        } else if (src == btConsultarPelicula) {
            new view.ConsultarPeliculaxGenero();
        } else if (src == btConsultarRecaudacionxPeliculas) {
            new view.ReporteRecaudacion();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Menu::new);
    }
}
