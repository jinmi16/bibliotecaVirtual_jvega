package bv.bean;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import org.primefaces.event.CellEditEvent;
import org.primefaces.context.RequestContext;
import bv.dao.PublicacionDao;
import bv.dao.BibliotecaDao;
import bv.dao.DocumentalDao;
import bv.dao.PerfilDocumentalDetalleDao;
import bv.dao.impl.DaoFactory;
import static bv.util.Constantes.BIBLIOTECA;
import static bv.util.Constantes.DOCUMENTAL;
import static bv.util.Constantes.PERFIL_DOCUMENTAL_DETALLE;
import static bv.util.Constantes.PUBLICACION;
import java.util.Map;
import javax.annotation.PostConstruct;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import vb.entidad.Documental;
import vb.entidad.PerfilDocumentalDetalle;
import vb.entidad.Publicacion;
import vb.dto.PerfilDto;
import vb.entidad.Biblioteca;

@ManagedBean
@ViewScoped
public class perfilDocumentalDetalleBean {

    private List<PerfilDocumentalDetalle> lstPerfilDocumentalDetalle;
    private final PerfilDocumentalDetalleDao objPerfilDocumentalDetalleDao;
    private String perfil;
    private String perfilSelect;
    private String perfilControl;
    private String observaciones;
    private String EstadoContRecSelec;
    private Documental documentalPnlControl;
    private List<SelectItem> cboPerfiles;
    private List<SelectItem> cboEstadoControlRecurso = new ArrayList<>();
    private List<SelectItem> cboVista;
    private List<SelectItem> cboRequerido;
    private String urlOld = "";
    private String rutaServidorArchivos;
    private boolean linkProbado = false;
    private boolean mostrarLink = false;
    private boolean mostrarVincular = true;
    private String pagDurac = "PAGINAS";
    private final BibliotecaDao bDao;

    private String idDocumentalControl = "";
    private final DocumentalDao ddao;
    private final PublicacionDao pubDao;
    private ArrayList<Documental> listaDoc = new ArrayList<>();
    private Publicacion pub = new Publicacion();
    private List<Documental> filterDocumental;
    //-------------
    private String ID_PERFIL;
    private String PERFIL_CBO;
    //--------------------
       private List<Documental> filtroTabla;
    private LazyDataModel<Documental> lazymodel;
    private String query = "";
    private int numeroRegistros = 0;

    public perfilDocumentalDetalleBean() {
        DaoFactory factory = DaoFactory.getInstance();
        objPerfilDocumentalDetalleDao = factory.getPerfilDocumentalDetalleDao(PERFIL_DOCUMENTAL_DETALLE);
        bDao = factory.getBibliotecaDao(BIBLIOTECA);
        ddao = factory.getDocumentalDao(DOCUMENTAL);
        pubDao = factory.getPublicacionDao(PUBLICACION);
        documentalPnlControl = new Documental();
    }

    public List<Documental> getFiltroTabla() {
        return filtroTabla;
    }

    public void setFiltroTabla(List<Documental> filtroTabla) {
        this.filtroTabla = filtroTabla;
    }

    public LazyDataModel<Documental> getLazymodel() {
        return lazymodel;
    }

