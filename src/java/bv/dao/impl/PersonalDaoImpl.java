package bv.dao.impl;

import bv.dao.PersonalDao;
import bv.util.ConectaDb;
import java.util.ArrayList;
import java.util.List;
import vb.entidad.LogTabla;
import vb.entidad.Personal;
import bv.util.sql;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Types;
import vb.dto.PublicacionDto;

public class PersonalDaoImpl implements PersonalDao {

    sql conector = new sql();

    @Override
    public int crearEntidad(Personal p, int idUsuario) {
        int n = 0;
        String[] parametros = new String[11];
        parametros[0] = String.valueOf(p.getID_PERSONAL_BIBLIOTECA());
        parametros[1] = p.getNOMBRES();
        parametros[2] = p.getPATERNO();
        parametros[3] = p.getMATERNO();
        parametros[4] = p.getDNI();
        parametros[5] = String.valueOf(p.getID_BIBLIOTECA_MEDIADOR());
        parametros[6] = p.getCARGO();
        parametros[7] = p.getCORREO();
        if (p.isBoolREPRESENTANTE()) {
            parametros[8] = "1";
        } else {
            parametros[8] = "0";
        }
        parametros[9] = "INSERT_PERSONAL_BIBLIOTECA";
        parametros[10] = p.getIdTipoUsuario();
        ArrayList<Object[]> result = conector.execProcedure("BV.SP_MANTENIMIENTO_PERSONAL_BIBLIOTECA_INSERT", parametros);
        for (Object[] dat : result) {
            if (dat[0].toString().equals("1")) {
                n = 1;
                LogTablaDaoImpl LogTablaDaoImpl = new LogTablaDaoImpl();
                LogTablaDaoImpl.registrarLogTabla(new LogTabla("BV", "PERSONAL_BIBLIOTECA", "", "1", String.valueOf(idUsuario)));
                LogTablaDaoImpl.registrarLogTabla(new LogTabla("BV", "USUARIO", "", "1", String.valueOf(idUsuario)));
            }
        }
        return n;
    }

    @Override
    public int eliminarEntidad(int codigo, int idUsuario) {
        int n = 0;
        String[] parametros = new String[10];
        parametros[0] = codigo + "";
        parametros[1] = "";
        parametros[2] = "";
        parametros[3] = "";
        parametros[4] = "";
        parametros[5] = "";
        parametros[6] = "";
        parametros[7] = "";
        parametros[8] = "";
        parametros[9] = "DEL_PERSONAL_BIBLIOTECA";
        ArrayList<Object[]> result = conector.execProcedure("BV.SP_MANTENIMIENTO_PERSONAL_BIBLIOTECA", parametros);
        if (result != null) {
            n = 1;
            LogTablaDaoImpl LogTablaDaoImpl = new LogTablaDaoImpl();
            LogTablaDaoImpl.registrarLogTabla(new LogTabla("BV", "PERSONAL_BIBLIOTECA", String.valueOf(codigo), "3", String.valueOf(idUsuario)));
        }
        return n;
    }

    @Override
    public int actualizarEntidad(Personal p, int idUsuario) {
        int n = 0;
        String[] parametros = new String[11];
        parametros[0] = String.valueOf(p.getID_PERSONAL_BIBLIOTECA());
        parametros[1] = p.getNOMBRES();
        parametros[2] = p.getPATERNO();
        parametros[3] = p.getMATERNO();
        parametros[4] = p.getDNI();
        parametros[5] = String.valueOf(p.getID_BIBLIOTECA_MEDIADOR());
        parametros[6] = p.getCARGO();
        parametros[7] = p.getCORREO();
        if (p.isBoolREPRESENTANTE()) {
            parametros[8] = "1";
        } else {
            parametros[8] = "0";
        }
        parametros[9] = "UPD_PERSONAL_BIBLIOTECA";
        if (p.isBoolActivo()) {
            parametros[10] = "1";
        } else {
            parametros[10] = "0";
        }
        ArrayList<Object[]> result = conector.execProcedure("BV.SP_MANTENIMIENTO_PERSONAL_BIBLIOTECA_UPDATE", parametros);
        if (result != null) {
            n = 1;
            LogTablaDaoImpl LogTablaDaoImpl = new LogTablaDaoImpl();
            LogTablaDaoImpl.registrarLogTabla(new LogTabla("BV", "PERSONAL_BIBLIOTECA", String.valueOf(p.getID_PERSONAL_BIBLIOTECA()), "2", String.valueOf(idUsuario)));

        }
        return n;

    }

