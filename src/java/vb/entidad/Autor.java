package vb.entidad;

import java.io.Serializable;
import java.util.List;

public class Autor implements Serializable{

    private Integer ID_AUTOR;
    private String NOMBRE;
    private String APELLIDO_PATERNO;
    private String APELLIDO_MATERNO;
    private String ID_PAIS;
    private String PAIS;
    private String ID_BIBLIOTECA_REGISTRO;
    private String ALTERNATIVO;
    private String AUTOR;
    // auxiliares
    private String ID_DOCUMENTAL;
    private List<Autor> lstAutor;
    private int countQuery;
    
      public Autor() {
    }

    public List<Autor> getLstAutor() {
        return lstAutor;
    }

    public String getAUTOR() {
        return AUTOR;
    }

    public void setAUTOR(String AUTOR) {
        this.AUTOR = AUTOR;
    }

    public void setLstAutor(List<Autor> lstAutor) {
        this.lstAutor = lstAutor;
    }

    public int getCountQuery() {
        return countQuery;
    }

    public void setCountQuery(int countQuery) {
        this.countQuery = countQuery;
    }

    public String getID_DOCUMENTAL() {
        return ID_DOCUMENTAL;
    }

    public void setID_DOCUMENTAL(String ID_DOCUMENTAL) {
        this.ID_DOCUMENTAL = ID_DOCUMENTAL;
    }
     public String getID_BIBLIOTECA_REGISTRO() {
        return ID_BIBLIOTECA_REGISTRO;
    }

    public void setID_BIBLIOTECA_REGISTRO(String ID_BIBLIOTECA_REGISTRO) {
        this.ID_BIBLIOTECA_REGISTRO = ID_BIBLIOTECA_REGISTRO;
    }

  
    public Autor(Integer ID_AUTOR, String NOMBRE, String APELLIDO_PATERNO, String APELLIDO_MATERNO, String ID_PAIS) {
        this.ID_AUTOR = ID_AUTOR;
        this.NOMBRE = NOMBRE;
        this.APELLIDO_PATERNO = APELLIDO_PATERNO;
        this.APELLIDO_MATERNO = APELLIDO_MATERNO;
        this.ID_PAIS = ID_PAIS;        
    }

    public Integer getID_AUTOR() {
        return ID_AUTOR;
    }

    public void setID_AUTOR(Integer ID_AUTOR) {
        this.ID_AUTOR = ID_AUTOR;
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

    public String getID_PAIS() {
        return ID_PAIS;
    }

    public void setID_PAIS(String ID_PAIS) {
        this.ID_PAIS = ID_PAIS;
    }

    public String getPAIS() {
        return PAIS;
    }

    public void setPAIS(String PAIS) {
        this.PAIS = PAIS;
    }

    public String getALTERNATIVO() {
        return ALTERNATIVO;
    }

    public void setALTERNATIVO(String ALTERNATIVO) {
        this.ALTERNATIVO = ALTERNATIVO;
    }
    
    

}
