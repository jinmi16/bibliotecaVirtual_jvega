package bv.dao.impl;

import bv.dao.UsuarioDao;
import java.util.ArrayList;
import java.util.List;
import vb.entidad.LogTabla;
import vb.entidad.Usuario;
import bv.util.sql;

/**
 *
 * @author virtual
 */
public class UsuarioDaoImpl implements UsuarioDao{

    sql conector = new sql();

    @Override
    public int crearEntidad(Usuario u,int idUsuario) {
        int n = 0;
        String[] parametros = new String[7];
        parametros[0] = String.valueOf(u.getID_USUARIO());
        parametros[1] = u.getUSUARIO();
        parametros[2] = u.getCONTRASENA();
        if (u.isBoolActivo()) {
            parametros[3] = "1";
        } else {
            parametros[3] = "0";
        }
        parametros[4] = String.valueOf(u.getID_PERSONAL_BIBLIOTECA());
        parametros[5] = String.valueOf(u.getID_TIPO_USUARIO());
        parametros[5] = "LIST_USUARIO";
        ArrayList<Object[]> result = conector.execProcedure("BV.SP_MANTENIMIENTO_USUARIO", parametros);
        if (result != null) {
            n = 1;
             LogTablaDaoImpl LogTablaDaoImpl = new LogTablaDaoImpl();
           LogTablaDaoImpl.registrarLogTabla(new LogTabla("BV", "USUARIO","", "1", String.valueOf(idUsuario)));
        }
        return n;
    }

    @Override
    public int actualizarEntidad(Usuario u, int idUsuario) {
        int n = 0;
        String[] parametros = new String[11];
        parametros[0] = String.valueOf(u.getID_USUARIO());
        parametros[1] = u.getUSUARIO();
        parametros[2] = u.getCONTRASENA();
        if (u.isBoolActivo()) {
            parametros[3] = "1";
        } else {
            parametros[3] = "0";
        }
        parametros[4] = String.valueOf(u.getID_PERSONAL_BIBLIOTECA());
        parametros[5] = String.valueOf(u.getID_TIPO_USUARIO());
        parametros[6] = "";
        parametros[7] = "UPD_USUARIO";
        parametros[8] = "";
        parametros[9] = "";
        parametros[10] = "";
        ArrayList<Object[]> result = conector.execProcedure("BV.SP_MANTENIMIENTO_USUARIO", parametros);
        if (result != null) {
            n = 1;
            LogTablaDaoImpl LogTablaDaoImpl = new LogTablaDaoImpl();
            LogTablaDaoImpl.registrarLogTabla(new LogTabla("BV", "USUARIO", String.valueOf(u.getID_TIPO_USUARIO()), "2", String.valueOf(idUsuario)));
        }
        return n;
    }

    @Override
    public int resetContrasena(Usuario u, int idUsuario) {
        int n = 0;
        String[] parametros = new String[11];
        parametros[0] = String.valueOf(u.getID_USUARIO());
        parametros[1] = u.getUSUARIO();
        parametros[2] = u.getCONTRASENA();
        if (u.isBoolActivo()) {
            parametros[3] = "1";
        } else {
            parametros[3] = "0";
        }
        parametros[4] = String.valueOf(u.getID_PERSONAL_BIBLIOTECA());
        parametros[5] = String.valueOf(u.getID_TIPO_USUARIO());
        parametros[6] = "0";
        parametros[7] = "RESET_USUARIO";
        parametros[8] = "";
        parametros[9] = "";
        parametros[10] = "";
        ArrayList<Object[]> result = conector.execProcedure("BV.SP_MANTENIMIENTO_USUARIO", parametros);
        if (result != null) {
            n = 1;
            LogTablaDaoImpl LogTablaDaoImpl = new LogTablaDaoImpl();
            LogTablaDaoImpl.registrarLogTabla(new LogTabla("BV", "USUARIO", String.valueOf(u.getID_USUARIO()), "2", String.valueOf(idUsuario)));
        }
        return n;
    }

    @Override
    public List<Usuario> obtenerEntidades() {
        List<Usuario> lstUsuario = new ArrayList<>();
        String[] parametros = new String[11];
        parametros[0] = "";
        parametros[1] = "";
        parametros[2] = "";
        parametros[3] = "";
        parametros[4] = "";
        parametros[5] = "";
        parametros[6] = "";
        parametros[7] = "LIST_USUARIO";
        parametros[8] = "";
        parametros[9] = "";
        parametros[10] = "";
        ArrayList<Object[]> data = conector.execProcedure("BV.SP_MANTENIMIENTO_USUARIO", parametros);
        for (Object[] datos : data) {
            Usuario usuario = new Usuario();
            usuario.setID_USUARIO(Integer.parseInt(datos[0].toString()));
            usuario.setUSUARIO(datos[1].toString());
            usuario.setCONTRASENA(datos[2].toString());
            if (datos[3].equals("1")) {
                usuario.setBoolActivo(true);
            } else {
                usuario.setBoolActivo(false);
            }
            usuario.setID_PERSONAL_BIBLIOTECA(Integer.parseInt(datos[4].toString()));
            usuario.setNombrePersonalBiblioteca(datos[5].toString());
            usuario.setID_TIPO_USUARIO(Integer.parseInt(datos[6].toString()));
            usuario.setTipoUsuario(datos[7].toString());
            usuario.setNombreBiblioteca(datos[8].toString());
            usuario.setCAMBIO_PASSW_SISTEMA(Integer.parseInt(datos[9].toString()));
            lstUsuario.add(usuario);
        }
        return lstUsuario;
    }
  
