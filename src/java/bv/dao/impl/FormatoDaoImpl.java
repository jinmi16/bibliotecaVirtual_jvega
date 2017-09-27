package bv.dao.impl;

import bv.dao.FormatoDao;
import java.util.ArrayList;
import java.util.List;
import bv.util.sql;

/**
 *
 * @author virtual
 */
public class FormatoDaoImpl implements FormatoDao {

    sql conector = new sql();

    @Override
    public List<Object[]> llenaComboFormato() {
        String[] array = new String[1];
        array[0] = "";
        ArrayList<Object[]> data = conector.execProcedure("BV.SP_LISTAR_FORMATO", array);
        return data;
    }

}
