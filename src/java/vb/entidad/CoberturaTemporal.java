package vb.entidad;

/**
 *
 * @author Renato Vásquez Tejada - renatovt11@gmail.com
 */
public class CoberturaTemporal {
    
    private int ID_COBERTURA_TEMPORAL;
    private String DESCRIPCION;
    private int ANO_INICIO;
    private int ANO_FIN;

    public int getID_COBERTURA_TEMPORAL() {
        return ID_COBERTURA_TEMPORAL;
    }

    public void setID_COBERTURA_TEMPORAL(int ID_COBERTURA_TEMPORAL) {
        this.ID_COBERTURA_TEMPORAL = ID_COBERTURA_TEMPORAL;
    }

    public String getDESCRIPCION() {
        return DESCRIPCION;
    }

    public void setDESCRIPCION(String DESCRIPCION) {
        this.DESCRIPCION = DESCRIPCION;
    }

    public int getANO_INICIO() {
        return ANO_INICIO;
    }

    public void setANO_INICIO(int ANO_INICIO) {
        this.ANO_INICIO = ANO_INICIO;
    }

    public int getANO_FIN() {
        return ANO_FIN;
    }

    public void setANO_FIN(int ANO_FIN) {
        this.ANO_FIN = ANO_FIN;
    }
            
    
    
}
