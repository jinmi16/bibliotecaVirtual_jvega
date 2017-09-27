package bv.dao.impl;

import bv.dao.TablaContenidoDao;
import java.util.ArrayList;
import vb.entidad.AuxContenido;
import bv.util.sql;

public class TablaContenidoDaoImpl implements TablaContenidoDao{

    sql conector = new sql();

    @Override
    public ArrayList<AuxContenido> lstTablaContenidosXidDocumental(String idDocumental) {
        ArrayList<AuxContenido> lst = new ArrayList<>();
        String[] parametros = new String[2];
        parametros[0] = idDocumental;
        parametros[1] = "LISTAR_X_ID";
        ArrayList<Object[]> data = conector.execProcedure("[BV].[SP_TABLA_CONTENIDO_LISTAR_X_ID_DOCUMENTAL]", parametros);
        for (Object[] d : data) {
            AuxContenido tc = new AuxContenido();
            tc.setIndice(d[0].toString());
            tc.setTema(d[1].toString());
            lst.add(tc);
        }
        return lst;
    }

}
