package app.view;

import javax.swing.*;

/**
 * Esta clase representa la interfaz gráfica para el formulario de gestión de clientes,
 * permitiendo introducir y enviar datos de nuevos clientes o editar los existentes.
 */
public class ClienteView2B {
    // Componentes de la interfaz de usuario
    private JFrame frame;
    private JButton volverButton, crearButton;
    private JPanel formPanel;
    private JTextField dniTextField, nombreTextField, apellidoTextField, telefonoTextField;

    /**
     * Constructor que inicializa la interfaz gráfica llamando al método prepareGUI.
     */
    public ClienteView2B() {
        prepareGUI();
    }

    /**
     * Prepara la interfaz gráfica inicializando los componentes y configurando los eventos.
     */
    private void prepareGUI() {
        frame = new JFrame("Gestión de Clientes - Formulario");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(formPanel);

        // Configura el evento para el botón de volver para cerrar la ventana
        volverButton.addActionListener(e -> frame.dispose());
    }

    /**
     * Limpia los campos de texto del formulario.
     */
    private void clearFields() {
        dniTextField.setText("");
        nombreTextField.setText("");
        apellidoTextField.setText("");
        telefonoTextField.setText("");
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
