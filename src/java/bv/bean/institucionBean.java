package bv.bean;

import bv.dao.InstitucionDao;
import bv.dao.impl.DaoFactory;
import static bv.util.Constantes.INSTITUCION;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import org.primefaces.context.RequestContext;
import vb.entidad.Institucion;

/**
 *
 * @author Renato Vásquez Tejada - renatovt11@gmail.com
 */
@ManagedBean
@RequestScoped
public class institucionBean {

    private Institucion institucion;
    private final InstitucionDao institucionDao;
    List<Institucion> instituciones;
    private List<SelectItem> comboAvanzado;
    private List<SelectItem> comboTipoInstitucion;
    private final FacesContext faceContext;
    int idUsuario;

    public institucionBean() {
        DaoFactory factory = DaoFactory.getInstance();
        faceContext = FacesContext.getCurrentInstance();
        institucion = new Institucion();
        institucionDao = factory.getInstitucionDao(INSTITUCION);
        idUsuario = (Integer) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("personalIdUsuario");
    }

    public Institucion getInstitucion() {
        return institucion;
    }

    public void setInstitucion(Institucion institucion) {
        this.institucion = institucion;
    }

    public List<Institucion> getInstituciones() {
        return instituciones;
    }

    public void setInstituciones(List<Institucion> instituciones) {
        this.instituciones = instituciones;
    }

    public void creaInstitucion() {
        int n = institucionDao.crearEntidad(institucion, idUsuario);
        if (n == 0) {
            FacesContext.getCurrentInstance().addMessage("gMensaje", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Ocurrió un error al ejecutar la sentencia."));
        } else {
            // institucion=new Institucion();
            RequestContext rc = RequestContext.getCurrentInstance();
            rc.execute("PF('dlgInstitucion').hide();");
            rc.execute("PF('cboInstitucion').selectValue(PF('cboInstitucion').options.length+1)");
            RequestContext.getCurrentInstance().update("frmAddBiblioteca:advanced");//:frmAddBiblioteca:advanced
            FacesContext.getCurrentInstance().addMessage("gMensaje", new FacesMessage(FacesMessage.SEVERITY_INFO, "Inserción correcta", "Se registro la institución correctamente."));
            RequestContext.getCurrentInstance().update("gMensaje");
            limpiarInstitucion();
            RequestContext.getCurrentInstance().update("frmInstitAdd");
        }
    }

    public void limpiarInstitucion() {
        //institucion.setID_INSTITUCION(1);
        institucion.setNOMBRE_INSTITUCION("");
        institucion.setNOMBRE_REPRESENTANTE("");
        institucion.setNOMBRE_REPRESENTANTE("");

    }

    public List<SelectItem> getComboAvanzado() {
        List<Object[]> lista = institucionDao.llenaComboAvanzado();
        comboAvanzado = new ArrayList<>();
        if (lista != null) {
            for (Object[] fila : lista) {
                comboAvanzado.add(new SelectItem(fila[0], fila[1].toString()));
            }
        }
        return comboAvanzado;
    }

    public List<SelectItem> getComboTipoInstitucion() {
        List<Object[]> lista = institucionDao.llenaComboTipoInstitucion();
        comboTipoInstitucion = new ArrayList<>();
        if (lista != null) {
            for (Object[] fila : lista) {
                comboTipoInstitucion.add(new SelectItem(fila[0], fila[1].toString()));
            }
        }
        return comboTipoInstitucion;
    }

    //ludwig
    //
}