    @Override
    public List<Personal> obtenerEntidades() {
        List<Personal> lstPersonal = new ArrayList<>();
        String[] array = new String[10];
        array[0] = "";
        array[1] = "";
        array[2] = "";
        array[3] = "";
        array[4] = "";
        array[5] = "";
        array[6] = "";
        array[7] = "";
        array[8] = "";
        array[9] = "LIST_PERSONAL_BIBLIOTECA";
        ArrayList<Object[]> data = conector.execProcedure("BV.SP_MANTENIMIENTO_PERSONAL_BIBLIOTECA", array);
        for (Object[] datos : data) {
            Personal personal = new Personal();
            personal.setID_PERSONAL_BIBLIOTECA(Integer.parseInt(datos[0].toString()));
            personal.setNOMBRES(datos[1].toString());
            personal.setPATERNO(datos[2].toString());
            personal.setMATERNO(datos[3].toString());
            personal.setDNI(datos[4].toString());
            personal.setID_BIBLIOTECA_MEDIADOR(Integer.parseInt(datos[5].toString()));
            personal.setBIBLIOTECA_MEDIADOR(datos[6].toString());
            personal.setCARGO(datos[7].toString());
            personal.setCORREO(datos[8].toString());
            personal.setREPRESENTANTE(datos[9].toString());
            if (datos[9].toString().equals("1")) {
                personal.setBoolREPRESENTANTE(true);
            } else {
                personal.setBoolREPRESENTANTE(true);
            }
            lstPersonal.add(personal);
        }
        return lstPersonal;
    }

    @Override
    public Personal buscarEntidad(int codigo) {
        Personal personal = null;
        String[] array = new String[10];
        array[0] = codigo + "";
        array[1] = "";
        array[2] = "";
        array[3] = "";
        array[4] = "";
        array[5] = "";
        array[6] = "";
        array[7] = "";
        array[8] = "";
        array[9] = "BUSCAR_PERSONAL_BIBLIOTECA";
        ArrayList<Object[]> data = conector.execProcedure("BV.SP_MANTENIMIENTO_PERSONAL_BIBLIOTECA", array);
        for (Object[] datos : data) {
            personal = new Personal();
            personal.setID_PERSONAL_BIBLIOTECA(Integer.parseInt(datos[0].toString()));
            personal.setNOMBRES(datos[1].toString());
            personal.setPATERNO(datos[2].toString());
            personal.setMATERNO(datos[3].toString());
            personal.setDNI(datos[4].toString());
            personal.setID_BIBLIOTECA_MEDIADOR(Integer.parseInt(datos[5].toString()));
            personal.setCARGO(datos[6].toString());
            personal.setCORREO(datos[7].toString());
            personal.setREPRESENTANTE(datos[8].toString());
        }
        return personal;
    }

    @Override
    public List<Object[]> obtenerBiblioteca(String idTipoUsuario, String idBibliotecaMediador) {
        String[] parametros = new String[2];
        parametros[0] = idTipoUsuario;
        parametros[1] = idBibliotecaMediador;
        List<Object[]> biliotecas = conector.execProcedure("BV.SP_LISTAR_BIBLIOTECAS", parametros);
        return biliotecas;
    }

