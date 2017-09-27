package bv.bean;

import bv.dao.PerfilDocumentalDetalleDao;
import bv.dao.PublicacionDao;
import bv.dao.impl.DaoFactory;
import static bv.util.Constantes.PERFIL_DOCUMENTAL_DETALLE;
import static bv.util.Constantes.PUBLICACION;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import org.primefaces.context.RequestContext;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import vb.dto.PublicacionDto;
import vb.entidad.Documental;

/**
 *
 * @author virtual
 */
@ManagedBean
@ViewScoped
public class publicacionBean {

    /**
     * Creates a new instance of publicacionBean
     */
    private final PerfilDocumentalDetalleDao pddDao;
    private List<PublicacionDto> lpublicacion = new ArrayList<>();
    private final PublicacionDao pubDao;
    String perfilControl;
    private List<SelectItem> cboPerfiles;
    String visibilidad;
    private List<SelectItem> cboVisibilidad;
//-------------
    private List<PublicacionDto> filtroTabla;
    private LazyDataModel<PublicacionDto> lazymodel;
    private String query = "";
    private int numeroRegistros = 0;
    private Documental dgn = new Documental();
    private String idBibliotecaSelect;

    public publicacionBean() {
        DaoFactory factory = DaoFactory.getInstance();
        pubDao = factory.getPublicacionDao(PUBLICACION);
        pddDao = factory.getPerfilDocumentalDetalleDao(PERFIL_DOCUMENTAL_DETALLE);
       // listarDocumentalPublicado();
    }

    @PostConstruct
    public void init() {
        lazymodel = new LazyDataModel<PublicacionDto>() {
            @Override
            public List<PublicacionDto> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
                String ID_BIBLIOTECA_FUENTE = String.valueOf(FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("personalidBibliotecaFuente"));
                if(idBibliotecaSelect==null){
                 idBibliotecaSelect=ID_BIBLIOTECA_FUENTE;
                }
               
                String idB=ID_BIBLIOTECA_FUENTE;
                if(ID_BIBLIOTECA_FUENTE.equals("2")){
                idB=idBibliotecaSelect;
                }else{
                idB=ID_BIBLIOTECA_FUENTE;
                }
                    PublicacionDto doc = pubDao.listPublicacionPaginado(perfilControl, idB, visibilidad, first, pageSize, query);
                 lpublicacion = doc.getLstPublicacionDto();
                List<PublicacionDto> lstDocumental = doc.getLstPublicacionDto();
                int countQuery = doc.getCoutQuery();
                lazymodel.setRowCount(countQuery);
                setNumeroRegistros(countQuery);
                return lpublicacion;
            }
        };
    }

    public void accionFiltrar() {
        RequestContext.getCurrentInstance().update("frmPub:tblPub");
        // RequestContext.getCurrentInstance().update("frmControl:tblControlDocumental");
    }

    public List<PublicacionDto> getFiltroTabla() {
        return filtroTabla;
    }

    public String getIdBibliotecaSelect() {
        return idBibliotecaSelect;
    }

    public void setIdBibliotecaSelect(String idBibliotecaSelect) {
        this.idBibliotecaSelect = idBibliotecaSelect;
    }

    
    public void setFiltroTabla(List<PublicacionDto> filtroTabla) {
        this.filtroTabla = filtroTabla;
    }

    public LazyDataModel<PublicacionDto> getLazymodel() {
        return lazymodel;
    }

    public void setLazymodel(LazyDataModel<PublicacionDto> lazymodel) {
        this.lazymodel = lazymodel;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public int getNumeroRegistros() {
        return numeroRegistros;
    }

    public void setNumeroRegistros(int numeroRegistros) {
        this.numeroRegistros = numeroRegistros;
    }

    public Documental getDgn() {
        return dgn;
    }

    public void setDgn(Documental dgn) {
        this.dgn = dgn;
    }

    public List<PublicacionDto> getLpublicacion() {
        return lpublicacion;
    }

    public void setLpublicacion(List<PublicacionDto> lpublicacion) {
        this.lpublicacion = lpublicacion;
    }

    public String getPerfilControl() {
        return perfilControl;
    }

    public void setPerfilControl(String perfilControl) {
        this.perfilControl = perfilControl;
    }

    public List<SelectItem> getCboPerfiles() {
        List<Object[]> lista = pddDao.obtenerPerfiles();
        cboPerfiles = new ArrayList<>();
        if (lista != null) {
            for (Object[] fila : lista) {
                cboPerfiles.add(new SelectItem(fila[0], fila[1].toString()));
            }
        }

        return cboPerfiles;
    }

    public void setCboPerfiles(List<SelectItem> cboPerfiles) {
        this.cboPerfiles = cboPerfiles;
    }

    public String getVisibilidad() {
        return visibilidad;
    }

    public void setVisibilidad(String visibilidad) {
        this.visibilidad = visibilidad;
    }

    public List<SelectItem> getCboVisibilidad() {
        cboVisibilidad = new ArrayList<>();
        cboVisibilidad.add(new SelectItem("1", "Publicado"));
        cboVisibilidad.add(new SelectItem("0", "No Publicado"));
        return cboVisibilidad;
    }

    public void setCboVisibilidad(List<SelectItem> cboVisibilidad) {
        this.cboVisibilidad = cboVisibilidad;
    }

    public void listarDocumentalPublicado() {
        String idBiblioteca = FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("personalidBibliotecaFuente").toString();
        if (perfilControl == null) {
            perfilControl = "-1";
        }
        if (visibilidad == null) {
            visibilidad = "-1";
        }
       // lpublicacion = pubDao.listPublicacion(perfilControl, idBiblioteca, visibilidad);
        //RequestContext.getCurrentInstance().update("frmPub:tblPub");
         RequestContext.getCurrentInstance().update("frmPub:tblPub");
    }

    public void cambiaVisiblidad(PublicacionDto pub) {
        int idBiblioteca = Integer.parseInt(FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("personalidBibliotecaFuente").toString());
        if (idBiblioteca == 2) {
            int update = pubDao.cambiaVisibilidad(pub);
            listarDocumentalPublicado();

        } else {
            msjError("gMensaje", "No tiene el permiso para realizar esta Accion");
            RequestContext.getCurrentInstance().update("gMensaje");

        }

    }

    private void msjError(String growl, String m) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(growl, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", m));
    }

}
