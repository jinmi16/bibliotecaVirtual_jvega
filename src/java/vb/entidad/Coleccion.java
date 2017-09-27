package vb.entidad;

/**
 *
 * @author Renato Vásquez Tejada - renatovt11@gmail.com
 */
public class Coleccion {
    private int ID_COLECCION;
    private String COD_COLECCION;
    private String DESCRIPCION;
    //auxiliar
    private String ID_DOCUMENTAL;

    public String getID_DOCUMENTAL() {
        return ID_DOCUMENTAL;
    }

    public void setID_DOCUMENTAL(String ID_DOCUMENTAL) {
        this.ID_DOCUMENTAL = ID_DOCUMENTAL;
    }

    public int getID_COLECCION() {
        return ID_COLECCION;
    }

    public void setID_COLECCION(int ID_COLECCION) {
        this.ID_COLECCION = ID_COLECCION;
    }

    public String getCOD_COLECCION() {
        return COD_COLECCION;
    }

    public void setCOD_COLECCION(String COD_COLECCION) {
        this.COD_COLECCION = COD_COLECCION;
    }

    public String getDESCRIPCION() {
        return DESCRIPCION;
    }

    public void setDESCRIPCION(String DESCRIPCION) {
        this.DESCRIPCION = DESCRIPCION;
    }
    
}
