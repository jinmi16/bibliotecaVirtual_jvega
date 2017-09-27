package bv.dao.impl;

import bv.dao.TemporalDao;
import java.util.List;
import bv.util.sql;

/**
 *
 * @author Renato Vásquez Tejada - renatovt11@gmail.com
 */
public class TemporalDaoImpl implements TemporalDao{
    
    sql conector = new sql();

    @Override
    public List<Object[]> llenaComboCoberturaTemporal(){
        String[] parametros = new String[5];
        parametros[0] = "";
        parametros[1] = "";
        parametros[2] = "";
        parametros[3] = "";
        parametros[4] = "COBERTURA_TEMPORAL_LIST";
        List<Object[]> list = conector.execProcedure("BV.SP_MANTENIMIENTO_COBERTURA_TEMPORAL", parametros);
        return list;
    }
    
}
