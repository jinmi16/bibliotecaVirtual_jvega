/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bv.dao;

import java.util.ArrayList;
import java.util.List;
import vb.entidad.reporteUsuarioDocumental;

/**
 *
 * @author virtual
 */
public interface ReporteDao {

    List<Object[]> listUsuarioDocumental(String date, String idBiblioteca);

    List<reporteUsuarioDocumental> obtenerEntidades(String date, String idBiblioteca);

    List<Object[]> listPeriodoTrabajadores(int Ano, int mesIni, int mesFin, int idUsuario, int TipoUsuario, String idBiblioteca,int anoFin);

    ArrayList<Object[]> listaConsolidadoMensual(int Anio, int idBiblioteca);

    ArrayList<Object[]> listaUsusariosMensual(int Anio, int idBiblioteca);

    int conteoMaxMensual(int Anio, int idBiblioteca);

    List<reporteUsuarioDocumental> obtenerPorPeriodo(int ano, int fecini, int fecfin, int idUsuario, int TipoUsuario, String idBiblioteca,int anoFin);

    List<Object[]> listaInserUpdTotales(int Ano, int mesIni, int mesFin, int idUsuario, int TipoUsuario, String idBiblioteca,int anoFin);

}
