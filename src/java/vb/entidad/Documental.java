package vb.entidad;

import java.util.Date;
import java.util.List;

/**
 *
 * @author Renato Vásquez Tejada - renatovt11@gmail.com
 */
public class Documental {

    private String ID_DOCUMENTAL;
    private String TITULO;
    private String TITULO_ALTERNATIVO;
    private String DESCRIPCION;
    private String RESUMEN;
    private Integer ID_TIPO;
    //variable extendida
    private String TIPO;
    private int ID_BIBLIOTECA_FUENTE;
    //variable extendida
    private String BIBLIOTECA_FUENTE;
    private String DISTRITO;
    private String PROVINCIA;
    private String ID_DOCUMENTAL_RELACION;
    //variable extendida
    private String DOCUMENTAL_RELACION;
    private String TIENE_COMO_VERSION;
    private String ES_PARTE_DE = "";
    private String TIENE_PARTE_DE;
    private Integer ID_COBERTURA_ESPACIAL;
    //variable extendida
    private String COBERTURA_ESPACIAL;
    private Integer ID_COBERTURA_TEMPORAL;
    //variable extendida
    private String COBERTURA_TEMPORAL;
    private String FECHA_DISPONIBLE;
    private String FECHA_PUBLICACION;
    private Date FECHA_ACEPTACION;
    private Date FECHA_COPYRIGHT;
    private String ID_FORMATO;
    //variable extendida
    private String FORMATO;
    private String FORMATO_EXTENSION;
    private Integer ID_FORMATO_MEDIO;
    private String FORMATO_MEDIO_DESCRIPCION;
    private Integer ID_EDITORIAL;
    //variable extendida
    private String EDITORIAL;
    private String DERECHO;
    private String DERECHO_ACCESO;
    private Integer ID_AUDIENCIA;
    //variable extendida
    private String AUDIENCIA;
    private String URL;
    private String ISBN;

    private String OTRO;
    private String NUMERO_PAGINAS;
    private Integer ACTIVO;
    private Integer ID_TIPO_OTRO;
    private String PAIS_DEFINIDO;
    private String CIUDAD_DEFINIDA;
    private Integer PAGINA_INICIO;
    private Integer PAGINA_FIN=0;
    private String NOTA;

    //// VARIABLE AUXILIAR DTO
    private String TIPO_OTRO;
    private String ID_BIBLIOTECA_DOCUMENTAL;

    private Integer ID_ALBUM;
    
    private List<Documental> lstDocumental;
    private int countQuery;

    public Documental() {

    }

    public int getCountQuery() {
        return countQuery;
    }

    public void setCountQuery(int countQuery) {
        this.countQuery = countQuery;
    }

  
    

    public List<Documental> getLstDocumental() {
        return lstDocumental;
    }

    public void setLstDocumental(List<Documental> lstDocumental) {
        this.lstDocumental = lstDocumental;
    }

    public String getID_BIBLIOTECA_DOCUMENTAL() {
        return ID_BIBLIOTECA_DOCUMENTAL;
    }

    public void setID_BIBLIOTECA_DOCUMENTAL(String ID_BIBLIOTECA_DOCUMENTAL) {
        this.ID_BIBLIOTECA_DOCUMENTAL = ID_BIBLIOTECA_DOCUMENTAL;
    }

    
    public String getTIPO_OTRO() {
        return TIPO_OTRO;
    }

    public void setTIPO_OTRO(String TIPO_OTRO) {
        this.TIPO_OTRO = TIPO_OTRO;
    }

    public String getPAIS_DEFINIDO() {
        return PAIS_DEFINIDO;
    }

    public void setPAIS_DEFINIDO(String PAIS_DEFINIDO) {
        this.PAIS_DEFINIDO = PAIS_DEFINIDO;
    }

    public String getCIUDAD_DEFINIDA() {
        return CIUDAD_DEFINIDA;
    }

    public void setCIUDAD_DEFINIDA(String CIUDAD_DEFINIDA) {
        this.CIUDAD_DEFINIDA = CIUDAD_DEFINIDA;
    }

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public String getOTRO() {
        return OTRO;
    }

    public void setOTRO(String OTRO) {
        this.OTRO = OTRO;
    }

    public String getID_DOCUMENTAL() {
        return ID_DOCUMENTAL;
    }

    public void setID_DOCUMENTAL(String ID_DOCUMENTAL) {
        this.ID_DOCUMENTAL = ID_DOCUMENTAL;
    }

    public String getTITULO() {
        return TITULO;
    }

    public void setTITULO(String TITULO) {
        this.TITULO = TITULO;
    }

    public String getTITULO_ALTERNATIVO() {
        return TITULO_ALTERNATIVO;
    }

    public void setTITULO_ALTERNATIVO(String TITULO_ALTERNATIVO) {
        this.TITULO_ALTERNATIVO = TITULO_ALTERNATIVO;
    }

