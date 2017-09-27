/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bv.dao;

import java.util.List;
import vb.entidad.Usuario;

/**
 *
 * @author virtual
 */
public interface UsuarioDao {

    int crearEntidad(Usuario u,int idUsuario);

    int actualizarEntidad(Usuario u, int idUsuario);

    int resetContrasena(Usuario u, int idUsuario);

    List<Usuario> obtenerEntidades();

    //List<Usuario> obtenerEntidadesParametros(String idTipoUsuario, String idBibliotecaMediador, String idPersonalBiblioteca);
    
    //PAGINADOR
    List<Usuario> obtenerEntidadesParametrosPaginadorFiltro(String idTipoUsuario, String idBibliotecaMediador, String idPersonalBiblioteca,int pagina,int registros,String palabra);
    public int contarUsuariosFiltro(String idTipoUsuario, String idBibliotecaMediador, String idPersonalBiblioteca,int pagina,int registros,String palabra);
    //---------
    
    List<Object[]> obtenerTipousuario(String idTipoUsuario, String idBibliotecaMediador, String idPersonalBiblioteca);

    int cambiarContrasena(Usuario usuario, int idUsuario);

}
