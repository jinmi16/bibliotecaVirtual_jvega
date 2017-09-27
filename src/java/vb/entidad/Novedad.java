package vb.entidad;

import java.io.Serializable;
import java.util.Date;


public class Novedad implements Serializable{

    private Integer ID_NOVEDADES;
    private String TITULO_CORTO;
    private String TITULO_COMPLETO;
    private String DESCRIPCION;
    private String CONTENIDO;
    private String S_FECHA_NOVEDAD;
    private Date FECHA_NOVEDAD;
    private Date FECHA_REGISTRO;
    private String MOSTAR_INICIO;
    private String ACTIVO;
    private String URL_IMAGEN;
    

    public Novedad() {
    }

    public Integer getID_NOVEDADES() {
        return ID_NOVEDADES;
    }

    public void setID_NOVEDADES(Integer ID_NOVEDADES) {
        this.ID_NOVEDADES = ID_NOVEDADES;
    }

    public String getTITULO_CORTO() {
        return TITULO_CORTO;
    }

    public void setTITULO_CORTO(String TITULO_CORTO) {
        this.TITULO_CORTO = TITULO_CORTO;
    }

    public String getTITULO_COMPLETO() {
        return TITULO_COMPLETO;
    }

    public void setTITULO_COMPLETO(String TITULO_COMPLETO) {
        this.TITULO_COMPLETO = TITULO_COMPLETO;
    }

    public String getDESCRIPCION() {
        return DESCRIPCION;
    }

    public void setDESCRIPCION(String DESCRIPCION) {
        this.DESCRIPCION = DESCRIPCION;
    }

    public String getCONTENIDO() {
        return CONTENIDO;
    }

    public void setCONTENIDO(String CONTENIDO) {
        this.CONTENIDO = CONTENIDO;
    }

    public String getS_FECHA_NOVEDAD() {
        return S_FECHA_NOVEDAD;
    }

    public void setS_FECHA_NOVEDAD(String S_FECHA_NOVEDAD) {
        this.S_FECHA_NOVEDAD = S_FECHA_NOVEDAD;
    }
    
    public Date getFECHA_NOVEDAD() {
        return FECHA_NOVEDAD;
    }

    public void setFECHA_NOVEDAD(Date FECHA_NOVEDAD) {
        this.FECHA_NOVEDAD = FECHA_NOVEDAD;
    }

    public Date getFECHA_REGISTRO() {
        return FECHA_REGISTRO;
    }

    public void setFECHA_REGISTRO(Date FECHA_REGISTRO) {
        this.FECHA_REGISTRO = FECHA_REGISTRO;
    }

    public String getMOSTAR_INICIO() {
        return MOSTAR_INICIO;
    }

    public void setMOSTAR_INICIO(String MOSTAR_INICIO) {
        this.MOSTAR_INICIO = MOSTAR_INICIO;
    }

    public String getACTIVO() {
        return ACTIVO;
    }

    public void setACTIVO(String ACTIVO) {
        this.ACTIVO = ACTIVO;
    }

    public String getURL_IMAGEN() {
        return URL_IMAGEN;
    }

    public void setURL_IMAGEN(String URL_IMAGEN) {
        this.URL_IMAGEN = URL_IMAGEN;
    }
    
    
}

