package org.example.controller;

import org.example.model.ClienteModel;
import org.example.view.ClienteView;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

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
        loadClientes();
    }

    private void loadClientes() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Query<ClienteModel> query = session.createQuery("FROM ClienteModel", ClienteModel.class);
        List<ClienteModel> clientes = query.list();

        DefaultTableModel tableModel = new DefaultTableModel(new Object[]{"DNI", "Nombre", "Apellido", "Teléfono"}, 0);
        for (ClienteModel cliente : clientes) {
            tableModel.addRow(new Object[]{
                    cliente.getClDni(),
                    cliente.getClNombre(),
                    cliente.getClApellido(),
                    cliente.getClTelefono()
            });
        }

        view.getTable().setModel(tableModel);

        session.getTransaction().commit();
        session.close();
    }
}