    @Override
    public List<Usuario> obtenerEntidadesParametrosPaginadorFiltro(String idTipoUsuario, String idBibliotecaMediador, String idPersonalBiblioteca,int pagina,int registros,String palabra ) {
        List<Usuario> lstUsuario = new ArrayList<>();
        String[] parametros = new String[14];
        parametros[0] = "";
        parametros[1] = "";
        parametros[2] = "";
        parametros[3] = "";
        parametros[4] = "";
        parametros[5] = "";
        parametros[6] = "";
        parametros[7] = "LIST_USUARIO_FILTRO";
        parametros[8] = idTipoUsuario;
        parametros[9] = idBibliotecaMediador;
        parametros[10] = idPersonalBiblioteca;
        parametros[11] = String.valueOf(pagina);
        parametros[12] = String.valueOf(registros);
        parametros[13] = palabra;
        ArrayList<Object[]> data = conector.execProcedure("[BV].[SP_PAGINACION_USUARIO]", parametros);
        for (Object[] datos : data) {
            Usuario usuario = new Usuario();
            usuario.setID_USUARIO(Integer.parseInt(datos[0].toString()));
            usuario.setUSUARIO(datos[1].toString());
            usuario.setCONTRASENA(datos[2].toString());
            if (datos[3].equals("1")) {
                usuario.setBoolActivo(true);
            } else {
                usuario.setBoolActivo(false);
            }
            usuario.setID_PERSONAL_BIBLIOTECA(Integer.parseInt(datos[4].toString()));
            usuario.setNombrePersonalBiblioteca(datos[5].toString());
            usuario.setID_TIPO_USUARIO(Integer.parseInt(datos[6].toString()));
            usuario.setTipoUsuario(datos[7].toString());
            usuario.setNombreBiblioteca(datos[8].toString());
            usuario.setCAMBIO_PASSW_SISTEMA(Integer.parseInt(datos[9].toString()));
            lstUsuario.add(usuario);
        }
        return lstUsuario;
    }

    @Override
    public int contarUsuariosFiltro(String idTipoUsuario, String idBibliotecaMediador, String idPersonalBiblioteca,int pagina,int registros,String palabra) {
        String[] parametros = new String[14];
        parametros[0] = "";
        parametros[1] = "";
        parametros[2] = "";
        parametros[3] = "";
        parametros[4] = "";
        parametros[5] = "";
        parametros[6] = "";
        parametros[7] = "CONTAR_USUARIO_FILTRO";
        parametros[8] = idTipoUsuario;
        parametros[9] = idBibliotecaMediador;
        parametros[10] = idPersonalBiblioteca;
        parametros[11] = String.valueOf(pagina);
        parametros[12] = String.valueOf(registros);
        parametros[13] = palabra;
        ArrayList<Object[]> data = conector.execProcedure("[BV].[SP_PAGINACION_USUARIO]", parametros);
        int total = 0;
        for (Object[] objects : data) {
            total = Integer.parseInt(objects[0].toString());
        }
        return total;
    }
  

    @Override
    public List<Object[]> obtenerTipousuario(String idTipoUsuario, String idBibliotecaMediador, String idPersonalBiblioteca) {
        String[] parametros = new String[11];
        parametros[0] = "";
        parametros[1] = "";
        parametros[2] = "";
        parametros[3] = "";
        parametros[4] = "";
        parametros[5] = "";
        parametros[6] = "";
        parametros[7] = "LIST_TIPO_USUARIO";
        parametros[8] = idTipoUsuario;
        parametros[9] = idBibliotecaMediador;
        parametros[10] = idPersonalBiblioteca;
        ArrayList<Object[]> lstTipoUsuario = conector.execProcedure("BV.SP_MANTENIMIENTO_USUARIO", parametros);
        return lstTipoUsuario;
    }

    @Override
    public int cambiarContrasena(Usuario usuario, int idUsuario) {
        int retorno = 0;
        String[] parametros = new String[2];
        parametros[0] = usuario.getID_USUARIO() + "";
        parametros[1] = usuario.getCONTRASENA_NUEVA1();
        ArrayList<Object[]> data = conector.execProcedure("BV.SP_CAMBIAR_CONTRASENA", parametros);
        for (Object[] datas : data) {
            retorno = Integer.parseInt(datas[0].toString());
            LogTablaDaoImpl LogTablaDaoImpl = new LogTablaDaoImpl();
            LogTablaDaoImpl.registrarLogTabla(new LogTabla("BV", "USUARIO", String.valueOf(usuario.getID_USUARIO()), "2", String.valueOf(idUsuario)));
        }
        return retorno;
    }

}
