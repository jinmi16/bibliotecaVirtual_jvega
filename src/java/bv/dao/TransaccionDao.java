/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bv.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import vb.entidad.AuxContenido;
import vb.entidad.Biblioteca;
import vb.entidad.Contribuidor;
import vb.entidad.Documental;
import vb.entidad.ImagenNovedad;
import vb.entidad.Novedad;

/**
 *
 * @author virtual
 */
public interface TransaccionDao {

    String insertTransaccion(Documental documental, ArrayList<Contribuidor> listContribuidor, ArrayList<String> listTema, ArrayList<String> listLenguaje, ArrayList<AuxContenido> listTablaContenido, ArrayList<String> listAutor, ArrayList<String> listColeccion, int ID_USUARIO, ArrayList<String> listSerie, ArrayList<String> lisRelacionDocumental) throws SQLException;

    String modificarTransaccion(Documental documental, ArrayList<Contribuidor> listContribuidor, ArrayList<String> listTema, ArrayList<String> listLenguaje, ArrayList<AuxContenido> listTablaContenido, ArrayList<String> listAutor, ArrayList<String> listColeccion, int ID_USUARIO, ArrayList<String> listSerie, ArrayList<String> lisRelacionDocumental) throws SQLException;

    String deleteTransaccion(String ID_DOCUMENTAL, int ID_USUARIO) throws SQLException;

    String insertTransaccionNovedades(Novedad novedad, List<ImagenNovedad> lstImagenNovedad, int imagenid, int idUsuario) throws SQLException;

    int crearBiblioteca(Biblioteca biblioteca, int idUsuario);
    int updateBiblioteca(Biblioteca biblioteca, int idUsuario);
}