    public void setLazymodel(LazyDataModel<Documental> lazymodel) {
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

    
    
    public String getPERFIL_CBO() {
        return PERFIL_CBO;
    }

    public void setPERFIL_CBO(String PERFIL_CBO) {
        this.PERFIL_CBO = PERFIL_CBO;
    }

    public String getEstadoContRecSelec() {
        return EstadoContRecSelec;
    }

    public void setEstadoContRecSelec(String EstadoContRecSelec) {
        this.EstadoContRecSelec = EstadoContRecSelec;
    }

    public String getID_PERFIL() {
        return ID_PERFIL;
    }

    public void setID_PERFIL(String ID_PERFIL) {
        this.ID_PERFIL = ID_PERFIL;
    }

    public List<SelectItem> getCboRequerido() {
        cboRequerido.add(new SelectItem(0, "Requerido"));
        cboRequerido.add(new SelectItem(1, "No Requerido"));
        return cboRequerido;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public boolean isMostrarLink() {
        return mostrarLink;
    }

    public void setMostrarLink(boolean mostrarLink) {
        this.mostrarLink = mostrarLink;
    }

    public void setCboRequerido(List<SelectItem> cboRequerido) {
        this.cboRequerido = cboRequerido;
    }

    public List<SelectItem> getCboVista() {
        cboVista.add(new SelectItem(0, "visible"));
        cboVista.add(new SelectItem(1, "No Visible"));
        return cboVista;
    }

    public void setCboVista(List<SelectItem> cboVista) {
        this.cboVista = cboVista;
    }

    public String getPerfilSelect() {
        return perfilSelect;
    }

    public String getPagDurac() {
        if (perfilControl != null) {
            if (perfilControl.equals("6")) {
                pagDurac = "DURACION";
            } else {
                pagDurac = "PAGINAS";
            }
        }
        return pagDurac;
    }

    public void setPagDurac(String pagDurac) {
        this.pagDurac = pagDurac;
    }

    public void setPerfilSelect(String perfilSelect) {
        this.perfilSelect = perfilSelect;
    }

    public Documental getDocumentalPnlControl() {
        return documentalPnlControl;
    }

    public void setDocumentalPnlControl(Documental documentalPnlControl) {
        this.documentalPnlControl = documentalPnlControl;
    }

    public String getPerfilControl() {
        return perfilControl;
    }

    public void setPerfilControl(String perfilControl) {
        this.perfilControl = perfilControl;
    }

    public List<SelectItem> getCboPerfiles() {
        List<Object[]> lista = objPerfilDocumentalDetalleDao.obtenerPerfiles();
        cboPerfiles = new ArrayList<>();
        if (lista != null) {
            for (Object[] fila : lista) {
                cboPerfiles.add(new SelectItem(fila[0], fila[1].toString()));
            }
        }
        return cboPerfiles;
    }
    public void accionFiltrar() {
        RequestContext.getCurrentInstance().update("frmControl:tblControlDocumental");
    }

    public void setCboPerfiles(List<SelectItem> cboPerfiles) {
        this.cboPerfiles = cboPerfiles;
    }

    public List<SelectItem> getCboEstadoControlRecurso() {
        List<Object[]> lista = objPerfilDocumentalDetalleDao.obtenerestadoControlRec("CTR_RECURSO");
        cboEstadoControlRecurso = new ArrayList<>();
        if (lista != null) {
            for (Object[] fila : lista) {
                cboEstadoControlRecurso.add(new SelectItem(fila[0], fila[1].toString()));
            }
        }

        return cboEstadoControlRecurso;
    }

    public void setCboEstadoControlRecurso(List<SelectItem> cboEstadoControlRecurso) {
        this.cboEstadoControlRecurso = cboEstadoControlRecurso;
    }

    public String getPerfil() {
        return perfil;
    }

    public void setPerfil(String perfil) {
        this.perfil = perfil;
    }

    public List<PerfilDocumentalDetalle> getLstPerfilDocumentalDetalle() {
        return lstPerfilDocumentalDetalle;
    }

    public void setLstPerfilDocumentalDetalle(List<PerfilDocumentalDetalle> lstPerfilDocumentalDetalle) {
        this.lstPerfilDocumentalDetalle = lstPerfilDocumentalDetalle;
    }

    public boolean isVisible(String campo) {
        boolean visible = false;
        for (PerfilDocumentalDetalle pd : lstPerfilDocumentalDetalle) {
            if (pd.getCAMPO().equals(campo)) {
                visible = pd.isVISTA();
            }
        }
        return visible;
    }

    public void listarDetallePerdil() {
       //lstPerfilDocumentalDetalle = objPerfilDocumentalDetalleDao.listarPerfilDocumentalDetalle(perfil);
        String idBiblioteca = FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("personalidBibliotecaFuente").toString();
        System.out.println("perfil: "+perfil+ " IdBiblioteca :"+idBiblioteca);
        lstPerfilDocumentalDetalle = objPerfilDocumentalDetalleDao.listarPerfilDocumentalDetalleXBiblioteca(perfil,idBiblioteca);
    }

    public void listarDetallePerdil(String perfil) {
       // lstPerfilDocumentalDetalle = objPerfilDocumentalDetalleDao.listarPerfilDocumentalDetalle(perfil);
    }

    public void listarDetallePerfiles() {
        String idBiblioteca = FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("personalidBibliotecaFuente").toString();
        System.out.println("perfil select="+perfilSelect+ " idBilioteca = "+idBiblioteca);
       lstPerfilDocumentalDetalle = objPerfilDocumentalDetalleDao.listarPerfilDocumentalDetalleXBiblioteca(perfilSelect,idBiblioteca);
    }

    public void editarPerfilDocumentalDetalle() {
        int idUsuario = (Integer) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("personalIdUsuario");
        int upd = objPerfilDocumentalDetalleDao.editarListPerfildocumentaldetalle(lstPerfilDocumentalDetalle, idUsuario);
    }

    private void msjError(String growl, String m) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(growl, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", m));
    }
    
    @PostConstruct
    public void init() {
        lazymodel = new LazyDataModel<Documental>() {
            @Override
            public List<Documental> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
                String ID_BIBLIOTECA_FUENTE = String.valueOf(FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("personalidBibliotecaFuente"));
                Documental doc = ddao.listDocumentalPublicacionPaginacion(perfilControl, ID_BIBLIOTECA_FUENTE, EstadoContRecSelec, first, pageSize, query);
                List<Documental> lstDocumental = doc.getLstDocumental();
                int countQuery = doc.getCountQuery();
                lazymodel.setRowCount(countQuery);
                setNumeroRegistros(countQuery);
                return lstDocumental;
            }
        };
    }

    public String actualizarTabla() {
        int idUsuario = (Integer) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("personalIdUsuario");
        int n = objPerfilDocumentalDetalleDao.editarListPerfildocumentaldetalle(lstPerfilDocumentalDetalle, idUsuario);
        if (n > 0) {
            FacesContext.getCurrentInstance().addMessage("gMensaje", new FacesMessage(FacesMessage.SEVERITY_INFO, "Éxito!", "ACTUALIZADO EXITOSAMENTE"));
            RequestContext.getCurrentInstance().update("gMensaje");
        } else {
            FacesContext.getCurrentInstance().addMessage("gMensaje", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "ERROR DE ACTUALIZACIO"));
            RequestContext.getCurrentInstance().update("gMensaje");
        }
        return "perfilDocDetalle";

    }

