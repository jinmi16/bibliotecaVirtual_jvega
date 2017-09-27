package bv.dao.impl;

import bv.dao.NovedadDao;
import java.util.ArrayList;
import java.util.List;
import vb.entidad.Novedad;
import bv.util.dateConverter;
import bv.util.sql;

public class NovedadDaoImpl implements NovedadDao {

    sql conector = new sql();
    dateConverter d = new dateConverter();

    @Override
    public int obetenerId() {
        int n = 0;
        String[] parametros = new String[10];
        parametros[0] = "";
        parametros[1] = "";
        parametros[2] = "";
        parametros[3] = "";
        parametros[4] = "";
        parametros[5] = "";
        parametros[6] = "";
        parametros[7] = "";
        parametros[8] = "";
        parametros[9] = "OBTENER_ID_NOVEDAD";
        ArrayList<Object[]> data = conector.execProcedure("[web].[SP_NOVEDADES_MANTENIMIENTO]", parametros);
        for (Object[] dat : data) {
            n = Integer.parseInt(dat[0].toString());
        }
        return n;
    }

    @Override
    public List<Novedad> obtenerEntidades() {
        List<Novedad> lista = new ArrayList<>();
        Novedad novedad;
        String[] parametros = new String[10];
        parametros[0] = "";
        parametros[1] = "";
        parametros[2] = "";
        parametros[3] = "";
        parametros[4] = "";
        parametros[5] = "";
        parametros[6] = "";
        parametros[7] = "";
        parametros[8] = "";
        parametros[9] = "LISTAR_NOVEDADES_INTERNO";
        List<Object[]> listain = conector.execProcedure("[web].[SP_NOVEDADES_MANTENIMIENTO]", parametros);
        try {
            for (Object[] dato : listain) {
                novedad = new Novedad();
                novedad.setID_NOVEDADES(Integer.parseInt(dato[0].toString()));
                novedad.setTITULO_CORTO(dato[1].toString());
                novedad.setTITULO_COMPLETO(dato[2].toString());
                novedad.setDESCRIPCION(dato[3].toString());
                novedad.setS_FECHA_NOVEDAD(dato[4].toString());
                novedad.setMOSTAR_INICIO(dato[5].toString());
                novedad.setACTIVO(dato[6].toString());
                novedad.setURL_IMAGEN(dato[7].toString());
                lista.add(novedad);
            }
        } catch (Exception e) {
            System.out.println("Error" + e.getMessage());
        }
        return lista;
    }

}