    public List<Object[]> obtenerBibliotecaDocumental(String idBibliotecaMediador) {
        String[] parametros = new String[1];
        parametros[0] = idBibliotecaMediador;
        List<Object[]> biliotecas = conector.execProcedure("BV.SP_LISTAR_BIBLIOTECAS_DOCUMENTAL", parametros);
        return biliotecas;
    }

    @Override
    public List<Personal> obtenerEntidades(String idBiblioteca) {
        List<Personal> lstPersonal = new ArrayList<>();
        String[] array = new String[10];
        array[0] = "";
        array[1] = "";
        array[2] = "";
        array[3] = "";
        array[4] = "";
        array[5] = idBiblioteca;
        array[6] = "";
        array[7] = "";
        array[8] = "";
        array[9] = "LIST_PERSONAL_BIBLIOTECA";
        ArrayList<Object[]> data = conector.execProcedure("BV.SP_MANTENIMIENTO_PERSONAL_BIBLIOTECA", array);
        for (Object[] datos : data) {
            Personal personal = new Personal();
            personal.setID_PERSONAL_BIBLIOTECA(Integer.parseInt(datos[0].toString()));
            personal.setNOMBRES(datos[1].toString());
            personal.setPATERNO(datos[2].toString());
            personal.setMATERNO(datos[3].toString());
            personal.setDNI(datos[4].toString());
            personal.setID_BIBLIOTECA_MEDIADOR(Integer.parseInt(datos[5].toString()));
            personal.setBIBLIOTECA_MEDIADOR(datos[6].toString());
            personal.setCARGO(datos[7].toString());
            personal.setCORREO(datos[8].toString());
            // personal.setREPRESENTANTE(datos[9].toString());
            if (datos[9].toString().equals("1")) {
                personal.setBoolREPRESENTANTE(true);
            } else {
                personal.setBoolREPRESENTANTE(false);
            }
            lstPersonal.add(personal);
        }
        return lstPersonal;

    }

    @Override
    public List<Personal> obtenerEntidades2(String idBiblioteca, String idTipoUsuario) {
        List<Personal> lstPersonal = new ArrayList<>();
        String[] array = new String[3];
        array[0] = idBiblioteca;
        array[1] = idTipoUsuario;
        array[2] = "";
        ArrayList<Object[]> data = conector.execProcedure("BV.SP_PERSONAL_LISTAR", array);
        for (Object[] datos : data) {
            Personal personal = new Personal();
            personal.setID_PERSONAL_BIBLIOTECA(Integer.parseInt(datos[0].toString()));
            personal.setNOMBRES(datos[1].toString());
            personal.setPATERNO(datos[2].toString());
            personal.setMATERNO(datos[3].toString());
            personal.setDNI(datos[4].toString());
            personal.setID_BIBLIOTECA_MEDIADOR(Integer.parseInt(datos[5].toString()));
            personal.setBIBLIOTECA_MEDIADOR(datos[6].toString());
            personal.setCARGO(datos[7].toString());
            personal.setCORREO(datos[8].toString());
            // personal.setREPRESENTANTE(datos[9].toString());
            if (datos[9].toString().equals("1")) {
                personal.setBoolActivo(true);
            } else {
                personal.setBoolActivo(false);
            }
            if (datos[10].toString().equals("1")) {
                personal.setBoolREPRESENTANTE(true);
            } else {
                personal.setBoolREPRESENTANTE(false);
            }
            lstPersonal.add(personal);
        }
        return lstPersonal;

    }

    @Override
    public String nomBiblioteca(String idBiblioteca) {
        String nombreBilioteca = "";
        String[] array = new String[1];
        array[0] = idBiblioteca;
        ArrayList<Object[]> data = conector.execProcedure("BV.SP_BIBLIOTECA_NOMBRE", array);
        for (Object[] datos : data) {
            nombreBilioteca = datos[0].toString();
        }
        return nombreBilioteca;
    }

