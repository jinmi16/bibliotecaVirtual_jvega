package bv.dao.impl;

import bv.dao.LogTablaDao;
import bv.util.ConectaDb;
import java.util.ArrayList;
import java.util.List;
import vb.entidad.LogTabla;
import bv.util.sql;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

/**
 *
 * @author virtual
 */
public class LogTablaDaoImpl implements LogTablaDao {

    sql conector = new sql();

    @Override
    public boolean registrarLogTabla(LogTabla lt) {
        boolean flag = false;
        String[] parametros = new String[5];
        parametros[0] = lt.getNameEsquema();
        parametros[1] = lt.getNameTabla();
        parametros[2] = lt.getID_REGISTRO();
        parametros[3] = lt.getID_ACCION();
        parametros[4] = lt.getID_USUARIO();
        List<Object[]> datos = conector.execProcedure("[BV].[SP_LOG_TABLA_REGISTRAR]", parametros);
        for (Object[] dato : datos) {
            flag = dato[0].toString().equals("1");
        }
        return flag;
    }

    @Override
    public List<Object[]> listarLog(int idBiblioteca) {
        List<Object[]> lst = new ArrayList<>();
        String[] parametros = new String[2];
        parametros[0] = "LISTAR";
        parametros[1] = String.valueOf(idBiblioteca);
        List<Object[]> datos = conector.execProcedure("[BV].[SP_LOG_CONSULTA]", parametros);
        for (Object[] d : datos) {
            lst.add(d);
        }
        return lst;
    }

    @Override
    public List<Object[]> listarLogDocumental(int idBiblioteca) {
        List<Object[]> lst = new ArrayList<>();
        String[] parametros = new String[2];
        parametros[0] = "LISTAR_HISTORIAL_DOCUMENTAL";
        parametros[1] = "" + idBiblioteca;
        List<Object[]> datos = conector.execProcedure("[BV].[SP_LOG_CONSULTA]", parametros);
        for (Object[] d : datos) {
            lst.add(d);
        }
        return lst;
    }

    @Override
    public LogTabla listarLogpaginacion(int idBiblioteca, int first, int pageSize, String query) {
        LogTabla logTabla = new LogTabla();
        List<LogTabla> lst = new ArrayList<>();
        LogTabla lt;
        ResultSet rs;

        ///----------------------------------------------------------
        Connection conn = ConectaDb.getConnection();

        try {
            CallableStatement cs = conn.prepareCall("{call [BV].[SP_LOG_CONSULTA_PAGINACION](?,?,?,?,?,?)}");
            cs.setString(1, "LISTAR");
            cs.setInt(2, idBiblioteca);
            cs.setInt(3, first);
            cs.setInt(4, pageSize);
            cs.setString(5, query);
            cs.registerOutParameter(6, Types.INTEGER);
            rs = cs.executeQuery();
            while (rs.next()) {
                lt = new LogTabla();
                lt.setFECHA(rs.getString(1));
                lt.setHORA(rs.getString(2));
                lt.setNameTabla(rs.getString(3));
                lt.setNameAccion(rs.getString(4));
                lt.setID_REGISTRO(rs.getString(5));
                lt.setNameUsuario(rs.getString(6));
                lt.setNamePersonal(rs.getString(7));
                lt.setNameBiblioteca(rs.getString(8));
                lst.add(lt);
            }
            logTabla.setLstLogTabla(lst);
            logTabla.setCountQuery(cs.getInt(6));

        } catch (SQLException e) {
            System.out.println("ERROR: " + e);
        }
        return logTabla;

    }

    @Override
    public LogTabla listarLogDocumentalPaginado(int idBiblioteca, int first, int pageSize, String query) {
        LogTabla logTabla = new LogTabla();
        List<LogTabla> lst = new ArrayList<>();
        LogTabla lt;
        ResultSet rs;

        ///----------------------------------------------------------
        Connection conn = ConectaDb.getConnection();

        try {
            CallableStatement cs = conn.prepareCall("{call [BV].[SP_LOG_CONSULTA_PAGINACION](?,?,?,?,?,?)}");
            cs.setString(1, "LISTAR_HISTORIAL_DOCUMENTAL");
            cs.setInt(2, idBiblioteca);
            cs.setInt(3, first);
            cs.setInt(4, pageSize);
            cs.setString(5, query);
            cs.registerOutParameter(6, Types.INTEGER);
            rs = cs.executeQuery();
            while (rs.next()) {
                lt = new LogTabla();
                lt.setFECHA(rs.getString(1));
                lt.setHORA(rs.getString(2));
                lt.setID_DOCUMENTAL(rs.getString(3));
                lt.setNameAccion(rs.getString(4));
                lt.setNameUsuario(rs.getString(5));
                lt.setNamePersonal(rs.getString(6));
                lt.setNameBiblioteca(rs.getString(7));
                lt.setOTRO(rs.getString(8));
                lst.add(lt);
            }
            logTabla.setLstLogTabla(lst);
            logTabla.setCountQuery(cs.getInt(6));

        } catch (SQLException e) {
            System.out.println("ERROR: " + e);
        }
        return logTabla;
    }

}
