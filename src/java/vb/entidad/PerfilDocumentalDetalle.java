package vb.entidad;

/**
 *
 * @author virtual
 */
public class PerfilDocumentalDetalle {

    private String ID_PERFIL_DOCUMENTAL;
    private int ID_PERFIL_DOCUMENTAL_DETALLE;
    private String TABLA;
    private String CAMPO;
    private boolean REQUERIDO;
    private boolean VISTA;
    private String strRequerido;
    private String strVista;
    private String ID_BIBLIOTECA;

    public String getStrRequerido() {
        return strRequerido;
    }

    public String getID_BIBLIOTECA() {
        return ID_BIBLIOTECA;
    }

    public void setID_BIBLIOTECA(String ID_BIBLIOTECA) {
        this.ID_BIBLIOTECA = ID_BIBLIOTECA;
    }

    
    
    public void setStrRequerido(String strRequerido) {
        this.strRequerido = strRequerido;
    }

    public String getStrVista() {
        return strVista;
    }

    public void setStrVista(String strVista) {
        this.strVista = strVista;
    }

    public boolean isVISTA() {
        return VISTA;
    }

    public void setVISTA(boolean VISTA) {
        this.VISTA = VISTA;
    }

    public boolean isREQUERIDO() {
        return REQUERIDO;
    }

    public void setREQUERIDO(boolean REQUERIDO) {
        this.REQUERIDO = REQUERIDO;
    }

    public PerfilDocumentalDetalle() {
    }

    public String getID_PERFIL_DOCUMENTAL() {
        return ID_PERFIL_DOCUMENTAL;
    }

    public void setID_PERFIL_DOCUMENTAL(String ID_PERFIL_DOCUMENTAL) {
        this.ID_PERFIL_DOCUMENTAL = ID_PERFIL_DOCUMENTAL;
    }

    public int getID_PERFIL_DOCUMENTAL_DETALLE() {
        return ID_PERFIL_DOCUMENTAL_DETALLE;
    }

    public void setID_PERFIL_DOCUMENTAL_DETALLE(int ID_PERFIL_DOCUMENTAL_DETALLE) {
        this.ID_PERFIL_DOCUMENTAL_DETALLE = ID_PERFIL_DOCUMENTAL_DETALLE;
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

}
