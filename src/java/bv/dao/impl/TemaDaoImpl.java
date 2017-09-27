package bv.dao.impl;

import bv.dao.TemaDao;
import java.util.ArrayList;
import java.util.List;
import vb.entidad.Tema;
import bv.util.sql;

/**
 *
 * @author Renato Vásquez Tejada - renatovt11@gmail.com
 */
public class TemaDaoImpl implements TemaDao{

    sql conector = new sql();

    @Override
    public int crearEntidad(Tema tema) {
        int n = 0;
        String[] parametros = new String[4];
        parametros[0] = "";
        parametros[1] = tema.getTEMA().trim();
        parametros[2] = "TEMA_INS";
        parametros[3] = tema.getID_BIBLIOTECA_REGISTRO();
        ArrayList<Object[]> data = conector.execProcedure("BV.SP_MANTENIMIENTO_TEMA", parametros);
        for (Object[] dat : data) {
            n = Integer.parseInt(dat[0].toString());
        }
        return n;
    }

    @Override
    public int actualizarEntidad(Tema tema) {
        int upd = 0;
        String[] parametros = new String[4];
        parametros[0] = String.valueOf(tema.getID_TEMA());
        parametros[1] = tema.getTEMA();
        parametros[2] = "TEMA_UPD";
        parametros[3] = tema.getID_BIBLIOTECA_REGISTRO();
        List<Object[]> list = conector.execProcedure("BV.SP_MANTENIMIENTO_TEMA", parametros);
        for (Object[] lista : list) {
            upd = Integer.parseInt(lista[0].toString());
        }
        return upd;
    }

    @Override
    public List<Tema> obtenerEntidades() {
        List<Object[]> objTema;
        List<Tema> listTema = new ArrayList<>();
        String[] parametros = new String[3];
        parametros[0] = "";
        parametros[1] = "";
        parametros[2] = "TEMA_LIST";
        objTema = conector.execProcedure("BV.SP_MANTENIMIENTO_TEMA", parametros);
        for (Object[] obj : objTema) {
            Tema tema = new Tema();
            tema.setID_TEMA(Integer.parseInt(obj[0].toString()));
            tema.setTEMA(obj[1].toString());
            listTema.add(tema);
        }
        return listTema;
    }

    @Override
    public List<Object[]> llenaComboTema(String idBiblioteca) {
        String[] parametros = new String[4];
        parametros[0] = "";
        parametros[1] = "";
        parametros[2] = "TEMA_LIST";
        parametros[3] = idBiblioteca;
        List<Object[]> list = conector.execProcedure("BV.SP_MANTENIMIENTO_TEMA", parametros);
        return list;
    }

    @Override
    public ArrayList<String> listTemaDocumentalXidDocumental(String idDocumental) {
        ArrayList<String> lstTemaDocumental = new ArrayList<>();
        String[] parametros = new String[2];
        parametros[0] = idDocumental;
        parametros[1] = "LISTAR_X_ID";
        ArrayList<Object[]> data = conector.execProcedure("[BV].[SP_TEMA_DOCUMENTAL_LISTAR_X_ID_DOCUMENTAL]", parametros);
        for (Object[] d : data) {
            String tem = d[0].toString();
            lstTemaDocumental.add(tem);
        }
        return lstTemaDocumental;

    }

    @Override
    public List<Tema> listarSerieIdDocumental(String idDocumental) {
        String[] parametros = new String[1];
        parametros[0] = idDocumental;
        List<Object[]> listaIn = conector.execProcedure("BV.SP_LISTAR_TEMAS_ID_DOCUMENTAL", parametros);
        ArrayList<Tema> lista = new ArrayList<>();
        for (Object[] dato : listaIn) {
            Tema tema = new Tema();
            tema.setID_TEMA(Integer.parseInt(dato[0].toString()));
            tema.setTEMA(dato[1].toString());
            lista.add(tema);
        }
        return lista;
    }

    @Override
    public ArrayList<Tema> obtenerSelecionTemas(String s) {
        ArrayList<Tema> Lista = new ArrayList<>();
        String[] parametros = new String[1];
        parametros[0] = s;
        ArrayList<Object[]> data = conector.execProcedure("BV.SP_LISTAR_SELECCION_TEMA", parametros);
        for (Object[] d : data) {
            Tema tema = new Tema();
            tema.setID_TEMA(Integer.parseInt(d[0].toString()));
            tema.setTEMA(d[1].toString());
            Lista.add(tema);
        }
        return Lista;
    }

}
