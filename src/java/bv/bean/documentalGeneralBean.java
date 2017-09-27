package bv.bean;

import java.util.List;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;
import bv.dao.DocumentalDao;
import bv.dao.impl.DaoFactory;
import static bv.util.Constantes.*;
import javax.annotation.PostConstruct;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import vb.entidad.Documental;

@ManagedBean
@ViewScoped
public class documentalGeneralBean {

    private final DocumentalDao documentalDao;
    private String perfil;
    private List<Documental> filtroDocumental;
    private LazyDataModel<Documental> lazymodelGeneral;
    private String palabra = "";
    private int numeroRegistros = 0;

    public documentalGeneralBean() {
        DaoFactory factory = DaoFactory.getInstance();
        documentalDao = factory.getDocumentalDao(DOCUMENTAL);
    }

    private int i = 0;
    private String palabraaux = "";

    @PostConstruct
    public void init() {
        lazymodelGeneral = new LazyDataModel<Documental>() {
            @Override
            public List<Documental> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
                int ID_BIBLIOTECA_FUENTE = Integer.parseInt(FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("personalidBibliotecaFuente").toString());
                List<Documental> listDocumental = documentalDao.listarDocumentalGeneralFiltro(perfil, ID_BIBLIOTECA_FUENTE, first, pageSize, palabra.trim());
                if (palabraaux.isEmpty()) {
                    if (i == 0 && palabra.equals("")) {
                        i = documentalDao.contarDocumentalGeneralFiltro(perfil, ID_BIBLIOTECA_FUENTE, first, pageSize, palabra.trim());
                    } else if (!palabra.equals("")) {
                        i = documentalDao.contarDocumentalGeneralFiltro(perfil, ID_BIBLIOTECA_FUENTE, first, pageSize, palabra.trim());
                        palabraaux = palabra;
                    }
                }
                if(!palabraaux.equals(palabra)){
                    i = documentalDao.contarDocumentalGeneralFiltro(perfil, ID_BIBLIOTECA_FUENTE, first, pageSize, palabra.trim());
                    palabraaux = "";
                }
                lazymodelGeneral.setRowCount(i);
                setNumeroRegistros(i);
                return listDocumental;
            }
        };
    }

    public int getNumeroRegistros() {
        return numeroRegistros;
    }

    public void setNumeroRegistros(int numeroRegistros) {
        this.numeroRegistros = numeroRegistros;
    }

    public String getPalabra() {
        return palabra;
    }

    public void setPalabra(String palabra) {
        this.palabra = palabra;
    }

    public List<Documental> getFiltroDocumental() {
        return filtroDocumental;
    }

    public void setFiltroDocumental(List<Documental> filtroDocumental) {
        this.filtroDocumental = filtroDocumental;
    }

    public LazyDataModel<Documental> getLazymodelGeneral() {
        return lazymodelGeneral;
    }

    public void setLazymodelGeneral(LazyDataModel<Documental> lazymodelGeneral) {
        this.lazymodelGeneral = lazymodelGeneral;
    }

    public void accionFiltrar() {
        RequestContext.getCurrentInstance().update("frmListDocumental:tblDocumental");
    }

    public String getPerfil() {
        return perfil;
    }

    public void setPerfil(String perfil) {
        this.perfil = perfil;
    }

}
