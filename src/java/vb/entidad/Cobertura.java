package vb.entidad;

/**
 *
 * @author virtual
 */
public class Cobertura {
    private int ID_COBERTURA_ESPACIAL;
   private String COBERTURA_ESPACIAL;//aux
   private String ID_PAIS;
   private String CIUDAD_ACTUAL;
   private String CIUDAD_ANTIGUA;
   private String ID_BIBLIOTECA_REGISTRO;

    public String getID_BIBLIOTECA_REGISTRO() {
        return ID_BIBLIOTECA_REGISTRO;
    }

    public void setID_BIBLIOTECA_REGISTRO(String ID_BIBLIOTECA_REGISTRO) {
        this.ID_BIBLIOTECA_REGISTRO = ID_BIBLIOTECA_REGISTRO;
    }

    public Cobertura(int ID_COBERTURA_ESPACIAL, String COBERTURA_ESPACIAL, String ID_PAIS, String CIUDAD_ACTUAL, String CIUDAD_ANTIGUA) {
        this.ID_COBERTURA_ESPACIAL = ID_COBERTURA_ESPACIAL;
        this.COBERTURA_ESPACIAL = COBERTURA_ESPACIAL;
        this.ID_PAIS = ID_PAIS;
        this.CIUDAD_ACTUAL = CIUDAD_ACTUAL;
        this.CIUDAD_ANTIGUA = CIUDAD_ANTIGUA;
    }
   
   

    public String getID_PAIS() {
        return ID_PAIS;
    }

    public void setID_PAIS(String ID_PAIS) {
        this.ID_PAIS = ID_PAIS;
    }

    public String getCIUDAD_ACTUAL() {
        return CIUDAD_ACTUAL;
    }

    public void setCIUDAD_ACTUAL(String CIUDAD_ACTUAL) {
        this.CIUDAD_ACTUAL = CIUDAD_ACTUAL;
    }

    public String getCIUDAD_ANTIGUA() {
        return CIUDAD_ANTIGUA;
    }

    public void setCIUDAD_ANTIGUA(String CIUDAD_ANTIGUA) {
        this.CIUDAD_ANTIGUA = CIUDAD_ANTIGUA;
    }
   

    public Cobertura() {
    }


   

    public int getID_COBERTURA_ESPACIAL() {
        return ID_COBERTURA_ESPACIAL;
    }

    public void setID_COBERTURA_ESPACIAL(int ID_COBERTURA_ESPACIAL) {
        this.ID_COBERTURA_ESPACIAL = ID_COBERTURA_ESPACIAL;
    }

    public String getCOBERTURA_ESPACIAL() {
        return COBERTURA_ESPACIAL;
    }

    public void setCOBERTURA_ESPACIAL(String COBERTURA_ESPACIAL) {
        this.COBERTURA_ESPACIAL = COBERTURA_ESPACIAL;
    }
   
    
}
