package bv.bean;

import bv.dao.ColeccionDao;
import bv.dao.impl.DaoFactory;
import static bv.util.Constantes.COLECCION;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;
import vb.entidad.Coleccion;

/**
 *
 * @author Renato Vásquez Tejada - renatovt11@gmail.com
 */
@ViewScoped
@ManagedBean
public class coleccionBean {

    private List<Coleccion> cboColeccion;
    private final ColeccionDao coleccionDao;
    private Coleccion coleccion;
    String idBiblioteca;

    public coleccionBean() {
        DaoFactory factory = DaoFactory.getInstance();
        idBiblioteca = FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("personalidBibliotecaFuente").toString();
        coleccion = new Coleccion();
        coleccionDao = factory.getColeccionDao(COLECCION);
        rfrhCboColeccion();
    }

    public List<Coleccion> getCboColeccion() {
        return cboColeccion;
    }

    public void rfrhCboColeccion() {

        List<Object[]> lista = coleccionDao.llenaComboColeccion(idBiblioteca);
        cboColeccion = new ArrayList<>();
        if (lista != null) {
            for (Object[] fila : lista) {
                Coleccion colec = new Coleccion();
                colec.setID_COLECCION(Integer.parseInt(fila[0].toString()));
                colec.setDESCRIPCION(fila[2].toString());
                cboColeccion.add(colec);
            }
        }
        RequestContext.getCurrentInstance().update("frmAddDocumental:cboColeccion");

    }

    public void setCboColeccion(List<Coleccion> cboColeccion) {
        this.cboColeccion = cboColeccion;
    }

    public Coleccion getColeccion() {
        return coleccion;
    }

    public void setColeccion(Coleccion coleccion) {
        this.coleccion = coleccion;
    }

    public void creaColeccion() {
        int n = coleccionDao.crearEntidad(coleccion, idBiblioteca);
        if (n == 0) {
            FacesContext.getCurrentInstance().addMessage("gMensaje", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Ocurrió un error al ejecutar la sentencia."));
        } else {
            RequestContext rc = RequestContext.getCurrentInstance();
            rc.execute("PF('dlgColeccion').hide();");
            FacesContext.getCurrentInstance().addMessage("gMensaje", new FacesMessage(FacesMessage.SEVERITY_INFO, "Inserción correcta", "Se registro la colección correctamente."));
        }
        limpiarColeccion();
    }

    public void limpiarColeccion() {
        coleccion = new Coleccion();
    }

}