    public String getDESCRIPCION() {
        return DESCRIPCION;
    }

    public void setDESCRIPCION(String DESCRIPCION) {
        this.DESCRIPCION = DESCRIPCION;
    }

    public String getRESUMEN() {
        return RESUMEN;
    }

    public void setRESUMEN(String RESUMEN) {
        this.RESUMEN = RESUMEN;
    }

    public Integer getID_TIPO() {
        return ID_TIPO;
    }

    public void setID_TIPO(Integer ID_TIPO) {
        this.ID_TIPO = ID_TIPO;
    }

    public int getID_BIBLIOTECA_FUENTE() {
        return ID_BIBLIOTECA_FUENTE;
    }

    public void setID_BIBLIOTECA_FUENTE(Integer ID_BIBLIOTECA_FUENTE) {
        this.ID_BIBLIOTECA_FUENTE = ID_BIBLIOTECA_FUENTE;
    }

    public String getID_DOCUMENTAL_RELACION() {
        return ID_DOCUMENTAL_RELACION;
    }

    public void setID_DOCUMENTAL_RELACION(String ID_DOCUMENTAL_RELACION) {
        this.ID_DOCUMENTAL_RELACION = ID_DOCUMENTAL_RELACION;
    }

    public String getTIENE_COMO_VERSION() {
        return TIENE_COMO_VERSION;
    }

    public void setTIENE_COMO_VERSION(String TIENE_COMO_VERSION) {
        this.TIENE_COMO_VERSION = TIENE_COMO_VERSION;
    }

    public String getES_PARTE_DE() {
        return ES_PARTE_DE;
    }

    public void setES_PARTE_DE(String ES_PARTE_DE) {
        this.ES_PARTE_DE = ES_PARTE_DE;
    }

    public String getTIENE_PARTE_DE() {
        return TIENE_PARTE_DE;
    }

    public void setTIENE_PARTE_DE(String TIENE_PARTE_DE) {
        this.TIENE_PARTE_DE = TIENE_PARTE_DE;
    }

    public Integer getID_COBERTURA_ESPACIAL() {
        return ID_COBERTURA_ESPACIAL;
    }

    public void setID_COBERTURA_ESPACIAL(Integer ID_COBERTURA_ESPACIAL) {
        this.ID_COBERTURA_ESPACIAL = ID_COBERTURA_ESPACIAL;
    }

    public Integer getID_COBERTURA_TEMPORAL() {
        return ID_COBERTURA_TEMPORAL;
    }

    public void setID_COBERTURA_TEMPORAL(Integer ID_COBERTURA_TEMPORAL) {
        this.ID_COBERTURA_TEMPORAL = ID_COBERTURA_TEMPORAL;
    }

    public String getFECHA_DISPONIBLE() {
        return FECHA_DISPONIBLE;
    }

    public void setFECHA_DISPONIBLE(String FECHA_DISPONIBLE) {
        this.FECHA_DISPONIBLE = FECHA_DISPONIBLE;
    }

    public String getFECHA_PUBLICACION() {
        return FECHA_PUBLICACION;
    }

    public void setFECHA_PUBLICACION(String FECHA_PUBLICACION) {
        this.FECHA_PUBLICACION = FECHA_PUBLICACION;
    }

    public Date getFECHA_ACEPTACION() {
        return FECHA_ACEPTACION;
    }

    public void setFECHA_ACEPTACION(Date FECHA_ACEPTACION) {
        this.FECHA_ACEPTACION = FECHA_ACEPTACION;
    }

    public Date getFECHA_COPYRIGHT() {
        return FECHA_COPYRIGHT;
    }

    public void setFECHA_COPYRIGHT(Date FECHA_COPYRIGHT) {
        this.FECHA_COPYRIGHT = FECHA_COPYRIGHT;
    }

    public String getID_FORMATO() {
        return ID_FORMATO;
    }

    public void setID_FORMATO(String ID_FORMATO) {
        this.ID_FORMATO = ID_FORMATO;
    }

    public String getFORMATO() {
        return FORMATO;
    }

    public void setFORMATO(String FORMATO) {
        this.FORMATO = FORMATO;
    }

    public String getFORMATO_EXTENSION() {
        return FORMATO_EXTENSION;
    }

    public void setFORMATO_EXTENSION(String FORMATO_EXTENSION) {
        this.FORMATO_EXTENSION = FORMATO_EXTENSION;
    }

    public Integer getID_FORMATO_MEDIO() {
        return ID_FORMATO_MEDIO;
    }

    public void setID_FORMATO_MEDIO(Integer ID_FORMATO_MEDIO) {
        this.ID_FORMATO_MEDIO = ID_FORMATO_MEDIO;
    }

    public String getFORMATO_MEDIO_DESCRIPCION() {
        return FORMATO_MEDIO_DESCRIPCION;
    }

