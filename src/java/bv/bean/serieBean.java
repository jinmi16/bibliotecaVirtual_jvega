package bv.bean;

import bv.dao.SerieDao;
import bv.dao.impl.DaoFactory;
import static bv.util.Constantes.SERIE;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;
import vb.entidad.Serie;

/**
 * @author Renato Vásquez Tejada - renatovt11@gmail.com
 */
@ManagedBean
@RequestScoped
public class serieBean {

    private Serie serie;
    private final SerieDao serieDao;
    private List<Serie> cboSerie;

    public serieBean() {
        DaoFactory factory = DaoFactory.getInstance();
        serieDao = factory.getSerieDao(SERIE);
        serie = new Serie();
        rfrhCboSerie();
    }

    public Serie getSerie() {
        return serie;
    }

    public void setSerie(Serie serie) {
        this.serie = serie;
    }

    public List<Serie> getCboSerie() {
        String idBiblioteca = FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("personalidBibliotecaFuente").toString();
        List<Object[]> lista = serieDao.llenaComboSerie(idBiblioteca);
        cboSerie = new ArrayList<>();
        if (lista != null) {
            for (Object[] fila : lista) {
                Serie ser = new Serie();
                ser.setID_SERIE(Integer.parseInt(fila[0].toString()));
                ser.setSERIE(fila[1].toString());
                cboSerie.add(ser);
            }
        }
        return cboSerie;
    }

    public void setCboSerie(List<Serie> cboSerie) {
        this.cboSerie = cboSerie;
    }

    public void rfrhCboSerie() {
        RequestContext.getCurrentInstance().update("frmAddDocumental:cboSerie");
    }

    public void crearSerie() {
        String idBiblioteca = FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("personalidBibliotecaFuente").toString();
        int n = serieDao.crearEntidad(serie, idBiblioteca);
        if (n == 0) {
            FacesContext.getCurrentInstance().addMessage("gMensaje", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Ocurrió un error al ejecutar la sentencia."));
        } else {
            RequestContext rc = RequestContext.getCurrentInstance();
            rc.execute("PF('dlgSerie').hide();");
            FacesContext.getCurrentInstance().addMessage("gMensaje", new FacesMessage(FacesMessage.SEVERITY_INFO, "Inserción correcta", "Se registro la serie correctamente."));
        }
        limpiarSerie();
        RequestContext.getCurrentInstance().update("frmSerie:gridSerie");
        RequestContext.getCurrentInstance().update("frmAddDocumental:cboSerie");
        RequestContext.getCurrentInstance().update("gMensaje");
    }

    public void limpiarSerie() {
        serie = new Serie();
    }

}
