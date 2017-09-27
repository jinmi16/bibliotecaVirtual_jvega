package vb.entidad;

import java.util.List;

/**
 *
 * @author Renato Vásquez Tejada - renatovt11@gmail.com
 */
public class Personal {

    private int ID_PERSONAL_BIBLIOTECA;
    private String NOMBRES;
    private String PATERNO;
    private String MATERNO;
    private String DNI;
    private int ID_BIBLIOTECA_MEDIADOR;
    private String BIBLIOTECA_MEDIADOR;
    //variables auxiliares
    private String idTipoUsuario;
    private boolean boolREPRESENTANTE;
    private boolean boolActivo;
    private String cuenta;
    private int  idUsuario;
    private String codMensaje;
    private String mensaje;
    
    
   private List<Personal> lstPersonal;
   private int countQuery;

    public String getCodMensaje() {
        return codMensaje;
    }

    public void setCodMensaje(String codMensaje) {
        this.codMensaje = codMensaje;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

   
    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

   
    public String getCuenta() {
        return cuenta;
    }

    public void setCuenta(String cuenta) {
        this.cuenta = cuenta;
    }

   
   
    public String getIdTipoUsuario() {
        return idTipoUsuario;
    }

    public List<Personal> getLstPersonal() {
        return lstPersonal;
    }

    public void setLstPersonal(List<Personal> lstPersonal) {
        this.lstPersonal = lstPersonal;
    }

    public int getCountQuery() {
        return countQuery;
    }

    public void setCountQuery(int countQuery) {
        this.countQuery = countQuery;
    }

    public void setIdTipoUsuario(String idTipoUsuario) {
        this.idTipoUsuario = idTipoUsuario;
    }

    public String getBIBLIOTECA_MEDIADOR() {
        return BIBLIOTECA_MEDIADOR;
    }

    public void setBIBLIOTECA_MEDIADOR(String BIBLIOTECA_MEDIADOR) {
        this.BIBLIOTECA_MEDIADOR = BIBLIOTECA_MEDIADOR;
    }
    private String CARGO;
    private String CORREO;
    private String REPRESENTANTE;

    public Personal() {
    }

    public boolean isBoolActivo() {
        return boolActivo;
    }

    public void setBoolActivo(boolean boolActivo) {
        this.boolActivo = boolActivo;
    }

    public boolean isBoolREPRESENTANTE() {
        return boolREPRESENTANTE;
    }

    public void setBoolREPRESENTANTE(boolean boolREPRESENTANTE) {
        this.boolREPRESENTANTE = boolREPRESENTANTE;
    }

    public String getREPRESENTANTE() {
        return REPRESENTANTE;
    }

    public void setREPRESENTANTE(String REPRESENTANTE) {
        this.REPRESENTANTE = REPRESENTANTE;
    }

    public int getID_PERSONAL_BIBLIOTECA() {
        return ID_PERSONAL_BIBLIOTECA;
    }

    public void setID_PERSONAL_BIBLIOTECA(int ID_PERSONAL_BIBLIOTECA) {
        this.ID_PERSONAL_BIBLIOTECA = ID_PERSONAL_BIBLIOTECA;
    }

    public String getNOMBRES() {
        return NOMBRES;
    }

    public void setNOMBRES(String NOMBRES) {
        this.NOMBRES = NOMBRES;
    }

    public String getPATERNO() {
        return PATERNO;
    }

    public void setPATERNO(String PATERNO) {
        this.PATERNO = PATERNO;
    }

    public String getMATERNO() {
        return MATERNO;
    }

    public void setMATERNO(String MATERNO) {
        this.MATERNO = MATERNO;
    }

    public String getDNI() {
        return DNI;
    }

    public void setDNI(String DNI) {
        this.DNI = DNI;
    }

    public int getID_BIBLIOTECA_MEDIADOR() {
        return ID_BIBLIOTECA_MEDIADOR;
    }

    public void setID_BIBLIOTECA_MEDIADOR(int ID_BIBLIOTECA_MEDIADOR) {
        this.ID_BIBLIOTECA_MEDIADOR = ID_BIBLIOTECA_MEDIADOR;
    }

    public String getCARGO() {
        return CARGO;
    }

    public void setCARGO(String CARGO) {
        this.CARGO = CARGO;
    }

    public String getCORREO() {
        return CORREO;
    }

    public void setCORREO(String CORREO) {
        this.CORREO = CORREO;
    }

}
