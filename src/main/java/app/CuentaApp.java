package app;

import app.controller.CuentaController;
import app.view.CuentaView;
import app.view.CrearCuentaView;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CuentaApp {
    public static void main(String[] args) {
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        CuentaView viewA = new CuentaView();
        CrearCuentaView viewB = new CrearCuentaView();
        CuentaController controller = new CuentaController(viewA, viewB, sessionFactory);
        viewA.setController(controller);
        viewA.showGUI();
    }
}
