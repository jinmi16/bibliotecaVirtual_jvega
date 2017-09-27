/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bv.dao;

import java.util.ArrayList;
import java.util.List;
import vb.entidad.Tema;

/**
 *
 * @author virtual
 */
public interface TemaDao {

    int crearEntidad(Tema tema);

    int actualizarEntidad(Tema tema);

    List<Tema> obtenerEntidades();

    List<Object[]> llenaComboTema(String idBiblioteca);

    ArrayList<String> listTemaDocumentalXidDocumental(String idDocumental);

    List<Tema> listarSerieIdDocumental(String idDocumental);

    ArrayList<Tema> obtenerSelecionTemas(String s);

}
