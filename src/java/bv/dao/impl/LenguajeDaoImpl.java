package bv.dao.impl;

import bv.dao.LenguajeDao;
import java.util.ArrayList;
import java.util.List;
import vb.entidad.Lenguaje;
import bv.util.sql;

/**
 *
 * @author Renato Vásquez Tejada - renatovt11@gmail.com
 */
public class LenguajeDaoImpl implements LenguajeDao {

    sql conector = new sql();

    @Override
    public List<Object[]> llenaComboLenguaje() {
        String[] parametros = new String[3];
        parametros[0] = "";
        parametros[1] = "";
        parametros[2] = "LENGUAJE_LIST";
        List<Object[]> list = conector.execProcedure("BV.SP_MANTENIMIENTO_LENGUAJE", parametros);
        return list;
    }

    @Override
    public ArrayList<String> lisLenguajeDocumentalXidDocumental(String idDocumental) {
        ArrayList<String> lst = new ArrayList<>();
        String[] parametros = new String[2];
        parametros[0] = idDocumental;
        parametros[1] = "LISTAR_X_ID";
        ArrayList<Object[]> data = conector.execProcedure("[BV].[SP_LENGUAJE_DOCUMENTAL_LISTAR_X_ID_DOCUMENTAL]", parametros);
        for (Object[] d : data) {
            String len = d[0].toString();
            lst.add(len);
        }
        return lst;
    }

    @Override
    public List<Lenguaje> listarSerieIdDocumental(String idDocumental) {
        String[] parametros = new String[1];
        parametros[0] = idDocumental;
        List<Object[]> listaIn = conector.execProcedure("BV.SP_LISTAR_LENGUAJE_ID_DOCUMENTAL", parametros);
        ArrayList<Lenguaje> lista = new ArrayList<>();
        for (Object[] dato : listaIn) {
            Lenguaje lenguaje = new Lenguaje();
            lenguaje.setID_LENGUAJE(dato[0].toString());
            lenguaje.setLENGUAJE(dato[1].toString());
            lista.add(lenguaje);
        }
        return lista;
    }

}
