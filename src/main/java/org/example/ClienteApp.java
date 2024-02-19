package org.example;

import org.example.controller.ClienteController;
import org.example.view.ClienteView;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class ClienteApp {
    public static void main(String[] args) {
        ClienteView view = new ClienteView();
        SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
        ClienteController controller = new ClienteController(view, sessionFactory);

        javax.swing.SwingUtilities.invokeLater(() -> view.setVisible(true));
    }
}
