/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bv.dao;

import java.util.List;
import vb.entidad.Cobertura;

/**
 *
 * @author virtual
 */
public interface EspacialDao {

    int CrearCobertura(Cobertura cobertura);

    int editarCobertura(Cobertura cobertura);

    List<Cobertura> obtenerCobertura();

    List<Object[]> llenaComboCobertura(String idBiblioteca);

    List<Object[]> llenaComboPais();

    Cobertura obtenerXidCoberturaEspacial(String idCoberturaespacial);

}