    public void onCellEdit(CellEditEvent event) {
        boolean estadoantiguo = (boolean) event.getOldValue();
        Object oldValue = event.getOldValue().toString();
        Object newValue = event.getNewValue();
        if (newValue != null && !newValue.equals(oldValue)) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cell Changed", "Old: " + oldValue + ", New:" + newValue);
            FacesContext.getCurrentInstance().addMessage("gMensaje", msg);
        }
    }

    public List<Documental> getFilterDocumental() {
        return filterDocumental;
    }

    public void setFilterDocumental(List<Documental> filterDocumental) {
        this.filterDocumental = filterDocumental;
    }

    public Publicacion getPub() {
        return pub;
    }

    public void setPub(Publicacion pub) {
        this.pub = pub;
    }

    public ArrayList<Documental> getListaDoc() {
        return listaDoc;
    }

    public void setListaDoc(ArrayList<Documental> listaDoc) {
        this.listaDoc = listaDoc;
    }

    public String getIdDocumentalControl() {
        return idDocumentalControl;
    }

    public void setIdDocumentalControl(String idDocumentalControl) {
        this.idDocumentalControl = idDocumentalControl;
    }

    public void listarTablaxPerfil() {

        String idBiblioteca = FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("personalidBibliotecaFuente").toString();
        if (perfilControl == null) {
            perfilControl = "-1";
        }
        if (EstadoContRecSelec == null) {
            EstadoContRecSelec = "-1";
        }
        if (EstadoContRecSelec.endsWith("2")) {
            mostrarVincular = false;
        } else {
            mostrarVincular = true;
        }
        listaDoc = ddao.listDocumentalPublicacion(perfilControl, idBiblioteca, EstadoContRecSelec);
        RequestContext.getCurrentInstance().update("frmControl:tblControlDocumental");
    }

    private List<String> publicar;
    public boolean flipPdf = false;
    public boolean fotMapPln = false;
    public boolean audVdo = false;
    public String tipoArchivo;
    public String archivo = "";
    public String archivofinal = "";

    public List<String> getPublicar() {
        return publicar;
    }

    public void setPublicar(List<String> publicar) {
        this.publicar = publicar;
    }

    public boolean isFlipPdf() {
        return flipPdf;
    }

    public void setFlipPdf(boolean flipPdf) {
        this.flipPdf = flipPdf;
    }

    public boolean isFotMapPln() {
        return fotMapPln;
    }

    public void setFotMapPln(boolean fotMapPln) {
        this.fotMapPln = fotMapPln;
    }

    public boolean isAudVdo() {
        return audVdo;
    }

    public void setAudVdo(boolean audVdo) {
        this.audVdo = audVdo;
    }

    public String getTipoArchivo() {
        return tipoArchivo;
    }

    public void setTipoArchivo(String tipoArchivo) {
        this.tipoArchivo = tipoArchivo;
    }

    public String getArchivo() {

        return archivo;
    }

    public void setArchivo(String archivo) {
        this.archivo = archivo;
    }

    public String getArchivofinal() {
        return archivofinal;
    }

    public void setArchivofinal(String archivofinal) {
        this.archivofinal = archivofinal;
    }

    public boolean isMostrarVincular() {
        return mostrarVincular;
    }

    public void setMostrarVincular(boolean mostrarVincular) {
        this.mostrarVincular = mostrarVincular;
    }

    String urlYt;

    public void mostrarCarpetaUbicacion(String id, String urlYoutube) {

        switch (perfilControl) {
            case "6":
                flipPdf = false;
                fotMapPln = false;
                audVdo = true;
                archivo = id;
                tipoArchivo = "youtube";
                urlYt = urlYoutube;
                cambiarLabel();
                RequestContext.getCurrentInstance().update("frmDlgControl:grdControl");
                break;
            case "5":
                flipPdf = false;
                fotMapPln = true;
                audVdo = false;
                archivo = id;
                tipoArchivo = "fotos";
                cambiarLabel();
                RequestContext.getCurrentInstance().update("frmDlgControl:grdControl");
                break;

            default:
                flipPdf = true;
                fotMapPln = false;
                audVdo = false;
                archivo = id;
                tipoArchivo = "FlippingBook";
                cambiarLabel();
                RequestContext.getCurrentInstance().update("frmDlgControl:grdControl");
                break;
        }
        RequestContext.getCurrentInstance().execute("PF('dlgControl').show()");

    }

    public void handleKeyEvent() {
        archivo = archivo.toLowerCase();
    }
    public String valor0;

    public void mostrarMsgcheck() {
        //List<String> listaOut = new ArrayList<String>();
        String msg = "";
        int l = publicar.size();
        String valorI = "";
        switch (l) {
            case 1:
                valorI = publicar.get(l - 1);
                if (valorI.equals("1")) {
                    msg = "Se agregará este documental al catálogo.";
                } else {
                    msg = "No agregará este documental al catálogo.";
                }
                valor0 = valorI;
                break;
            case 2:
                if (publicar.contains(valor0)) {
                    int position = publicar.indexOf(valor0);
                    publicar.remove(position);
                }
                int s = publicar.size();
                String valorII = publicar.get(s - 1);
                if (valorII.equals("1")) {
                    msg = "Se agregará este documental al catálogo.";
                } else {
                    msg = "No agregará este documental al catálogo.";
                }
                valor0 = valorII;
                break;
            default:

                break;
        }
        FacesContext.getCurrentInstance().addMessage("gMensaje", new FacesMessage(msg));
        RequestContext.getCurrentInstance().update("gMensaje");
        RequestContext.getCurrentInstance().update("frmDlgControl:chkPublicar");
    }

    public void cambiarLabel() {
        String concat = "";
        int ID_BIBLIOTECA_FUENTE = Integer.parseInt(FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("personalidBibliotecaFuente").toString());
        String idBiblioteca = String.valueOf(ID_BIBLIOTECA_FUENTE);

        switch (tipoArchivo) {
            case "FlippingBook":
                archivofinal = tipoArchivo.toLowerCase() + "/" + archivo.trim() + "/index.html";

                break;
            case "PDF":
                archivofinal = tipoArchivo.toLowerCase() + "/" + archivo.trim() + ".pdf";
                break;
            case "fotos":
                archivofinal = "imagen/" + tipoArchivo.toLowerCase() + "/" + archivo.trim() + ".jpg";
                break;
            case "mapas_planos":
                archivofinal = "imagen/" + tipoArchivo.toLowerCase() + "/" + archivo.trim() + ".jpg";
                break;
            case "audio":
                archivofinal = tipoArchivo.toLowerCase() + "/" + archivo.trim() + ".mp3";
                break;
            case "video":
                archivofinal = tipoArchivo.toLowerCase() + "/" + archivo.trim() + ".mp4";
                break;
            case "youtube":
                archivofinal = urlYt;
                break;
        }
        mostrarLink = true;
        RequestContext.getCurrentInstance().update("frmDlgControl:txtMuestra");
        RequestContext.getCurrentInstance().update("frmDlgControl:grdControl:link");
    }

    public void limpiar() {
        archivo = "";
        archivofinal = "";
        tipoArchivo = "";
        publicar = new ArrayList<>();
        tipoArchivo = "";
        archivofinal = "";
        publicar = new ArrayList<>();
        linkProbado = false;
        mostrarLink = false;
        observaciones = "";

        RequestContext.getCurrentInstance().update("frmDlgControl:grdControl");
    }

    public void registrarControlado() {
        String idDoc = documentalPnlControl.getID_DOCUMENTAL();

        int idUsuario = (Integer) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("personalIdUsuario");
        String publicado;
        if (publicar.size() > 0) {
            publicado = publicar.get(0);
        } else {
            publicado = "0";
        }
        ExternalContext ext = FacesContext.getCurrentInstance().getExternalContext();
        rutaServidorArchivos = ext.getInitParameter("rutaServidorArchivos");

        //****validaciones
        ArrayList<String> lstErrores = new ArrayList<>();

        switch (perfilControl) {
            case "6":
                if (archivofinal.trim().length() == 0) {
                    lstErrores.add("Campo Ruta Final esta Vacio");
                }
                if (!linkProbado) {
                    lstErrores.add("Debe validar el Link antes de Aceptar");
                }
                break;
            default:
                if (archivofinal.trim().length() == 0) {
                    lstErrores.add("Campo Ruta Final esta Vacio");
                }
                Biblioteca bib = obtenerServidorBiblioteca();
                //boolean existe = ddao.validarFichero(rutaServidorArchivos, archivofinal);
                boolean existe = ddao.validarFichero(rutaServidorArchivos + bib.getDIRECTORIO(), archivofinal);
                if (!existe) {
                    lstErrores.add("El Fichero no existe en el Servidor de Archivos");
                }
                if (!linkProbado) {
                    lstErrores.add("Debe validar el Link antes de Aceptar");
                }

                break;

        }

        //---------------
        if (lstErrores.isEmpty()) {
            String mensage = ddao.controlDocumental(idDoc, archivofinal, idUsuario, publicado, perfilControl);
            limpiar();
            publicar = new ArrayList<>();
            FacesContext.getCurrentInstance().addMessage("gMensaje", new FacesMessage(mensage));
            RequestContext.getCurrentInstance().update("gMensaje");
            RequestContext.getCurrentInstance().execute("PF('dlgControl').hide()");
            listarTablaxPerfil();

        } else {
            String mensaje = "No se pudo insertar el documento.\nPor los motivos:<br/>";
            for (int i = 0; i < lstErrores.size(); i++) {
                String motivo = "-" + lstErrores.get(i) + "<br/>";
                mensaje = mensaje + motivo;
            }
            msjError("gMensaje", mensaje);
            RequestContext.getCurrentInstance().update("gMensaje");

        }

    }

    public void registrarObservacion() {
        String idDoc = documentalPnlControl.getID_DOCUMENTAL();
        int idUsuario = (Integer) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("personalIdUsuario");
        String mensage = ddao.controlDocumentalObservacion(idDoc, "5", observaciones, idUsuario);
        limpiar();
        FacesContext.getCurrentInstance().addMessage("gMensaje", new FacesMessage(mensage));
        RequestContext.getCurrentInstance().update("gMensaje");
        RequestContext.getCurrentInstance().execute("PF('dlgControl').hide()");
        listarTablaxPerfil();

    }

    public void redireccionar(String ID_DOCUMENTAL) {
        try {
            PerfilDto pdto = objPerfilDocumentalDetalleDao.obtenerPerfilXidDocumental(ID_DOCUMENTAL);
            FacesContext.getCurrentInstance().addMessage("gMensaje", new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta!", "Modificando DOCUMENTAL: " + ID_DOCUMENTAL));
            FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
            FacesContext.getCurrentInstance().getExternalContext().redirect("/BibliotecaVirtual/perfilDocumental/UpdCont?ID_PERFIL_DOCUMENTAL=" + pdto.getID_perfil() + "&PERFIL_DOCUMENTAL=" + pdto.getPerfil() + "&ID_DOCUMENTAL=" + ID_DOCUMENTAL + "&CONT=1");
        } catch (IOException ex) {
            System.out.println(ex.getMessage() + " method : redireccionar");
        }

    }

    public void redirectUrl() throws IOException {
        if (archivofinal.trim().length() > 0) {
            ExternalContext ext = FacesContext.getCurrentInstance().getExternalContext();
            rutaServidorArchivos = ext.getInitParameter("rutaServidorArchivos");
            Biblioteca bib = obtenerServidorBiblioteca();

            String url = bib.getURL() + bib.getDIRECTORIO() + archivofinal;

            boolean existe = ddao.validarFichero(rutaServidorArchivos + bib.getDIRECTORIO(), archivofinal);
            if (!existe) {

                if (tipoArchivo.equals("youtube")) {
                    RequestContext.getCurrentInstance().execute("pasarPagina('" + urlYt + "')");
                    RequestContext.getCurrentInstance().update("frmDlgControl:grdControl:link");
                    linkProbado = true;

                } else {
                    String mensaje = "El Fichero no existe en el Servidor de Archivos";
                    msjError("gMensaje", mensaje);
                    RequestContext.getCurrentInstance().update("gMensaje");

                }

            } else {
                RequestContext.getCurrentInstance().execute(" $('.cambiarColorControlLink').css({'color':'blue !important'}); ");

                // RequestContext.getCurrentInstance().execute("window.open('" + url + "','_blank');");
                RequestContext.getCurrentInstance().execute("pasarPagina('" + url + "')");
                RequestContext.getCurrentInstance().update("frmDlgControl:grdControl:link");
                linkProbado = true;
            }

        }
    }

    public Biblioteca obtenerServidorBiblioteca() {
        int ID_BIBLIOTECA_FUENTE = Integer.parseInt(FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("personalidBibliotecaFuente").toString());
        String idBiblioteca = String.valueOf(ID_BIBLIOTECA_FUENTE);

        Biblioteca bib = bDao.oobtenerServidorBiblioteca(idBiblioteca);
        return bib;

    }

}
