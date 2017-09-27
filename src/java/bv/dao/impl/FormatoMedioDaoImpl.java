package bv.dao.impl;

import bv.dao.FormatoMedioDao;
import java.util.ArrayList;
import vb.entidad.FormatoMedio;
import java.util.List;
import bv.util.sql;

public class FormatoMedioDaoImpl implements FormatoMedioDao {

    sql conector = new sql();

    @Override
    public List<Object[]> llenaComboFormatoMedio() {
        String[] parametros = new String[3];
        parametros[0] = "";
        parametros[1] = "";
        parametros[2] = "LIST_CBOFORMATOMEDIO";
        List<Object[]> list = conector.execProcedure("BV.SP_MANTENIMIENTO_FORMATO_MEDIO", parametros);
        return list;
    }

    @Override
    public int crearEntidad(FormatoMedio fm) {
        int n = 0;
        String[] parametros = new String[3];
        parametros[0] = "";
        parametros[1] = fm.getDESCRIPCION();
        parametros[2] = "ADD_FORMATOMEDIO";
        ArrayList<Object[]> datos = conector.execProcedure("BV.SP_MANTENIMIENTO_FORMATO_MEDIO", parametros);
        for (Object[] d : datos) {
            if (d[0].toString().equals("1")) {
                n = 1;
            }
        }
        return n;
    }

    @Override
    public int actualizarEntidad(FormatoMedio fm) {
        int n = 0;
        String[] parametros = new String[3];
        parametros[0] = String.valueOf(fm.getID_FORMATO_MEDIO());
        parametros[1] = fm.getDESCRIPCION();
        parametros[2] = "UPD_FORMATOMEDIO";
        ArrayList<Object[]> datos = conector.execProcedure("BV.SP_MANTENIMIENTO_FORMATO_MEDIO", parametros);
        for (Object[] d : datos) {
            if (d[0].toString().equals("1")) {
                n = 1;
            }
        }
        return n;
    }

    @Override
    public FormatoMedio buscarEntidad(int codigo) {
        FormatoMedio fm = null;
        String[] parametros = new String[3];
        parametros[0] = String.valueOf(codigo);
        parametros[1] = "";
        parametros[2] = "FINDX_FORMATOMEDIO";
        List<Object[]> listaIn = conector.execProcedure("BV.SP_MANTENIMIENTO_FORMATO_MEDIO", parametros);
        for (Object[] dato : listaIn) {
            fm = new FormatoMedio();
            fm.setID_FORMATO_MEDIO(Integer.parseInt(dato[0].toString()));
            fm.setDESCRIPCION(dato[1].toString());
        }
        return fm;
    }

}
