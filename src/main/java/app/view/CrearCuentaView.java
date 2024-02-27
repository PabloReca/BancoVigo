package app.view;

import javax.swing.*;

/**
 * Esta clase representa la interfaz gráfica para el formulario de gestión de clientes,
 * permitiendo introducir y enviar datos de nuevos clientes o editar los existentes.
 */
public class CrearCuentaView {
    // Componentes de la interfaz de usuario
    private JFrame frame;
    private JButton volverButton, crearButton;
    private JPanel cuentaFormPanel;
    private JTextField clienteTextField, codCuentaTextField, saldoTextField, codSucursalTextField;
    private JTextField fechaCreacionTextField;

    /**
     * Constructor que inicializa la interfaz gráfica llamando al método prepareGUI.
     */
    public CrearCuentaView() {
        prepareGUI();
    }

    /**
     * Prepara la interfaz gráfica inicializando los componentes y configurando los eventos.
     */
    private void prepareGUI() {
        frame = new JFrame("Gestión de Clientes - Formulario");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(cuentaFormPanel);

        // Configura el evento para el botón de volver para cerrar la ventana
        volverButton.addActionListener(e -> frame.dispose());
    }

    /**
     * Limpia los campos de texto del formulario.
     */
    private void clearFields() {
        clienteTextField.setText("");
        codCuentaTextField.setText("");
        saldoTextField.setText("");
        codSucursalTextField.setText("");
    }

    /**
     * Muestra la interfaz gráfica de usuario en el hilo de despacho de eventos de Swing,
     * limpiando los campos antes de hacer visible la ventana.
     */
    public void showGUI() {
        SwingUtilities.invokeLater(() -> {
            clearFields(); // Limpia los campos antes de mostrar la GUI
            frame.setSize(700, 300); // Establece el tamaño de la ventana
            frame.setLocationRelativeTo(null); // Centra la ventana en la pantalla
            frame.setVisible(true); // Hace visible la ventana
        });
    }

    // Métodos getter para permitir el acceso a los componentes de la interfaz de usuario desde otras clases
    public JTextField getClienteTextField() {
        return clienteTextField;
    }

    public JTextField getCodCuentaTextField() {
        return codCuentaTextField;
    }

    public JTextField getSaldoTextField() {
        return saldoTextField;
    }

    public JTextField getCodSucursalTextField() {
        return codSucursalTextField;
    }

    public JTextField getFechaCreacionTextField() {return codSucursalTextField;}

    public JButton getCrearButton() {
        return crearButton;
    }

    public JFrame getFrame() {
        return frame;
    }
}
