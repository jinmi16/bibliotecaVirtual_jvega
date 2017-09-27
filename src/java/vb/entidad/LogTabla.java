package vb.entidad;

import java.util.List;

/**
 *
 * @author virtual
 */
public class LogTabla {

    private Integer ID_LOG;
    private String ID_TABLA;
    private String ID_REGISTRO;
    private String ID_ACCION;
    private String ID_USUARIO;
    private String ID_BIBLIOTECA;
    private String FECHA;
    private String HORA;
    private String ID_DOCUMENTAL;
    private String OTRO;
    // variables auxiliares
    private String nameTabla;
    private String nameEsquema;
    private String nameAccion;
    private String nameUsuario;
    private String namePersonal;
    private String nameBiblioteca;
    
    //*****------
   private List<LogTabla> lstLogTabla;
   private int countQuery;

    public List<LogTabla> getLstLogTabla() {
        return lstLogTabla;
    }

    public String getID_BIBLIOTECA() {
        return ID_BIBLIOTECA;
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

    public void setID_BIBLIOTECA(String ID_BIBLIOTECA) {
        this.ID_BIBLIOTECA = ID_BIBLIOTECA;
    }

    public String getNameBiblioteca() {
        return nameBiblioteca;
    }

    public void setNameBiblioteca(String nameBiblioteca) {
        this.nameBiblioteca = nameBiblioteca;
    }

    public String getNamePersonal() {
        return namePersonal;
    }

    public void setNamePersonal(String namePersonal) {
        this.namePersonal = namePersonal;
    }

    public String getNameUsuario() {
        return nameUsuario;
    }

    public void setNameUsuario(String nameUsuario) {
        this.nameUsuario = nameUsuario;
    }

    
    public String getNameAccion() {
        return nameAccion;
    }

    public void setNameAccion(String nameAccion) {
        this.nameAccion = nameAccion;
    }

    
    public String getHORA() {
        return HORA;
    }

    public void setHORA(String HORA) {
        this.HORA = HORA;
    }

    public void setLstLogTabla(List<LogTabla> lstLogTabla) {
        this.lstLogTabla = lstLogTabla;
    }

    public int getCountQuery() {
        return countQuery;
    }

    public void setCountQuery(int countQuery) {
        this.countQuery = countQuery;
    }
    
   

    
    
    
    
    public LogTabla(String nameEsquema, String nameTabla, String ID_REGISTRO, String ID_ACCION, String ID_USUARIO) {
        this.ID_REGISTRO = ID_REGISTRO;
        this.ID_ACCION = ID_ACCION;
        this.ID_USUARIO = ID_USUARIO;
        this.nameTabla = nameTabla;
        this.nameEsquema = nameEsquema;
    }
    

    
    
    public LogTabla() {
    }

    public String getNameTabla() {
        return nameTabla;
    }

    public void setNameTabla(String nameTabla) {
        this.nameTabla = nameTabla;
    }

    public String getNameEsquema() {
        return nameEsquema;
    }

    public void setNameEsquema(String nameEsquema) {
        this.nameEsquema = nameEsquema;
    }

  

    public Integer getID_LOG() {
        return ID_LOG;
    }

    public void setID_LOG(Integer ID_LOG) {
        this.ID_LOG = ID_LOG;
    }

    public String getID_TABLA() {
        return ID_TABLA;
    }

    public void setID_TABLA(String ID_TABLA) {
        this.ID_TABLA = ID_TABLA;
    }

    public String getID_REGISTRO() {
        return ID_REGISTRO;
    }

    public void setID_REGISTRO(String ID_REGISTRO) {
        this.ID_REGISTRO = ID_REGISTRO;
    }

    public String getID_ACCION() {
        return ID_ACCION;
    }

    public void setID_ACCION(String ID_ACCION) {
        this.ID_ACCION = ID_ACCION;
    }

    public String getID_USUARIO() {
        return ID_USUARIO;
    }

    public void setID_USUARIO(String ID_USUARIO) {
        this.ID_USUARIO = ID_USUARIO;
    }

    public String getFECHA() {
        return FECHA;
    }

    public void setFECHA(String FECHA) {
        this.FECHA = FECHA;
    }
    
    

}
