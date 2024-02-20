package app;

import app.controller.ClienteController;
import app.view.ClienteView2A;
import app.view.ClienteView2B;
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
