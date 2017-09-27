/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bv.dao;

import java.util.ArrayList;
import java.util.List;
import vb.entidad.Coleccion;

/**
 *
 * @author virtual
 */
public interface ColeccionDao {

    int crearEntidad(Coleccion coleccion);

    int actualizarEntidad(Coleccion coleccion);

    List<Object[]> llenaComboColeccion(String idBiblioteca);

    ArrayList<String> listColeccionDocumentallXidDocumental(String idDocumental);

    List<Coleccion> obtenerSeleccion(String colecciones);

    List<Coleccion> listarColeccionIdDocumental(String idDocumental);

    int crearEntidad(Coleccion coleccion, String idBiblioiteca);

}
