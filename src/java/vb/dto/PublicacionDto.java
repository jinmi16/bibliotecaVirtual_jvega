package vb.dto;

import java.util.Date;
import java.util.List;

/**
 *
 * @author virtual
 */
public class PublicacionDto {

    private String ID_PUBLICACION;
    private String ID_DOCUMENTAL;
    private String OTRO;
    private String TITULO;
    private String URL;
    private int NRO_VISITAS;
    private int ID_USUARIO;
    private String NOMBRE;
    private String APELLIDO_PATERNO;
    private String APELLIDO_MATERNO;
    private Date FECHA_PUBLICACION;
    private int VISIBLE;
    private String CLASS_VISIBLE;
    private String ICONO_VISIBLE;
    private String Responsable;
    
    private List<PublicacionDto> lstPublicacionDto;
    private int coutQuery;

    public List<PublicacionDto> getLstPublicacionDto() {
        return lstPublicacionDto;
    }

    public void setLstPublicacionDto(List<PublicacionDto> lstPublicacionDto) {
        this.lstPublicacionDto = lstPublicacionDto;
    }

    public int getCoutQuery() {
        return coutQuery;
    }

    public void setCoutQuery(int coutQuery) {
        this.coutQuery = coutQuery;
    }

    
    
    public String getID_PUBLICACION() {
        return ID_PUBLICACION;
    }

    public void setID_PUBLICACION(String ID_PUBLICACION) {
        this.ID_PUBLICACION = ID_PUBLICACION;
    }

    public String getID_DOCUMENTAL() {
        return ID_DOCUMENTAL;
    }

    public String getResponsable() {
        
        return Responsable=APELLIDO_PATERNO+ " "+APELLIDO_MATERNO+", "+NOMBRE;
    }

    public void setResponsable(String Responsable) {
        this.Responsable = Responsable;
    }

    public void setID_DOCUMENTAL(String ID_DOCUMENTAL) {
        this.ID_DOCUMENTAL = ID_DOCUMENTAL;
    }

    public String getOTRO() {
        return OTRO;
    }

    public void setOTRO(String OTRO) {
        this.OTRO = OTRO;
    }

    public String getTITULO() {
        return TITULO;
    }

    public void setTITULO(String TITULO) {
        this.TITULO = TITULO;
    }

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    public int getNRO_VISITAS() {
        return NRO_VISITAS;
    }

    public void setNRO_VISITAS(int NRO_VISITAS) {
        this.NRO_VISITAS = NRO_VISITAS;
    }

    public int getID_USUARIO() {
        return ID_USUARIO;
    }

    public void setID_USUARIO(int ID_USUARIO) {
        this.ID_USUARIO = ID_USUARIO;
    }

    public String getNOMBRE() {
        return NOMBRE;
    }

    public void setNOMBRE(String NOMBRE) {
        this.NOMBRE = NOMBRE;
    }

    public String getAPELLIDO_PATERNO() {
        return APELLIDO_PATERNO;
    }

    public void setAPELLIDO_PATERNO(String APELLIDO_PATERNO) {
        this.APELLIDO_PATERNO = APELLIDO_PATERNO;
    }

    public String getAPELLIDO_MATERNO() {
        return APELLIDO_MATERNO;
    }

    public void setAPELLIDO_MATERNO(String APELLIDO_MATERNO) {
        this.APELLIDO_MATERNO = APELLIDO_MATERNO;
    }

    public Date getFECHA_PUBLICACION() {
        return FECHA_PUBLICACION;
    }

    public void setFECHA_PUBLICACION(Date FECHA_PUBLICACION) {
        this.FECHA_PUBLICACION = FECHA_PUBLICACION;
    }

    public int getVISIBLE() {
        return VISIBLE;
    }

    public void setVISIBLE(int VISIBLE) {
        this.VISIBLE = VISIBLE;
    }

    public String getCLASS_VISIBLE() {
        return CLASS_VISIBLE;
    }

    public void setCLASS_VISIBLE(String CLASS_VISIBLE) {
        this.CLASS_VISIBLE = CLASS_VISIBLE;
    }

    public String getICONO_VISIBLE() {
        return ICONO_VISIBLE;
    }

    public void setICONO_VISIBLE(String ICONO_VISIBLE) {
        this.ICONO_VISIBLE = ICONO_VISIBLE;
    }

}
