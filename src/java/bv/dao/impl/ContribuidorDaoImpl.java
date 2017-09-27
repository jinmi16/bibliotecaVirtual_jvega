package bv.dao.impl;

import bv.dao.ContribuidorDao;
import java.util.ArrayList;
import java.util.List;
import vb.entidad.Contribuidor;
import bv.util.sql;
//import vb.util.sqlTrans;

public class ContribuidorDaoImpl implements ContribuidorDao {

    sql conector = new sql();

    /*public boolean registarContribuidorTransaction(Contribuidor contribuidor, Connection cn) throws SQLException {
        sqlTrans trans = new sqlTrans();
        boolean flag = false;
        ArrayList<Object[]> data = null;
        String[] parametros = new String[4];
        parametros[0] = "";
        parametros[1] = contribuidor.getID_DOCUMENTAL();
        parametros[2] = contribuidor.getCONTRIBUIDOR();
        parametros[3] = "INSERT_CONTRIBUIDOR";
        data = trans.execTransProcedure("BV.SP_MANTENIMIENTO_CONTRIBUIDOR", parametros, cn);
        for (Object[] dat : data) {
            if (dat[0].toString().equals("1")) {
                flag = true;
            }
        }

        return flag;

    }*/
    @Override
    public ArrayList<Contribuidor> listarContribuidorXidDocumental(String idDocumental) {
        ArrayList<Contribuidor> lstContribuidor = new ArrayList<>();
        int n = 1;
        String[] parametros = new String[2];
        parametros[0] = idDocumental;
        parametros[1] = "LISTAR_X_ID";
        ArrayList<Object[]> data = conector.execProcedure("[BV].[SP_CONTRIBUIDOR_LISTAR_X_ID_DOCUMENTAL]", parametros);
        for (Object[] d : data) {
            Contribuidor cont = new Contribuidor();
            cont.setID_CONTRIBUIDOR(Integer.parseInt(d[0].toString()));
            cont.setID_DOCUMENTAL(d[1].toString());
            cont.setCONTRIBUIDOR(d[2].toString());
            cont.setIndice(n);
            n = n + 1;
            lstContribuidor.add(cont);
        }
        return lstContribuidor;
    }

    @Override
    public List<Contribuidor> listarContribuidorIdDocumental(String idDocumental) {
        String[] parametros = new String[1];
        parametros[0] = idDocumental;
        List<Object[]> listaIn = conector.execProcedure("BV.SP_LISTAR_CONTRIBUIDOR_ID_DOCUMENTAL", parametros);
        ArrayList<Contribuidor> lista = new ArrayList<>();
        for (Object[] dato : listaIn) {
            Contribuidor contribuidor = new Contribuidor();
            contribuidor.setID_CONTRIBUIDOR(Integer.parseInt(dato[0].toString()));
            contribuidor.setCONTRIBUIDOR(dato[1].toString());
            lista.add(contribuidor);
        }
        return lista;
    }

}
