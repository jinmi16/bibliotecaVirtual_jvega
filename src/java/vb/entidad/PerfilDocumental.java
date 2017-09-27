package vb.entidad;

/**
 *
 * @author Renato Vásquez Tejada - renatovt11@gmail.com
 */
public class PerfilDocumental {
    
    private int ID_PERFIL_DOCUMENTAL;
    private String PERFIL_DOCUMENTAL;
    private String DESCRIPCION;
    private int ESTADO;
    private String ICONO;
    
    /////------------------
    private int ID_PERFIL;
    private  int ID_BIBLIOTECA;
    private  String STR_ESTADO;

    public int getID_PERFIL_DOCUMENTAL() {
        return ID_PERFIL_DOCUMENTAL;
    }

    public void setID_PERFIL_DOCUMENTAL(int ID_PERFIL_DOCUMENTAL) {
        this.ID_PERFIL_DOCUMENTAL = ID_PERFIL_DOCUMENTAL;
    }

    public String getPERFIL_DOCUMENTAL() {
        return PERFIL_DOCUMENTAL;
    }

    public void setPERFIL_DOCUMENTAL(String PERFIL_DOCUMENTAL) {
        this.PERFIL_DOCUMENTAL = PERFIL_DOCUMENTAL;
    }

    public String getDESCRIPCION() {
        return DESCRIPCION;
    }

    public void setDESCRIPCION(String DESCRIPCION) {
        this.DESCRIPCION = DESCRIPCION;
    }

    public int getESTADO() {
        return ESTADO;
    }

    public void setESTADO(int ESTADO) {
        this.ESTADO = ESTADO;
    }

    public String getICONO() {
        return ICONO;
    }

    public void setICONO(String ICONO) {
        this.ICONO = ICONO;
    }

    public int getID_PERFIL() {
        return ID_PERFIL;
    }

    public void setID_PERFIL(int ID_PERFIL) {
        this.ID_PERFIL = ID_PERFIL;
    }

    public int getID_BIBLIOTECA() {
        return ID_BIBLIOTECA;
    }

    public void setID_BIBLIOTECA(int ID_BIBLIOTECA) {
        this.ID_BIBLIOTECA = ID_BIBLIOTECA;
    }

    public String getSTR_ESTADO() {
        return STR_ESTADO;
    }

    public void setSTR_ESTADO(String STR_ESTADO) {
        this.STR_ESTADO = STR_ESTADO;
    }
    
}
