/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bv.util;

import bv.dao.impl.TransaccionDaoImpl;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import vb.entidad.Biblioteca;

/**
 *
 * @author virtual
 */
public class TESTING {

    public static void main(String[] args) {

      
      
    }

    public static void pruebas() {

        // TransaccionDaoImpl tDao = new TransaccionDaoImpl();
        Biblioteca bi = new Biblioteca();
        bi.setNOMBRE_BIBLIOTECA("SISTEMAS_PRUEBA_2_29112016");
        bi.setID_INSTITUCION_MEDIADOR(3);

        bi.setID_DISTRITO("01");
        bi.setID_PROVINCIA("01");
        bi.setID_REGION("14");

        ArrayList<String> selPer = new ArrayList<>();
        selPer.add("1");
        selPer.add("2");
        selPer.add("3");
        bi.setSelectedPerfiles(selPer);
        //-------------------------------------------------------
        Connection conn = ConectaDb.getConnection();
        int insert = 0;
        try {
            conn.setAutoCommit(false);
            CallableStatement cs = conn.prepareCall("{call BV.SP_BIBLIOTECA_INSERTAR(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
            cs.setInt(1, bi.getID_BIBLIOTECA_MEDIADOR());
            cs.setInt(2, bi.getID_INSTITUCION_MEDIADOR());
            cs.setString(3, bi.getNOMBRE_BIBLIOTECA());
            cs.setString(4, bi.getDIRECCION());
            cs.setString(5, bi.getEMAIL());
            cs.setString(6, bi.getCODIGO_SNB());
            cs.setString(7, bi.getID_DISTRITO());
            cs.setString(8, bi.getID_PROVINCIA());
            cs.setString(9, bi.getID_REGION());
            cs.setString(10, "");
            cs.setString(11, "INSERT_BIBLIOTECA");
            cs.setString(12, bi.getURL());
            cs.setString(13, bi.getDIRECTORIO());

            if (bi.getTITULO_MAPA() == null) {
                bi.setTITULO_MAPA("");
            }
            cs.setString(14, bi.getTITULO_MAPA());
            if (bi.getLATITUD() == null) {
                bi.setLATITUD("");
            }
            cs.setString(15, bi.getLATITUD());
            if (bi.getLONGITUD() == null) {
                bi.setLONGITUD("");
            }
            cs.setString(16, bi.getLONGITUD());
            //cs.registerOutParameter(17, java.sql.JDBCType.INTEGER);
            cs.registerOutParameter(17, Types.INTEGER);
            cs.execute();

            int IdBibliotecaNew = cs.getInt(17);
            //********************
            for (String pd : bi.getSelectedPerfiles()) {
                CallableStatement csPD = conn.prepareCall("{call BV.SP_PERFIL_DOCUMENTAL_INSERTAR(?,?,?)}");
                csPD.setInt(1, Integer.parseInt(pd));
                csPD.setString(2, "1");
                csPD.setInt(3, IdBibliotecaNew);
                csPD.execute();
            }
            conn.commit();
            insert = 1;
        } catch (SQLException ex) {
            //insert = ex.getMessage();
            ex.printStackTrace();
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException ex1) {
                    ex1.printStackTrace();
                }
            }
        } finally {

            // return insert;
        }

        //---------------------------------------------------------------------------------- 
//        int create = tDao.crearEntidad(bi, 35);
//        if (create == 1) {
//            System.out.println("creracion exitosa");
//        } else {
//            System.out.println("error de creacion");
//        }
    }

    public static void pruebaparametroOutp() {

        CallableStatement cs = null;
        ResultSet rs = null;
        Connection cn = ConectaDb.getConnection();

        try {
            cs = cn.prepareCall("{CALL dbo.SP_TEST_DOCUMENTAL(?,?)}");
            // cs = cn.prepareCall("{CALL dbo.SP_TEST_DOCUMENTAL2(?)}");
            cs.setString(1, "");
            cs.registerOutParameter(2, java.sql.Types.INTEGER);
            cs.execute();
//           rs = cs.executeQuery();
//            while (rs.next()) {
//                System.out.println("| " + rs.getString(1) + " | " + rs.getString(2) + " | " + rs.getString(3));
//            }
//            

            System.out.println("total registros :" + cs.getInt(2));

            // rs = cs.executeQuery();
            //  System.out.println("total registros :"+cs.getInt(2));
            //   System.out.println("total r :"+r);
            //   rs=(ResultSet) cs.getObject(2);
//            if (rs.next()) {
//                System.out.println("total registros"+rs.getInt(1));
//                
//            } else {
//                System.out.println("No se encontró al personal.");
//            }
        } catch (SQLException ex) {
            System.out.println("mensaje 01: " + ex.getMessage());
        } finally {
            try {
                cn.close();
            } catch (SQLException ex) {
                System.out.println("mensaje 02: " + ex.getMessage());
            }
        }

    }

}
