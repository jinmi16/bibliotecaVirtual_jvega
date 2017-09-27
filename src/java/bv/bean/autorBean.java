package bv.bean;

import bv.dao.AutorDao;
import bv.dao.PaisDao;
import bv.dao.impl.DaoFactory;
import static bv.util.Constantes.AUTOR;
import static bv.util.Constantes.PAIS;
import java.io.Serializable;
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
import vb.entidad.Autor;
import vb.entidad.LogTabla;

@ManagedBean(name = "autor")
@ViewScoped
public class autorBean implements Serializable {
    
    private final AutorDao daoa;
    private final PaisDao daop;
    private List<Autor> ListAut;
    private Autor autAux = new Autor();
    private Autor autAdd = new Autor();
    private Autor autAddAlternativo = new Autor();
    private List<SelectItem> cboPais;
    private List<Autor> filterListAutor;
    private List<Autor> cboAutores;
    private int tipoAutor;
    private final String idBiblioteca;
    private final int idUsuario;
    
        // ---- paginado
        private List<Autor> filtroTabla;
    private LazyDataModel<Autor> lazymodel;
    private String query = "";
    private int numeroRegistros = 0;
    
    
    
    public autorBean() {
        DaoFactory factory = DaoFactory.getInstance();
        daoa = factory.getAutorDao(AUTOR);
        daop = factory.getPaisDao(PAIS);
        idBiblioteca = FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("personalidBibliotecaFuente").toString();
        idUsuario = (Integer) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("personalIdUsuario");
       // ListAut = daoa.obtenerEntidades(idBiblioteca);
        rfrhCboAutor();
    }
    
