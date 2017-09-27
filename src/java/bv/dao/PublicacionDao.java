/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bv.dao;

import java.util.ArrayList;
import vb.dto.PublicacionDto;

/**
 *
 * @author virtual
 */
public interface PublicacionDao {

    ArrayList<PublicacionDto> listPublicacion(String ID_PERFIL, String ID_BIBLIOTECA, String visibilidad);
PublicacionDto listPublicacionPaginado(String ID_PERFIL, String ID_BIBLIOTECA, String visibilidad,int first, int pageSize,String query);

    int cambiaVisibilidad(PublicacionDto pub);

}
