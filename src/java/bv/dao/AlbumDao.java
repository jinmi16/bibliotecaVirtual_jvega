/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bv.dao;

import java.util.List;
import vb.entidad.Album;

/**
 *
 * @author virtual
 */
public interface AlbumDao {

    List<Object[]> llenaComboAlbum(String ID_BIBLIOTECA);
    int creaAlbum(Album album);
    
}
