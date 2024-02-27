package app.controller;

import app.model.CuentaModel;
import app.view.CuentaView;
import app.view.CrearCuentaView;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;


import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.List;


/**
 * Controlador para gestionar clientes.
 * Se encarga de la interacción entre la vista y el modelo, incluyendo acciones como cargar, crear, actualizar y borrar clientes.
 */
public class CuentaController {
    private final SessionFactory sessionFactory;
    private final CuentaView viewA;
    private final CrearCuentaView viewB;

    public CuentaController(CuentaView viewA, CrearCuentaView viewB, SessionFactory sessionFactory) {
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
        loadCuentas();
        setupTableListener();
        setupDeleteButtonAction();
        setupCreateButtonActionInViewB();
    }

    /**
     * Carga y muestra la lista de clientes en la vista.
     */
    private void loadCuentas() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Query<CuentaModel> query = session.createQuery("FROM ClienteModel", CuentaModel.class);
        List<CuentaModel> clientes = query.list();

        DefaultTableModel tableModel = new DefaultTableModel(new Object[]{"DNI", "Nombre", "Apellido", "Teléfono"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                // Define si las celdas son editables
                return true;
            }
        };

        // Agrega los clientes al modelo de la tabla
        clientes.forEach(cuenta -> tableModel.addRow(new Object[]{cuenta.getCuCodCuenta(), cuenta.getCliente(), cuenta.getCuCodSucursal(), cuenta.getCuSaldo(), cuenta.getCuFechaCreacion()}));

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
        // Elimina todos los ActionListeners previos para evitar duplicados
        ActionListener[] listeners = viewA.getBorrarButton().getActionListeners();
        for (ActionListener listener : listeners) {
            viewA.getBorrarButton().removeActionListener(listener);
        }

        // Añade el ActionListener
        viewA.getBorrarButton().addActionListener(e -> {
            int selectedRow = viewA.getTable().getSelectedRow();
            if (selectedRow >= 0) {
                String dni = viewA.getTable().getValueAt(selectedRow, 0).toString();
                deleteCuenta(dni);
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
            String cliente = viewB.getClienteTextField().getText();
            String codCuentaStr = viewB.getCodCuentaTextField().getText();
            String saldoStr = viewB.getSaldoTextField().getText();
            String codSucursalStr = viewB.getCodSucursalTextField().getText();
            String fechaCreacion = viewB.getFechaCreacionTextField().getText();

            if (cliente.isEmpty() || codCuentaStr.isEmpty() || saldoStr.isEmpty() || codSucursalStr.isEmpty() || fechaCreacion.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Todos los campos son obligatorios.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            int codCuenta = Integer.parseInt(codCuentaStr);
            int saldo = Integer.parseInt(saldoStr);
            int codSucursal = Integer.parseInt(codSucursalStr);

            createCliente(cliente, codCuenta, saldo, codSucursal, fechaCreacion);
        });
    }

    /**
     * Borra un cliente y sus cuentas asociadas de la base de datos.
     *
     * @param dni El DNI del cliente a borrar.
     */
    public void deleteCuenta(String dni) {
        int confirm = JOptionPane.showConfirmDialog(null, "¿Estás seguro de que quieres eliminar este cliente y todas sus cuentas asociadas?", "Confirmar eliminación", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            Session session = sessionFactory.openSession();
            try {
                session.beginTransaction();
                CuentaModel cuenta = session.get(CuentaModel.class, dni);
                if (cuenta != null) {
                    session.delete(cuenta);
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
                loadCuentas(); // Recarga la lista de clientes
            }
        }
    }


    /**
     * Actualiza la información de un cliente en la base de datos.
     *
     * @param row    La fila de la tabla donde se encuentra el cliente.
     * @param column La columna de la tabla que se ha actualizado.
     * @param data   El nuevo valor para el cliente.
     */
    private void updateClienteInDatabase(int row, int column, Object data) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        try {
            String dni = viewA.getTable().getValueAt(row, 0).toString();
            CuentaModel cliente = session.get(CuentaModel.class, dni);
            if (cliente != null) {
                // Actualiza el cliente según la columna modificada
                switch (column) {
                    case 1:
                        cliente.setCliente((String) data);
                        break;
                    case 2:
                        cliente.setCuCodCuenta(Integer.parseInt((String) data));
                        break;
                    case 3:
                        cliente.setCuSaldo(Integer.valueOf((String) data));
                        break;
                    case 4:
                        cliente.setCuCodSucursal(Integer.valueOf((String) data));
                        break;
                    case 5:
                        cliente.setCuFechaCreacion((data.toString()));
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
     * @param cliente  El DNI del cliente.
     * @param codCuenta   El nombre del cliente.
     * @param saldo    El saldo del cliente.
     * @param saldo    El saldo del cliente.
     * @param  codSucursal teléfono del cliente.
     */
    private void createCliente(String cliente, int codCuenta, int saldo, int codSucursal, String fechaCreacion) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            CuentaModel cuenta = new CuentaModel();
            cuenta.setCliente(cliente);
            cuenta.setCuCodCuenta(codCuenta);
            cuenta.setCuSaldo(saldo);
            cuenta.setCuCodSucursal(codSucursal);
            cuenta.setCuFechaCreacion(fechaCreacion);
            session.save(cuenta);
            session.getTransaction().commit();
            JOptionPane.showMessageDialog(null, "Cliente creado con éxito.");
            viewB.getFrame().dispose();
            loadCuentas(); // Recarga la lista de clientes
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
