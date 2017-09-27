package bv.dao.impl;

import bv.dao.InstitucionDao;
import java.util.ArrayList;
import java.util.List;
import vb.entidad.Institucion;
import vb.entidad.LogTabla;
import bv.util.sql;

/**
 *
 * @author Renato Vásquez Tejada - renatovt11@gmail.com
 */
public class InstitucionDaoImpl implements InstitucionDao{

    sql conector = new sql();

    @Override
    public int crearEntidad(Institucion institucion, int idUsuario) {
        int n = 0;
        String[] parametros = new String[5];
        parametros[0] = "";
        parametros[1] = institucion.getNOMBRE_INSTITUCION();
        parametros[2] = institucion.getNOMBRE_REPRESENTANTE();
        parametros[3] = institucion.getTIPO_INSTITUCION() + "";
        parametros[4] = "INSERT_INSTITUCION";
        ArrayList<Object[]> data = conector.execProcedure("BV.SP_MANTENIMIENTO_INSTITUCION", parametros);
        if (data != null) {
            n = 1;
            LogTablaDaoImpl LogTablaDaoImpl = new LogTablaDaoImpl();
            LogTablaDaoImpl.registrarLogTabla(new LogTabla("BV", "INSTITUCION", "", "1", String.valueOf(idUsuario)));
        }
        return n;
    }

    @Override
    public List<Institucion> obtenerEntidades() {
        List<Institucion> linstituciones = new ArrayList<>();
        String[] parametros = new String[5];
        parametros[0] = "";
        parametros[1] = "";
        parametros[2] = "";
        parametros[3] = "";
        parametros[4] = "LISTAR_INSTITUCION";
        ArrayList<Object[]> data = conector.execProcedure("bvirtual.SP_MANTENIMIENTO_INSTITUCION", parametros);
        for (Object[] datos : data) {
            int id = Integer.parseInt(datos[0].toString());
            String nombreins = datos[1].toString();
            String nombrerep = datos[2].toString();
            int tipo = Integer.parseInt(datos[3].toString());
            linstituciones.add(new Institucion(id, nombreins, nombrerep, tipo));
        }
        return linstituciones;
    }

    @Override
    public List<Object[]> llenaComboAvanzado() {
        String[] parametros = new String[5];
        parametros[0] = "";
        parametros[1] = "";
        parametros[2] = "";
        parametros[3] = "";
        parametros[4] = "LISTAR_INSTITUCION";
        List<Object[]> list = conector.execProcedure("BV.SP_MANTENIMIENTO_INSTITUCION", parametros);
        return list;
    }

    @Override
    public List<Object[]> llenaComboTipoInstitucion() {
        String[] parametros = new String[5];
        parametros[0] = "";
        parametros[1] = "";
        parametros[2] = "";
        parametros[3] = "";
        parametros[4] = "LISTAR_TIPO_INSTITUCION";
        List<Object[]> list = conector.execProcedure("BV.SP_MANTENIMIENTO_INSTITUCION", parametros);
        return list;
    }
}
