package vb.entidad;


public class Publicacion {
    
    private String ID_PUBLICACION;
    private String FECHA;
    private String ID_DOCUMENTAL;
    private String NRO_VISITAS;
    private String VISIBLE;

    public Publicacion() {
    }

    public String getID_PUBLICACION() {
        return ID_PUBLICACION;
    }

    public void setID_PUBLICACION(String ID_PUBLICACION) {
        this.ID_PUBLICACION = ID_PUBLICACION;
    }

    public String getFECHA() {
        return FECHA;
    }

    public void setFECHA(String FECHA) {
        this.FECHA = FECHA;
    }

    public String getID_DOCUMENTAL() {
        return ID_DOCUMENTAL;
    }

    public void setID_DOCUMENTAL(String ID_DOCUMENTAL) {
        this.ID_DOCUMENTAL = ID_DOCUMENTAL;
    }

    public String getNRO_VISITAS() {
        return NRO_VISITAS;
    }

    public void setNRO_VISITAS(String NRO_VISITAS) {
        this.NRO_VISITAS = NRO_VISITAS;
    }

    public String getVISIBLE() {
        return VISIBLE;
    }

    public void setVISIBLE(String VISIBLE) {
        this.VISIBLE = VISIBLE;
    }
    
    
    
}
