/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bv.dao;

import java.util.ArrayList;
import java.util.List;
import vb.entidad.Biblioteca;
import vb.entidad.PerfilDocumental;
import vb.entidad.PerfilDocumentalDetalle;

/**
 *
 * @author virtual
 */
public interface BibliotecaDao {

    int crearEntidad(Biblioteca biblioteca, int idUsuario);

    int eliminarEntidad(int codigo, int idUsuario);

    int actualizarEntidad(Biblioteca biblioteca, int idUsuario);

    List<Biblioteca> obtenerEntidades();

    List<Object[]> obtenerRegiones();

    List<Object[]> obtenerProvincias(String idRegion);

    List<Object[]> obtenerDistritos(String idRegion, String idProvincia);

    Biblioteca cargarEntidad(String idBiblioteca);

    ArrayList<Biblioteca> lstMapeoBibliotecas(String idBiblioteca);
    ArrayList<PerfilDocumental> lstPerfilBiblioteca(PerfilDocumental perfilDocumental);
    ArrayList<PerfilDocumentalDetalle> lstPerfilDocumentalDetalle(PerfilDocumental perfilDocumental);
    //  ArrayList<PerfilDocumental>
    

    Biblioteca oobtenerServidorBiblioteca(String idBiblioteca);

}
