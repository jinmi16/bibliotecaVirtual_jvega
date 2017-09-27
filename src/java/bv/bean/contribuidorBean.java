package bv.bean;

import bv.dao.impl.DaoFactory;
import java.util.ArrayList;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.RowEditEvent;
import vb.entidad.Contribuidor;

@ManagedBean
@ViewScoped
public class contribuidorBean {

    private Contribuidor objContribuidor;
    private ArrayList<Contribuidor> lstContribuidor = new ArrayList<>();
    private ArrayList<Contribuidor> lstContribuidorAux = new ArrayList<>();
    private String disabledContribuidor = "true";

    public contribuidorBean() {
        objContribuidor = new Contribuidor();
    }

    public Contribuidor getObjContribuidor() {
        if (objContribuidor.getIndice() == 0) {
            objContribuidor.setIndice(1);
        }
        return objContribuidor;
    }

    public void setObjContribuidor(Contribuidor objContribuidor) {
        this.objContribuidor = objContribuidor;
    }

    public ArrayList<Contribuidor> getLstContribuidor() {
        return lstContribuidor;
    }

    public void setLstContribuidor(ArrayList<Contribuidor> lstContribuidor) {
        this.lstContribuidor = lstContribuidor;
    }

    public ArrayList<Contribuidor> getLstContribuidorAux() {
        return lstContribuidorAux;
    }

    public void setLstContribuidorAux(ArrayList<Contribuidor> lstContribuidorAux) {
        this.lstContribuidorAux = lstContribuidorAux;
    }

    public String getDisabledContribuidor() {
        return disabledContribuidor;
    }

    public void setDisabledContribuidor(String disabledContribuidor) {
        this.disabledContribuidor = disabledContribuidor;
    }

    public void pasarContribuidores() {
        if (!lstContribuidor.isEmpty()) {
            lstContribuidorAux = lstContribuidor;
        }
    }

    public void limpiarContribuidores() {
        lstContribuidor = new ArrayList<>();
    }

    public void insertarContribuidores() {
        if (objContribuidor.getCONTRIBUIDOR().trim().length() < 1) {
            msjError("gMensaje", "El campo  es obligatorio");
        } else {
            Integer a = 0;
            if (!lstContribuidor.isEmpty()) {
                for (int x = 0; x < lstContribuidor.size(); x++) {
                    Contribuidor con = lstContribuidor.get(x);
                    if (con.getIndice() == objContribuidor.getIndice()) {
                        msjError("gMensaje", "Este indice ya se añadio!!");
                        objContribuidor = new Contribuidor();
                        a = 1;
                        break;
                    }
                }
                if (a == 0) {
                    lstContribuidor.add(objContribuidor);
                    msjCorrecto("gMensaje", "Se inserto registro");
                    objContribuidor = new Contribuidor();
                    contarContribuidores();
                }
            } else {
                lstContribuidor.add(objContribuidor);
                msjCorrecto("gMensaje", "Se inserto registro");
                objContribuidor = new Contribuidor();
                contarContribuidores();
            }
        }
    }

    public void sacarContribuidores(Contribuidor c) {
        lstContribuidor.remove(c.getIndice() - 1);
    }

    //Eventos de Row Editor
    public void onRowEditContribuidores(RowEditEvent event) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage("gMensaje", new FacesMessage(FacesMessage.SEVERITY_INFO, "Editado!", ((Contribuidor) event.getObject()).getIndice() + ""));
        disabledContribuidor = "true";
    }

    public void onRowCancelContribuidores(RowEditEvent event) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage("gMensaje", new FacesMessage(FacesMessage.SEVERITY_INFO, "Cancelado", ((Contribuidor) event.getObject()).getIndice() + ""));
        disabledContribuidor = "true";
    }

    public void onRowInitContribuidores(RowEditEvent event) {
//        RequestContext rc = RequestContext.getCurrentInstance();
//        rc.execute("PF('btnborrar').style.display='none'"); 
//        FacesContext context = FacesContext.getCurrentInstance();
//        context.addMessage("gMensaje", new FacesMessage(FacesMessage.SEVERITY_INFO, "Casdasdasdasancelado", ((AuxContenido) event.getObject()).getIndice()+""));
//        disabledContribuidor = "false";
    }

    //contador
    public void contarContribuidores() {
        objContribuidor = new Contribuidor();
        int n = lstContribuidor.size();
        objContribuidor.setIndice(n + 1);
    }

    //accesorios
    private void msjError(String m) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", m));
    }

    private void msjError(String growl, String m) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(growl, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", m));
    }

    private void msjCorrecto(String m) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Éxito!", m));
    }

    private void msjCorrecto(String growl, String m) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(growl, new FacesMessage(FacesMessage.SEVERITY_INFO, "Éxito!", m));
    }

    private void limpiarEntidadContribuidores(Contribuidor c) {
        c.setIndice(1);
        c.setCONTRIBUIDOR("");
    }

}
