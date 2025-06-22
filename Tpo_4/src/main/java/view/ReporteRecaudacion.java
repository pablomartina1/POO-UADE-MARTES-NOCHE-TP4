package view;

import controller.VentasController;
import model.Pelicula;
import model.Reportes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Map;

public class ReporteRecaudacion extends JFrame {

    private final JTextArea txtReporte;
    private final JButton btnGenerar;
    private final JButton btnCancelar;

    public ReporteRecaudacion() {
        setTitle("Reporte de Recaudación");
        setSize(600, 450);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        txtReporte = new JTextArea();
        txtReporte.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(txtReporte);

        btnGenerar = new JButton("Generar Reporte");
        btnGenerar.addActionListener(this::generarReporte);

        btnCancelar = new JButton("Cancelar");
        btnCancelar.addActionListener(e -> {
            this.dispose();
        });
        add(btnCancelar);

        JPanel panel = new JPanel(new BorderLayout());
        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(btnGenerar, BorderLayout.SOUTH);

        add(panel);
        setVisible(true);
    }

    private void generarReporte(ActionEvent e) {
        StringBuilder sb = new StringBuilder();

        sb.append("REPORTE DE RECAUDACIÓN\n\n");

        sb.append("Recaudación total por película (ID):\n");
        Map<Pelicula, Float> recaudacionPorPelicula = Reportes.recaudacionPorPelicula();
        if (recaudacionPorPelicula.isEmpty()) {
            sb.append("No hay datos de recaudación por película.\n");
        } else {
            recaudacionPorPelicula.forEach((id, monto) ->
                    sb.append(String.format("Película ID: %d - Recaudación: $%.2f\n", id, monto))
            );
        }

        sb.append("\nRecaudación total por función (ID):\n");
        Map<Integer, Float> recaudacionPorFuncion = Reportes.recaudacionPorFuncion();
        if (recaudacionPorFuncion.isEmpty()) {
            sb.append("No hay datos de recaudación por función.\n");
        } else {
            recaudacionPorFuncion.forEach((id, monto) ->
                    sb.append(String.format("Función ID: %d - Recaudación: $%.2f\n", id, monto))
            );
        }

        sb.append("\nRecaudación total por combos vendidos: $")
                .append(String.format("%.2f", Reportes.recaudacionPorCombos()))
                .append("\n");

        sb.append("\nRecaudación total general: $")
                .append(String.format("%.2f", Reportes.recaudacionTotal()))
                .append("\n");

        sb.append("\nCombo más vendido: ")
                .append(VentasController.getInstance().comboMasVendido())
                .append("\n");

        sb.append("\nPelícula con mayor recaudación: ")
                .append(Reportes.peliculaConMayorRecaudacion())
                .append("\n");

        txtReporte.setText(sb.toString());
    }
}
