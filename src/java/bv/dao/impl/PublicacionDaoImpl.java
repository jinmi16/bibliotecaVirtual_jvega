package bv.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import vb.dto.PublicacionDto;
import bv.util.sql;
import bv.dao.PublicacionDao;
import bv.util.ConectaDb;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Types;

/**
 *
 * @author virtual
 */
public class PublicacionDaoImpl implements PublicacionDao {

    sql conector = new sql();

    @Override
    public ArrayList<PublicacionDto> listPublicacion(String ID_PERFIL, String ID_BIBLIOTECA, String visibilidad) {
        String[] parametros = new String[3];
        ArrayList<PublicacionDto> lPublicacion = new ArrayList<>();
        parametros[0] = ID_PERFIL;
        parametros[1] = ID_BIBLIOTECA;
        parametros[2] = visibilidad;
        ArrayList<Object[]> data = conector.execProcedure("[BV].[SP_LISTAR_DOCUMENTAL_PUBLICADO]", parametros);
        for (Object[] datos : data) {
            PublicacionDto dto = new PublicacionDto();

            dto.setID_DOCUMENTAL(datos[0].toString());
            dto.setOTRO(datos[1].toString());
            dto.setTITULO(datos[2].toString());
            dto.setURL(datos[3].toString());
            dto.setNRO_VISITAS(Integer.parseInt(datos[4].toString()));
            dto.setID_USUARIO(Integer.parseInt(datos[5].toString()));
            dto.setNOMBRE(datos[6].toString());
            dto.setAPELLIDO_PATERNO(datos[7].toString());
            dto.setAPELLIDO_MATERNO(datos[8].toString());
            dto.setFECHA_PUBLICACION((Date) datos[9]);
            dto.setVISIBLE(Integer.parseInt(datos[10].toString()));
            if (dto.getVISIBLE() == 1) {
                dto.setCLASS_VISIBLE("GreenBack");
                dto.setICONO_VISIBLE("fa fa-eye");
            } else if (dto.getVISIBLE() == 0) {
                dto.setCLASS_VISIBLE("RedBack");
                dto.setICONO_VISIBLE("fa fa-eye-slash");
            }
            lPublicacion.add(dto);
        }
        return lPublicacion;
    }

    @Override
    public PublicacionDto listPublicacionPaginado(String ID_PERFIL, String ID_BIBLIOTECA, String visibilidad, int first, int pageSize, String query) {
        PublicacionDto publicacionDto = new PublicacionDto();
        ArrayList<PublicacionDto> lPublicacion = new ArrayList<>();
        Connection conn = ConectaDb.getConnection();
        ResultSet rs;
        try {
            CallableStatement cs = conn.prepareCall("{call [BV].[SP_LISTAR_DOCUMENTAL_PUBLICADO_PAGINACION](?,?,?,?,?,?,?)}");
            System.out.println("[BV].[SP_LISTAR_DOCUMENTAL_PUBLICADO_PAGINACION]('"+ID_PERFIL+"','"+ID_BIBLIOTECA+"','"+visibilidad+"','"+first+"','"+pageSize+"','"+query+"','output')");
            cs.setInt(1, Integer.parseInt(ID_PERFIL));
            cs.setInt(2, Integer.parseInt(ID_BIBLIOTECA));
            cs.setInt(3, Integer.parseInt(visibilidad));
            cs.setInt(4, first);
            cs.setInt(5, pageSize);
            cs.setString(6, query);
            cs.registerOutParameter(7, Types.INTEGER);
            rs = cs.executeQuery();
             PublicacionDto dto ;
            while (rs.next()) { 
                System.out.println("documental  :" +rs.getString(1)+" - "+rs.getString(2));
                dto = new PublicacionDto();
                dto.setID_DOCUMENTAL(rs.getString(1));
                dto.setOTRO(rs.getString(2));
                dto.setTITULO(rs.getString(3));
                dto.setURL(rs.getString(4));
                dto.setNRO_VISITAS(rs.getInt(5));
                dto.setID_USUARIO(rs.getInt(6));
                dto.setNOMBRE(rs.getString(7));
                dto.setAPELLIDO_PATERNO(rs.getString(8));
                dto.setAPELLIDO_MATERNO(rs.getString(9));
                dto.setFECHA_PUBLICACION(rs.getDate(10));
                dto.setVISIBLE(rs.getInt(11));
                
                if (dto.getVISIBLE() == 1) {
                    dto.setCLASS_VISIBLE("GreenBack");
                    dto.setICONO_VISIBLE("fa fa-eye");
                } else if (dto.getVISIBLE() == 0) {
                    dto.setCLASS_VISIBLE("RedBack");
                    dto.setICONO_VISIBLE("fa fa-eye-slash");
                }
                lPublicacion.add(dto);
              
            }
            publicacionDto.setLstPublicacionDto(lPublicacion);
            publicacionDto.setCoutQuery(cs.getInt(7));
        } catch (Exception e) {
            System.out.println("ERROR :"+e);
        }
        return publicacionDto;
    }

    @Override
    public int cambiaVisibilidad(PublicacionDto pub) {
        int update = 0;
        String[] parametros = new String[2];
        parametros[0] = pub.getID_DOCUMENTAL();
        if (pub.getVISIBLE() == 1) {
            parametros[1] = "0";
        } else if (pub.getVISIBLE() == 0) {
            parametros[1] = "1";
        }
        ArrayList<Object[]> data = conector.execProcedure("[BV].[SP_UPDATE_PUBLICACION_VISIBLE]", parametros);
        for (Object[] datos : data) {
            update = Integer.parseInt(datos[0].toString());
        }
        return update;
    }

}
