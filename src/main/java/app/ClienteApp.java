package app;

import app.controller.ClienteController;
import app.view.ClientesView;
import app.view.CrearClienteView;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class ClienteApp {
    public static void main(String[] args) {
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        ClientesView viewA = new ClientesView();
        CrearClienteView viewB = new CrearClienteView();
        ClienteController controller = new ClienteController(viewA, viewB, sessionFactory);
        viewA.setController(controller);
        viewA.showGUI();
    }
}
