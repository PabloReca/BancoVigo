package org.example.view;

import javax.swing.*;
import java.awt.*;

public class ClienteView {
    private final JFrame frame;
    private final JTable table;

    public ClienteView() {
        frame = new JFrame("Clientes");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 400);
        frame.setLayout(new BorderLayout());

        table = new JTable();
        JScrollPane scrollPane = new JScrollPane(table);
        frame.add(scrollPane, BorderLayout.CENTER);
    }

    public void setVisible(boolean visible) {
        frame.setVisible(visible);
    }

    public JTable getTable() {
        return table;
    }
}
