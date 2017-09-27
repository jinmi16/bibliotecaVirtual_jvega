package bv.bean;

import bv.dao.EditorialDao;
import bv.dao.impl.DaoFactory;
import static bv.util.Constantes.EDITORIAL;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import org.primefaces.context.RequestContext;
import vb.entidad.Editorial;

@ManagedBean
@ViewScoped
public class editorialBean {

    private Editorial editorial;
    private List<SelectItem> cboEditorial;
    private final EditorialDao editorialDao;
    private Editorial editEditorial = new Editorial();
    private String disabledcboEditorial = "true";
    String idBiblioteca;

    public editorialBean() {
        DaoFactory factory = DaoFactory.getInstance();
        editorial = new Editorial();
        editorialDao = factory.getEditorialDao(EDITORIAL);
        idBiblioteca = FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("personalidBibliotecaFuente").toString();
        rfrhCboEditorial();
    }

    public String getDisabledcboEditorial() {
        return disabledcboEditorial;
    }

    public void setDisabledcboEditorial(String disabledcboEditorial) {
        this.disabledcboEditorial = disabledcboEditorial;
    }

    public Editorial getEditEditorial() {
        return editEditorial;
    }

    public void setEditEditorial(Editorial editEditorial) {
        this.editEditorial = editEditorial;
    }

    public Editorial getEditorial() {
        return editorial;
    }

    public void setEditorial(Editorial editorial) {
        this.editorial = editorial;
    }

    public List<SelectItem> getCboEditorial() {

        return cboEditorial;
    }

    public void rfrhCboEditorial() {
        List<Object[]> lista = editorialDao.llenaComboEditorial(idBiblioteca);
        cboEditorial = new ArrayList<>();
        if (lista != null) {
            for (Object[] fila : lista) {
                cboEditorial.add(new SelectItem(fila[0], fila[1].toString()));
            }
        }
        RequestContext.getCurrentInstance().update("frmAddDocumental:cboEditorial");
    }

    public void setCboEditorial(List<SelectItem> cboEditorial) {
        this.cboEditorial = cboEditorial;
    }

    public void crearEditorial() {
        editorial.setID_BIBLIOTECA_REGISTRO(idBiblioteca);
        int insert = editorialDao.crearEntidad(editorial);
        if (insert == 0) {
            msjError("gMensaje", "no se inserto la editorial.");
            editorial = new Editorial();
        } else {
            RequestContext rc = RequestContext.getCurrentInstance();
            rc.execute("PF('dlgEditorial').hide();");
            rc.execute("PF('cboEditorial').selectValue(PF('cboEditorial').options.length)");
            msjCorrecto("gMensaje", "Se inserto la editorial.");
            rfrhCboEditorial();
            rc.update("gMensaje");
        }
        limpiarEditorial();
    }

    public void pasarEditorial(int idEditorial) {
        editEditorial = editorialDao.buscarEntidad(idEditorial);
        RequestContext rc = RequestContext.getCurrentInstance();
        rc.execute("PF('dlgEditEditorial').show()");
        RequestContext.getCurrentInstance().update("dlgEditEditorial");
    }

    public void modificarEd() {
        int n = editorialDao.actualizarEntidad(editEditorial);
        RequestContext rc = RequestContext.getCurrentInstance();
        rc.execute("PF('dlgEditEditorial').hide()");
        if (n == 1) {
            msjCorrecto("gMensaje", "Se modifico la editorial.");
            RequestContext.getCurrentInstance().update("frmAddDocumental:cboEditorial");
        } else {
            msjError("gMensaje", "Error.");
        }
    }

    public void cboEditorialChange(int idEditorial) {
        if (idEditorial != 0) {
            disabledcboEditorial = "false";
            RequestContext.getCurrentInstance().update("frmAddDocumental:btnEditEditorial");
        } else {
            disabledcboEditorial = "true";
            RequestContext.getCurrentInstance().update("frmAddDocumental:btnEditEditorial");
        }
    }

    //accesorios
    private void msjError(String growl, String m) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(growl, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", m));
    }

    private void msjCorrecto(String growl, String m) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(growl, new FacesMessage(FacesMessage.SEVERITY_INFO, "Éxito!", m));
    }

    private void limpiarEditorial() {
        editorial = new Editorial();
    }

}
