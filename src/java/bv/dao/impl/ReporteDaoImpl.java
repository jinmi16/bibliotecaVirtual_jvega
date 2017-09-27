package bv.dao.impl;

import bv.dao.ReporteDao;
import vb.entidad.reporteUsuarioDocumental;
import java.util.ArrayList;
import java.util.List;
import bv.util.sql;

/**
 *
 * @author Renato Vásquez Tejada - renatovt11@gmail.com
 */
public class ReporteDaoImpl implements ReporteDao {

    sql conector = new sql();

    @Override
    public List<Object[]> listUsuarioDocumental(String date, String idBiblioteca) {
        String[] parametros = new String[2];
        parametros[0] = date;
        parametros[1] = idBiblioteca;
        List<Object[]> usuarioDocumental = conector.execProcedure("BV.SP_REPORTE_USUARIO_DOCUMENTAL", parametros);
        return usuarioDocumental;
    }

    @Override
    public List<reporteUsuarioDocumental> obtenerEntidades(String date, String idBiblioteca) {
        List<reporteUsuarioDocumental> listU = new ArrayList<>();
        String[] parametros = new String[2];
        parametros[0] = date;
        parametros[1] = idBiblioteca;
        List<Object[]> lista = conector.execProcedure("BV.SP_REPORTE_USUARIO_DOCUMENTAL", parametros);
        for (Object[] list : lista) {
            reporteUsuarioDocumental usuarioD = new reporteUsuarioDocumental();
            usuarioD.setUSUARIO(list[0].toString());
            usuarioD.setCONTEO(Integer.parseInt(list[1].toString()));
            listU.add(usuarioD);
        }
        return listU;
    }

    @Override
    public List<Object[]> listPeriodoTrabajadores(int Ano, int mesIni, int mesFin, int idUsuario, int TipoUsuario, String idBiblioteca, int anoFin) {
        String[] parametros = new String[7];
        parametros[0] = Ano + "";
        parametros[1] = mesIni + "";
        parametros[2] = mesFin + "";
        if (TipoUsuario == 3) {
            parametros[3] = idUsuario + "";
            parametros[4] = "LIST_UNICO";
        } else {
            parametros[3] = "";
            parametros[4] = "LIST_TOTAL";
        }
        parametros[5] = idBiblioteca;
        parametros[6] = ""+anoFin;
        
        List<Object[]> list = conector.execProcedure("BV.SP_REPORTE_RANGO_TRABAJADORES", parametros);
        return list;
    }

    @Override
    public ArrayList<Object[]> listaConsolidadoMensual(int Anio, int idBiblioteca) {
        String[] parametros = new String[3];
        parametros[0] = String.valueOf(Anio);
        parametros[1] = String.valueOf(idBiblioteca);
        parametros[2] = "LIST_DATOS";
        ArrayList<Object[]> lista = conector.execProcedure("[BV].[SP_REPORTE_LINEAL]", parametros);
        return lista;
    }

    @Override
    public ArrayList<Object[]> listaUsusariosMensual(int Anio, int idBiblioteca) {
        String[] parametros = new String[3];
        parametros[0] = String.valueOf(Anio);
        parametros[1] = String.valueOf(idBiblioteca);
        parametros[2] = "LIST_USUARIOS";
        ArrayList<Object[]> lstUsuario = conector.execProcedure("[BV].[SP_REPORTE_LINEAL]", parametros);
        return lstUsuario;
    }

    @Override
    public int conteoMaxMensual(int Anio, int idBiblioteca) {
        Integer i = 0;
        String[] parametros = new String[3];
        parametros[0] = String.valueOf(Anio);
        parametros[1] = String.valueOf(idBiblioteca);
        parametros[2] = "MAX_CONTEO";
        ArrayList<Object[]> lstUsuario = conector.execProcedure("[BV].[SP_REPORTE_LINEAL]", parametros);
        for (Object[] data : lstUsuario) {
            i = Integer.parseInt(data[0].toString());
        }
        return i;
    }

    @Override
    public List<reporteUsuarioDocumental> obtenerPorPeriodo(int ano, int fecini, int fecfin, int idUsuario, int TipoUsuario, String idBiblioteca,int anoFin) {
        List<reporteUsuarioDocumental> listU = new ArrayList<>();
        String[] parametros = new String[7];
        parametros[0] = ano + "";
        parametros[1] = fecini + "";
        parametros[2] = fecfin + "";
        if (TipoUsuario == 3) {
            parametros[3] = idUsuario + "";
            parametros[4] = "LIST_UNICO";
        } else {
            parametros[3] = "";
            parametros[4] = "LIST_TOTAL";
        }
        parametros[5] = idBiblioteca;
        parametros[6] = anoFin+"";
        List<Object[]> lista = conector.execProcedure("BV.SP_REPORTE_RANGO_TRABAJADORES", parametros);
        for (Object[] list : lista) {
            reporteUsuarioDocumental usuarioD = new reporteUsuarioDocumental();
            usuarioD.setUSUARIO(list[0].toString());
            usuarioD.setCONTEO(Integer.parseInt(list[1].toString()));
            listU.add(usuarioD);
        }
        return listU;
    }

    @Override
    public List<Object[]> listaInserUpdTotales(int Ano, int mesIni, int mesFin, int idUsuario, int TipoUsuario, String idBiblioteca,int anoFin) {
        String[] parametros = new String[7];
        parametros[0] = Ano + "";
        parametros[1] = mesIni + "";
        parametros[2] = mesFin + "";
        if (TipoUsuario == 3) {
            parametros[3] = idUsuario + "";
            parametros[4] = "LIST_INSERT_UPD_XUSUARIO";
        } else {
            parametros[3] = "";
            parametros[4] = "LIST_INSERT_UPD_TOTAL";
        }
        parametros[5] = idBiblioteca;
        parametros[6] = anoFin+"";
        List<Object[]> list = conector.execProcedure("BV.SP_REPORTE_RANGO_TRABAJADORES", parametros);
        return list;
    }

}
