package vb.Listener;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;


public class SessionListener implements HttpSessionListener {

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        System.out.println("Se creo una conexion");
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        System.out.println("se cerro una conexion");
    }
}
