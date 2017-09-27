package bv.dao.impl;

import bv.dao.TipoDao;
import java.util.List;
import vb.entidad.Tipo;
import vb.servicio.entidadService;
import bv.util.sql;

/**
 *
 * @author Renato Vásquez Tejada - renatovt11@gmail.com
 */
public class TipoDaoImpl implements TipoDao{
    
    sql conector = new sql();

    @Override
    public List<Object[]> llenaComboTipo1(){
        String[] parametros = new String[3];
        parametros[0] = "";
        parametros[1] = "";
        parametros[2] = "TIPO_LIST";
        List<Object[]> list = conector.execProcedure("BV.SP_MANTENIMIENTO_TIPO", parametros);
        return list;
    }
    
    @Override
    public List<Object[]> llenaComboTipoXperfil(String perfil){
        String[] parametros = new String[1];
        parametros[0] = perfil;
        List<Object[]> list = conector.execProcedure("BV.SP_LISTAR_TIPO_XPERFIL", parametros);
        return list;
    }
    
}