    @Override
    public Personal listaPaginada(String idBiblioteca, String idTipoUsuario, int first, int pageSize, String query) {
        Personal Personal = new Personal();
        //SP_PERSONAL_LISTA_PAGINADA
        List<Personal> lstPersonal = new ArrayList<>();

        Personal obj;
        Connection conn = ConectaDb.getConnection();
        ResultSet rs;
        try {
            CallableStatement cs = conn.prepareCall("{call [BV].[SP_PERSONAL_LISTA_PAGINADA](?,?,?,?,?,?)}");
            cs.setString(1, idBiblioteca);
            cs.setString(2, idTipoUsuario);
            cs.setInt(3, first);
            cs.setInt(4, pageSize);
            cs.setString(5, query);
            cs.registerOutParameter(6, Types.INTEGER);
            rs = cs.executeQuery();
            while (rs.next()) {
                Personal personal = new Personal();
                personal.setID_PERSONAL_BIBLIOTECA(rs.getInt(1));
                personal.setNOMBRES(rs.getString(2));
                personal.setPATERNO(rs.getString(3));
                personal.setMATERNO(rs.getString(4));
                personal.setDNI(rs.getString(5));
                personal.setID_BIBLIOTECA_MEDIADOR(rs.getInt(6));
                personal.setBIBLIOTECA_MEDIADOR(rs.getString(7));
                personal.setCARGO(rs.getString(8));
                personal.setCORREO(rs.getString(9));
                // personal.setREPRESENTANTE(datos[9].toString());
                if (rs.getString(10).equals("1")) {
                    personal.setBoolActivo(true);
                } else {
                    personal.setBoolActivo(false);
                }
                if (rs.getString(11).equals("1")) {
                    personal.setBoolREPRESENTANTE(true);
                } else {
                    personal.setBoolREPRESENTANTE(false);
                }
                lstPersonal.add(personal);
            }
            Personal.setLstPersonal(lstPersonal);
            Personal.setCountQuery(cs.getInt(6));
        } catch (Exception e) {
            System.out.println("ERROR :" + e);
        }

        return Personal;

    }

    @Override
    public Personal cuentaGet(int idUsuario) {

        Personal personal = new Personal();
        Connection conn = ConectaDb.getConnection();
        ResultSet rs;
        try {
            CallableStatement cs = conn.prepareCall("{call BV.SP_CUENTA_DATOS(?)}");
            cs.setInt(1, idUsuario);

            rs = cs.executeQuery();
            while (rs.next()) {
                personal.setNOMBRES(rs.getString(1));
                personal.setPATERNO(rs.getString(2));
                personal.setMATERNO(rs.getString(3));
                personal.setDNI(rs.getString(4));
                personal.setBIBLIOTECA_MEDIADOR(rs.getString(5));
                personal.setCORREO(rs.getString(6));
                personal.setCuenta(rs.getString(7));
            }
        } catch (Exception e) {
            System.out.println("ERROR :" + e);
        }

        return personal;

    }

    @Override
    public Personal cuentaupd(Personal p) {

        Personal personal = new Personal();
        Connection conn = ConectaDb.getConnection();
        ResultSet rs;
        try {
            CallableStatement cs = conn.prepareCall("{call BV.SP_CUENTA_UPD(?,?,?,?,?,?,?,?)}");
            cs.setInt(1, p.getIdUsuario());
            cs.setInt(2, p.getID_PERSONAL_BIBLIOTECA());
            cs.setString(3, p.getNOMBRES());
            cs.setString(4, p.getPATERNO());
            cs.setString(5, p.getMATERNO());
            cs.setString(6, p.getDNI());
            cs.setString(7, p.getCORREO());
            cs.setString(8, p.getCuenta());
            rs = cs.executeQuery();
            while (rs.next()) {
                personal.setCodMensaje(rs.getString(1));
                personal.setMensaje(rs.getString(2));
               ;
            }
        } catch (Exception e) {
            System.out.println("ERROR :" + e);
        }

        return personal;


    }

}
