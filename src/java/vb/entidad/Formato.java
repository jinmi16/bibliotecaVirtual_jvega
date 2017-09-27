package vb.entidad;

import java.io.Serializable;

/**
 *
 * @author virtual
 */
public class Formato implements Serializable{
    
    private String ID_FORMATO;
    private String TIPO;
    //auxiliar
        private String displayValor;

    public Formato() {
    }
        
        
        

    public String getID_FORMATO() {
        return ID_FORMATO;
    }

    public void setID_FORMATO(String ID_FORMATO) {
        this.ID_FORMATO = ID_FORMATO;
    }

    public String getTIPO() {
        return TIPO;
    }

    public void setTIPO(String TIPO) {
        this.TIPO = TIPO;
    }

    public String getDisplayValor() {
        return displayValor;
    }

    public void setDisplayValor(String displayValor) {
        this.displayValor = displayValor;
    }
        
        
    
}
