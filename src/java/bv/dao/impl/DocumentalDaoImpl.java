package bv.dao.impl;

import bv.dao.DocumentalDao;
import bv.util.ConectaDb;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import vb.entidad.Documental;
import bv.util.sql;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Types;

/**
 *
 * @author Renato Vásquez Tejada - renatovt11@gmail.com
 */
public class DocumentalDaoImpl implements DocumentalDao {

    sql conector = new sql();

    @Override
    public int validarTitulo(Documental documental) {
        int n = 0;
        String[] parametros = new String[5];
        parametros[0] = documental.getTITULO();
        parametros[1] = documental.getOTRO();
        parametros[2] = documental.getID_TIPO_OTRO() + "";
        parametros[3] = documental.getID_BIBLIOTECA_FUENTE() + "";
        parametros[4] = documental.getID_TIPO() + "";
        ArrayList<Object[]> data = conector.execProcedure("BV.SP_VALIDA_TITULO_DOCUMENTAL", parametros);
        for (Object[] dat : data) {
            n = Integer.parseInt(dat[0].toString());
        }
        return n;
    }

    @Override
    public String generarIdDocumental(int Tipo) {
        String n = "";
        String[] parametros = new String[1];
        parametros[0] = Tipo + "";
        ArrayList<Object[]> data = conector.execProcedure("[BV].[SP_GENERAR_ID_DOCUMENTAL]", parametros);
        for (Object[] dat : data) {
            n = dat[0].toString();
        }
        return n;
    }

    @Override
    public List<Object[]> llenaComboDocumentalRelacion(String idDocumental) {
        String[] array = new String[4];
        array[0] = "LIST_COMBO_DOCUMENTAL";
        array[1] = "";
        array[2] = "";
        array[3] = idDocumental;
        ArrayList<Object[]> data = conector.execProcedure("BV.SP_LISTAR_DOCUMENTAL", array);
        return data;
    }

    @Override
    public Documental listarDocumentalFiltroYConteo(String perfil, int tipoUsuario, int idUsuario, int ID_BIBLIOTECA_FUENTE, int first, int pageSize, String palabra) {
        Documental documental = new Documental();
        Documental doc;
        List<Documental> lstDocumental = new ArrayList<>();
        ResultSet rs;

        ///----------------------------------------------------------
        Connection conn = ConectaDb.getConnection();
        try {
            CallableStatement cs = conn.prepareCall("{call [BV].[SP_DOCUMENTAL_PAGINACION_COUNT](?,?,?,?,?,?,?,?)}");
            switch (tipoUsuario) {
                case 3:
                    cs.setString(1, "LIST_DOCUMENTAL_UNICO");
                    break;
                case 2:
                    cs.setString(1, "LIST_DOCUMENTAL_REPRERSENTANTE");
                    break;
                default:
                    cs.setString(1, "LIST_DOCUMENTAL_ADMINISTRADOR");
                    break;
            }
            cs.setString(2, perfil);
            cs.setInt(3, idUsuario);
            cs.setInt(4, ID_BIBLIOTECA_FUENTE);
            cs.setInt(5, first);
            cs.setInt(6, pageSize);
            cs.setString(7, palabra);
            cs.registerOutParameter(8, Types.INTEGER);

            rs = cs.executeQuery();
            while (rs.next()) {
                doc = new Documental();
                doc.setID_DOCUMENTAL(rs.getString(1));
                doc.setTITULO(rs.getString(2));
                doc.setTITULO_ALTERNATIVO(rs.getString(3));
                doc.setDESCRIPCION(rs.getString(4));
                doc.setRESUMEN(rs.getString(5));
                doc.setID_TIPO(rs.getInt(6));
                doc.setTIPO(rs.getString(7));
                doc.setID_BIBLIOTECA_FUENTE(rs.getInt(8));
                doc.setBIBLIOTECA_FUENTE(rs.getString(9));
                doc.setTIENE_COMO_VERSION(rs.getString(10));
                doc.setES_PARTE_DE(rs.getString(11));
                doc.setTIENE_PARTE_DE(rs.getString(12));
                doc.setID_COBERTURA_ESPACIAL(rs.getInt(13));
                doc.setCOBERTURA_ESPACIAL(rs.getString(14));
                doc.setID_COBERTURA_TEMPORAL(rs.getInt(15));
                doc.setCOBERTURA_TEMPORAL(rs.getString(16));
                doc.setFECHA_DISPONIBLE(rs.getString(17));
                doc.setFECHA_PUBLICACION(rs.getString(18));
                doc.setFECHA_ACEPTACION(rs.getDate(19));
                doc.setFECHA_COPYRIGHT(rs.getDate(20));
                doc.setID_FORMATO(rs.getString(21));
                doc.setFORMATO_EXTENSION(rs.getString(22));
                doc.setFORMATO_MEDIO_DESCRIPCION(rs.getString(23));
                doc.setID_EDITORIAL(rs.getInt(24));
                doc.setEDITORIAL(rs.getString(25));
                doc.setDERECHO(rs.getString(26));
                doc.setDERECHO_ACCESO(rs.getString(27));
                doc.setID_AUDIENCIA(rs.getInt(28));
                doc.setAUDIENCIA(rs.getString(29));
                doc.setURL(rs.getString(30));
                doc.setISBN(rs.getString(31));
                doc.setOTRO(rs.getString(32));
                doc.setNUMERO_PAGINAS(rs.getString(33));
                lstDocumental.add(doc);

            }
            documental.setLstDocumental(lstDocumental);
            documental.setCountQuery(cs.getInt(8));

        } catch (Exception e) {
            System.out.println("error : " + e);
        }

        return documental;

    }

