package app;

import app.controller.CuentaController;
import app.view.CuentaView;
import app.view.CrearCuentaView;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class

CuentaApp {
    public static void main(String[] args) {
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        CuentaView viewC = new CuentaView();
        CrearCuentaView viewD = new CrearCuentaView();
        CuentaController controller = new CuentaController(viewC, viewD, sessionFactory);
        viewC.setController(controller);
        viewC.showGUI();
    }
}
