package org.example.view;

import javax.swing.*;
import java.awt.*;

public class ClienteView {
    private final JFrame frame;
    private final JTable table;
    private final JButton deleteButton;

    public ClienteView() {
        frame = new JFrame("Gestión de Clientes");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 400);

        table = new JTable();
        JScrollPane scrollPane = new JScrollPane(table);
        frame.add(scrollPane, BorderLayout.CENTER);

        deleteButton = new JButton("Borrar Cliente Seleccionado");
        frame.add(deleteButton, BorderLayout.SOUTH);
    }

    public void setVisible(boolean visible) {
        frame.setVisible(visible);
    }

    public JTable getTable() {
        return table;
    }

    public JButton getDeleteButton() {
        return deleteButton;
    }
}
