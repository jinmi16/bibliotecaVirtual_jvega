package bv.dao.impl;

import bv.dao.AlbumDao;
import bv.util.sql;
import java.util.ArrayList;
import java.util.List;
import vb.entidad.Album;

/**
 *
 * @author virtual
 */
public class AlbumDaoImpl implements AlbumDao {

    sql conector = new sql();

    @Override
    public List<Object[]> llenaComboAlbum(String ID_BIBLIOTECA) {
        String[] parametros = new String[1];
        parametros[0] = ID_BIBLIOTECA;
        List<Object[]> list = conector.execProcedure("BV.SP_LLENA_COMBO_ALBUM", parametros);
        return list;
    }

    @Override
    public int creaAlbum(Album album) {
        int n = 0;
        String[] parametros = new String[2];
        parametros[0] = album.getDESCRIPCION().trim();
        parametros[1] = album.getID_BIBLIOTECA() + "";
        ArrayList<Object[]> data = conector.execProcedure("BV.SP_INSERT_ALBUM", parametros);
        for (Object[] dat : data) {
            n = Integer.parseInt(dat[0].toString());
        }
        return n;
    }
}
