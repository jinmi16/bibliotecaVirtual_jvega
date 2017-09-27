package bv.dao.impl;

import bv.dao.ColeccionDao;
import java.util.ArrayList;
import java.util.List;
import vb.entidad.Coleccion;
import bv.util.sql;

/**
 *
 * @author Renato Vásquez Tejada - renatovt11@gmail.com
 */
public class ColeccionDaoImpl implements ColeccionDao{

    sql conector = new sql();

    @Override
    public int crearEntidad(Coleccion coleccion) {
        int n = 0;
        String[] parametros = new String[5];
        parametros[0] = "";
        parametros[1] = coleccion.getCOD_COLECCION();
        parametros[2] = coleccion.getDESCRIPCION();
        parametros[3] = "COLECCION_INS";
        parametros[4] = "COLECCION_INS";
        ArrayList<Object[]> data = conector.execProcedure("BV.SP_MANTENIMIENTO_COLECCION", parametros);
        for (Object[] dat : data) {
            if (dat[0].toString().equals("1")) {
                n = 1;
            }
        }
        return n;
    }

    @Override
    public int actualizarEntidad(Coleccion coleccion) {
        int update = 0;
        String[] parametros = new String[5];
        parametros[0] = coleccion.getID_COLECCION()+ "";
        parametros[1] = coleccion.getCOD_COLECCION() + "";
        parametros[2] = coleccion.getDESCRIPCION();
        parametros[3] = "COLECCION_UPD";
        parametros[4] = "";
        List<Object[]> list = conector.execProcedure("BV.SP_MANTENIMIENTO_COLECCION", parametros);
        for (Object[] lista : list) {
            update = Integer.parseInt(lista[0].toString());
        }
        return update;
    }

    @Override
    public List<Object[]> llenaComboColeccion(String idBiblioteca) {
        String[] parametros = new String[5];
        parametros[0] = "";
        parametros[1] = "";
        parametros[2] = "";
        parametros[3] = "COLECCION_LIST";
        parametros[4] = idBiblioteca;
        List<Object[]> list = conector.execProcedure("BV.SP_MANTENIMIENTO_COLECCION", parametros);
        return list;
    }

    @Override
    public ArrayList<String> listColeccionDocumentallXidDocumental(String idDocumental) {
        ArrayList<String> lst = new ArrayList<>();
        String[] parametros = new String[2];
        parametros[0] = idDocumental;
        parametros[1] = "LISTAR_X_ID";
        ArrayList<Object[]> data = conector.execProcedure("[BV].[SP_COLECCION_DOCUMENTAL_LISTAR_X_ID_DOCUMENTAL]", parametros);
        for (Object[] d : data) {
            String obj = d[0].toString();
            lst.add(obj);
        }
        return lst;
    }
    
    @Override
    public List<Coleccion> obtenerSeleccion(String colecciones){
        List<Coleccion> listColeccion = new ArrayList<>();
        String[] parametros = new String[1];
        parametros[0] = colecciones;
        List<Object[]> list = conector.execProcedure("BV.SP_LISTAR_SELECCION_COLECCION", parametros);
        for (Object[] lista : list) {
            Coleccion coleccion = new Coleccion();
            coleccion.setID_COLECCION(Integer.parseInt(lista[0].toString()));
            coleccion.setCOD_COLECCION(lista[1].toString());
            coleccion.setDESCRIPCION(lista[2].toString());
            listColeccion.add(coleccion);
        }
        return listColeccion;
    }
    
    @Override
    public List<Coleccion> listarColeccionIdDocumental(String idDocumental) {
        String[] parametros = new String[1];
        parametros[0] = idDocumental;
        List<Object[]> listaIn = conector.execProcedure("BV.SP_LISTAR_COLECCION_ID_DOCUMENTAL", parametros);
        ArrayList<Coleccion> lista = new ArrayList<>();
        for (Object[] dato : listaIn) {
            Coleccion coleccion = new Coleccion();
            coleccion.setID_COLECCION(Integer.parseInt(dato[0].toString()));
            coleccion.setCOD_COLECCION(dato[1].toString());
            coleccion.setDESCRIPCION(dato[2].toString());
            lista.add(coleccion);
        }
        return lista;
    }
    
    @Override
    public int crearEntidad(Coleccion coleccion,String idBiblioiteca) {
        int n = 0;
        String[] parametros = new String[5];
        parametros[0] = "";
        parametros[1] = coleccion.getCOD_COLECCION();
        parametros[2] = coleccion.getDESCRIPCION();
        parametros[3] = "COLECCION_INS";
        parametros[4] = idBiblioiteca;
        ArrayList<Object[]> data = conector.execProcedure("BV.SP_MANTENIMIENTO_COLECCION", parametros);
        for (Object[] dat : data) {
            if (dat[0].toString().equals("1")) {
                n = 1;
            }
        }
        return n;
    }
    
     

}
