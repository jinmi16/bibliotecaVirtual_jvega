package vb.entidad;


public class MetadataTabla {
    
  private int ID_METADATA;
private String TABLA;
private String  CAMPO;
private String  ETIQUETA_DC;
private String DESCRIPCION;
private int LONGITUD;

 


    public MetadataTabla() {
    }

    public MetadataTabla(int ID_METADATA, String CAMPO, String ETIQUETA_DC, String DESCRIPCION) {
        this.ID_METADATA = ID_METADATA;
        this.CAMPO = CAMPO;
        this.ETIQUETA_DC = ETIQUETA_DC;
        this.DESCRIPCION = DESCRIPCION;
    }
       public int getLONGITUD() {
        return LONGITUD;
    }

    public void setLONGITUD(int LONGITUD) {
        this.LONGITUD = LONGITUD;
    }


    public int getID_METADATA() {
        return ID_METADATA;
    }

    public void setID_METADATA(int ID_METADATA) {
        this.ID_METADATA = ID_METADATA;
    }

    public String getTABLA() {
        return TABLA;
    }

    public void setTABLA(String TABLA) {
        this.TABLA = TABLA;
    }

    public String getCAMPO() {
        return CAMPO;
    }

    public void setCAMPO(String CAMPO) {
        this.CAMPO = CAMPO;
    }

    public String getETIQUETA_DC() {
        return ETIQUETA_DC;
    }

    public void setETIQUETA_DC(String ETIQUETA_DC) {
        this.ETIQUETA_DC = ETIQUETA_DC;
    }

    public String getDESCRIPCION() {
        return DESCRIPCION;
    }

    public void setDESCRIPCION(String DESCRIPCION) {
        this.DESCRIPCION = DESCRIPCION;
    }

    
    
    
    
    
    
    
    
}
