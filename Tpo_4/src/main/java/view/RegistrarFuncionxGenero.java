package view;

import controller.FuncionController;
import dto.FuncionDTO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class RegistrarFuncionxGenero extends JFrame {

    private final JTextField txtFuncionID;
    private final JTextField txtFecha;
    private final JTextField txtHorario;
    private final JTextField txtSalaID;
    private final JTextField txtGenero;
    private final JTextField txtNombrePelicula;
    private final JButton btnRegistrar;
    private final JButton btnCancelar;

    public RegistrarFuncionxGenero() {
        setTitle("Registrar Función");
        setSize(400, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(7, 2));

        add(new JLabel("ID Función:"));
        txtFuncionID = new JTextField();
        add(txtFuncionID);

        add(new JLabel("Fecha (dd/MM/yyyy):"));
        txtFecha = new JTextField();
        add(txtFecha);

        add(new JLabel("Horario (HH:mm):"));
        txtHorario = new JTextField();
        add(txtHorario);

        add(new JLabel("Sala ID:"));
        txtSalaID = new JTextField();
        add(txtSalaID);

        add(new JLabel("Género (ej: TERROR):"));
        txtGenero = new JTextField();
        add(txtGenero);

        add(new JLabel("Nombre Película:"));
        txtNombrePelicula = new JTextField();
        add(txtNombrePelicula);

        btnRegistrar = new JButton("Registrar");
        btnRegistrar.addActionListener(this::registrarFuncion);
        add(btnRegistrar);

        btnCancelar = new JButton("Cancelar");
        btnCancelar.addActionListener(e -> {
            this.dispose();
        });
        add(btnCancelar);

        setVisible(true);
    }

    private void registrarFuncion(ActionEvent e) {
        try {
            int funcionID = Integer.parseInt(txtFuncionID.getText());
            Date fecha = new SimpleDateFormat("dd/MM/yyyy").parse(txtFecha.getText());
            String horario = txtHorario.getText();
            int salaID = Integer.parseInt(txtSalaID.getText());
            String genero = txtGenero.getText().toUpperCase();
            String nombrePelicula = txtNombrePelicula.getText();

            FuncionDTO dto = new FuncionDTO(funcionID, fecha, horario, salaID, genero, nombrePelicula);
            FuncionController.getInstance().registrarFuncion(dto);
            JOptionPane.showMessageDialog(this, "Función registrada con éxito.");
            dispose();

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Formato numérico inválido.");
        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(this, "Formato de fecha inválido. Use dd/MM/yyyy");
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, "Género inválido.");
        }
    }
}
