/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bv.dao;

import vb.entidad.Usuario;

/**
 *
 * @author virtual
 */
public interface LoginDao {

    Usuario validar(String user, String contrasena);

    int recuperar(String email);

}
