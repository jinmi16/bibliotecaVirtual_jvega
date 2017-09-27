/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bv.dao;

import java.util.List;
import vb.entidad.Institucion;

/**
 *
 * @author virtual
 */
public interface InstitucionDao {

    int crearEntidad(Institucion institucion, int idUsuario);

    List<Institucion> obtenerEntidades();

    List<Object[]> llenaComboAvanzado();

    List<Object[]> llenaComboTipoInstitucion();

}
