package vb.entidad;

public class Institucion {
    public int ID_INSTITUCION;
    public String NOMBRE_INSTITUCION="";
    public String NOMBRE_REPRESENTANTE="";
    public int TIPO_INSTITUCION;
    
    public Institucion() {}
 
    public Institucion(int ID_INSTITUCION, String NOMBRE_INSTITUCION, String NOMBRE_REPRESENTANTE, int TIPO_INSTITUCION) {
        this.ID_INSTITUCION = ID_INSTITUCION;
        this.NOMBRE_INSTITUCION = NOMBRE_INSTITUCION;
        this.NOMBRE_REPRESENTANTE = NOMBRE_REPRESENTANTE;
    }

    public int getID_INSTITUCION() {
        return ID_INSTITUCION;
    }

    public void setID_INSTITUCION(int ID_INSTITUCIÓN) {
        this.ID_INSTITUCION = ID_INSTITUCIÓN;
    }

    public String getNOMBRE_INSTITUCION() {
        return NOMBRE_INSTITUCION;
    }

    public void setNOMBRE_INSTITUCION(String NOMBRE_INSTITUCION) {
        this.NOMBRE_INSTITUCION = NOMBRE_INSTITUCION;
    }

    public String getNOMBRE_REPRESENTANTE() {
        return NOMBRE_REPRESENTANTE;
    }

    public void setNOMBRE_REPRESENTANTE(String NOMBRE_REPRESENTANTE) {
        this.NOMBRE_REPRESENTANTE = NOMBRE_REPRESENTANTE;
    }

    public int getTIPO_INSTITUCION() {
        return TIPO_INSTITUCION;
    }

    public void setTIPO_INSTITUCION(int TIPO_INSTITUCION) {
        this.TIPO_INSTITUCION = TIPO_INSTITUCION;
    }
    
    @Override
    public String toString() {
        return NOMBRE_REPRESENTANTE;
    }
    
}
