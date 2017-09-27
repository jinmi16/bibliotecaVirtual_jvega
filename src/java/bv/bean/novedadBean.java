package bv.bean;

import bv.dao.NovedadDao;
import bv.dao.TransaccionDao;
import bv.dao.impl.DaoFactory;
import static bv.util.Constantes.NOVEDAD;
import static bv.util.Constantes.TRANSACCION;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ViewScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;
import org.primefaces.model.UploadedFile;
import vb.entidad.ImagenNovedad;
import vb.entidad.Novedad;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.StreamedContent;

@ManagedBean
@ViewScoped
public class novedadBean {

    private final TransaccionDao transaccion;
    private final NovedadDao novDao;
    private int imagenid;
    private List<ImagenNovedad> listaImagenes;
    private List<ImagenNovedad> listaImagenes2;
    private ImagenNovedad imagenHome2;
    private ImagenNovedad imagenHome;
    private Novedad novedad;
    private String rutaNovedad = "img/novedades/";
    private String rutaHome = "img/novedades-index/";
    private int it = 0;
    private List<Novedad> lstNovedad;
    private Novedad nov;

    private StreamedContent imgs;

    public novedadBean() {
        DaoFactory factory = DaoFactory.getInstance();
        transaccion = factory.getTransaccionDao(TRANSACCION);
        novDao = factory.getNovedadDao(NOVEDAD);
        imagenid = novDao.obetenerId();
        lstNovedad = novDao.obtenerEntidades();
        nov = new Novedad();
        if (it == 0) {
            listaImagenes2 = new ArrayList<>();
            listaImagenes = new ArrayList<>();
            novedad = new Novedad();
            imagenHome2 = new ImagenNovedad();
            imagenHome = new ImagenNovedad();
            it++;
        }
    }

    public List<ImagenNovedad> getListaImagenes2() {
        return listaImagenes2;
    }

    public void setListaImagenes2(List<ImagenNovedad> listaImagenes2) {
        this.listaImagenes2 = listaImagenes2;
    }

    public ImagenNovedad getImagenHome() {
        return imagenHome;
    }

    public void setImagenHome(ImagenNovedad imagenHome) {
        this.imagenHome = imagenHome;
    }

    public ImagenNovedad getImagenHome2() {
        return imagenHome2;
    }

    public void setImagenHome2(ImagenNovedad imagenHome2) {
        this.imagenHome2 = imagenHome2;

    }

    public Novedad getNovedad() {
        return novedad;
    }

    public void setNovedad(Novedad novedad) {
        this.novedad = novedad;

    }

    public List<ImagenNovedad> getListaImagenes() {
        return listaImagenes;
    }

    public void setListaImagenes(List<ImagenNovedad> listaImagenes) {
        this.listaImagenes = listaImagenes;
    }

