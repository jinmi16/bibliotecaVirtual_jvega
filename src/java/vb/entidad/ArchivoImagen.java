package vb.entidad;

import java.io.Serializable;


public class ArchivoImagen implements Serializable{
    
    private int ID_IMAGEN;
    private String NOMBRE_IMAGEN;

    public ArchivoImagen() {
    }
    
    public int getID_IMAGEN() {
        return ID_IMAGEN;
    }

    public void setID_IMAGEN(int ID_IMAGEN) {
        this.ID_IMAGEN = ID_IMAGEN;
    }

    public String getNOMBRE_IMAGEN() {
        return NOMBRE_IMAGEN;
    }

    public void setNOMBRE_IMAGEN(String NOMBRE_IMAGEN) {
        this.NOMBRE_IMAGEN = NOMBRE_IMAGEN;
    }
    
    
    
}