    @Override
    public List<Documental> listarDocumentalFiltro(String perfil, int tipoUsuario, int idUsuario, int ID_BIBLIOTECA_FUENTE, int first, int pageSize, String palabra) {
        List<Documental> lstDocumental = new ArrayList<>();
        Documental doc;
        String[] parametros = new String[7];
        parametros[1] = perfil;
        switch (tipoUsuario) {
            case 3:
                parametros[2] = idUsuario + "";
                parametros[0] = "LIST_DOCUMENTAL_UNICO";
                break;
            case 2:
                parametros[2] = idUsuario + "";
                parametros[0] = "LIST_DOCUMENTAL_REPRERSENTANTE";
                break;
            default:
                parametros[2] = "";
                parametros[0] = "LIST_DOCUMENTAL_ADMINISTRADOR";
                break;
        }
        parametros[3] = String.valueOf(ID_BIBLIOTECA_FUENTE);
        parametros[4] = String.valueOf(first);
        parametros[5] = String.valueOf(pageSize);
        parametros[6] = palabra;
        ArrayList<Object[]> data = conector.execProcedure("[BV].[SP_DOCUMENTAL_PAGINACION]", parametros);
        for (Object[] d : data) {
            doc = new Documental();
            doc.setID_DOCUMENTAL(d[0].toString());
            doc.setTITULO(d[1].toString());
            doc.setTITULO_ALTERNATIVO(d[2].toString());
            doc.setDESCRIPCION(d[3].toString());
            doc.setRESUMEN(d[4].toString());
            doc.setID_TIPO(Integer.parseInt(d[5].toString()));
            doc.setTIPO(d[6].toString());
            doc.setID_BIBLIOTECA_FUENTE(Integer.parseInt(d[7].toString()));
            doc.setBIBLIOTECA_FUENTE(d[8].toString());
            doc.setTIENE_COMO_VERSION(d[9].toString());
            doc.setES_PARTE_DE(d[10].toString());
            doc.setTIENE_PARTE_DE(d[11].toString());
            doc.setID_COBERTURA_ESPACIAL(Integer.parseInt(d[12].toString()));
            doc.setCOBERTURA_ESPACIAL(d[13].toString());
            doc.setID_COBERTURA_TEMPORAL(Integer.parseInt(d[14].toString()));
            doc.setCOBERTURA_TEMPORAL(d[15].toString());
            doc.setFECHA_DISPONIBLE(d[16].toString());
            doc.setFECHA_PUBLICACION(d[17].toString());
            doc.setFECHA_ACEPTACION((Date) d[18]);
            doc.setFECHA_COPYRIGHT((Date) d[19]);
            doc.setID_FORMATO(d[20].toString());
            doc.setFORMATO_EXTENSION(d[21].toString());
            doc.setFORMATO_MEDIO_DESCRIPCION(d[22].toString());
            doc.setID_EDITORIAL(Integer.parseInt(d[23].toString()));
            doc.setEDITORIAL(d[24].toString());
            doc.setDERECHO(d[25].toString());
            doc.setDERECHO_ACCESO(d[26].toString());
            doc.setID_AUDIENCIA(Integer.parseInt(d[27].toString()));
            doc.setAUDIENCIA(d[28].toString());
            doc.setURL(d[29].toString());
            doc.setISBN(d[30].toString());
            doc.setOTRO(d[31].toString());
            doc.setNUMERO_PAGINAS(d[32].toString());
            lstDocumental.add(doc);
        }
        return lstDocumental;

    }

