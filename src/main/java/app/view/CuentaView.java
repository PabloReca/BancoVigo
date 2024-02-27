package app.view;

import app.controller.CuentaController;

import javax.swing.*;

public class CuentaView {
    JFrame frame;
    private JTable table1;
    private JButton borrarButton;
    private JButton crearButton;
    private JPanel cuentasPanel;



    // Controlador para la lógica de negocio relacionada con los clientes
    private CuentaController controller;

    /**
     * Constructor que inicializa la interfaz gráfica y configura los eventos de los botones.
     */
    public CuentaView() {
        // Inicialización de la ventana principal
        frame = new JFrame("Gestión de Clientes - Lista");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(cuentasPanel);

        // Configuración del evento para el botón de borrar
        borrarButton.addActionListener(e -> {
            int selectedRow = table1.getSelectedRow();
            if (selectedRow >= 0) {
                // Obtiene el DNI del cliente seleccionado y solicita su eliminación
                String dni = table1.getModel().getValueAt(selectedRow, 0).toString();
                controller.deleteCuenta(dni);
            } else {
                // Muestra un mensaje si no se ha seleccionado ningún cliente
                JOptionPane.showMessageDialog(null, "Por favor, seleccione un cliente para borrar.");
            }
        });

        // Configuración del evento para el botón de crear
        crearButton.addActionListener(e -> controller.showClienteForm());
    }

    /**
     * Establece el controlador para esta vista.
     * @param controller El controlador que maneja las operaciones de negocio.
     */
    public void setController(CuentaController controller) {
        this.controller = controller;
    }

    /**
     * Muestra la interfaz gráfica de usuario
     */
    public void showGUI() {
        SwingUtilities.invokeLater(() -> {
            frame.setSize(700, 300); // Establece el tamaño de la ventana
            frame.setLocationRelativeTo(null); // Centra la ventana en la pantalla
            frame.setVisible(true); // Hace visible la ventana
        });
    }

    // Métodos getter para acceder a los componentes de la UI desde fuera de la clase
    public JTable getTable() {
        return table1;
    }

    public JButton getBorrarButton() {
        return borrarButton;
    }
}
