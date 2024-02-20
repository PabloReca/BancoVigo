package app.view;

import javax.swing.*;
import java.awt.*;

public class ClienteView {
    private final JFrame frame;
    private final JTable table;
    private final JButton deleteButton;
    private final JButton createButton;
    private final JPanel mainPanel;
    private final JPanel formPanel;
    private final JTextField dniField, nombreField, apellidoField, telefonoField;
    private final JButton submitButton;

    public ClienteView() {
        frame = new JFrame("Gestión de Clientes");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);

        table = new JTable();
        JScrollPane scrollPane = new JScrollPane(table);

        deleteButton = new JButton("Borrar Cliente Seleccionado");
        createButton = new JButton("Crear Nuevo Cliente");

        mainPanel = new JPanel(new CardLayout());
        formPanel = new JPanel(new GridLayout(5, 2, 10, 10));

        dniField = new JTextField();
        nombreField = new JTextField();
        apellidoField = new JTextField();
        telefonoField = new JTextField();

        formPanel.add(new JLabel("DNI:"));
        formPanel.add(dniField);
        formPanel.add(new JLabel("Nombre:"));
        formPanel.add(nombreField);
        formPanel.add(new JLabel("Apellido:"));
        formPanel.add(apellidoField);
        formPanel.add(new JLabel("Teléfono:"));
        formPanel.add(telefonoField);

        submitButton = new JButton("Crear");
        formPanel.add(new JLabel("")); // Placeholder for layout alignment
        formPanel.add(submitButton);

        mainPanel.add(scrollPane, "Tabla");
        mainPanel.add(formPanel, "Formulario");

        frame.add(mainPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(deleteButton);
        buttonPanel.add(createButton);
        frame.add(buttonPanel, BorderLayout.SOUTH);
    }

    public void showTablePanel() {
        CardLayout cardLayout = (CardLayout) mainPanel.getLayout();
        cardLayout.show(mainPanel, "Tabla");
        deleteButton.setVisible(true);
        createButton.setVisible(true);
    }

    public void showFormPanel() {
        CardLayout cardLayout = (CardLayout) mainPanel.getLayout();
        cardLayout.show(mainPanel, "Formulario");
        deleteButton.setVisible(false);
        createButton.setVisible(false); // Also hide the create button
    }

    public JTable getTable() {
        return table;
    }

    public JButton getDeleteButton() {
        return deleteButton;
    }

    public JButton getCreateButton() {
        return createButton;
    }

    public JTextField getDniField() {
        return dniField;
    }

    public JTextField getNombreField() {
        return nombreField;
    }

    public JTextField getApellidoField() {
        return apellidoField;
    }

    public JTextField getTelefonoField() {
        return telefonoField;
    }

    public JButton getSubmitButton() {
        return submitButton;
    }

    public void setVisible(boolean visible) {
        frame.setVisible(visible);
    }
}
