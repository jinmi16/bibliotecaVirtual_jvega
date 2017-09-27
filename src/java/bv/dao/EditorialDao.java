/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bv.dao;

import java.util.List;
import vb.entidad.Editorial;

/**
 *
 * @author virtual
 */
public interface EditorialDao {

    int crearEntidad(Editorial ed);

    int actualizarEntidad(Editorial ed);

    List<Editorial> obtenerEntidades();

    Editorial buscarEntidad(int codigo);

    List<Object[]> llenaComboEditorial(String idBiblioteca);

}
