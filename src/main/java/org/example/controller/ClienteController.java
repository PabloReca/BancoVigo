package org.example.controller;

import org.example.model.ClienteModel;
import org.example.view.ClienteView;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class ClienteController {
    private final SessionFactory sessionFactory;
    private final ClienteView view;

    public ClienteController(ClienteView view, SessionFactory sessionFactory) {
        this.view = view;
        this.sessionFactory = sessionFactory;
        initController();
    }

    // Inicializa el controlador cargando los datos de clientes y configurando los oyentes
    private void initController() {
        loadClientes();
        setupTableListener();
    }

    // Carga los clientes de la base de datos y los muestra en la tabla
    private void loadClientes() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Query<ClienteModel> query = session.createQuery("FROM ClienteModel", ClienteModel.class);
        List<ClienteModel> clientes = query.list();

        DefaultTableModel tableModel = new DefaultTableModel(new Object[]{"DNI", "Nombre", "Apellido", "Teléfono"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return true; // Permitir edición en todas las columnas
            }
        };

        clientes.forEach(cliente -> tableModel.addRow(new Object[]{
                cliente.getClDni(), cliente.getClNombre(), cliente.getClApellido(), cliente.getClTelefono()
        }));

        view.getTable().setModel(tableModel);

        session.getTransaction().commit();
        session.close();
    }

    // Configura el oyente para cambios en el modelo de la tabla
    private void setupTableListener() {
        view.getTable().getModel().addTableModelListener(e -> {
            if (e.getType() == TableModelEvent.UPDATE) {
                int row = e.getFirstRow();
                int column = e.getColumn();
                DefaultTableModel model = (DefaultTableModel) e.getSource();
                Object data = model.getValueAt(row, column);

                updateClienteInDatabase(row, column, data);
            }
        });
    }

    // Actualiza la información de un cliente en la base de datos
    private void updateClienteInDatabase(int row, int column, Object newValue) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        try {
            DefaultTableModel model = (DefaultTableModel) view.getTable().getModel();
            String dni = (String) model.getValueAt(row, 0); // El DNI está en la primera columna

            ClienteModel cliente = session.get(ClienteModel.class, dni);
            if (cliente != null) {
                // Identificar el campo específico que se está actualizando
                String fieldName = "";
                switch (column) {
                    case 1:
                        cliente.setClNombre((String) newValue);
                        fieldName = "nombre";
                        break;
                    case 2:
                        cliente.setClApellido((String) newValue);
                        fieldName = "apellido";
                        break;
                    case 3:
                        cliente.setClTelefono(Integer.valueOf((String) newValue));
                        fieldName = "teléfono";
                        break;
                }

                session.saveOrUpdate(cliente);
                session.getTransaction().commit();

                // Mensaje de éxito
                System.out.println("Cliente con DNI " + dni + " actualizado. Campo '" + fieldName + "' cambiado a: " + newValue);
            } else {
                JOptionPane.showMessageDialog(null, "Cliente no encontrado con DNI: " + dni, "Error", JOptionPane.ERROR_MESSAGE);
                // Mensaje de error por cliente no encontrado
                System.out.println("Error: Cliente con DNI " + dni + " no encontrado.");
            }
        } catch (Exception e) {
            session.getTransaction().rollback();
            JOptionPane.showMessageDialog(null, "Error al actualizar el cliente: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            // Mensaje de error por excepción
            System.out.println("Error al actualizar el cliente: " + e.getMessage());
        } finally {
            session.close();
        }
    }
}
