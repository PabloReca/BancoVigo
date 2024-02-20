package org.example.controller;

import org.example.model.ClienteModel;
import org.example.view.ClienteView2A;
import org.example.view.ClienteView2B;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.table.DefaultTableModel;
import java.util.List;

/**
 * Controlador para gestionar clientes.
 * Se encarga de la interacción entre la vista y el modelo, incluyendo acciones como cargar, crear, actualizar y borrar clientes.
 */
public class ClienteController {
    private final SessionFactory sessionFactory;
    private final ClienteView2A viewA;
    private final ClienteView2B viewB;

    public ClienteController(ClienteView2A viewA, ClienteView2B viewB, SessionFactory sessionFactory) {
        this.viewA = viewA;
        this.viewB = viewB;
        this.sessionFactory = sessionFactory;
        initController();
    }

    /**
     * Inicializa el controlador, estableciendo la relación con las vistas y cargando los datos iniciales.
     */
    private void initController() {
        System.out.println("Inicializando controlador...");
        viewA.setController(this);
        loadClientes();
        setupTableListener();
        setupDeleteButtonAction();
        setupCreateButtonActionInViewB();
    }

    /**
     * Carga y muestra la lista de clientes en la vista.
     */
    private void loadClientes() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Query<ClienteModel> query = session.createQuery("FROM ClienteModel", ClienteModel.class);
        List<ClienteModel> clientes = query.list();

        DefaultTableModel tableModel = new DefaultTableModel(new Object[]{"DNI", "Nombre", "Apellido", "Teléfono"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                // Define si las celdas son editables
                return true;
            }
        };

        // Agrega los clientes al modelo de la tabla
        clientes.forEach(cliente ->
                tableModel.addRow(new Object[]{cliente.getClDni(), cliente.getClNombre(), cliente.getClApellido(), cliente.getClTelefono()}));

        viewA.getTable().setModel(tableModel);

        session.getTransaction().commit();
        session.close();
    }

    /**
     * Configura el listener para la tabla de clientes, permitiendo actualizar la información directamente desde la vista.
     */
    private void setupTableListener() {
        viewA.getTable().getModel().addTableModelListener(e -> {
            if (e.getType() == TableModelEvent.UPDATE) {
                int row = e.getFirstRow();
                int column = e.getColumn();
                DefaultTableModel model = (DefaultTableModel) e.getSource();
                Object data = model.getValueAt(row, column);
                updateClienteInDatabase(row, column, data);
            }
        });
    }

    /**
     * Configura la acción del botón de borrar cliente.
     */
    private void setupDeleteButtonAction() {
        viewA.getBorrarButton().addActionListener(e -> {
            int selectedRow = viewA.getTable().getSelectedRow();
            if (selectedRow >= 0) {
                String dni = viewA.getTable().getValueAt(selectedRow, 0).toString();
                deleteCliente(dni);
            } else {
                JOptionPane.showMessageDialog(null, "Por favor, seleccione un cliente para borrar.");
            }
        });
    }

    /**
     * Muestra el formulario para crear un nuevo cliente.
     */
    public void showClienteForm() {
        if (viewB != null) {
            viewB.showGUI();
        } else {
            System.out.println("La vista B no está inicializada.");
        }
    }

    /**
     * Configura la acción del botón de crear cliente en la vista B.
     */
    private void setupCreateButtonActionInViewB() {
        viewB.getCrearButton().addActionListener(e -> {
            String dni = viewB.getDniTextField().getText();
            String nombre = viewB.getNombreTextField().getText();
            String apellido = viewB.getApellidoTextField().getText();
            String telefono = viewB.getTelefonoTextField().getText();
            if (dni.isEmpty() || nombre.isEmpty() || apellido.isEmpty() || telefono.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Todos los campos son obligatorios.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            createCliente(dni, nombre, apellido, telefono);
        });
    }

    /**
     * Borra un cliente y sus cuentas asociadas de la base de datos.
     *
     * @param dni El DNI del cliente a borrar.
     */
    public void deleteCliente(String dni) {
        int confirm = JOptionPane.showConfirmDialog(null, "¿Estás seguro de que quieres eliminar este cliente y todas sus cuentas asociadas?", "Confirmar eliminación", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            Session session = sessionFactory.openSession();
            try {
                session.beginTransaction();
                ClienteModel cliente = session.get(ClienteModel.class, dni);
                if (cliente != null) {
                    session.delete(cliente);
                    session.getTransaction().commit();
                    JOptionPane.showMessageDialog(null, "Cliente y sus cuentas asociadas eliminados con éxito.");
                } else {
                    JOptionPane.showMessageDialog(null, "Cliente no encontrado.");
                    session.getTransaction().rollback();
                }
            } catch (Exception e) {
                if (session.getTransaction() != null) {
                    session.getTransaction().rollback();
                }
                JOptionPane.showMessageDialog(null, "Error al eliminar el cliente: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            } finally {
                session.close();
                loadClientes(); // Recarga la lista de clientes
            }
        }
    }

    /**
     * Actualiza la información de un cliente en la base de datos.
     *
     * @param row La fila de la tabla donde se encuentra el cliente.
     * @param column La columna de la tabla que se ha actualizado.
     * @param data El nuevo valor para el cliente.
     */
    private void updateClienteInDatabase(int row, int column, Object data) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        try {
            String dni = viewA.getTable().getValueAt(row, 0).toString();
            ClienteModel cliente = session.get(ClienteModel.class, dni);
            if (cliente != null) {
                // Actualiza el cliente según la columna modificada
                switch (column) {
                    case 1:
                        cliente.setClNombre((String) data);
                        break;
                    case 2:
                        cliente.setClApellido((String) data);
                        break;
                    case 3:
                        cliente.setClTelefono(Integer.parseInt(data.toString()));
                        break;
                }
                session.saveOrUpdate(cliente);
                session.getTransaction().commit();
                JOptionPane.showMessageDialog(null, "Cliente actualizado con éxito.");
            }
        } catch (Exception e) {
            session.getTransaction().rollback();
            JOptionPane.showMessageDialog(null, "Error al actualizar el cliente: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            session.close();
        }
    }

    /**
     * Crea un nuevo cliente y lo guarda en la base de datos.
     *
     * @param dni El DNI del cliente.
     * @param nombre El nombre del cliente.
     * @param apellido El apellido del cliente.
     * @param telefono El teléfono del cliente.
     */
    private void createCliente(String dni, String nombre, String apellido, String telefono) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            ClienteModel cliente = new ClienteModel();
            cliente.setClDni(dni);
            cliente.setClNombre(nombre);
            cliente.setClApellido(apellido);
            cliente.setClTelefono(Integer.valueOf(telefono));
            session.save(cliente);
            session.getTransaction().commit();
            JOptionPane.showMessageDialog(null, "Cliente creado con éxito.");
            viewB.getFrame().dispose();
            loadClientes(); // Recarga la lista de clientes
        } catch (Exception e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            JOptionPane.showMessageDialog(null, "Error al crear el cliente: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            session.close();
        }
    }
}
