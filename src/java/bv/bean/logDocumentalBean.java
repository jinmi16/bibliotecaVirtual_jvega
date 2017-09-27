package bv.bean;

import bv.dao.LogTablaDao;
import bv.dao.impl.DaoFactory;
import static bv.util.Constantes.LOG_TABLA;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import vb.entidad.LogTabla;

@ManagedBean
@javax.faces.bean.ViewScoped
public class logDocumentalBean {

    private final LogTablaDao log;
    //***** tabla paginada
    private List<LogTabla> filtroTabla;
    private LazyDataModel<LogTabla> lazymodel;
    private String query = "";
    private int numeroRegistros = 0;

    public logDocumentalBean() {
        DaoFactory factory = DaoFactory.getInstance();
        log = factory.getLogTablaDao(LOG_TABLA);
        String idBiblioteca = String.valueOf(FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("personalidBibliotecaFuente"));
    }

    @PostConstruct
    public void init() {
        lazymodel = new LazyDataModel<LogTabla>() {
            @Override
            public List<LogTabla> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
                String ID_BIBLIOTECA_FUENTE = String.valueOf(FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("personalidBibliotecaFuente"));
                LogTabla doc = log.listarLogDocumentalPaginado(Integer.parseInt(ID_BIBLIOTECA_FUENTE), first, pageSize, query);
                List<LogTabla> lst = doc.getLstLogTabla();
                int countQuery = doc.getCountQuery();
                lazymodel.setRowCount(countQuery);
                setNumeroRegistros(countQuery);
                return lst;
            }
        };
    }

    public void accionFiltrar() {
        RequestContext.getCurrentInstance().update("frmLog:tblLog");
    }

    public List<LogTabla> getFiltroTabla() {
        return filtroTabla;
    }

    public void setFiltroTabla(List<LogTabla> filtroTabla) {
        this.filtroTabla = filtroTabla;
    }

    public LazyDataModel<LogTabla> getLazymodel() {
        return lazymodel;
    }

    public void setLazymodel(LazyDataModel<LogTabla> lazymodel) {
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

}
