package bv.dao.impl;

import bv.dao.TipoOtroDao;
import java.util.List;
import bv.util.sql;

/**
 *
 * @author Renato Vásquez Tejada - renatovt11@gmail.com
 */
public class TipoOtroDaoImpl implements TipoOtroDao {

    sql conector = new sql();

    @Override
    public List<Object[]> llenaComboTipoOtro() {
        String[] parametros = new String[3];
        parametros[0] = "";
        parametros[1] = "";
        parametros[2] = "LISTAR_TIPO_OTRO";
        List<Object[]> list = conector.execProcedure("BV.SP_MANTENIMIENTO_TIPO_OTRO", parametros);
        return list;
    }

}