    @Override
    public int contarDocumentalFiltro(String perfil, int tipoUsuario, int idUsuario, int ID_BIBLIOTECA_FUENTE, int first, int pageSize, String palabra) {
        String[] parametros = new String[7];
        parametros[1] = perfil;
        switch (tipoUsuario) {
            case 3:
                parametros[2] = idUsuario + "";
                parametros[0] = "COUNT_LIST_DOCUMENTAL_UNICO";
                break;
            case 2:
                parametros[2] = "";
                parametros[0] = "COUNT_LIST_DOCUMENTAL_REPRERSENTANTE";
                break;
            default:
                parametros[2] = "";
                parametros[0] = "COUNT_LIST_DOCUMENTAL_ADMINISTRADOR";
                break;
        }
        parametros[3] = String.valueOf(ID_BIBLIOTECA_FUENTE);
        parametros[4] = String.valueOf(first);
        parametros[5] = String.valueOf(pageSize);
        parametros[6] = palabra;
        ArrayList<Object[]> data = conector.execProcedure("[BV].[SP_DOCUMENTAL_PAGINACION]", parametros);
        int total = 0;
        for (Object[] objects : data) {
            total = Integer.parseInt(objects[0].toString());
        }
        return total;
    }

    @Override
    public List<Documental> listarDocumentalGeneralFiltro(String perfil, int idBiblioteca, int first, int pageSize, String palabra) {
        List<Documental> lstDocumental = new ArrayList<>();
        Documental doc;
        String[] parametros = new String[7];
        parametros[0] = "LIST_DOCUMENTAL_TODO";
        parametros[1] = perfil;
        parametros[2] = "";
        parametros[3] = String.valueOf(idBiblioteca);
        parametros[4] = String.valueOf(first);
        parametros[5] = String.valueOf(pageSize);
        parametros[6] = palabra;
        ArrayList<Object[]> data = conector.execProcedure("[BV].[SP_DOCUMENTAL_PAGINACION]", parametros);
        for (Object[] d : data) {
            doc = new Documental();
            doc.setID_DOCUMENTAL(d[0].toString());
            doc.setTITULO(d[1].toString());
            doc.setTITULO_ALTERNATIVO(d[2].toString());
            doc.setDESCRIPCION(d[3].toString());
            doc.setRESUMEN(d[4].toString());
            doc.setID_TIPO(Integer.parseInt(d[5].toString()));
            doc.setTIPO(d[6].toString());
            doc.setID_BIBLIOTECA_FUENTE(Integer.parseInt(d[7].toString()));
            doc.setBIBLIOTECA_FUENTE(d[8].toString());
            doc.setTIENE_COMO_VERSION(d[9].toString());
            doc.setES_PARTE_DE(d[10].toString());
            doc.setTIENE_PARTE_DE(d[11].toString());
            doc.setID_COBERTURA_ESPACIAL(Integer.parseInt(d[12].toString()));
            doc.setCOBERTURA_ESPACIAL(d[13].toString());
            doc.setID_COBERTURA_TEMPORAL(Integer.parseInt(d[14].toString()));
            doc.setCOBERTURA_TEMPORAL(d[15].toString());
            doc.setFECHA_DISPONIBLE(d[16].toString());
            doc.setFECHA_PUBLICACION(d[17].toString());
            doc.setFECHA_ACEPTACION((Date) d[18]);
            doc.setFECHA_COPYRIGHT((Date) d[19]);
            doc.setID_FORMATO(d[20].toString());
            doc.setFORMATO_EXTENSION(d[21].toString());
            doc.setFORMATO_MEDIO_DESCRIPCION(d[22].toString());
            doc.setID_EDITORIAL(Integer.parseInt(d[23].toString()));
            doc.setEDITORIAL(d[24].toString());
            doc.setDERECHO(d[25].toString());
            doc.setDERECHO_ACCESO(d[26].toString());
            doc.setID_AUDIENCIA(Integer.parseInt(d[27].toString()));
            doc.setAUDIENCIA(d[28].toString());
            doc.setURL(d[29].toString());
            doc.setISBN(d[30].toString());
            doc.setOTRO(d[31].toString());
            doc.setNUMERO_PAGINAS(d[32].toString());
            lstDocumental.add(doc);
        }
        return lstDocumental;
    }

