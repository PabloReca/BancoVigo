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

    private void initController() {
        System.out.println("Inicializando controlador...");
        loadClientes();
        setupTableListener();
        setupDeleteButtonAction();
    }

    private void loadClientes() {
        System.out.println("Cargando clientes...");
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Query<ClienteModel> query = session.createQuery("FROM ClienteModel", ClienteModel.class);
        List<ClienteModel> clientes = query.list();

        DefaultTableModel tableModel = new DefaultTableModel(new Object[]{"DNI", "Nombre", "Apellido", "Teléfono"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                // Todas las columnas son editables
                return true;
            }
        };

        clientes.forEach(cliente -> tableModel.addRow(new Object[]{cliente.getClDni(), cliente.getClNombre(), cliente.getClApellido(), cliente.getClTelefono()}));

        view.getTable().setModel(tableModel);

        session.getTransaction().commit();
        session.close();
    }

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

    private void setupDeleteButtonAction() {
        view.getDeleteButton().addActionListener(e -> {
            int selectedRow = view.getTable().getSelectedRow();
            if (selectedRow >= 0) {
                String dni = view.getTable().getValueAt(selectedRow, 0).toString();
                deleteCliente(dni);
            } else {
                JOptionPane.showMessageDialog(null, "Por favor, seleccione un cliente para borrar.");
            }
        });
    }

    public void deleteCliente(String dni) {
        int confirm = JOptionPane.showConfirmDialog(null, "¿Estás seguro de que quieres eliminar este cliente y todas sus cuentas asociadas?", "Confirmar eliminación", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            Session session = sessionFactory.openSession();
            try {
                session.beginTransaction();
                ClienteModel cliente = session.get(ClienteModel.class, dni);
                if (cliente != null) {
                    session.delete(cliente); // Hibernate borra las cuentas asociadas automáticamente
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
                if (session != null) {
                    session.close();
                }
                loadClientes(); // Recargar la lista de clientes para reflejar la eliminación
            }
        }
    }


    private void updateClienteInDatabase(int row, int column, Object data) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        try {
            String dni = view.getTable().getValueAt(row, 0).toString();
            ClienteModel cliente = session.get(ClienteModel.class, dni);
            if (cliente != null) {
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
}
