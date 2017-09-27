package vb.entidad;
/**
 *
 * @author Renato Vásquez Tejada - renatovt11@gmail.com
 */
public class Usuario {

    private int ID_USUARIO;
    private String USUARIO;
    private String CONTRASENA;
    private String ACTIVO;
    private int ID_PERSONAL_BIBLIOTECA;
    private int ID_TIPO_USUARIO;
    /// variables auxiliares
    private boolean boolActivo;
    private String nombrePersonalBiblioteca;
    private String tipoUsuario;
    private String nombreBiblioteca;
    
    private int CAMBIO_PASSW_SISTEMA;
    private boolean boolResetContraseña = false;
    

    public boolean isBoolResetContraseña() {
        return boolResetContraseña;
    }

    public void setBoolResetContraseña(boolean boolResetContraseña) {
        this.boolResetContraseña = boolResetContraseña;
    }

    public int getCAMBIO_PASSW_SISTEMA() {
        return CAMBIO_PASSW_SISTEMA;
    }

    public void setCAMBIO_PASSW_SISTEMA(int CAMBIO_PASSW_SISTEMA) {
        this.CAMBIO_PASSW_SISTEMA = CAMBIO_PASSW_SISTEMA;
    }
    
    private String CONTRASENA_ANT;
    private String CONTRASENA_NUEVA1;
    private String CONTRASENA_NUEVA2;


    public String getNombreBiblioteca() {
        return nombreBiblioteca;
    }

    public void setNombreBiblioteca(String nombreBiblioteca) {
        this.nombreBiblioteca = nombreBiblioteca;
    }

    public String getNombrePersonalBiblioteca() {
        return nombrePersonalBiblioteca;
    }

    public void setNombrePersonalBiblioteca(String nombrePersonalBiblioteca) {
        this.nombrePersonalBiblioteca = nombrePersonalBiblioteca;
    }

    public String getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public boolean isBoolActivo() {
        return boolActivo;
    }

    public void setBoolActivo(boolean boolActivo) {
        this.boolActivo = boolActivo;
    }

    public int getID_USUARIO() {
        return ID_USUARIO;
    }

    public void setID_USUARIO(int ID_USUARIO) {
        this.ID_USUARIO = ID_USUARIO;
    }

    public String getUSUARIO() {
        return USUARIO;
    }

    public void setUSUARIO(String USUARIO) {
        this.USUARIO = USUARIO;
    }

    public String getCONTRASENA() {
        return CONTRASENA;
    }

    public void setCONTRASENA(String CONTRASENA) {
        this.CONTRASENA = CONTRASENA;
    }

    public String getACTIVO() {
        return ACTIVO;
    }

    public void setACTIVO(String ACTIVO) {
        this.ACTIVO = ACTIVO;
    }

    public int getID_PERSONAL_BIBLIOTECA() {
        return ID_PERSONAL_BIBLIOTECA;
    }

    public void setID_PERSONAL_BIBLIOTECA(int ID_PERSONAL_BIBLIOTECA) {
        this.ID_PERSONAL_BIBLIOTECA = ID_PERSONAL_BIBLIOTECA;
    }

    public int getID_TIPO_USUARIO() {
        return ID_TIPO_USUARIO;
    }

    public void setID_TIPO_USUARIO(int ID_TIPO_USUARIO) {
        this.ID_TIPO_USUARIO = ID_TIPO_USUARIO;
    }



    
    
    public String getCONTRASENA_ANT() {
        return CONTRASENA_ANT;
    }

    public void setCONTRASENA_ANT(String CONTRASENA_ANT) {
        this.CONTRASENA_ANT = CONTRASENA_ANT;
    }

    public String getCONTRASENA_NUEVA1() {
        return CONTRASENA_NUEVA1;
    }

    public void setCONTRASENA_NUEVA1(String CONTRASENA_NUEVA1) {
        this.CONTRASENA_NUEVA1 = CONTRASENA_NUEVA1;
    }

    public String getCONTRASENA_NUEVA2() {
        return CONTRASENA_NUEVA2;
    }

    public void setCONTRASENA_NUEVA2(String CONTRASENA_NUEVA2) {
        this.CONTRASENA_NUEVA2 = CONTRASENA_NUEVA2;
    }

}
