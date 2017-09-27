package vb.entidad;


public class ImagenNovedad {
    

   private Integer ID_IMAGEN_NOVEDAD;
   private Integer ID_NOVEDAD;
   private String URL_NOVEDAD;
   private Integer ORDEN;


    public ImagenNovedad() {
    }

    public Integer getID_IMAGEN_NOVEDAD() {
        return ID_IMAGEN_NOVEDAD;
    }

    public void setID_IMAGEN_NOVEDAD(Integer ID_IMAGEN_NOVEDAD) {
        this.ID_IMAGEN_NOVEDAD = ID_IMAGEN_NOVEDAD;
    }
   
    public Integer getID_NOVEDAD() {
        return ID_NOVEDAD;
    }

    public void setID_NOVEDAD(Integer ID_NOVEDAD) {
        this.ID_NOVEDAD = ID_NOVEDAD;
    }

    public String getURL_NOVEDAD() {
        return URL_NOVEDAD;
    }

    public void setURL_NOVEDAD(String URL_NOVEDAD) {
        this.URL_NOVEDAD = URL_NOVEDAD;
    }

    public Integer getORDEN() {
        return ORDEN;
    }

    public void setORDEN(Integer ORDEN) {
        this.ORDEN = ORDEN;
    }
    
    
}

