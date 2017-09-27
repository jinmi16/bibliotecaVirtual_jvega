package bv.dao.impl;

import bv.dao.LoginDao;
import java.util.ArrayList;
import java.util.Properties;
import vb.entidad.Usuario;
import bv.util.sql;

/**
 *
 * @author Renato Vásquez Tejada - renatovt11@gmail.com
 */
public class LoginDaoImpl implements LoginDao{
    sql conector = new sql();
    
    @Override
    public Usuario validar(String user, String contrasena) {
        Usuario usuario = null;
        String[] array = new String[2];
        array[0] = user;
        array[1] = contrasena;
        ArrayList<Object[]> data = conector.execProcedure("BV.SP_LOGIN_USUARIO", array);
        for(Object[] datos : data){
            usuario = new Usuario();
            usuario.setID_USUARIO(Integer.parseInt(datos[0].toString()));
            usuario.setUSUARIO(datos[1].toString());
            usuario.setCONTRASENA(contrasena);
            usuario.setACTIVO(datos[3].toString());
            usuario.setID_PERSONAL_BIBLIOTECA(Integer.parseInt(datos[4].toString()));
            usuario.setID_TIPO_USUARIO(Integer.parseInt(datos[5].toString()));
            usuario.setCAMBIO_PASSW_SISTEMA(Integer.parseInt(datos[6].toString()));
            usuario.setTipoUsuario(datos[7].toString());
        }
        return usuario;
    }

    @Override
    public int recuperar(String email) {
        int correcto = 0;
        if(email.equals("renatovt11@gmail.com")){
            Properties props = new Properties(); 
            props.put("mail.smtp.host","mail.lineadecodigo.com");
            props.put("mail.transport.protocol","smtp");
            props.put("mail.smtp.auth", "true");
            props.setProperty("mail.user", "renatovt12@gmail.com");
            props.setProperty("mail.password", "29682628112937");
            correcto = 1;
        }
        return correcto;
    }
}