     @PostConstruct
    public void init() {
        lazymodel = new LazyDataModel<Autor>() {
            @Override
            public List<Autor> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
                String ID_BIBLIOTECA_FUENTE = String.valueOf(FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("personalidBibliotecaFuente"));
                Autor doc = daoa.listAutorPaginado(ID_BIBLIOTECA_FUENTE, first, pageSize, query);
                List<Autor> lst = doc.getLstAutor();
                int countQuery = doc.getCountQuery();
                lazymodel.setRowCount(countQuery);
                setNumeroRegistros(countQuery);
                return lst;
            }
        };
    }
    public void accionFiltrar() {
        RequestContext.getCurrentInstance().update("frmListAutor:tablaAutor");
    }

    public List<Autor> getFiltroTabla() {
        return filtroTabla;
    }

    public void setFiltroTabla(List<Autor> filtroTabla) {
        this.filtroTabla = filtroTabla;
    }

    public LazyDataModel<Autor> getLazymodel() {
        return lazymodel;
    }

    public void setLazymodel(LazyDataModel<Autor> lazymodel) {
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
    
    
    
    
    public Autor getAutAddAlternativo() {
        return autAddAlternativo;
    }
    
    public void setAutAddAlternativo(Autor autAddAlternativo) {
        this.autAddAlternativo = autAddAlternativo;
    }
    
    public int getTipoAutor() {
        return tipoAutor;
    }
    
    public void setTipoAutor(int tipoAutor) {
        this.tipoAutor = tipoAutor;
    }

    //Listar autores
    public List<Autor> getListAut() {
        return ListAut;
    }

    //combo de autores
    public List<Autor> getCboAutores() {
        return cboAutores;
    }
    
    public void rfrhCboAutor() {
        List<Object[]> lista = daoa.cboAutores(idBiblioteca);
        cboAutores = new ArrayList<>();
        if (lista != null) {
            for (Object[] fila : lista) {
                Autor autor = new Autor();
                autor.setID_AUTOR(Integer.parseInt(fila[0].toString()));
                autor.setNOMBRE(fila[1].toString());
                cboAutores.add(autor);
            }
        }
        RequestContext.getCurrentInstance().update("frmAddDocumental:cboAutor");
    }
    
    public void setCboAutores(List<Autor> cboAutores) {
        this.cboAutores = cboAutores;
    }

    //filtrado
    public List<Autor> getFilterListAutor() {
        return filterListAutor;
    }
    
    public void setFilterListAutor(List<Autor> filterListAutor) {
        this.filterListAutor = filterListAutor;
    }

    //cargar actualizar
    public Autor getAutAux() {
        return autAux;
    }
    
    public void setAutAux(Autor autAux) {
        this.autAux = autAux;
    }
    //carga combo pais

    public List<SelectItem> getCboPais() {
        List<Object[]> lista = daop.llenaComboTipoInstitucion();
        cboPais = new ArrayList<>();
        if (lista != null) {
            for (Object[] fila : lista) {
                cboPais.add(new SelectItem(fila[0], fila[1].toString()));
            }
        }
        return cboPais;
    }

    //insertar autor    
    public Autor getAutAdd() {
        return autAdd;
    }
    
    public void setAutAdd(Autor autAdd) {
        this.autAdd = autAdd;
    }
    
    public void mostrarCamposAutores() {
        if (tipoAutor == 1) {
            RequestContext.getCurrentInstance().execute(" $('.autorAux1').css({'display':'table-row'}); ");
            RequestContext.getCurrentInstance().execute(" $('.autorAux2').css({'display':'none'}); ");
        } else {
            RequestContext.getCurrentInstance().execute(" $('.autorAux1').css({'display':'none'}); ");
            RequestContext.getCurrentInstance().execute(" $('.autorAux2').css({'display':'table-row'}); ");
        }
    }
    
    public void crearAutor() {
        String nombre = fragmentarNombre(autAdd.getNOMBRE());
        String pat = letraCapital(autAdd.getAPELLIDO_PATERNO());
        String mat = letraCapital(autAdd.getAPELLIDO_MATERNO());
        autAdd.setNOMBRE(nombre);
        autAdd.setAPELLIDO_PATERNO(pat);
        autAdd.setAPELLIDO_MATERNO(mat);
        autAdd.setID_BIBLIOTECA_REGISTRO(idBiblioteca);
        int flag1 = daoa.buscarEntidad(autAdd);
        if (flag1 == 0) {
            int flag2 = daoa.crearEntidad(autAdd, idUsuario);
            if (flag2 == 0) {
                msjError("No se ha podido registrar.");
            } else {
                msjCorrecto("Su registro se ha agregado con éxito.");
            }
        } else {
            msjError("El registro que esta tratando de ingresar ya existe.");
        }
        limpiarEntidadAutor(autAdd);
        
    }
    
    public void crearAutorDocumental() {        
        if (autAdd != null && autAddAlternativo != null) {
            if (tipoAutor == 1) {
                autAdd.setID_BIBLIOTECA_REGISTRO(idBiblioteca);
                if (autAdd.getNOMBRE().trim().isEmpty() && autAdd.getAPELLIDO_MATERNO().trim().isEmpty() && autAdd.getAPELLIDO_PATERNO().trim().isEmpty()) {
                    msjError("gMensaje", "Debe ingresar por lo menos un campo.");
                } else {
                    int flag1 = daoa.buscarEntidad(autAdd);
                    if (flag1 == 0) {
                        int flag2 = daoa.crearEntidad(autAdd, idUsuario);
                        if (flag2 == 0) {
                            msjError("gMensaje", "No se ha podido registrar.");
                        } else {
                            msjCorrecto("gMensaje", "Su registro se ha agregado con éxito.");
                        }
                    } else {
                        msjError("gMensaje", "El registro que esta tratando de ingresar ya existe.");
                    }
                    tipoAutor = 0;
                    limpiarEntidadAutor(autAdd);
                    rfrhCboAutor();
                    RequestContext.getCurrentInstance().update("frmAutor");
                    RequestContext.getCurrentInstance().execute("PF('dlgAutor').hide()");                    
                }
            } else if (tipoAutor == 2) {
                autAddAlternativo.setID_BIBLIOTECA_REGISTRO(idBiblioteca);
                if (autAddAlternativo.getALTERNATIVO().trim().isEmpty()) {
                    msjError("gMensaje", "Debe rellenar el campo.");
                } else {
                    int flag1 = daoa.buscarEntidadAlternativo(autAddAlternativo);
                    if (flag1 == 0) {
                        int flag2 = daoa.crearEntidadAlternativo(autAddAlternativo, idUsuario);
                        if (flag2 == 0) {
                            msjError("gMensaje", "No se ha podido registrar.");
                        } else {
                            msjCorrecto("gMensaje", "Su registro se ha agregado con éxito.");
                        }
                    } else {
                        msjError("gMensaje", "El registro que esta tratando de ingresar ya existe.");
                    }
                    tipoAutor = 0;
                    limpiarEntidadAutor(autAddAlternativo);
                    rfrhCboAutor();                    
                    RequestContext.getCurrentInstance().update("frmAutor");
                    RequestContext.getCurrentInstance().execute("PF('dlgAutor').hide()");                    
                }
            }
        }
    }
    
    private String fragmentarNombre(String s) {
        String delimitadores = " ";
        String nombre = s;
        String[] NombreSeparado = nombre.split(delimitadores);
        String nombres = "";
        for (int i = 0; i < NombreSeparado.length; i++) {
            String nombrep = "";
            int p = NombreSeparado[i].length();
            for (int j = 0; j < p; j++) {
                String word = "";
                if (j == 0) {
                    String letrainicio = (NombreSeparado[i].charAt(j) + "").toUpperCase();
                    word += letrainicio;
                } else {
                    String letrarestante = (NombreSeparado[i].charAt(j) + "").toLowerCase();
                    word += letrarestante;
                }
                nombrep += word + "";
            }
            nombres = nombres + nombrep + " ";
        }
        nombres = nombres.substring(0, nombres.length() - 1);
        return nombres;
    }
    
    private String letraCapital(String s) {
        String[] palabra = new String[s.length()];
        String letra = s;
        String letras = "";
        for (int i = 0; i < letra.length(); i++) {
            String word = "";
            if (i == 0) {
                String letrainicio = (letra.charAt(i) + "").toUpperCase();
                palabra[i] = letrainicio;
                word += palabra[i];
            } else {
                String letrarestante = (letra.charAt(i) + "").toLowerCase();
                palabra[i] = letrarestante;
                word += palabra[i];
            }
            letras += word;
        }
        return letras;
    }

    //Borrar autor
    public void borrarAutor(Autor aut) {
        int flag = daoa.eliminarEntidad(aut.getID_AUTOR(), idUsuario);
        if (flag == 0) {
            msjError("gMensaje", "Este autor se encuentra asignado a otros documentos");
        } else {
            ListAut = daoa.obtenerEntidades(idBiblioteca);
            msjCorrecto("gMensaje", "Se borro correctamente");
        }
    }

    //Modificar autor
    public void actualizarAutor() {
        
        int flag = daoa.actualizarEntidad(autAux, idUsuario);
        if (flag == 0) {
            msjError("gMensaje", "No se pudo actualizar correctamante");
        } else {
            msjCorrecto("gMensaje", "Se modifico");
        }
    }

    //accesorios
    private void msjError(String m) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", m));
    }
    
    private void msjError(String growl, String m) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(growl, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", m));
        RequestContext.getCurrentInstance().update(growl);
    }
    
    private void msjCorrecto(String m) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Éxito!", m));
    }
    
    private void msjCorrecto(String growl, String m) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(growl, new FacesMessage(FacesMessage.SEVERITY_INFO, "Éxito!", m));
        RequestContext.getCurrentInstance().update(growl);
    }
    
    private void limpiarEntidadAutor(Autor t) {
        t.setNOMBRE("");
        t.setAPELLIDO_PATERNO("");
        t.setAPELLIDO_MATERNO("");
        t.setID_PAIS("");
        t.setALTERNATIVO("");
    }
    
}
