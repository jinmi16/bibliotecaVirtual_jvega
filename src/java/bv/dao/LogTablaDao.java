/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bv.dao;

import java.util.List;
import vb.entidad.LogTabla;

/**
 *
 * @author virtual
 */
public interface LogTablaDao {

    boolean registrarLogTabla(LogTabla lt);

    List<Object[]> listarLog(int idBiblioteca);

    List<Object[]> listarLogDocumental(int idBiblioteca);
    LogTabla listarLogDocumentalPaginado(int idBiblioteca,int first, int pageSize,String query);
    LogTabla listarLogpaginacion(int idBiblioteca,int first, int pageSize,String query);

}
