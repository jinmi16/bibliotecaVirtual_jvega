/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bv.dao;

import java.util.ArrayList;
import java.util.List;
import vb.entidad.Autor;

/**
 *
 * @author virtual
 */
public interface AutorDao {

    List<Autor> obtenerEntidades(String idBiblioteca);
    Autor listAutorPaginado(String idBiblioteca,int first, int pageSize,String query);

    int crearEntidad(Autor aut, int idUsuario);

    int eliminarEntidad(int codigo, int idUsuario);

    int actualizarEntidad(Autor aut, int idUsuario);

    int buscarEntidad(Autor aut);

    int buscarEntidadAlternativo(Autor aut);

    int crearEntidadAlternativo(Autor aut, int idUsuario);

    List<Object[]> cboAutores(String idBiblioteca);

    ArrayList<String> listAutorDocumentallXidDocumental(String idDocumental);

    List<Autor> listarAutorIdDocumental(String idDocumental);

}
