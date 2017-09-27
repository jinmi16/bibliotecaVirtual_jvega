/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bv.bean;

import bv.dao.PersonalDao;
import bv.dao.impl.DaoFactory;
import static bv.util.Constantes.PERSONAL;
import java.io.IOException;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;
import vb.entidad.Personal;

/**
 *
 * @author virtual
 */
@ManagedBean(name = "micuenta")
@ViewScoped
public class miCuentaBean {

    private Personal personal;
    private final PersonalDao personalDao;
    int idUsuario = (Integer) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("personalIdUsuario");
    int idPersonal = (Integer) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("personalIdPersonalBiblioteca");

    /**
     * Creates a new instance of NewJSFManagedBean
     */
    public miCuentaBean() {
        DaoFactory factory = DaoFactory.getInstance();
        personalDao = factory.getPersonalDao(PERSONAL);
        personal = new Personal();
        personal = personalDao.cuentaGet(idUsuario);

    }

    public Personal getPersonal() {
        return personal;
    }

    public void setPersonal(Personal personal) {
        this.personal = personal;
    }

    public void micuentaUpd() throws IOException {
        
        personal.setIdUsuario(idUsuario);
        personal.setID_PERSONAL_BIBLIOTECA(idPersonal);
        Personal p = personalDao.cuentaupd(personal);
        // int dataUpdate = personalDao.actualizarEntidad(personalUpd, idUsuario);//bibliotecaDao.actualizarEntidad(bibliotecaUpd);
        RequestContext rc = RequestContext.getCurrentInstance();

        switch (p.getCodMensaje()) {
            case "0":
                rc.execute("PF('tblPersonal').clearFilters();");
                FacesContext.getCurrentInstance().addMessage("gMensaje", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No se pudo editar este registro."));
                RequestContext.getCurrentInstance().update("frmPersonalAdd:grid");
                RequestContext.getCurrentInstance().update("gMensaje");
                break;
            case "1":
                FacesContext.getCurrentInstance().addMessage("gMensaje", new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto", p.getMensaje()));
                rc.execute("PF('tblPersonal').clearFilters();");
                rc.execute("PF('dlbUpdPersonal').hide();");
                 personal = new Personal() ;
                 RequestContext.getCurrentInstance().update("frmPersonalAdd:grid");
                 RequestContext.getCurrentInstance().update("gMensaje");
             //    update=":frmPersonalAdd:grid :gMensaje"
                break;

            case "2":
                rc.execute("PF('tblPersonal').clearFilters();");
            FacesContext.getCurrentInstance().addMessage("gMensaje", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", p.getMensaje()));
             RequestContext.getCurrentInstance().update("frmPersonalAdd:grid");
             RequestContext.getCurrentInstance().update("gMensaje");
                break;

        }
       //  FacesContext.getCurrentInstance().getExternalContext().redirect("/BibliotecaVirtual/bv/inicio.xhtml");

    }

}