    public void agegarNovedad() throws SQLException {
        int idUsuario = (Integer) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("personalIdUsuario");
        String msg = transaccion.insertTransaccionNovedades(novedad, listaImagenes, imagenid, idUsuario);
        if (msg.equals("1")) {
            FacesContext.getCurrentInstance().addMessage("gMensaje", new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto", "Se agrego el contenido correctamente"));
            RequestContext.getCurrentInstance().update("gMensaje");
            RequestContext.getCurrentInstance().update("frmNovedad");
            limpiar();
        } else {
            FacesContext.getCurrentInstance().addMessage("gMensaje", new FacesMessage(FacesMessage.SEVERITY_INFO, "Error", msg));
            RequestContext.getCurrentInstance().update("gMensaje");
        }
    }

    //****Home    
    public void abrirDlgImagenPortada() {
        Map<String, Object> options = new HashMap<>();
        options.put("modal", true);
        options.put("width", 700);
        options.put("closable", false);
        options.put("headerElement", "customheader");
        RequestContext.getCurrentInstance().openDialog("novedadImagenPortada", options, null);
    }

    public void onReturnNovedadHome(SelectEvent event) {
        ImagenNovedad img = new ImagenNovedad();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Data regresada", event.getObject().toString()));
        ArrayList<UploadedFile> listaIn = (ArrayList<UploadedFile>) event.getObject();
        for (UploadedFile list : listaIn) {
            img.setID_IMAGEN_NOVEDAD(imagenid);
            img.setID_NOVEDAD(imagenid);
            img.setURL_NOVEDAD(rutaHome + list.getFileName());
            img.setORDEN(1);
        }
        imagenHome = img;
        listaImagenes.add(img);
        RequestContext.getCurrentInstance().update("frmNovedad:txtImgHome");
    }

    /*Novedad*/
    public void abrirDlgImagenPortadaAccion() {
        Map<String, Object> options = new HashMap<>();
        options.put("modal", true);
        options.put("width", 700);
        options.put("closable", false);
        options.put("headerElement", "customheader");
        RequestContext.getCurrentInstance().openDialog("novedadImagenPortada2", options, null);
    }

    public void onReturnNovedadHome2(SelectEvent event) {
        ImagenNovedad img = new ImagenNovedad();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Data regresada", event.getObject().toString()));
        ArrayList<UploadedFile> listaIn = (ArrayList<UploadedFile>) event.getObject();
        for (UploadedFile list : listaIn) {
            img.setID_IMAGEN_NOVEDAD(imagenid);
            img.setID_NOVEDAD(imagenid);
            img.setURL_NOVEDAD(rutaNovedad + list.getFileName());
            img.setORDEN(2);
        }
        imagenHome2 = img;
        listaImagenes.add(img);
        RequestContext.getCurrentInstance().update("frmNovedad:txtImgHome2");
    }

    ////Novedad detalles
    public void abrirDlgImagenesDetalles() {
        Map<String, Object> options = new HashMap<>();
        options.put("modal", true);
        options.put("width", 700);
        options.put("closable", false);
        options.put("headerElement", "customheader");
        RequestContext.getCurrentInstance().openDialog("novedadImagenesDetalles", options, null);
    }

    public void onReturnFromdocumentalUpd(SelectEvent event) {
        ImagenNovedad img;
        int n = 3;
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Data regresada", event.getObject().toString()));
        ArrayList<UploadedFile> listaIn = (ArrayList<UploadedFile>) event.getObject();
        for (UploadedFile list : listaIn) {
            img = new ImagenNovedad();
            img.setID_IMAGEN_NOVEDAD(imagenid);
            img.setID_NOVEDAD(imagenid);
            img.setURL_NOVEDAD(rutaNovedad + list.getFileName());
            img.setORDEN(n);
            listaImagenes2.add(img);
            listaImagenes.add(img);
            n++;
        }
        RequestContext.getCurrentInstance().update("frmNovedad:listContenidos");
    }

    public void limpiar() {
        novedad = new Novedad();
        listaImagenes2 = new ArrayList<ImagenNovedad>();
        listaImagenes = new ArrayList<ImagenNovedad>();
        imagenHome2 = new ImagenNovedad();
        imagenHome = new ImagenNovedad();
    }

    public Novedad getNov() {
        if (nov.getACTIVO()!=null && nov.getMOSTAR_INICIO()!=null) {
            if (nov.getMOSTAR_INICIO().equals("1")) {
                nov.setMOSTAR_INICIO("Si");
            }
            if (nov.getACTIVO().equals("1")) {
                nov.setACTIVO("Si");
            } else if (nov.getACTIVO().equals("0") || nov.getMOSTAR_INICIO().equals("0")) {
                nov.setACTIVO("No");
                nov.setMOSTAR_INICIO("No");
            }
        }
        return nov;
    }

    public void setNov(Novedad nov) {
        this.nov = nov;
    }

    public List<Novedad> getLstNovedad() {
        return lstNovedad;
    }

    public void setLstNovedad(List<Novedad> lstNovedad) {
        this.lstNovedad = lstNovedad;
    }

}
