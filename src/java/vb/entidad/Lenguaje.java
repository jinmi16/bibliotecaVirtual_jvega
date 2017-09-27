package vb.entidad;
/**
 *
 * @author Renato Vásquez Tejada - renatovt11@gmail.com
 */
public class Lenguaje {
    private String ID_LENGUAJE;
    private String LENGUAJE;
    //auxiliar 
    private String ID_DOCUMENTAL;

    public String getID_DOCUMENTAL() {
        return ID_DOCUMENTAL;
    }

    public void setID_DOCUMENTAL(String ID_DOCUMENTAL) {
        this.ID_DOCUMENTAL = ID_DOCUMENTAL;
    }

    public String getID_LENGUAJE() {
        return ID_LENGUAJE;
    }

    public void setID_LENGUAJE(String ID_LENGUAJE) {
        this.ID_LENGUAJE = ID_LENGUAJE;
    }

    public String getLENGUAJE() {
        return LENGUAJE;
    }

    public void setLENGUAJE(String LENGUAJE) {
        this.LENGUAJE = LENGUAJE;
    }
}
