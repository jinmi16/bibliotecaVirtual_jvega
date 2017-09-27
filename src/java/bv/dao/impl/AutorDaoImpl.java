package bv.dao.impl;

import bv.dao.AutorDao;
import bv.util.ConectaDb;
import java.util.ArrayList;
import java.util.List;
import vb.entidad.Autor;
import vb.entidad.LogTabla;
import bv.util.sql;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

public class AutorDaoImpl implements AutorDao {

    sql conector = new sql();

    @Override
    public List<Autor> obtenerEntidades(String idBiblioteca) {
        String[] parametros = new String[7];
        parametros[0] = "";
        parametros[1] = "";
        parametros[2] = "";
        parametros[3] = "";
        parametros[4] = "";
        parametros[5] = "LIST_AUTOR";
        parametros[6] = idBiblioteca;
        List<Object[]> listaIn = conector.execProcedure("BV.SP_MANTENIMIENTO_AUTOR", parametros);
        ArrayList<Autor> lista = new ArrayList<>();
        Autor aut;
        for (Object[] dato : listaIn) {
            aut = new Autor();
            aut.setID_AUTOR(Integer.parseInt(dato[0].toString()));
            aut.setNOMBRE(dato[1].toString());
            aut.setAPELLIDO_PATERNO(dato[2].toString());
            aut.setAPELLIDO_MATERNO(dato[3].toString());
            aut.setID_PAIS(dato[4].toString());
            aut.setPAIS(dato[5].toString());
            lista.add(aut);
        }
        return lista;
    }
     @Override
    public Autor listAutorPaginado(String idBiblioteca, int first, int pageSize, String query) {
        Autor autor = new Autor();
        List<Autor> lst = new ArrayList<>();
        Autor a;
        ResultSet rs;
        ///----------------------------------------------------------
        Connection conn = ConectaDb.getConnection();
        try {
            CallableStatement cs = conn.prepareCall("{call [BV].[SP_AUTOR_LISTA_PAGINADA](?,?,?,?,?)}");
            cs.setString(1,idBiblioteca);
            cs.setInt(2, first);
            cs.setInt(3, pageSize);
            cs.setString(4, query);
            cs.registerOutParameter(5, Types.INTEGER);
            rs = cs.executeQuery();
            while (rs.next()) {
                a = new Autor();
                a.setID_AUTOR(rs.getInt(1));
                a.setNOMBRE(rs.getString(2));
                a.setAPELLIDO_PATERNO(rs.getString(3));
                a.setAPELLIDO_MATERNO(rs.getString(4));
                a.setID_PAIS(rs.getString(5));
                a.setPAIS(rs.getString(6));
                a.setALTERNATIVO(rs.getString(7));
                a.setAUTOR(rs.getString(8));
                lst.add(a);
            }
            autor.setLstAutor(lst);
            autor.setCountQuery(cs.getInt(5));
        } catch (SQLException e) {
            System.out.println("ERROR: " + e);
        }
        return autor;
    }
    @Override
    public int crearEntidad(Autor aut, int idUsuario) {
        int n = 0;
        List<Object[]> listaIn;
        String[] parametros = new String[7];
        parametros[0] = "";
        parametros[1] = aut.getNOMBRE();
        parametros[2] = aut.getAPELLIDO_PATERNO();
        parametros[3] = aut.getAPELLIDO_MATERNO();
        parametros[4] = aut.getID_PAIS();
        parametros[5] = "ADD_AUTOR";
        parametros[6] = aut.getID_BIBLIOTECA_REGISTRO();
        listaIn = conector.execProcedure("BV.SP_MANTENIMIENTO_AUTOR", parametros);
        for (Object[] dato : listaIn) {
            if (dato[0].toString().equals("1")) {
                n = 1;
                LogTablaDaoImpl LogTablaDaoImpl = new LogTablaDaoImpl();
                LogTablaDaoImpl.registrarLogTabla(new LogTabla("BV", "AUTOR", "", "1", String.valueOf(idUsuario)));
            }
        }
        return n;
    }

    @Override
    public int eliminarEntidad(int codigo, int idUsuario) {
        int n = 0;
        List<Object[]> listaIn;
        String[] parametros = new String[7];
        parametros[0] = codigo + "";
        parametros[1] = "";
        parametros[2] = "";
        parametros[3] = "";
        parametros[4] = "";
        parametros[5] = "DEL_AUTOR";
        parametros[6] = "";
        listaIn = conector.execProcedure("BV.SP_MANTENIMIENTO_AUTOR", parametros);
        for (Object[] dato : listaIn) {
            if (dato[0].toString().equals("1")) {
                n = 1;
                LogTablaDaoImpl LogTablaDaoImpl = new LogTablaDaoImpl();
                LogTablaDaoImpl.registrarLogTabla(new LogTabla("BV", "AUTOR", "", "3", String.valueOf(idUsuario)));
            }
        }
        return n;
    }

