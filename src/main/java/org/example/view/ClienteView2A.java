package org.example.view;

import org.example.controller.ClienteController;
import javax.swing.*;

public class ClienteView2A {
    private final JFrame frame;
    private JTable table1;
    private JButton borrarButton;
    private JButton crearButton;
    private JPanel mainPanel;
    private ClienteController controller;

    public ClienteView2A() {
        frame = new JFrame("Gestión de Clientes - Lista");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(mainPanel);

        borrarButton.addActionListener(e -> {
            int selectedRow = table1.getSelectedRow();
            if (selectedRow >= 0) {
                String dni = table1.getModel().getValueAt(selectedRow, 0).toString();
                controller.deleteCliente(dni);
            } else {
                JOptionPane.showMessageDialog(null, "Por favor, seleccione un cliente para borrar.");
            }
        });
        crearButton.addActionListener(e -> controller.showClienteForm());
    }

    public void setController(ClienteController controller) {
        this.controller = controller;
    }

    public void showGUI() {
        SwingUtilities.invokeLater(() -> {
            frame.setSize(700, 300); // Establece el tamaño deseado
            frame.setLocationRelativeTo(null); // Esto centra la ventana en la pantalla
            frame.setVisible(true);
        });
    }


    public JTable getTable() {
        return table1;
    }

    public JButton getBorrarButton() {
        return borrarButton;
    }

}
