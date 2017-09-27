package bv.bean;

import bv.dao.EspacialDao;
import bv.dao.impl.DaoFactory;
import static bv.util.Constantes.ESPACIAL;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;

import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import org.primefaces.context.RequestContext;
import vb.entidad.Cobertura;

/**
 *
 * @author virtual
 */
@ManagedBean
@ViewScoped
public class coberturaBean implements Serializable {

    private Cobertura cobertura;
    private final EspacialDao espacialDao;
    private List<Cobertura> lstCobertura;
    private List<SelectItem> cboCobertura;
    private List<SelectItem> cboPais;
    private final String idBiblioteca;
    private boolean disabledCoberturaEdit = true;

    public coberturaBean() {
        idBiblioteca = FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("personalidBibliotecaFuente").toString();
        DaoFactory factory = DaoFactory.getInstance();
        cobertura = new Cobertura();
        espacialDao = factory.getEspacialDao(ESPACIAL);
    }

    public List<SelectItem> getCboPais() {
        List<Object[]> lista = espacialDao.llenaComboPais();
        cboPais = new ArrayList<>();
        if (lista != null) {
            for (Object[] fila : lista) {
                cboPais.add(new SelectItem(fila[0], fila[1].toString()));
            }
        }
        return cboPais;
    }

    public void setCboPais(List<SelectItem> cboPais) {
        this.cboPais = cboPais;
    }

    public List<SelectItem> getCboCobertura() {
        List<Object[]> lista = espacialDao.llenaComboCobertura(idBiblioteca);
        cboCobertura = new ArrayList<>();
        if (lista != null) {
            for (Object[] fila : lista) {
                cboCobertura.add(new SelectItem(fila[0], fila[1].toString()));
            }
        }
        return cboCobertura;
    }

    public void setCboCobertura(List<SelectItem> cboCobertura) {
        this.cboCobertura = cboCobertura;
    }

    public Cobertura getCobertura() {
        return cobertura;
    }

    public void setCobertura(Cobertura cobertura) {
        this.cobertura = cobertura;
    }

    public List<Cobertura> getLstCobertura() {
        lstCobertura = espacialDao.obtenerCobertura();

        return lstCobertura;
    }

    public void setLstCobertura(List<Cobertura> lstCobertura) {
        this.lstCobertura = lstCobertura;
    }

    ////-----------------------
    public List<Cobertura> completarCobertura(String query) {
        List<Cobertura> lstCoberturaAll = espacialDao.obtenerCobertura();
        List<Cobertura> lstCoberturafilter = new ArrayList<>();
        for (int i = 0; i < lstCoberturaAll.size(); i++) {
            Cobertura cobert = lstCoberturaAll.get(i);
            if (cobert.getCOBERTURA_ESPACIAL().toLowerCase().startsWith(query)) {
                lstCoberturafilter.add(cobert);
            }
        }
        return lstCoberturafilter;
    }

    //-------------
    public void creaCobertura() {
        int n;
        cobertura.setID_BIBLIOTECA_REGISTRO(idBiblioteca);
        if (cobertura.getID_PAIS().equals("-1")) {
            cobertura.setID_PAIS("");
        }
        if (cobertura.getID_PAIS().equals("") && cobertura.getCIUDAD_ACTUAL().length() == 0 && cobertura.getCIUDAD_ANTIGUA().length() == 0) {
            n = 0;
        } else {
            n = espacialDao.CrearCobertura(cobertura);
        }
        if (n == 0) {
            FacesContext.getCurrentInstance().addMessage("gMensaje", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Ocurrió un error al ejecutar la sentencia."));
            RequestContext rc = RequestContext.getCurrentInstance();
            rc.execute("PF('dlgCoberturaEscpacial').hide();");
        } else {
            RequestContext rc = RequestContext.getCurrentInstance();
            rc.execute("PF('dlgCoberturaEscpacial').hide();");
            rc.execute("PF('cboCobertura').selectValue(PF('cboCobertura').options.length+1)");
            RequestContext.getCurrentInstance().update("frmAddDocumental:cboCobertura");
            FacesContext.getCurrentInstance().addMessage("gMensaje", new FacesMessage(FacesMessage.SEVERITY_INFO, "Inserción correcta", "Se registro correctamente."));
            RequestContext.getCurrentInstance().update("gMensaje");
            limpiarCobertura();
            RequestContext.getCurrentInstance().update("frmCobertura");
        }

    }

    public void editarCobertura() {
        int n;
        if (cobertura.getID_PAIS().equals("-1")) {
            cobertura.setID_PAIS("");
        }
        if (cobertura.getID_PAIS().equals("") && cobertura.getCIUDAD_ACTUAL().length() == 0 && cobertura.getCIUDAD_ANTIGUA().length() == 0) {
            n = 0;
        } else {
            n = espacialDao.editarCobertura(cobertura);
        }
        if (n == 0) {
            FacesContext.getCurrentInstance().addMessage("gMensaje", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Ocurrió un error al ejecutar la sentencia."));
            RequestContext rc = RequestContext.getCurrentInstance();
            rc.execute("PF('dlgAddCoberturaEditar').hide();");
            rc.update("gMensaje");
        } else {
            RequestContext rc = RequestContext.getCurrentInstance();
            rc.execute("PF('dlgAddCoberturaEditar').hide();");
            rc.update("gMensaje");
            FacesContext.getCurrentInstance().addMessage("gMensaje", new FacesMessage(FacesMessage.SEVERITY_INFO, "Modificacion correcta", "Se Modifico correctamente."));
        }
        limpiarCobertura();
    }

    public void limpiarCobertura() {
        cobertura = new Cobertura();
    }

    public void cboEditarCoberturaChage(int idCobertura) {
        if (idCobertura != (-1)) {
            disabledCoberturaEdit = false;
            RequestContext.getCurrentInstance().update("frmAddDocumental:btnEditCoberturaespacial");
        } else {
            disabledCoberturaEdit = true;
            RequestContext.getCurrentInstance().update("frmAddDocumental:btnEditCoberturaespacial");
        }
    }

    public void pasarCobertura(int idCobertura) {
        cobertura = espacialDao.obtenerXidCoberturaEspacial(String.valueOf(idCobertura));
        RequestContext rc = RequestContext.getCurrentInstance();
        rc.execute("PF('dlgAddCoberturaEditar').show()");
        RequestContext.getCurrentInstance().update("dlgAddCoberturaEditar");
    }

    public boolean isDisabledCoberturaEdit() {
        return disabledCoberturaEdit;
    }

    public void setDisabledCoberturaEdit(boolean disabledCoberturaEdit) {
        this.disabledCoberturaEdit = disabledCoberturaEdit;
    }

}