    @Override
    public int contarDocumentalGeneralFiltro(String perfil, int idBiblioteca, int first, int pageSize, String palabra) {
        String[] parametros = new String[7];
        parametros[0] = "COUNT_LIST_DOCUMENTAL_TODO";
        parametros[1] = perfil;
        parametros[2] = "";
        parametros[3] = String.valueOf(idBiblioteca);
        parametros[4] = String.valueOf(first);
        parametros[5] = String.valueOf(pageSize);
        parametros[6] = palabra;
        ArrayList<Object[]> data = conector.execProcedure("[BV].[SP_DOCUMENTAL_PAGINACION]", parametros);
        int total = 0;
        for (Object[] objects : data) {
            total = Integer.parseInt(objects[0].toString());
        }
        return total;
    }

    @Override
    public Documental listarXIdDocumental(String idDocumental) {
        Documental doc = new Documental();

        String[] parametros = new String[3];
        parametros[0] = "LISTAR_X_ID";
        parametros[1] = "";
        parametros[2] = idDocumental;
        ArrayList<Object[]> data = conector.execProcedure("[BV].[SP_DOCUMENTAL_LISTAR_X_ID_DOCUMENTAL]", parametros);
        for (Object[] d : data) {
            doc.setID_DOCUMENTAL(d[0].toString());
            doc.setTITULO(d[1].toString());
            doc.setTITULO_ALTERNATIVO(d[2].toString());
            doc.setDESCRIPCION(d[3].toString());
            doc.setRESUMEN(d[4].toString());
            if (Integer.parseInt(d[5].toString()) > 0) {
                doc.setID_TIPO(Integer.parseInt(d[5].toString()));
            } else {
                doc.setID_TIPO(-1);
            }
            doc.setID_BIBLIOTECA_FUENTE(Integer.parseInt(d[6].toString()));

//            if (d[7].toString().length() > 0) {
//                doc.setID_DOCUMENTAL_RELACION(d[7].toString());
//            } else {
//                doc.setID_DOCUMENTAL_RELACION("-1");
//            }
            doc.setTIENE_COMO_VERSION(d[7].toString());
            doc.setES_PARTE_DE(d[8].toString());
            doc.setTIENE_PARTE_DE(d[9].toString());

            if (Integer.parseInt(d[10].toString()) > 0) {
                doc.setID_COBERTURA_ESPACIAL(Integer.parseInt(d[10].toString()));
            } else {
                doc.setID_COBERTURA_ESPACIAL(-1);
            }

            if (Integer.parseInt(d[11].toString()) > 0) {
                doc.setID_COBERTURA_TEMPORAL(Integer.parseInt(d[11].toString()));
            } else {
                doc.setID_COBERTURA_TEMPORAL(-1);
            }

            doc.setFECHA_DISPONIBLE(d[12].toString());
            doc.setFECHA_PUBLICACION(d[13].toString());
            doc.setFECHA_ACEPTACION(null);
            doc.setFECHA_COPYRIGHT(null);
            if (d[16].toString().length() > 0) {
                doc.setID_FORMATO(d[16].toString());
            } else {
                doc.setID_FORMATO("-1");
            }

            doc.setFORMATO_EXTENSION(d[17].toString());
            doc.setID_FORMATO_MEDIO(Integer.parseInt(d[18].toString()));
            if (Integer.parseInt(d[19].toString()) > 0) {
                doc.setID_EDITORIAL(Integer.parseInt(d[19].toString()));
            } else {
                doc.setID_EDITORIAL(-1);
            }

            doc.setDERECHO(d[20].toString());
            doc.setDERECHO_ACCESO(d[21].toString());
            if (Integer.parseInt(d[22].toString()) > 0) {
                doc.setID_AUDIENCIA(Integer.parseInt(d[22].toString()));
            } else {
                doc.setID_AUDIENCIA(-1);
            }

            doc.setURL(d[23].toString());
            doc.setISBN(d[24].toString());
            doc.setOTRO(d[25].toString());
            doc.setNUMERO_PAGINAS(d[26].toString());
            doc.setACTIVO(Integer.parseInt(d[27].toString()));
            doc.setID_TIPO_OTRO(Integer.parseInt(d[28].toString()));
            doc.setPAIS_DEFINIDO(d[29].toString());
            doc.setCIUDAD_DEFINIDA(d[30].toString());
            doc.setPAGINA_INICIO(Integer.parseInt(d[31].toString()));
            doc.setPAGINA_FIN(Integer.parseInt(d[32].toString()));
            doc.setNOTA(d[33].toString());

        }

        return doc;
    }

