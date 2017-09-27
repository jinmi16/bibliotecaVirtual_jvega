/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bv.dao;

import java.util.ArrayList;
import vb.entidad.AuxContenido;

/**
 *
 * @author virtual
 */
public interface TablaContenidoDao {
    
    ArrayList<AuxContenido> lstTablaContenidosXidDocumental(String idDocumental);
    
}
