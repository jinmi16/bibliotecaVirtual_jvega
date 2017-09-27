/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bv.dao;

import java.util.List;
import vb.entidad.PerfilDocumental;

/**
 *
 * @author virtual
 */
public interface PerfilDocumentalDao {

    List<PerfilDocumental> listaPerfil();

    List<PerfilDocumental> listaPerfilDocumental(String idBiblioteca);

}
