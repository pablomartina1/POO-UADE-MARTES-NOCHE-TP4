package view;

import controller.FuncionController;
import controller.PeliculasController;
import model.TipoGenero;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class ConsultarPeliculaxGenero extends JFrame {

    private final JComboBox<TipoGenero> cmbGenero;
    private final JTextArea txtResultados;
    private final JButton btnBuscar;
    private final JButton btnCancelar;

    public ConsultarPeliculaxGenero() {
        setTitle("Consultar Películas por Género");
        setSize(500, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel pnlSuperior = new JPanel(new FlowLayout());

        pnlSuperior.add(new JLabel("Género:"));
        cmbGenero = new JComboBox<>(TipoGenero.values());
        pnlSuperior.add(cmbGenero);

        btnBuscar = new JButton("Buscar");
        btnBuscar.addActionListener(this::buscarPeliculasYFuncionesPorGenero);
        pnlSuperior.add(btnBuscar);

        btnCancelar = new JButton("Cancelar");
        btnCancelar.addActionListener(e -> this.dispose());
        pnlSuperior.add(btnCancelar);

        txtResultados = new JTextArea();
        txtResultados.setEditable(false);
        JScrollPane scroll = new JScrollPane(txtResultados);

        add(pnlSuperior, BorderLayout.NORTH);
        add(scroll, BorderLayout.CENTER);

        setVisible(true);
    }

    private void buscarPeliculasYFuncionesPorGenero(ActionEvent e) {
        TipoGenero generoSeleccionado = (TipoGenero) cmbGenero.getSelectedItem();

        if (generoSeleccionado == null) {
            JOptionPane.showMessageDialog(this,
                    "Por favor seleccioná un género válido.",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        List<String> resumenPeliculas = PeliculasController.getInstance()
                .getResumenPeliculasPorGenero(generoSeleccionado.name());

        List<String> funcionesSemana = FuncionController.getInstance()
                .getFuncionesDisponiblesSemana();

        StringBuilder sb = new StringBuilder();

        if (resumenPeliculas.isEmpty()) {
            sb.append("No se encontraron películas para el género: ")
                    .append(generoSeleccionado.name()).append("\n");
        } else {
            sb.append("Películas:\n");
            for (String p : resumenPeliculas) {
                sb.append(p).append("\n");
            }
        }

        sb.append("\nFunciones disponibles esta semana:\n");
        if (funcionesSemana.isEmpty()) {
            sb.append("No hay funciones esta semana.\n");
        } else {
            for (String f : funcionesSemana) {
                sb.append(f).append("\n");
            }
        }

        txtResultados.setText(sb.toString());
    }
}