    @Override
    public Documental listarDocumentalDetalle(String idDocumental) {
        Documental documental = null;
        String[] parametros = new String[1];
        parametros[0] = idDocumental;
        ArrayList<Object[]> data = conector.execProcedure("[BV].[SP_LISTAR_DOCUMENTAL_DETALLE]", parametros);
        for (Object[] datos : data) {
            documental = new Documental();
            documental.setID_DOCUMENTAL(idDocumental);
            documental.setTITULO(datos[1].toString());
            documental.setEDITORIAL(datos[2].toString());
            documental.setFECHA_PUBLICACION(datos[3].toString());
            documental.setTIPO(datos[4].toString());
            documental.setTIENE_COMO_VERSION(datos[5].toString());
            documental.setCOBERTURA_ESPACIAL(datos[6].toString());
            documental.setCOBERTURA_TEMPORAL(datos[7].toString());
            documental.setDERECHO(datos[8].toString());
            documental.setAUDIENCIA(datos[9].toString());
            documental.setFORMATO(datos[10].toString());
            documental.setFORMATO_EXTENSION(datos[11].toString());
            documental.setFORMATO_MEDIO_DESCRIPCION(datos[12].toString());
            documental.setNUMERO_PAGINAS(datos[13].toString());
        }
        return documental;
    }

    @Override
    public Documental listarDocumentalDetalleControl(String idDocumental) {
        Documental documental = null;
        String[] parametros = new String[1];
        parametros[0] = idDocumental;
        ArrayList<Object[]> data = conector.execProcedure("[BV].[SP_LISTAR_DOCUMENTAL_DETALLE_CONTROL]", parametros);
        for (Object[] datos : data) {
            documental = new Documental();
            documental.setID_DOCUMENTAL(idDocumental);
            documental.setTITULO(datos[1].toString());
            documental.setEDITORIAL(datos[2].toString());
            documental.setFECHA_PUBLICACION(datos[3].toString());
            documental.setTIPO(datos[4].toString());
            documental.setTIENE_COMO_VERSION(datos[5].toString());
            documental.setCOBERTURA_ESPACIAL(datos[6].toString());
            documental.setCOBERTURA_TEMPORAL(datos[7].toString());
            documental.setDERECHO(datos[8].toString());
            documental.setAUDIENCIA(datos[9].toString());
            documental.setFORMATO(datos[10].toString());
            documental.setFORMATO_EXTENSION(datos[11].toString());
            documental.setFORMATO_MEDIO_DESCRIPCION(datos[12].toString());
            documental.setNUMERO_PAGINAS(datos[13].toString());
            documental.setTIPO_OTRO(datos[14].toString());
            documental.setOTRO(datos[15].toString());
            documental.setID_TIPO_OTRO(Integer.parseInt(datos[16].toString()));
        }
        return documental;
    }

    @Override
    public ArrayList<String> listDocumentalRelacionXidDocumental(String idDocumental) {
        ArrayList<String> lst = new ArrayList<>();
        String[] parametros = new String[3];
        parametros[0] = idDocumental;
        parametros[1] = "";
        parametros[2] = "LIST_X_ID";
        ArrayList<Object[]> data = conector.execProcedure("[BV].[SP_MANTENIMIENTO_DOCUMENTAL_RELACION_DOCUMENTAL]", parametros);
        for (Object[] d : data) {
            String len = d[0].toString();

            lst.add(len);

        }
        return lst;

    }