    public void setFORMATO_MEDIO_DESCRIPCION(String FORMATO_MEDIO_DESCRIPCION) {
        this.FORMATO_MEDIO_DESCRIPCION = FORMATO_MEDIO_DESCRIPCION;
    }

    public Integer getID_EDITORIAL() {
        return ID_EDITORIAL;
    }

    public void setID_EDITORIAL(Integer ID_EDITORIAL) {
        this.ID_EDITORIAL = ID_EDITORIAL;
    }

    public String getDERECHO() {
        return DERECHO;
    }

    public void setDERECHO(String DERECHO) {
        this.DERECHO = DERECHO;
    }

    public String getDERECHO_ACCESO() {
        return DERECHO_ACCESO;
    }

    public void setDERECHO_ACCESO(String DERECHO_ACCESO) {
        this.DERECHO_ACCESO = DERECHO_ACCESO;
    }

    public Integer getID_AUDIENCIA() {
        return ID_AUDIENCIA;
    }

    public void setID_AUDIENCIA(Integer ID_AUDIENCIA) {
        this.ID_AUDIENCIA = ID_AUDIENCIA;
    }

    //VARIABLES EXTENDIDAS
    public String getTIPO() {
        return TIPO;
    }

    public void setTIPO(String TIPO) {
        this.TIPO = TIPO;
    }

    public String getBIBLIOTECA_FUENTE() {
        return BIBLIOTECA_FUENTE;
    }

    public void setBIBLIOTECA_FUENTE(String BIBLIOTECA_FUENTE) {
        this.BIBLIOTECA_FUENTE = BIBLIOTECA_FUENTE;
    }

    public String getDOCUMENTAL_RELACION() {
        return DOCUMENTAL_RELACION;
    }

    public void setDOCUMENTAL_RELACION(String DOCUMENTAL_RELACION) {
        this.DOCUMENTAL_RELACION = DOCUMENTAL_RELACION;
    }

    public String getCOBERTURA_ESPACIAL() {
        return COBERTURA_ESPACIAL;
    }

    public void setCOBERTURA_ESPACIAL(String COBERTURA_ESPACIAL) {
        this.COBERTURA_ESPACIAL = COBERTURA_ESPACIAL;
    }

    public String getCOBERTURA_TEMPORAL() {
        return COBERTURA_TEMPORAL;
    }

    public void setCOBERTURA_TEMPORAL(String COBERTURA_TEMPORAL) {
        this.COBERTURA_TEMPORAL = COBERTURA_TEMPORAL;
    }

    public String getEDITORIAL() {
        return EDITORIAL;
    }

    public void setEDITORIAL(String EDITORIAL) {
        this.EDITORIAL = EDITORIAL;
    }

    public String getAUDIENCIA() {
        return AUDIENCIA;
    }

    public void setAUDIENCIA(String AUDIENCIA) {
        this.AUDIENCIA = AUDIENCIA;
    }

    public String getNUMERO_PAGINAS() {
        return NUMERO_PAGINAS;
    }

    public void setNUMERO_PAGINAS(String NUMERO_PAGINAS) {
        this.NUMERO_PAGINAS = NUMERO_PAGINAS;
    }

    public Integer getACTIVO() {
        return ACTIVO;
    }

    public void setACTIVO(Integer ACTIVO) {
        this.ACTIVO = ACTIVO;
    }

    public Integer getID_TIPO_OTRO() {
        return ID_TIPO_OTRO;
    }

    public void setID_TIPO_OTRO(Integer ID_TIPO_OTRO) {
        this.ID_TIPO_OTRO = ID_TIPO_OTRO;
    }

    public Integer getPAGINA_INICIO() {
        return PAGINA_INICIO;
    }

    public void setPAGINA_INICIO(Integer PAGINA_INICIO) {
        this.PAGINA_INICIO = PAGINA_INICIO;
    }

    public Integer getPAGINA_FIN() {
        return PAGINA_FIN;
    }

    public void setPAGINA_FIN(Integer PAGINA_FIN) {
        this.PAGINA_FIN = PAGINA_FIN;
    }

    public String getNOTA() {
        return NOTA;
    }

    public void setNOTA(String NOTA) {
        this.NOTA = NOTA;
    }

    public String getDISTRITO() {
        return DISTRITO;
    }

    public void setDISTRITO(String DISTRITO) {
        this.DISTRITO = DISTRITO;
    }

    public String getPROVINCIA() {
        return PROVINCIA;
    }

    public void setPROVINCIA(String PROVINCIA) {
        this.PROVINCIA = PROVINCIA;
    }

    public Integer getID_ALBUM() {
        return ID_ALBUM;
    }

    public void setID_ALBUM(Integer ID_ALBUM) {
        this.ID_ALBUM = ID_ALBUM;
    }

}
