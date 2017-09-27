package vb.entidad;


public class Contribuidor {
    private int ID_CONTRIBUIDOR;
    private String ID_DOCUMENTAL;
    private String CONTRIBUIDOR;
    //  AUXILIARES
    private int indice;

   

    public Contribuidor() {
    }

    public Contribuidor(String ID_DOCUMENTAL, String CONTRIBUIDOR) {
        this.ID_DOCUMENTAL = ID_DOCUMENTAL;
        this.CONTRIBUIDOR = CONTRIBUIDOR;
    }
    
    

    public int getID_CONTRIBUIDOR() {
        return ID_CONTRIBUIDOR;
    }

    public void setID_CONTRIBUIDOR(int ID_CONTRIBUIDOR) {
        this.ID_CONTRIBUIDOR = ID_CONTRIBUIDOR;
    }

    public String getID_DOCUMENTAL() {
        return ID_DOCUMENTAL;
    }

    public void setID_DOCUMENTAL(String ID_DOCUMENTAL) {
        this.ID_DOCUMENTAL = ID_DOCUMENTAL;
    }

    public String getCONTRIBUIDOR() {
        return CONTRIBUIDOR;
    }

    public void setCONTRIBUIDOR(String CONTRIBUIDOR) {
        this.CONTRIBUIDOR = CONTRIBUIDOR;
    }
     public int getIndice() {
        return indice;
    }

    public void setIndice(int indice) {
        this.indice = indice;
    }
    
    
}
