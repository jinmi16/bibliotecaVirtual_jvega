package bv.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ConectaDb {

    public ConectaDb() {
    }

    public static Connection getConnection() {
        Connection cn = null;
        try {
            ResourceBundle props = ResourceBundle.getBundle("bv.util.bv");
            Class.forName(props.getString("DRIVER"));
            cn = DriverManager.getConnection(props.getString("URL"),props.getString("USUARIO"),props.getString("PASSWORD"));
           // System.out.println("CONEXION EXITOSA");

        } catch (ClassNotFoundException | SQLException objException) {
            System.out.println("Error: " + objException.getMessage());
        }
        return cn;
    }

}
