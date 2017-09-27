package vb.entidad;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Renato Vásquez Tejada - renatovt11@gmail.com
 */
public class Biblioteca {

    private int ID_BIBLIOTECA_MEDIADOR;
    private int ID_INSTITUCION_MEDIADOR;
    private String NOMBRE_INSTITUCION;
    private String NOMBRE_BIBLIOTECA;
    private String DIRECCION;
    private String EMAIL;
    private String CODIGO_SNB;
    private String ID_DISTRITO;
    private String DISTRITO;
    private String ID_PROVINCIA;
    private String PROVINCIA;
    private String ID_REGION;
    private String REGION;
    private Boolean ACTIVO;
    private int NRO_PERSONAL;
    private String URL;
    private String DIRECTORIO;
    private String TITULO_MAPA;
    private String LATITUD;
    private String LONGITUD;
    // auxiliares
    ArrayList<String> selectedPerfiles;
    ArrayList<PerfilDocumental> lstPerfilDocumental;
    List<PerfilDocumentalDetalle> lstPerfilDocumentalDetalle;

    public List<PerfilDocumentalDetalle> getLstPerfilDocumentalDetalle() {
        return lstPerfilDocumentalDetalle;
    }

    public void setLstPerfilDocumentalDetalle(List<PerfilDocumentalDetalle> lstPerfilDocumentalDetalle) {
        this.lstPerfilDocumentalDetalle = lstPerfilDocumentalDetalle;
    }

    
    
    public int getID_BIBLIOTECA_MEDIADOR() {
        return ID_BIBLIOTECA_MEDIADOR;
    }

    public void setID_BIBLIOTECA_MEDIADOR(int ID_BIBLIOTECA_MEDIADOR) {
        this.ID_BIBLIOTECA_MEDIADOR = ID_BIBLIOTECA_MEDIADOR;
    }

    public int getID_INSTITUCION_MEDIADOR() {
        return ID_INSTITUCION_MEDIADOR;
    }

    public void setID_INSTITUCION_MEDIADOR(int ID_INSTITUCION_MEDIADOR) {
        this.ID_INSTITUCION_MEDIADOR = ID_INSTITUCION_MEDIADOR;
    }

    public String getNOMBRE_INSTITUCION() {
        return NOMBRE_INSTITUCION;
    }

    public void setNOMBRE_INSTITUCION(String NOMBRE_INSTITUCION) {
        this.NOMBRE_INSTITUCION = NOMBRE_INSTITUCION;
    }

    public String getNOMBRE_BIBLIOTECA() {
        return NOMBRE_BIBLIOTECA;
    }

    public void setNOMBRE_BIBLIOTECA(String NOMBRE_BIBLIOTECA) {
        this.NOMBRE_BIBLIOTECA = NOMBRE_BIBLIOTECA;
    }

    public String getDIRECCION() {
        return DIRECCION;
    }

    public void setDIRECCION(String DIRECCION) {
        this.DIRECCION = DIRECCION;
    }

    public String getEMAIL() {
        return EMAIL;
    }

    public void setEMAIL(String EMAIL) {
        this.EMAIL = EMAIL;
    }

    public String getCODIGO_SNB() {
        return CODIGO_SNB;
    }

    public void setCODIGO_SNB(String CODIGO_SNB) {
        this.CODIGO_SNB = CODIGO_SNB;
    }

    public String getID_DISTRITO() {
        return ID_DISTRITO;
    }

    public void setID_DISTRITO(String ID_DISTRITO) {
        this.ID_DISTRITO = ID_DISTRITO;
    }

    public String getDISTRITO() {
        return DISTRITO;
    }

    public void setDISTRITO(String DISTRITO) {
        this.DISTRITO = DISTRITO;
    }

    public String getID_PROVINCIA() {
        return ID_PROVINCIA;
    }

    public void setID_PROVINCIA(String ID_PROVINCIA) {
        this.ID_PROVINCIA = ID_PROVINCIA;
    }

    public String getPROVINCIA() {
        return PROVINCIA;
    }

    public void setPROVINCIA(String PROVINCIA) {
        this.PROVINCIA = PROVINCIA;
    }

    public String getID_REGION() {
        return ID_REGION;
    }

    public void setID_REGION(String ID_REGION) {
        this.ID_REGION = ID_REGION;
    }

    public String getREGION() {
        return REGION;
    }

    public void setREGION(String REGION) {
        this.REGION = REGION;
    }

    public Boolean getACTIVO() {
        return ACTIVO;
    }

    public void setACTIVO(Boolean ACTIVO) {
        this.ACTIVO = ACTIVO;
    }

    public int getNRO_PERSONAL() {
        return NRO_PERSONAL;
    }

    public void setNRO_PERSONAL(int NRO_PERSONAL) {
        this.NRO_PERSONAL = NRO_PERSONAL;
    }

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    public String getDIRECTORIO() {
        return DIRECTORIO;
    }

    public void setDIRECTORIO(String DIRECTORIO) {
        this.DIRECTORIO = DIRECTORIO;
    }

    public String getTITULO_MAPA() {
        return TITULO_MAPA;
    }

    public void setTITULO_MAPA(String TITULO_MAPA) {
        this.TITULO_MAPA = TITULO_MAPA;
    }

    public String getLATITUD() {
        return LATITUD;
    }

    public void setLATITUD(String LATITUD) {
        this.LATITUD = LATITUD;
    }

    public String getLONGITUD() {
        return LONGITUD;
    }

    public void setLONGITUD(String LONGITUD) {
        this.LONGITUD = LONGITUD;
    }

    public ArrayList<String> getSelectedPerfiles() {
        return selectedPerfiles;
    }

    public void setSelectedPerfiles(ArrayList<String> selectedPerfiles) {
        this.selectedPerfiles = selectedPerfiles;
    }

    public ArrayList<PerfilDocumental> getLstPerfilDocumental() {
        return lstPerfilDocumental;
    }

    public void setLstPerfilDocumental(ArrayList<PerfilDocumental> lstPerfilDocumental) {
        this.lstPerfilDocumental = lstPerfilDocumental;
    }


}
