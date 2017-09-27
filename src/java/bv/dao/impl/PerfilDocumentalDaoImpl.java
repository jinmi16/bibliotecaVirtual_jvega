package bv.dao.impl;

import bv.dao.PerfilDocumentalDao;
import java.util.ArrayList;
import java.util.List;
import vb.entidad.PerfilDocumental;
import bv.util.sql;

/**
 *
 * @author Renato Vásquez Tejada - renatovt11@gmail.com
 */
public class PerfilDocumentalDaoImpl implements PerfilDocumentalDao{

    sql conector = new sql();

    @Override
    public List<PerfilDocumental> listaPerfil() {
        List<PerfilDocumental> lista = new ArrayList<>();
        String[] parametros = new String[5];
        parametros[0] = "";
        parametros[1] = "";
        parametros[2] = "";
        parametros[3] = "";
        parametros[4] = "PERFIL_DOCUMENTAL_LIST";
        ArrayList<Object[]> data = conector.execProcedure("BV.SP_MANTENIMIENTO_PERFIL_DOCUMENTAL", parametros);
        for (Object[] datas : data) {
            PerfilDocumental pd = new PerfilDocumental();
            pd.setID_PERFIL_DOCUMENTAL(Integer.parseInt(datas[0].toString()));
            pd.setPERFIL_DOCUMENTAL(datas[1].toString());
            pd.setDESCRIPCION(datas[2].toString());
            pd.setESTADO(Integer.parseInt(datas[3].toString()));
            pd.setICONO(datas[4].toString());
            lista.add(pd);
        }
        return lista;
    }

    @Override
    public List<PerfilDocumental> listaPerfilDocumental(String idBiblioteca) {
        List<PerfilDocumental> lista = new ArrayList<>();
        String[] parametros = new String[2];
        parametros[0] = idBiblioteca;
        parametros[1] = "PERFIL_DOCUMENTAL_LIST";
        ArrayList<Object[]> data = conector.execProcedure(" BV.SP_LISTAR_PERFIL_DOCUMENTAL_XBIBLIOTECA", parametros);
        for (Object[] datas : data) {
            PerfilDocumental pd = new PerfilDocumental();
            pd.setID_PERFIL_DOCUMENTAL(Integer.parseInt(datas[0].toString()));
            pd.setPERFIL_DOCUMENTAL(datas[1].toString());
            pd.setDESCRIPCION(datas[2].toString());
            pd.setESTADO(Integer.parseInt(datas[3].toString()));
            pd.setICONO(datas[4].toString());
            lista.add(pd);
        }
        return lista;
    }

}
