package org.example;

import org.example.view.ClienteView2A;
import org.example.view.ClienteView2B;
import org.example.controller.ClienteController;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class ClienteApp {
    public static void main(String[] args) {
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        ClienteView2A viewA = new ClienteView2A();
        ClienteView2B viewB = new ClienteView2B();
        ClienteController controller = new ClienteController(viewA, viewB, sessionFactory);
        viewA.setController(controller);
        viewA.showGUI();
    }
}
