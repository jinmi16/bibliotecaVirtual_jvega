/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bv.dao;

import java.util.List;
import vb.entidad.FormatoMedio;

/**
 *
 * @author virtual
 */
public interface FormatoMedioDao {

    List<Object[]> llenaComboFormatoMedio();

    int crearEntidad(FormatoMedio fm);

    int actualizarEntidad(FormatoMedio fm);

    FormatoMedio buscarEntidad(int codigo);

}
