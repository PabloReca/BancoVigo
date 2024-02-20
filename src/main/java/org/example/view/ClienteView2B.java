package org.example.view;

import javax.swing.*;

public class ClienteView2B {
    private JFrame frame;
    private JButton volverButton, crearButton;
    private JPanel formPanel;
    private JTextField dniTextField, nombreTextField, apellidoTextField, telefonoTextField;

    public ClienteView2B() {
        prepareGUI();
    }

    private void prepareGUI() {
        frame = new JFrame("Gestión de Clientes - Formulario");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(formPanel);

        volverButton.addActionListener(e -> {
            frame.dispose(); // Cierra esta ventana
        });

    }
    private void clearFields() {
        dniTextField.setText("");
        nombreTextField.setText("");
        apellidoTextField.setText("");
        telefonoTextField.setText("");
    }

    public void showGUI() {
        SwingUtilities.invokeLater(() -> {
            clearFields(); // Limpia los campos antes de mostrar la GUI
            frame.setSize(700, 300); // Establece el tamaño deseado
            frame.setLocationRelativeTo(null); // Esto centra la ventana en la pantalla
            frame.setVisible(true);
        });
    }

    public JTextField getDniTextField() {
        return dniTextField;
    }

    public JTextField getNombreTextField() {
        return nombreTextField;
    }

    public JTextField getApellidoTextField() {
        return apellidoTextField;
    }

    public JTextField getTelefonoTextField() {
        return telefonoTextField;
    }

    public JButton getCrearButton() {
        return crearButton;
    }

    public JFrame getFrame() {
        return frame;
    }


}