    @Override
    public ArrayList<Documental> listDocumentalPublicacion(String perfilControl, String idBiblioteca, String idEstadoControl) {
        ArrayList<Documental> lstDocPublicacion = new ArrayList<>();
        Documental doc;
        String[] parametros = new String[5];
        parametros[0] = "1";
        parametros[1] = perfilControl;
        parametros[2] = "";
        parametros[3] = idBiblioteca;
        parametros[4] = idEstadoControl;
        ArrayList<Object[]> data = conector.execProcedure("[BV].[SP_PUBLICACION_LISTA]", parametros);
        for (Object[] d : data) {
            doc = new Documental();
            doc.setID_DOCUMENTAL(d[0].toString());
            doc.setTITULO(d[1].toString());
            doc.setISBN(d[2].toString());
            doc.setURL(d[3].toString());
            doc.setNUMERO_PAGINAS(d[4].toString());
            doc.setOTRO(d[5].toString());
            lstDocPublicacion.add(doc);
        }
        return lstDocPublicacion;
    }

    @Override
    public Documental listDocumentalPublicacionPaginacion(String perfilControl, String idBiblioteca, String idEstadoControl, int first, int pageSize, String query) {
        ArrayList<Documental> lstDocPublicacion = new ArrayList<>();
        Documental documental=new Documental();
        Documental doc;

        ResultSet rs;

        ///----------------------------------------------------------
        Connection conn = ConectaDb.getConnection();

        try {
            CallableStatement cs = conn.prepareCall("{call [BV].[SP_PUBLICACION_LISTA_PAGINACION](?,?,?,?,?,?,?,?,?)}");
            cs.setString(1,"");
            cs.setString(2,perfilControl);
            cs.setString(3,"");
            cs.setString(4,idBiblioteca);
            cs.setString(5,idEstadoControl);
            cs.setInt(6,first);
            cs.setInt(7,pageSize);
            cs.setString(8,query);
            cs.registerOutParameter(9, Types.INTEGER);
             rs = cs.executeQuery();
             while (rs.next()) {
                  doc = new Documental();
            doc.setID_DOCUMENTAL(rs.getString(1));
            doc.setTITULO(rs.getString(2));
            doc.setISBN(rs.getString(3));
            doc.setURL(rs.getString(4));
            doc.setNUMERO_PAGINAS(rs.getString(5));
            doc.setOTRO(rs.getString(6));
            lstDocPublicacion.add(doc);
                 
                
            }
             documental.setLstDocumental(lstDocPublicacion);
             documental.setCountQuery(cs.getInt(9));
            
            
            
        } catch (Exception e) {
            System.out.println("ERROR :"+e);
        }

       
        return documental;

    }

    @Override
    public String nombreArchivo(String id) {
        String nombreArchivo = "";
        String[] parametros = new String[1];
        parametros[0] = id;
        ArrayList<Object[]> data = conector.execProcedure("[BV].[SP_GENERA_NOMBRE_ARCHIVO]", parametros);
        for (Object[] d : data) {
            nombreArchivo = d[0].toString();
        }
        return nombreArchivo;
    }

    @Override
    public String controlDocumental(String idDoc, String url, int idUsuario, String publicado, String perfil) {
        String msg = "";
        String[] parametros = new String[6];
        parametros[0] = idDoc;
        parametros[1] = url;
        parametros[2] = "4";
        parametros[3] = String.valueOf(idUsuario);
        parametros[4] = publicado;
        parametros[5] = perfil;
        ArrayList<Object[]> data = conector.execProcedure("[BV].[SP_CONTROL_DOCUMENTAL]", parametros);
        for (Object[] d : data) {
            msg = d[1].toString();
        }
        return msg;
    }

    @Override
    public boolean validarFichero(String servArch, String archivo) {
        boolean existe;
        String url = servArch + archivo;
        File fichero = new File(url);
        existe = fichero.exists();
        return existe;

    }

    @Override
    public String controlDocumentalObservacion(String idDoc, String estadoCont, String observacion, int idUsuario) {
        String msg = "";
        String[] parametros = new String[4];
        parametros[0] = idDoc;
        parametros[1] = estadoCont;
        parametros[2] = observacion;
        parametros[3] = String.valueOf(idUsuario);

        ArrayList<Object[]> data = conector.execProcedure("[BV].[SP_DOCUMENTAL_ESTADO_CONTROL_Insertar]", parametros);
        for (Object[] d : data) {
            msg = d[1].toString();
        }
        return msg;

    }

}
