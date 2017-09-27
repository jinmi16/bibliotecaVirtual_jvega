/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bv.dao;

import java.util.List;
import vb.entidad.Personal;

/**
 *
 * @author virtual
 */
public interface PersonalDao {

    int crearEntidad(Personal p, int idUsuario);

    int eliminarEntidad(int codigo, int idUsuario);

    int actualizarEntidad(Personal p, int idUsuario);

    List<Personal> obtenerEntidades();

    Personal buscarEntidad(int codigo);
    Personal cuentaGet(int idUsuario);
    Personal cuentaupd(Personal p);
    

    List<Object[]> obtenerBiblioteca(String idTipoUsuario, String idBibliotecaMediador);

    List<Personal> obtenerEntidades(String idBiblioteca);
    Personal listaPaginada(String idBiblioteca,String idTipoUsuario,int first, int pageSize,String query);

    List<Personal> obtenerEntidades2(String idBiblioteca, String idTipoUsuario);

    String nomBiblioteca(String idBiblioteca);
    List<Object[]> obtenerBibliotecaDocumental(String idBibliotecaMediador);

}
