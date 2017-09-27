/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bv.dao;

import java.util.ArrayList;
import java.util.List;
import vb.entidad.Serie;

/**
 *
 * @author virtual
 */
public interface SerieDao {

    int crearEntidad(Serie serie);

    int actualizarEntidad(Serie serie);

    ArrayList<String> listSerieDocumentallXidDocumental(String idDocumental);

    List<Serie> obtenerSeleccion(String series);

    List<Object[]> llenaComboSerie(String idBiblioteca);

    List<Serie> listarSerieIdDocumental(String idDocumental);

    int crearEntidad(Serie serie, String idBiblioteca);

}
