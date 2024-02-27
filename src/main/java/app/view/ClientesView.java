package app.view;

import app.controller.ClienteController;

import javax.swing.*;

/**
 * Esta clase representa la interfaz gráfica para la gestión de clientes,
 * incluyendo funcionalidades para crear y borrar clientes.
 */
public class ClientesView {
    // Componentes de la interfaz de usuario
    private final JFrame frame;
    private JTable table1;
    private JButton borrarButton;
    private JButton crearButton;
    private JButton cuentaButton;
    private JPanel mainPanel;

    // Controlador para la lógica de negocio relacionada con los clientes
    private ClienteController controller;

    /**
     * Constructor que inicializa la interfaz gráfica y configura los eventos de los botones.
     */
    public ClientesView() {
        // Inicialización de la ventana principal
        frame = new JFrame("Gestión de Clientes - Lista");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(mainPanel);

        // Configuración del evento para el botón de borrar
        borrarButton.addActionListener(e -> {
            int selectedRow = table1.getSelectedRow();
            if (selectedRow >= 0) {
                // Obtiene el DNI del cliente seleccionado y solicita su eliminación
                String dni = table1.getModel().getValueAt(selectedRow, 0).toString();
                controller.deleteCliente(dni);
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
    public void setController(ClienteController controller) {
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
