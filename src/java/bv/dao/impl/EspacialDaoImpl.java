/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bv.dao.impl;

import java.util.ArrayList;
import java.util.List;
import vb.entidad.Cobertura;
import bv.util.sql;
import bv.dao.EspacialDao;

/**
 *
 * @author virtual
 */
public class EspacialDaoImpl implements EspacialDao {

    sql conector = new sql();

    @Override
    public int CrearCobertura(Cobertura cobertura) {
        int n = 0;
        String[] parametros = new String[6];
        parametros[0] = "";
        parametros[1] = cobertura.getID_PAIS();
        parametros[2] = cobertura.getCIUDAD_ACTUAL();
        parametros[3] = cobertura.getCIUDAD_ANTIGUA();
        parametros[4] = "CREAR_COBERTURA_ESPACIAL";
        parametros[5] = cobertura.getID_BIBLIOTECA_REGISTRO();
        ArrayList<Object[]> data = conector.execProcedure("BV.SP_MANTENEDOR_COBERTURA_ESPACIAL", parametros);
        for (Object[] dat : data) {
            if (dat[0].toString().equals("1")) {
                n = 1;
            }
        }
        return n;
    }

    @Override
    public int editarCobertura(Cobertura cobertura) {
        int n = 0;
        String[] parametros = new String[6];
        parametros[0] = String.valueOf(cobertura.getID_COBERTURA_ESPACIAL());
        parametros[1] = cobertura.getID_PAIS();
        parametros[2] = cobertura.getCIUDAD_ACTUAL();
        parametros[3] = cobertura.getCIUDAD_ANTIGUA();
        parametros[4] = "EDITAR_COBERTURA_ESPACIAL";
        parametros[5] = cobertura.getID_BIBLIOTECA_REGISTRO();
        ArrayList<Object[]> data = conector.execProcedure("BV.SP_MANTENEDOR_COBERTURA_ESPACIAL", parametros);
        for (Object[] dat : data) {
            if (dat[0].toString().equals("1")) {
                n = 1;
            }
        }
        return n;
    }

    ////----------------------------------------
    @Override
    public List<Cobertura> obtenerCobertura() {
        List<Cobertura> lstCobertura = new ArrayList<>();
        String[] array = new String[3];
        array[0] = "";
        array[1] = "";
        array[2] = "LIST_COBERTURA_ESPACIAL";
        ArrayList<Object[]> data = conector.execProcedure("BV.SP_MANTENEDOR_COBERTURA_ESPACIAL", array);
        for (Object[] datos : data) {
            Cobertura cobertura = new Cobertura();
            cobertura.setID_COBERTURA_ESPACIAL(Integer.parseInt(datos[0].toString()));
            cobertura.setCOBERTURA_ESPACIAL(datos[1].toString());
            lstCobertura.add(cobertura);
        }
        return lstCobertura;
    }

    @Override
    public List<Object[]> llenaComboCobertura(String idBiblioteca) {
        String[] array = new String[6];
        array[0] = "";
        array[1] = "";
        array[2] = "";
        array[3] = "";
        array[4] = "LIST_COBERTURA_ESPACIAL";
        array[5] = idBiblioteca;
        ArrayList<Object[]> data = conector.execProcedure("BV.SP_MANTENEDOR_COBERTURA_ESPACIAL", array);
        return data;
    }

    @Override
    public List<Object[]> llenaComboPais() {
        String[] array = new String[1];
        array[0] = "LIST_PAIS";
        ArrayList<Object[]> data = conector.execProcedure("[BV].[SP_PAIS_LIST]", array);
        return data;
    }

    @Override
    public Cobertura obtenerXidCoberturaEspacial(String idCoberturaespacial) {
        Cobertura cobertura = new Cobertura();
        String[] array = new String[6];
        array[0] = idCoberturaespacial;
        array[1] = "";
        array[2] = "";
        array[3] = "";
        array[4] = "LIST_COBERTURA_ESPACIAL_X_ID";
        array[5] = "";
        ArrayList<Object[]> data = conector.execProcedure("BV.SP_MANTENEDOR_COBERTURA_ESPACIAL", array);
        for (Object[] datos : data) {
            cobertura.setID_COBERTURA_ESPACIAL(Integer.parseInt(datos[0].toString()));
            cobertura.setID_PAIS(datos[1].toString());
            cobertura.setCIUDAD_ACTUAL(datos[2].toString());
            cobertura.setCIUDAD_ANTIGUA(datos[3].toString());
            cobertura.setID_BIBLIOTECA_REGISTRO(datos[4].toString());
        }

        return cobertura;
    }

}
