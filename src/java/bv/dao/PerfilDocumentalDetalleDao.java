/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bv.dao;

import java.util.List;
import vb.dto.PerfilDto;
import vb.entidad.PerfilDocumentalDetalle;

/**
 *
 * @author virtual
 */
public interface PerfilDocumentalDetalleDao {

    List<PerfilDocumentalDetalle> listarPerfilDocumentalDetalle(String perfil);
    List<PerfilDocumentalDetalle> listarPerfilDocumentalDetalleXBiblioteca(String perfil,String idBiblioteca);
    List<PerfilDocumentalDetalle> listarPerfilDocumentalDetalleIdPerfilIdBiblioteca(String perfil);

    List<Object[]> obtenerPerfiles();
    List<Object[]> obtenerestadoControlRec(String grupo);

    int editarListPerfildocumentaldetalle(List<PerfilDocumentalDetalle> lstPerfilDocumentalDetalle, int idUsuario);

    PerfilDto obtenerPerfilXidDocumental(String idDocumental);

}
