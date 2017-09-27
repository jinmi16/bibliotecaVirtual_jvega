package vb.servicio;

import vb.entidad.Usuario;

/**
 *
 * @author Renato Vásquez Tejada - renatovt11@gmail.com
 */
public interface loginService {
    
    public Usuario validar(String user, String password);
    public int recuperar(String email);
    
}
