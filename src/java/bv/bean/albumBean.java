package bv.bean;

import bv.dao.AlbumDao;
import bv.dao.impl.DaoFactory;
import static bv.util.Constantes.ALBUM;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import org.primefaces.context.RequestContext;
import vb.entidad.Album;

/**
 *
 * @author virtual
 */
@ManagedBean
@ViewScoped
public class albumBean {

    private final AlbumDao aDao;
    private List<SelectItem> cboAlbum = null;
    private Album album;
    String idBiblioteca;

    public albumBean() {
        idBiblioteca = FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("personalidBibliotecaFuente").toString();
        album = new Album();
        DaoFactory factory = DaoFactory.getInstance();
        aDao = factory.getAlbumDao(ALBUM);
    }

    public void creaAlbum() {
        if (album.getDESCRIPCION().trim().isEmpty()) {
            FacesContext.getCurrentInstance().addMessage("gMensaje", new FacesMessage(FacesMessage.SEVERITY_WARN, "Error", "Ingrese una descripción."));
            RequestContext.getCurrentInstance().update("gMensaje");
        } else {
            album.setID_BIBLIOTECA(Integer.parseInt(idBiblioteca));
            int insert = aDao.creaAlbum(album);
            if (insert == 0) {
                FacesContext.getCurrentInstance().addMessage("gMensaje", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Ocurrió un error al ejecutar la sentencia."));
                RequestContext.getCurrentInstance().update("gMensaje");
            } else {
                RequestContext rc = RequestContext.getCurrentInstance();
                rc.execute("PF('dlgAlbum').hide();");
                FacesContext.getCurrentInstance().addMessage("gMensaje", new FacesMessage(FacesMessage.SEVERITY_INFO, "Inserción correcta", "Se registro el album correctamente."));
                RequestContext.getCurrentInstance().update("gMensaje");
            }
        }
    }

    public List<SelectItem> getCboAlbum() {
        List<Object[]> lista = aDao.llenaComboAlbum(idBiblioteca);
        cboAlbum = new ArrayList<>();
        if (lista != null) {
            for (Object[] fila : lista) {
                cboAlbum.add(new SelectItem(fila[0], fila[1].toString()));
            }
        }
        return cboAlbum;
    }

    public void setCboAlbum(List<SelectItem> cboAlbum) {
        this.cboAlbum = cboAlbum;
    }

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

}