    @Override
    public int actualizarEntidad(Autor aut, int idUsuario) {
        int n = 0;
        List<Object[]> listaIn;
        String[] parametros = new String[7];
        parametros[0] = String.valueOf(aut.getID_AUTOR());
        parametros[1] = aut.getNOMBRE();
        parametros[2] = aut.getAPELLIDO_PATERNO();
        parametros[3] = aut.getAPELLIDO_MATERNO();
        parametros[4] = aut.getID_PAIS();
        parametros[5] = "UPD_AUTOR";
        parametros[6] = aut.getID_BIBLIOTECA_REGISTRO();
        listaIn = conector.execProcedure("BV.SP_MANTENIMIENTO_AUTOR", parametros);
        for (Object[] dato : listaIn) {
            if (dato[0].toString().equals("1")) {
                n = 1;
                LogTablaDaoImpl LogTablaDaoImpl = new LogTablaDaoImpl();
                LogTablaDaoImpl.registrarLogTabla(new LogTabla("BV", "AUTOR", "", "2", String.valueOf(idUsuario)));
            }
        }
        return n;
    }

    @Override
    public int buscarEntidad(Autor aut) {
        int n = 0;
        List<Object[]> listaIn;
        String[] parametros = new String[7];
        parametros[0] = "";
        parametros[1] = aut.getNOMBRE();
        parametros[2] = aut.getAPELLIDO_PATERNO();
        parametros[3] = aut.getAPELLIDO_MATERNO();
        parametros[4] = aut.getID_PAIS();
        parametros[5] = "FIND_AUTOR";
        parametros[6] = aut.getID_BIBLIOTECA_REGISTRO();
        listaIn = conector.execProcedure("BV.SP_MANTENIMIENTO_AUTOR", parametros);
        for (Object[] dato : listaIn) {
            if (dato[0].toString().equals("1")) {
                n = 1;
            }
        }
        return n;
    }

    @Override
    public int buscarEntidadAlternativo(Autor aut) {
        int n = 0;
        List<Object[]> listaIn;
        String[] parametros = new String[5];
        parametros[0] = "";
        parametros[1] = aut.getID_PAIS();
        parametros[2] = "FIND_ALTERNATIVO";
        parametros[3] = aut.getID_BIBLIOTECA_REGISTRO();
        parametros[4] = aut.getALTERNATIVO();
        listaIn = conector.execProcedure("BV.SP_MANTENIMIENTO_AUTOR_ALTERNATIVO", parametros);
        for (Object[] dato : listaIn) {
            if (dato[0].toString().equals("1")) {
                n = 1;
            }
        }
        return n;
    }

    @Override
    public int crearEntidadAlternativo(Autor aut, int idUsuario) {
        int n = 0;
        List<Object[]> listaIn;
        String[] parametros = new String[5];
        parametros[0] = "";
        parametros[1] = aut.getID_PAIS();
        parametros[2] = "ADD_AUTOR_ALTERNATIVO";
        parametros[3] = aut.getID_BIBLIOTECA_REGISTRO();
        parametros[4] = aut.getALTERNATIVO();
        listaIn = conector.execProcedure("BV.SP_MANTENIMIENTO_AUTOR_ALTERNATIVO", parametros);
        for (Object[] dato : listaIn) {
            if (dato[0].toString().equals("1")) {
                n = 1;
                LogTablaDaoImpl LogTablaDaoImpl = new LogTablaDaoImpl();
                LogTablaDaoImpl.registrarLogTabla(new LogTabla("BV", "AUTOR", "", "1", String.valueOf(idUsuario)));
            }
        }
        return n;
    }

    @Override
    public List<Object[]> cboAutores(String idBiblioteca) {
        String[] parametros = new String[7];
        parametros[0] = "";
        parametros[1] = "";
        parametros[2] = "";
        parametros[3] = "";
        parametros[4] = "";
        parametros[5] = "LIST_CBOAUTOR";
        parametros[6] = idBiblioteca;
        ArrayList<Object[]> lista = conector.execProcedure("BV.SP_MANTENIMIENTO_AUTOR", parametros);
        return lista;
    }

    @Override
    public ArrayList<String> listAutorDocumentallXidDocumental(String idDocumental) {
        ArrayList<String> lst = new ArrayList<>();
        String[] parametros = new String[2];
        parametros[0] = idDocumental;
        parametros[1] = "LISTAR_X_ID";
        ArrayList<Object[]> data = conector.execProcedure("[BV].[SP_AUTOR_DOCUMENTAL_LISTAR_X_ID_DOCUMENTAL]", parametros);
        for (Object[] d : data) {
            String obj = d[0].toString();
            lst.add(obj);
        }
        return lst;

    }

    @Override
    public List<Autor> listarAutorIdDocumental(String idDocumental) {
        String[] parametros = new String[1];
        parametros[0] = idDocumental;
        List<Object[]> listaIn = conector.execProcedure("BV.SP_LISTAR_AUTOR_ID_DOCUMENTAL", parametros);
        ArrayList<Autor> lista = new ArrayList<>();
        for (Object[] dato : listaIn) {
            Autor aut = new Autor();
            aut.setID_AUTOR(Integer.parseInt(dato[0].toString()));
            aut.setNOMBRE(dato[1].toString());
            aut.setAPELLIDO_PATERNO(dato[2].toString());
            aut.setAPELLIDO_MATERNO(dato[3].toString());
            aut.setID_PAIS(dato[4].toString());
            aut.setPAIS(dato[5].toString());
            lista.add(aut);
        }
        return lista;
    }

   

}
