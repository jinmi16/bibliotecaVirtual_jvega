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
public class logTablaBean {

//    private List<Object[]> lstLogTabla;
//    private List<Object[]> lstLogTablaFilter;
    private final LogTablaDao log;
    // ---- paginado
        private List<LogTabla> filtroTabla;
    private LazyDataModel<LogTabla> lazymodel;
    private String query = "";
    private int numeroRegistros = 0;

    public logTablaBean() {
        DaoFactory factory = DaoFactory.getInstance();
        log = factory.getLogTablaDao(LOG_TABLA);
       //  String idBiblioteca = FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("personalidBibliotecaFuente").toString();
       // lstLogTabla = log.listarLog(Integer.parseInt(idBiblioteca));
    }
    
    @PostConstruct
    public void init() {
        lazymodel = new LazyDataModel<LogTabla>() {
            @Override
            public List<LogTabla> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
                String ID_BIBLIOTECA_FUENTE = String.valueOf(FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("personalidBibliotecaFuente"));
                LogTabla doc = log.listarLogpaginacion(Integer.parseInt(ID_BIBLIOTECA_FUENTE), first, pageSize, query);
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
    
    
    
    
    //--------------
    
    
//
//    public List<Object[]> getLstLogTabla() {
//
//        return lstLogTabla;
//    }
//
//    public void setLstLogTabla(List<Object[]> lstLogTabla) {
//        this.lstLogTabla = lstLogTabla;
//    }
//
//    public List<Object[]> getLstLogTablaFilter() {
//        return lstLogTablaFilter;
//    }
//
//    public void setLstLogTablaFilter(List<Object[]> lstLogTablaFilter) {
//        this.lstLogTablaFilter = lstLogTablaFilter;
  //  }

}
