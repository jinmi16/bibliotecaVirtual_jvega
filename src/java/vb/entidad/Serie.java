package vb.entidad;

/**
 *
 * @author Renato Vásquez Tejada - renatovt11@gmail.com
 */
public class Serie {
    
    private int ID_SERIE;
    private String SERIE;
    // auxiliar
    private String ID_DOCUMENTAL;

    public String getID_DOCUMENTAL() {
        return ID_DOCUMENTAL;
    }

    public void setID_DOCUMENTAL(String ID_DOCUMENTAL) {
        this.ID_DOCUMENTAL = ID_DOCUMENTAL;
    }

    public Integer getID_SERIE() {
        return ID_SERIE;
    }

    public void setID_SERIE(int ID_SERIE) {
        this.ID_SERIE = ID_SERIE;
    }

    public String getSERIE() {
        return SERIE;
    }

    public void setSERIE(String SERIE) {
        this.SERIE = SERIE;
    }

    
    
}
