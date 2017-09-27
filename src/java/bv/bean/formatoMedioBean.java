package bv.bean;

import bv.dao.FormatoMedioDao;
import bv.dao.impl.DaoFactory;
import static bv.util.Constantes.FORMATO_MEDIO;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;
import vb.entidad.FormatoMedio;

@ManagedBean
@ViewScoped
public class formatoMedioBean {

    private final FormatoMedioDao formatoMedioDao;
    private List<FormatoMedio> cboFormatoMedio;
    private FormatoMedio formatomedio = new FormatoMedio();

    public formatoMedioBean() {
        DaoFactory factory = DaoFactory.getInstance();
        formatoMedioDao = factory.getFormatoMedioDao(FORMATO_MEDIO);
        rfrhCboFrmtMedio();
    }

    public FormatoMedio getFormatomedio() {
        return formatomedio;
    }

    public void setFormatomedio(FormatoMedio formatomedio) {
        this.formatomedio = formatomedio;
    }

    public List<FormatoMedio> getCboFormatoMedio() {

        return cboFormatoMedio;
    }

    public void rfrhCboFrmtMedio() {
        List<Object[]> lista = formatoMedioDao.llenaComboFormatoMedio();
        cboFormatoMedio = new ArrayList<>();
        if (lista != null) {
            for (Object[] fila : lista) {
                FormatoMedio entformatomedio = new FormatoMedio();
                entformatomedio.setID_FORMATO_MEDIO(Integer.parseInt(fila[0].toString()));
                entformatomedio.setDESCRIPCION(fila[1].toString());
                cboFormatoMedio.add(entformatomedio);
            }
        }
        RequestContext.getCurrentInstance().update("frmAddDocumental:cboFormatoMedio");
    }

    public void setCboFormatoMedio(List<FormatoMedio> cboFormatoMedio) {
        this.cboFormatoMedio = cboFormatoMedio;
    }

    public void agregarFormatoMedio() {
        int n = formatoMedioDao.crearEntidad(formatomedio);
        if (n == 0) {
            FacesContext.getCurrentInstance().addMessage("gMensaje", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Ocurrió un error al ejecutar este proceso."));
        } else {
            RequestContext rc = RequestContext.getCurrentInstance();
            rc.execute("PF('dlgAddFormatoMedio').hide()");
            rc.execute("PF('cboFormatoMedio').selectValue(PF('cboFormatoMedio').options.length)");
            FacesContext.getCurrentInstance().addMessage("gMensaje", new FacesMessage(FacesMessage.SEVERITY_INFO, "Inserción correcta", "Se registro formato correctamente."));
        }
    }

}
