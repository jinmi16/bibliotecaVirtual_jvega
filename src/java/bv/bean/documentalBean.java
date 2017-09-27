package bv.bean;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import org.primefaces.context.RequestContext;
import org.primefaces.event.RowEditEvent;
import org.primefaces.event.SelectEvent;
import bv.dao.AutorDao;
import bv.dao.BibliotecaDao;
import bv.dao.ColeccionDao;
import bv.dao.ContribuidorDao;
import bv.dao.DocumentalDao;
import bv.dao.FormatoMedioDao;
import bv.dao.LenguajeDao;
import bv.dao.PerfilDocumentalDao;
import bv.dao.PerfilDocumentalDetalleDao;
import bv.dao.SerieDao;
import bv.dao.TablaContenidoDao;
import bv.dao.TemaDao;
import bv.dao.TransaccionDao;
import bv.dao.impl.DaoFactory;
import static bv.util.Constantes.*;
import javax.annotation.PostConstruct;
import javax.faces.context.ExternalContext;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import vb.dto.PerfilDto;
import vb.entidad.Autor;
import vb.entidad.AuxContenido;
import vb.entidad.Biblioteca;
import vb.entidad.Cobertura;
import vb.entidad.Coleccion;
import vb.entidad.Contribuidor;
import vb.entidad.Documental;
import vb.entidad.FormatoMedio;
import vb.entidad.Lenguaje;
import vb.entidad.PerfilDocumental;
import vb.entidad.PerfilDocumentalDetalle;
import vb.entidad.Serie;
import vb.entidad.Tema;

@ManagedBean
@ViewScoped
public class documentalBean {

    private int cargoUpdate;

    private String ID_DOCUMENTAL = "";
    private boolean boolTipo = false;
    private String antTITULO;
    private String antOTRO;
    private int antID_TIPO_OTRO;
    private int antID_BIBLIOTECA_FUENTE;
    private String lblNropag_Duracion;
    private ArrayList<String> selectedRelacionDocumental = new ArrayList<>();
    private final TransaccionDao tranDao;
    private String registrartModificar;
    private boolean rduracion;
    private boolean ocultarBiblioteca;
    private boolean rnropag;

    ///// ************ VARIABLES DOCUMENTAL   *********
    private Documental documental;
    private final DocumentalDao documentalDao;
    private String classValidacion;
    //private List<Documental> lstDocumental;
    // private List<Documental> lstDocumentalFilter;
    //VARIABLES COBERTURA
    private Cobertura cobertura;
    private List<SelectItem> cboDocumentalRelacion;
    ////******* CONTRIBUIDOR  ***
    private Contribuidor objContribuidor;
    private final ContribuidorDao conntribuidorDao;
    private ArrayList<Contribuidor> lstContribuidor = new ArrayList<>();
    private ArrayList<Contribuidor> selectedContribuidor = new ArrayList<>();
    private String disabledContribuidor = "true";
    ///******* variables de perfil documental
    private String perfil;
    private List<PerfilDocumentalDetalle> lstPerfilDocumentalDetalle;
    private PerfilDocumentalDetalleDao objPerfilDocumentalDetalleDao;
    //    TABLA DE CONTENIDOS
    private AuxContenido aux;
    private final TablaContenidoDao tablaContenidodao;
    private ArrayList<AuxContenido> listaTemas = new ArrayList<>();
    private ArrayList<AuxContenido> selectedContenidos = new ArrayList<>();
    private String disabled = "true";
    //-------------------------
    private AuxContenido tc;
    private ArrayList<AuxContenido> lstTc;

    //      SERIE
    private ArrayList<String> selectedSerie = new ArrayList<>();
    //------------------------AUTOR
    private final AutorDao autorDao;
    private ArrayList<String> selectedAutor = new ArrayList<>();
    //PERFIL DOCMENTAL
    private List<PerfilDocumental> listPerfilDocumental;
    private final PerfilDocumentalDao pdDao;
    private String perfil_documental;
    //COLECCION EDITAR
    private List<Coleccion> listColeccion;
    //SERIE EDITAR
    private final SerieDao serieDao;
    private List<Serie> listSerie;
    //VARIABLES DE TIENE PARTE DE
    private String indicador1;
    private String nindicador1="0";
    private String indicador2;
    String nindicador2="0";
    //   EXTENSION
    private String peso;
    private String extension;
    //TIENE PARTE DE
    boolean rindicador2 = false;
    boolean rnindicador2 = false;
    //FORMATO MEDIO
    private FormatoMedio fm;
    private final FormatoMedioDao daoFM;
    private String disabledcboFormatoMedio = "true";
    //COLECCION
    private final ColeccionDao coleccionDao;
    private ArrayList<String> selectedColeccion = new ArrayList<>();
    //TEMA
    private final TemaDao temaDao;
    private List<Tema> listaTema;
    private ArrayList<String> selectedTema = new ArrayList<>();
    //LENGUAJE
    private final LenguajeDao lenguajeDao;
    private ArrayList<String> selectedLenguaje = new ArrayList<>();
    private String Control = "0";
//---------------------
    private final BibliotecaDao bDao;
//-------------------------------------------------------------------------------------------------------------------------

    public documentalBean() {
        DaoFactory factory = DaoFactory.getInstance();

        //FACTORY DE DAOS
        bDao = factory.getBibliotecaDao(BIBLIOTECA);
        conntribuidorDao = factory.getContribuidorDao(CONTRIBUIDOR);
        objPerfilDocumentalDetalleDao = factory.getPerfilDocumentalDetalleDao(PERFIL_DOCUMENTAL_DETALLE);
        autorDao = factory.getAutorDao(AUTOR);
        daoFM = factory.getFormatoMedioDao(FORMATO_MEDIO);
        coleccionDao = factory.getColeccionDao(COLECCION);
        lenguajeDao = factory.getLenguajeDao(LENGUAJE);
        documentalDao = factory.getDocumentalDao(DOCUMENTAL);
        tablaContenidodao = factory.getTablaContenidoDao(TABLA_CONTENIDO);
        pdDao = factory.getPerfilDocumentalDao(PERFIL_DOCUMENTAL);
        serieDao = factory.getSerieDao(SERIE);
        tranDao = factory.getTransaccionDao(TRANSACCION);
        temaDao = factory.getTemaDao(TEMA);

        //OBJETOS
        documental = new Documental();
        cobertura = new Cobertura();
        objContribuidor = new Contribuidor();
        aux = new AuxContenido();
        fm = new FormatoMedio();

        documental.setFORMATO_EXTENSION("Pulse acá para cambiar el peso del archivo.");
        documental.setES_PARTE_DE("Pulse acá para cambiar el campo.");
            cargoUpdate = 0;
    }

    public boolean isOcultarBiblioteca() {
        String idBiblioteca = String.valueOf(FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("personalidBibliotecaFuente"));
        if (idBiblioteca.equals("2")) {
            ocultarBiblioteca = true;
        } else {
            ocultarBiblioteca = false;
        }
        return ocultarBiblioteca;
    }

    public void setOcultarBiblioteca(boolean ocultarBiblioteca) {
        this.ocultarBiblioteca = ocultarBiblioteca;
    }

    public String getID_DOCUMENTAL() {
        return ID_DOCUMENTAL;
    }

    public void setID_DOCUMENTAL(String ID_DOCUMENTAL) {
        this.ID_DOCUMENTAL = ID_DOCUMENTAL;
    }

    public String getRegistrartModificar() {
        if (ID_DOCUMENTAL.length() > 0) {
            registrartModificar = "Modificar";
        } else {
            registrartModificar = "Registrar";
        }

        return registrartModificar;
    }

    public String getControl() {
        return Control;
    }

    public void setControl(String Control) {
        this.Control = Control;
    }

    public void setRegistrartModificar(String registrartModificar) {
        this.registrartModificar = registrartModificar;
    }

    //********** listar para actualizar
    public void cargarObjetosForUpdate() {
        if (ID_DOCUMENTAL.length() > 0 && cargoUpdate == 0) {
            documental = documentalDao.listarXIdDocumental(ID_DOCUMENTAL);
            selectedTema = temaDao.listTemaDocumentalXidDocumental(ID_DOCUMENTAL);
            selectedContenidos = tablaContenidodao.lstTablaContenidosXidDocumental(ID_DOCUMENTAL);
            listaTemas = tablaContenidodao.lstTablaContenidosXidDocumental(ID_DOCUMENTAL);
            selectedAutor = autorDao.listAutorDocumentallXidDocumental(ID_DOCUMENTAL);
            selectedContribuidor = conntribuidorDao.listarContribuidorXidDocumental(ID_DOCUMENTAL);
            lstContribuidor = conntribuidorDao.listarContribuidorXidDocumental(ID_DOCUMENTAL);
            selectedSerie = serieDao.listSerieDocumentallXidDocumental(ID_DOCUMENTAL);
            selectedColeccion = coleccionDao.listColeccionDocumentallXidDocumental(ID_DOCUMENTAL);
            selectedLenguaje = lenguajeDao.lisLenguajeDocumentalXidDocumental(ID_DOCUMENTAL);
            selectedRelacionDocumental = documentalDao.listDocumentalRelacionXidDocumental(ID_DOCUMENTAL);
            if (documental.getES_PARTE_DE().trim().length() > 0) {
                arrayEsparteDe(documental.getES_PARTE_DE());
                changeCboIndicador();
                RequestContext.getCurrentInstance().update("gridEsParte");
            } else {
                documental.setES_PARTE_DE("Pulse acá para cambiar el campo.");
            }
            if (documental.getFORMATO_EXTENSION().trim().length() > 0) {
                arrayFormatoExtension(documental.getFORMATO_EXTENSION());
            } else {
                documental.setFORMATO_EXTENSION("Pulse acá para cambiar el peso del archivo.");
            }
            antTITULO = documental.getTITULO().trim();
            antOTRO = documental.getOTRO().trim();
            antID_TIPO_OTRO = documental.getID_TIPO_OTRO();
            antID_BIBLIOTECA_FUENTE = documental.getID_BIBLIOTECA_FUENTE();
            changeCboTipo();
            boolTipo = true;
            cargoUpdate++;
        }
    }

    //*******************
    public void opentablaContenidoExcel() {
        Map<String, Object> options = new HashMap<String, Object>();
        options.put("modal", true);
        options.put("width", 640);
        options.put("closable", false);
        options.put("headerElement", "customheader");
        RequestContext.getCurrentInstance().openDialog("tablaContenidoExcel", options, null);
    }

    public void onReturnFromdocumentalUpd(SelectEvent event) {
        selectedContenidos = new ArrayList<>();
        listaTemas = new ArrayList<>();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Data Returned", event.getObject().toString()));
        ArrayList<String> listaIn = (ArrayList<String>) event.getObject();
        for (int i = 0; i < listaIn.size(); i++) {
            tc = new AuxContenido();
            tc.setIndice(String.valueOf(i + 1));
            tc.setTema(listaIn.get(i));
            selectedContenidos.add(tc);
        }
        listaTemas = selectedContenidos;
        RequestContext.getCurrentInstance().update("frmTablaContenido:tblContenido");
        RequestContext.getCurrentInstance().update("frmAddDocumental:listContenidos");
    }

    //****
    public boolean isRnropag() {
        rnropag = !perfil.equals("6");
        return rnropag;
    }

    public void setRnropag(boolean rnropag) {
        this.rnropag = rnropag;
    }

    public boolean isRduracion() {
        rduracion = perfil.equals("6");
        return rduracion;
    }

    public void setRduracion(boolean rduracion) {
        this.rduracion = rduracion;
    }

    public ArrayList<String> getSelectedRelacionDocumental() {
        return selectedRelacionDocumental;
    }

    public void setSelectedRelacionDocumental(ArrayList<String> selectedRelacionDocumental) {
        this.selectedRelacionDocumental = selectedRelacionDocumental;
    }

    public void arrayEsparteDe(String espartede) {
        // TOMO#51;VOLUMEN#2
        // NUMERO#51,Alcance al 488
        espartede = espartede.replace("#", "\\$");// NUMERO,51,Alcance al 488
        espartede = espartede.replace(";", "\\$");
        String Array[] = espartede.split("\\$");
        int n = Array.length;
        if (n == 4) {
            indicador1 = Array[0].replace("\\", "");
            nindicador1 = String.valueOf(Array[1]);
            indicador2 = Array[2];
            nindicador2 = String.valueOf(Array[3]);

        } else if (n == 2) {
            indicador1 = Array[0].replace("\\", "");
            nindicador1 = String.valueOf(Array[1]);
        }
    }

    public void arrayFormatoExtension(String formatoExt) {
        String Array[] = formatoExt.split(" ");
        peso = Array[0];
        extension = Array[1];
    }

    public String getLblNropag_Duracion() {
        switch (perfil) {
            case "1":
                lblNropag_Duracion = "NÚMERO DE IMAGENES";
                break;
            case "6":
                lblNropag_Duracion = "DURACION";
                break;
            default:
                lblNropag_Duracion = "NÚMERO DE IMAGENES";
                break;
        }
        return lblNropag_Duracion;
    }

    public void setLblNropag_Duracion(String lblNropag_Duracion) {
        this.lblNropag_Duracion = lblNropag_Duracion;
    }

    public boolean isBoolTipo() {
        return boolTipo;
    }

    public void setBoolTipo(boolean boolTipo) {
        this.boolTipo = boolTipo;
    }

    //SERIE
    public List<Serie> getListSerie() {
        return listSerie;
    }

    public void setListSerie(List<Serie> listSerie) {
        this.listSerie = listSerie;
    }

    public List<Coleccion> getListColeccion() {
        return listColeccion;
    }

    public void setListColeccion(List<Coleccion> listColeccion) {
        this.listColeccion = listColeccion;
    }

    public void editarColeccion() {
        String colecciones = "";
        listColeccion = new ArrayList<>();
        for (int i = 0; i < selectedColeccion.size(); i++) {
            colecciones = colecciones + "," + selectedColeccion.get(i);
        }
        colecciones = colecciones.substring(1, colecciones.length());
        listColeccion = coleccionDao.obtenerSeleccion(colecciones);
        RequestContext.getCurrentInstance().update("dlgEditColeccion");
        RequestContext.getCurrentInstance().execute("PF('dlgEditColeccion').show()");
    }

    public void editarSeries() {
        String series = "";
        listSerie = new ArrayList<>();
        if (!selectedSerie.isEmpty()) {
            for (int i = 0; i < selectedSerie.size(); i++) {
                series = series + "," + selectedSerie.get(i);
            }
            series = series.substring(1, series.length());
            listSerie = serieDao.obtenerSeleccion(series);

            RequestContext.getCurrentInstance().update("dlgEditSerie");
            RequestContext.getCurrentInstance().execute("PF('dlgEditSerie').show()");
        } else {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage("gMensaje", new FacesMessage(FacesMessage.SEVERITY_INFO, "Error", "Debe seleccionar elementos de la lista primero"));
            RequestContext.getCurrentInstance().update("gMensaje");
        }

    }

    public void cellCancelSerie(RowEditEvent event) {
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Edición Cancelada", ((Serie) event.getObject()).getSERIE());
        FacesContext.getCurrentInstance().addMessage("gMensaje", msg);
        RequestContext.getCurrentInstance().update("gMensaje");
    }

    public void cellEditSerie(RowEditEvent event) {
        Serie serie = (Serie) event.getObject();
        int update = serieDao.actualizarEntidad(serie);
        if (update == 0) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Ocurrió un error.");
            FacesContext.getCurrentInstance().addMessage("gMensaje", msg);
            RequestContext.getCurrentInstance().update("gMensaje");
        } else {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcta!", "Edición correcta.");
            FacesContext.getCurrentInstance().addMessage("gMensaje", msg);
            RequestContext.getCurrentInstance().update("gMensaje");
            RequestContext.getCurrentInstance().update("frmAddDocumental:cboSerie");
        }
    }

    public void cellCancelColeccion(RowEditEvent event) {
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Edición Cancelada", ((Serie) event.getObject()).getSERIE());
        FacesContext.getCurrentInstance().addMessage("gMensaje", msg);
        RequestContext.getCurrentInstance().update("gMensaje");
    }

    public void cellEditColeccion(RowEditEvent event) {
        Coleccion coleccion = (Coleccion) event.getObject();
        int update = coleccionDao.actualizarEntidad(coleccion);
        if (update == 0) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Ocurrió un error.");
            FacesContext.getCurrentInstance().addMessage("gMensaje", msg);
            RequestContext.getCurrentInstance().update("gMensaje");
        } else {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcta!", "Edición correcta.");
            FacesContext.getCurrentInstance().addMessage("gMensaje", msg);
            RequestContext.getCurrentInstance().update("gMensaje");
            RequestContext.getCurrentInstance().update("frmAddDocumental:cboSerie");
        }
    }

    //editar coleccion
    //
    public List<PerfilDocumental> getListPerfilDocumental() {
        String idBiblioteca = String.valueOf(FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("personalidBibliotecaFuente"));
        List<PerfilDocumental> lista = pdDao.listaPerfilDocumental(idBiblioteca);
        return lista;
    }

    public void setListPerfilDocumental(List<PerfilDocumental> listPerfilDocumental) {
        this.listPerfilDocumental = listPerfilDocumental;
    }

    public String getPerfil_documental() {
        return perfil_documental;
    }

    public void setPerfil_documental(String perfil_documental) {
        this.perfil_documental = perfil_documental;
    }

    public String getNindicador1() {
        return nindicador1;
    }

    public void setNindicador1(String nindicador1) {
        this.nindicador1 = nindicador1;
    }

    public String getIndicador2() {
        return indicador2;
    }

    public void setIndicador2(String indicador2) {
        this.indicador2 = indicador2;
    }

    public String getNindicador2() {
        return nindicador2;
    }

    public void setNindicador2(String nindicador2) {
        this.nindicador2 = nindicador2;
    }

    public String getIndicador1() {
        return indicador1;
    }

    public void setIndicador1(String indicador1) {
        this.indicador1 = indicador1;
    }

    public ArrayList<String> getSelectedColeccion() {
        return selectedColeccion;
    }

    public void setSelectedColeccion(ArrayList<String> selectedColeccion) {
        this.selectedColeccion = selectedColeccion;
    }

    //TEMA
    public ArrayList<String> getSelectedTema() {
        return selectedTema;
    }

    public void setSelectedTema(ArrayList<String> selectedTema) {
        this.selectedTema = selectedTema;
    }

    public List<Tema> getListaTema() {
        return listaTema;
    }

    public void setListaTema(List<Tema> listaTema) {
        this.listaTema = listaTema;
    }

    public void editarTemas() {
        String s = "";
        listaTema = new ArrayList<>();
        if (!selectedTema.isEmpty()) {
            for (int i = 0; i < selectedTema.size(); i++) {
                if (i == 0) {
                    s = s + selectedTema.get(i);
                } else {
                    s = s + "," + selectedTema.get(i);
                }
            }
            listaTema = temaDao.obtenerSelecionTemas(s);
            RequestContext.getCurrentInstance().update("dlgEditTema");
            RequestContext.getCurrentInstance().execute("PF('dlgEditTema').show()");
        } else {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage("gMensaje", new FacesMessage(FacesMessage.SEVERITY_INFO, "Error", "Debe seleccionar elementos de la lista primero"));
            RequestContext.getCurrentInstance().update("gMensaje");
        }
    }

    public void cellCancelTema(RowEditEvent event) {
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Edición Cancelada", ((Tema) event.getObject()).getTEMA());
        FacesContext.getCurrentInstance().addMessage("gMensaje", msg);
        RequestContext.getCurrentInstance().update("gMensaje");
    }

    public void cellEditTema(RowEditEvent event) {
        Tema tema = (Tema) event.getObject();

        String idBiblioteca = FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("personalidBibliotecaFuente").toString();
        tema.setID_BIBLIOTECA_REGISTRO(idBiblioteca);
        int update = temaDao.actualizarEntidad(tema);
        if (update == 0) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Ocurrió un error.");
            FacesContext.getCurrentInstance().addMessage("gMensaje", msg);
            RequestContext.getCurrentInstance().update("gMensaje");
        } else {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto!", "Edición correcta.");
            FacesContext.getCurrentInstance().addMessage("gMensaje", msg);
            RequestContext.getCurrentInstance().update("gMensaje");
            RequestContext.getCurrentInstance().update("frmAddDocumental:cboTema");
        }
    }

    public ArrayList<String> getSelectedLenguaje() {
        return selectedLenguaje;
    }

    public void setSelectedLenguaje(ArrayList<String> selectedLenguaje) {
        this.selectedLenguaje = selectedLenguaje;
    }

    //metodos de perfil documental 
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
        String idBiblioteca = FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("personalidBibliotecaFuente").toString();

        //lstPerfilDocumentalDetalle = objPerfilDocumentalDetalleDao.listarPerfilDocumentalDetalle(perfil);
        lstPerfilDocumentalDetalle = objPerfilDocumentalDetalleDao.listarPerfilDocumentalDetalleXBiblioteca(perfil, idBiblioteca);
    }

    // METODOS DOCUMENTAL ******
    public Documental getDocumental() {
        return documental;
    }

    public void setDocumental(Documental documental) {
        this.documental = documental;
    }

    public String getClassValidacion() {
        return classValidacion;
    }

    public void setClassValidacion(String classValidacion) {
        this.classValidacion = classValidacion;
    }

//    public List<Documental> getLstDocumental() {
//        int tipoUsuario = Integer.parseInt(FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("personalTipoUsuario").toString());
//        int idUsuario = (Integer) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("personalIdUsuario");
//        int ID_BIBLIOTECA_FUENTE = Integer.parseInt(FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("personalidBibliotecaFuente").toString());
//
//        lstDocumental = documentalDao.listarDocumental(perfil, tipoUsuario, idUsuario, ID_BIBLIOTECA_FUENTE);
//        return lstDocumental;
//    }
//    public List<Documental> getLstDocumentalGn() {
//        int ID_BIBLIOTECA_FUENTE = Integer.parseInt(FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("personalidBibliotecaFuente").toString());
//        lstDocumental = documentalDao.listarDocumentalGn(perfil, ID_BIBLIOTECA_FUENTE);
//        return lstDocumental;
//    }
//    public List<Documental> getLstDocumentalFilter() {
//        return lstDocumentalFilter;
//    }
//    public void setLstDocumentalFilter(List<Documental> lstDocumentalFilter) {
//        this.lstDocumentalFilter = lstDocumentalFilter;
//    }
//    public void setLstDocumental(List<Documental> lstDocumental) {
//        this.lstDocumental = lstDocumental;
//    }
    public void validaTitulo() {
        int antIgual = 0;
        ArrayList<String> arrayErrores = new ArrayList<>();
        RequestContext execJs = RequestContext.getCurrentInstance();
        if (documental.getID_TIPO_OTRO() < 6) {
            if (documental.getTITULO().trim().equals("")) {
                execJs.execute("validarCampo('.txtTitulo')");
                arrayErrores.add("No ingresó un título.");
            } else {
                execJs.execute("$('.txtTitulo').style('border','','important');");
            }

            if (documental.getID_TIPO_OTRO() == -1) {
                execJs.execute("validarCampo('.cboTipoOtro')");
                arrayErrores.add("No ingresó seleccionó un tipo de código..");
            } else {
                execJs.execute("$('.cboTipoOtro').style('border','','important');");
            }

            if (documental.getOTRO().trim().equals("") && documental.getID_TIPO_OTRO() != 5) {
                execJs.execute("validarCampo('.txtOtro')");
                arrayErrores.add("No ingresó un código.");
            } else {
                execJs.execute("$('.txtOtro').style('border','','important');");
            }

            if (!ID_DOCUMENTAL.equals("")) {
                int ID_BIBLIOTECA_FUENTE = Integer.parseInt(FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("personalidBibliotecaFuente").toString());
                if (documental.getTITULO().equals(antTITULO) && documental.getOTRO().trim().equals(antOTRO) && documental.getID_TIPO_OTRO() == antID_TIPO_OTRO && documental.getID_BIBLIOTECA_FUENTE() == ID_BIBLIOTECA_FUENTE) {
                    antIgual = 1;
                } else {
                    antIgual = 0;
                }
            }

            if (arrayErrores.isEmpty()) {
                int ID_BIBLIOTECA_FUENTE = Integer.parseInt(FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("personalidBibliotecaFuente").toString());
                documental.setID_BIBLIOTECA_FUENTE(ID_BIBLIOTECA_FUENTE);
                int validar = 0;
                if (antIgual != 1) {
                    validar = documentalDao.validarTitulo(documental);
                }
                if (validar > 0) {
                    classValidacion = "btnValidar-red";
                    FacesContext.getCurrentInstance().addMessage("gMensaje", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "El títutlo ingresado ya existe."));
                } else {
                    classValidacion = "btnValidar-green";
                    FacesContext.getCurrentInstance().addMessage("gMensaje", new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto", "No hay coincidencias con el título ingresado, puede ser usado."));
                }
            } else {
                classValidacion = "btnValidar-red";
                String mensaje = "No se pudo insertar el documento.\nPor los motivos:<br/>";
                for (int i = 0; i < arrayErrores.size(); i++) {
                    String motivo = "-" + arrayErrores.get(i) + "<br/>";
                    mensaje = mensaje + motivo;
                }
                msjError("gMensaje", mensaje);
                RequestContext.getCurrentInstance().update("gMensaje");
            }
        }
    }

    public List<SelectItem> getCboDocumentalRelacion() {

        String idBiblioteca = FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("personalidBibliotecaFuente").toString();
        List<Object[]> lista = documentalDao.llenaComboDocumentalRelacion(idBiblioteca);
        cboDocumentalRelacion = new ArrayList<>();
        if (lista != null) {
            for (Object[] fila : lista) {
                if (fila[1].toString().length() > 100) {
                    cboDocumentalRelacion.add(new SelectItem(fila[0], fila[1].toString().substring(0, 96) + "..."));
                } else {
                    cboDocumentalRelacion.add(new SelectItem(fila[0], fila[1].toString()));
                }
            }
        }
        return cboDocumentalRelacion;
    }

    public void setCboDocumentalRelacion(List<SelectItem> cboDocumentalRelacion) {
        this.cboDocumentalRelacion = cboDocumentalRelacion;
    }

    public String getDisabled() {
        return disabled;
    }

    public void setDisabled(String disabled) {
        this.disabled = disabled;
    }

    public AuxContenido getAux() {
        if (aux.getIndice() != null) {
            if (aux.getIndice().equals("0")) {
                aux.setIndice("1");
            }
        }
        return aux;
    }

    public ArrayList<AuxContenido> getSelectedContenidos() {
        return selectedContenidos;
    }

    public void setSelectedContenidos(ArrayList<AuxContenido> selectedContenidos) {
        this.selectedContenidos = selectedContenidos;
    }

    public void setAux(AuxContenido aux) {
        this.aux = aux;
    }

    public ArrayList<AuxContenido> getListaTemas() {
        return listaTemas;
    }

    public void setListaTemas(ArrayList<AuxContenido> listaTemas) {
        this.listaTemas = listaTemas;
    }

    public void pasar() {
        /// se usa en tabal de contenidos

        selectedContenidos = new ArrayList<>();
        selectedContenidos.addAll(listaTemas);
        //update=":frmAddDocumental:listContenidos"
        // onclick="PF('dlgIndice').hide()" 

        RequestContext rc = RequestContext.getCurrentInstance();
        rc.execute("PF('dlgIndice').hide()");
        RequestContext.getCurrentInstance().update("frmAddDocumental:listContenidos");

    }

    public void limpiar() {
        listaTemas = selectedContenidos;
        RequestContext.getCurrentInstance().execute("PF('dlgIndice').hide()");
        RequestContext.getCurrentInstance().update("frmTablaContenido:tblContenido");
    }

    public void insertar() {
        if (aux.getTema().trim().length() < 1) {
            msjError("gMensaje", "El campo de tema es obligatorio");
        } else {
            Integer a = 0;

            if (!listaTemas.isEmpty()) {
                for (AuxContenido con : listaTemas) {
                    if (aux.getIndice().equals(con.getIndice())) {
                        msjError("gMensaje", "Este indice ya se añadio!!");
                        aux = new AuxContenido();
                        a = 1;
                        break;
                    }
                }
                if (a == 0) {
                    listaTemas.add(aux);
                    msjCorrecto("gMensaje", "Se inserto registro");
                    RequestContext.getCurrentInstance().update("frmTablaContenido");
                    aux = new AuxContenido();
                    contar();
                }
            } else {
                listaTemas.add(aux);
                msjCorrecto("gMensaje", "Se inserto registro");
                aux = new AuxContenido();
                contar();
            }
        }
    }

    //contador
    public void contar() {
        aux = new AuxContenido();
        int n = listaTemas.size();
        aux.setIndice(n + 1 + "");
    }

    //Eventos de Row Editor
    public void onRowEdit(RowEditEvent event) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage("gMensaje", new FacesMessage(FacesMessage.SEVERITY_INFO, "Editado!", ((AuxContenido) event.getObject()).getIndice() + ""));
        disabled = "true";
    }

    public void onRowCancel(RowEditEvent event) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage("gMensaje", new FacesMessage(FacesMessage.SEVERITY_INFO, "Cancelado", ((AuxContenido) event.getObject()).getIndice() + ""));
        disabled = "true";
    }

    public ArrayList<String> getSelectedSerie() {
        return selectedSerie;
    }

    public void setSelectedSerie(ArrayList<String> selectedSerie) {
        this.selectedSerie = selectedSerie;
    }

    public ArrayList<String> getSelectedAutor() {
        return selectedAutor;
    }

    public void setSelectedAutor(ArrayList<String> selectedAutor) {
        this.selectedAutor = selectedAutor;
    }

    public String getPeso() {
        return peso;
    }

    public void setPeso(String peso) {
        this.peso = peso;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public void procesarExtension() {
        String s = peso + " " + extension;
        documental.setFORMATO_EXTENSION(s);
    }

    public boolean isRindicador2() {
        return rindicador2;
    }

    public void setRindicador2(boolean rindicador2) {
        this.rindicador2 = rindicador2;
    }

    public boolean isRnindicador2() {
        return rnindicador2;
    }

    public void setRnindicador2(boolean rnindicador2) {
        this.rnindicador2 = rnindicador2;
    }

    public void changeCboIndicador() {
        
        switch (indicador1) {
            case "TOMO":
                indicador2 = "VOLUMEN";
                rindicador2 = true;
                rnindicador2 = true;
                break;
            case "VOLUMEN":
                indicador2 = "TOMO";
                rindicador2 = true;
                rnindicador2 = true;
                break;
            default:
                rindicador2 = false;
                rnindicador2 = false;
                break;
        }
        
    }

    public void procesarTieneParte() {
        String s;
        if (indicador1.equals("TOMO") || indicador1.equals("VOLUMEN")) {
            s = indicador1 + "#" + nindicador1 + ";" + indicador2 + "#" + nindicador2;
        } else {
            s = indicador1 + "#" + nindicador1;
        }
        documental.setES_PARTE_DE(s);
    }

    //TIPO ENCUADERNADO
    public void changeCboTipo() {
        if (documental.getID_TIPO() == 25 || documental.getID_TIPO() == 28 || documental.getID_TIPO() == 29 || documental.getID_TIPO() == 30 || documental.getID_TIPO() == 24) {
            RequestContext.getCurrentInstance().execute(" $('.rowPaginaInicio').css({'display':'table-row'}); ");
            RequestContext.getCurrentInstance().execute(" $('.rowPaginaFin').css({'display':'table-row'}); ");
        } else {
            RequestContext.getCurrentInstance().execute(" $('.rowPaginaInicio').css({'display':'none'}); ");
            RequestContext.getCurrentInstance().execute(" $('.rowPaginaFin').css({'display':'none'}); ");
        }
    }

    public String getDisabledcboFormatoMedio() {
        return disabledcboFormatoMedio;
    }

    public void setDisabledcboFormatoMedio(String disabledcboFormatoMedio) {
        this.disabledcboFormatoMedio = disabledcboFormatoMedio;
    }

    public FormatoMedio getFm() {
        return fm;
    }

    public void setFm(FormatoMedio fm) {
        this.fm = fm;
    }

    public void obtenerIdFormatoMedio() {
        int x = documental.getID_FORMATO_MEDIO();
        fm = daoFM.buscarEntidad(x);
        RequestContext rc = RequestContext.getCurrentInstance();
        rc.execute("PF('dlgEditFormatoMedio').show()");
        RequestContext.getCurrentInstance().update("dlgEditFormatoMedio");
    }

    public void cboFormatoChange() {
        int id = documental.getID_FORMATO_MEDIO();
        if (id != 0) {
            disabledcboFormatoMedio = "false";
            RequestContext.getCurrentInstance().update("frmAddDocumental:btnEditarFM");
        } else {
            disabledcboFormatoMedio = "true";
            RequestContext.getCurrentInstance().update("frmAddDocumental:btnEditarFM");
        }
    }

    public void actualizarFormatoMedio() {
        int ufm = daoFM.actualizarEntidad(fm);
        RequestContext rc = RequestContext.getCurrentInstance();
        rc.execute("PF('dlgEditFormatoMedio').hide()");
        if (ufm == 1) {
            msjCorrecto("gMensaje", "Se modifico el formato.");
            RequestContext.getCurrentInstance().update("frmAddDocumental:cboFormatoMedio");
        } else {
            msjError("gMensaje", "Error.");
        }
    }

    //*** METODOS CONTRIBUIDOR **
    public ArrayList<Contribuidor> getSelectedContribuidor() {
        return selectedContribuidor;
    }

    public void setSelectedContribuidor(ArrayList<Contribuidor> selectedContribuidor) {
        this.selectedContribuidor = selectedContribuidor;
    }

    public Contribuidor getObjContribuidor() {
        if (objContribuidor.getIndice() == 0) {
            objContribuidor.setIndice(1);
        }
        return objContribuidor;
    }

    public void setObjContribuidor(Contribuidor objContribuidor) {
        this.objContribuidor = objContribuidor;
    }

    public ArrayList<Contribuidor> getLstContribuidor() {
        return lstContribuidor;
    }

    public void setLstContribuidor(ArrayList<Contribuidor> lstContribuidor) {
        this.lstContribuidor = lstContribuidor;
    }

    public String getDisabledContribuidor() {
        return disabledContribuidor;
    }

    public void setDisabledContribuidor(String disabledContribuidor) {
        this.disabledContribuidor = disabledContribuidor;
    }

    public void pasarContribuidores() {
        //String ID_DOCUMENTAL="LI_00001";
        // Contribuidor cont;
//        if (lstContribuidor.isEmpty()) {
//         
//        } else {
//            for (Contribuidor c : lstContribuidor) {
////                cont = new Contribuidor();
////                cont.setIndice(c.getIndice());
////                cont.setID_DOCUMENTAL(ID_DOCUMENTAL);
////                cont.setCONTRIBUIDOR(c.getCONTRIBUIDOR());
//               // selectedContribuidor.add(cont);
//            }
//            //lstContribuidorAux= lstContribuidor;
//        }

        if (!lstContribuidor.isEmpty()) {
            ///-----------------------
            selectedContribuidor = new ArrayList<>();
            selectedContribuidor.addAll(lstContribuidor);

            RequestContext rc = RequestContext.getCurrentInstance();
            rc.execute("PF('dlgContribuidor').hide()");
            RequestContext.getCurrentInstance().update("frmAddDocumental:listContribuidores");

        }

    }

    public void pasarContribuidores2() {
        /// se usa en tabal de contenidos

        selectedContenidos = new ArrayList<>();
        selectedContenidos.addAll(listaTemas);
        //update=":frmAddDocumental:listContenidos"
        // onclick="PF('dlgIndice').hide()" 

        RequestContext rc = RequestContext.getCurrentInstance();
        rc.execute("PF('dlgIndice').hide()");
        RequestContext.getCurrentInstance().update("frmAddDocumental:listContenidos");

    }

    public void limpiarContribuidores() {
        lstContribuidor = selectedContribuidor;

        RequestContext.getCurrentInstance().execute("PF('dlgContribuidor').hide()");
        RequestContext.getCurrentInstance().update("frmContribuidor:tblContribuidor");

    }

    public void insertarContribuidores() {
        if (objContribuidor.getCONTRIBUIDOR().trim().length() < 1) {
            msjError("gMensaje", "El campo  es obligatorio");
        } else {
            Integer a = 0;
            if (!lstContribuidor.isEmpty()) {
                for (int x = 0; x < lstContribuidor.size(); x++) {
                    Contribuidor con = lstContribuidor.get(x);
                    if (con.getIndice() == objContribuidor.getIndice()) {
                        msjError("gMensaje", "Este indice ya se añadio!!");
                        objContribuidor = new Contribuidor();
                        a = 1;
                        break;
                    }
                }
                if (a == 0) {
                    lstContribuidor.add(objContribuidor);
                    msjCorrecto("gMensaje", "Se inserto registro");
                    objContribuidor = new Contribuidor();
                    contarContribuidores();
                }
            } else {
                lstContribuidor.add(objContribuidor);
                msjCorrecto("gMensaje", "Se inserto registro");
                objContribuidor = new Contribuidor();
                contarContribuidores();
            }
        }
    }

    //Eventos de Row Editor
    public void onRowEditContribuidores(RowEditEvent event) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage("gMensaje", new FacesMessage(FacesMessage.SEVERITY_INFO, "Editado!", ((Contribuidor) event.getObject()).getIndice() + ""));
        disabledContribuidor = "true";
    }

    public void onRowCancelContribuidores(RowEditEvent event) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage("gMensaje", new FacesMessage(FacesMessage.SEVERITY_INFO, "Cancelado", ((Contribuidor) event.getObject()).getIndice() + ""));
        disabledContribuidor = "true";
    }

    //contador
    public void contarContribuidores() {
        objContribuidor = new Contribuidor();
        int n = lstContribuidor.size();
        objContribuidor.setIndice(n + 1);
    }

    private void limpiarEntidadContribuidores(Contribuidor c) {
        c.setIndice(1);
        c.setCONTRIBUIDOR("");
    }

    //METODOS COBERTURA
    public Cobertura getCobertura() {
        return cobertura;
    }

    public void setCobertura(Cobertura cobertura) {
        this.cobertura = cobertura;
    }

    //GENERICOS
    //accesorios    
    private void msjError(String growl, String m) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(growl, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", m));
    }

    private void msjCorrecto(String growl, String m) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(growl, new FacesMessage(FacesMessage.SEVERITY_INFO, "Éxito!", m));
    }

    public void generaInsert() throws SQLException, IOException {
        int ID_USUARIO = Integer.parseInt(FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("personalIdUsuario").toString());
        String insert = tranDao.insertTransaccion(documental, selectedContribuidor, selectedTema, selectedLenguaje, selectedContenidos, selectedAutor, selectedColeccion, ID_USUARIO, selectedSerie, selectedRelacionDocumental);
        if (insert.equals("1")) {
            FacesContext.getCurrentInstance().addMessage("gMensaje", new FacesMessage(FacesMessage.SEVERITY_INFO, "Éxito!", "Documental insertado exitosamente."));
            FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
            FacesContext.getCurrentInstance().getExternalContext().redirect("/BibliotecaVirtual/Documental/Upd?ID_PERFIL_DOCUMENTAL=" + perfil + "&PERFIL_DOCUMENTAL=" + perfil_documental);
        } else {
            msjError("gMensaje", "No se pudo insertar el documento por: " + insert);
            RequestContext.getCurrentInstance().update("gMensaje");
        }
    }

    public void redireccionar(String perfil, String perfil_documental, String ID_DOCUMENTAL) {
        try {
            FacesContext.getCurrentInstance().addMessage("gMensaje", new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta!", "Modificando DOCUMENTAL: " + ID_DOCUMENTAL));
            FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
            FacesContext.getCurrentInstance().getExternalContext().redirect("/BibliotecaVirtual/Documental/Upd?ID_PERFIL_DOCUMENTAL=" + perfil + "&PERFIL_DOCUMENTAL=" + perfil_documental + "&ID_DOCUMENTAL=" + ID_DOCUMENTAL);
        } catch (IOException ex) {

        }

    }

    public void modificarDocumental() throws SQLException, IOException {
        int ID_USUARIO = Integer.parseInt(FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("personalIdUsuario").toString());
        String upd = tranDao.modificarTransaccion(documental, selectedContribuidor, selectedTema, selectedLenguaje, selectedContenidos, selectedAutor, selectedColeccion, ID_USUARIO, selectedSerie, selectedRelacionDocumental);
        if (upd.equals("1")) {
            FacesContext.getCurrentInstance().addMessage("gMensaje", new FacesMessage(FacesMessage.SEVERITY_INFO, "Éxito!", "Documental modificado exitosamente."));
            FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
            if (Control.equals("0")) {
                FacesContext.getCurrentInstance().getExternalContext().redirect("/BibliotecaVirtual/Documental/Upd?ID_PERFIL_DOCUMENTAL=" + perfil + "&PERFIL_DOCUMENTAL=" + perfil_documental);
            } else if (Control.equals("1")) {//si es de control que redireccione a la pagina de control
                PerfilDto pdto = objPerfilDocumentalDetalleDao.obtenerPerfilXidDocumental(ID_DOCUMENTAL);
                FacesContext.getCurrentInstance().getExternalContext().redirect("/BibliotecaVirtual/perfilDocumental/UpdCont?ID_PERFIL_DOCUMENTAL=" + pdto.getID_perfil() + "&PERFIL_DOCUMENTAL=" + pdto.getPerfil() + "&ID_DOCUMENTAL=" + "");
                Control = "0";
            }

            ID_DOCUMENTAL = "";
            documental = new Documental();
        } else {
            msjError("gMensaje", "No se pudo insertar el documento por: " + upd);
            RequestContext.getCurrentInstance().update("gMensaje");
        }
    }

    public ArrayList<String> listValidacion() {
        int idTipOtro = documental.getID_TIPO_OTRO();
        RequestContext execJs = RequestContext.getCurrentInstance();
        ArrayList<String> arrayErrores = new ArrayList<>();
        for (PerfilDocumentalDetalle pd : lstPerfilDocumentalDetalle) {
            //String cam=pd.getCAMPO();
            switch (pd.getCAMPO()) {
                case "TITULO":
                    if (idTipOtro != 5) {
                        if (pd.isVISTA() && pd.isREQUERIDO()) {
                            if (documental.getTITULO().equals("")) {
                                arrayErrores.add("No ingresó el título.");
                                execJs.execute("validarCampo('.txtTitulo')");
                            } else if (classValidacion == null) {
                                arrayErrores.add("No validó el título.");
                                execJs.execute("validarCampo('.btnValidarTitulo')");
                            } else if (classValidacion.equals("btnValidar-red")) {
                                arrayErrores.add("El títutlo ingresado está en uso.");
                                execJs.execute("validarCampo('.txtTitulo')");
                            } else {
                                execJs.execute("$('.txtTitulo').style('border','','important');");
                            }
                        }
                    }
                    break;
                case "TEMA":
                    if (pd.isVISTA() && pd.isREQUERIDO()) {
                        if (selectedTema.isEmpty()) {
                            arrayErrores.add("Debe seleccionar por lo menos un tema.");
                            execJs.execute("validarCampo('.cboTema')");
                        } else {
                            execJs.execute("$('.cboTema').style('border','','important');");
                        }
                    }
                    break;
                case "TITULO_ALTERNATIVO":
                    if (pd.isVISTA() && pd.isREQUERIDO()) {
                    }
                    break;
                case "DESCRIPCION":
                    if (pd.isVISTA() && pd.isREQUERIDO()) {
                    }
                    break;
                case "RESUMEN":
                    if (pd.isVISTA() && pd.isREQUERIDO()) {
                    }
                    break;
                case "ID_TIPO":
                    if (pd.isVISTA() && pd.isREQUERIDO()) {
                        if (-1 == documental.getID_TIPO()) {
                            arrayErrores.add("No ha seleccionado un tipo.");
                            execJs.execute("validarCampo('.cboTipo')");
                        } else if (documental.getID_TIPO() == 25) {
                                                       
                            if (0 ==documental.getPAGINA_FIN() ) {
                                arrayErrores.add("No ha ingresado una página de fin para el encuardernado.");
                                execJs.execute("validarCampo('.txtPaginaFin')");
                            }
                        } else {
                            execJs.execute("$('.cboTipo').style('border','','important');");
                        }
                    }
                    break;
                case "TIPO_OTRO":
                    if (idTipOtro != 5) {
                        if (pd.isVISTA() && pd.isREQUERIDO()) {
                            if (documental.getID_TIPO_OTRO() == -1) {
                                arrayErrores.add("No ha seleccionado un tipo de codigo");
                                execJs.execute("validarCampo('.cboTipoOtro')");
                            }
                        }
                    }

                    break;
                case "ID_BIBLIOTECA_FUENTE":
                    if (pd.isVISTA() && pd.isREQUERIDO()) {
                    }
                    break;
                case "ID_DOCUMENTAL_RELACION":
                    if (pd.isVISTA() && pd.isREQUERIDO()) {
                    }
                    break;
                case "TIENE_COMO_VERSION":
                    if (pd.isVISTA() && pd.isREQUERIDO()) {
                    }
                    break;
                case "ES_PARTE_DE":
                    if (pd.isVISTA() && pd.isREQUERIDO()) {

                    }
                    break;
                case "TIENE_PARTE_DE":
                    if (pd.isVISTA() && pd.isREQUERIDO()) {
                    }
                    break;
                case "ID_COBERTURA_ESPACIAL":
                    if (pd.isVISTA() && pd.isREQUERIDO()) {
                        if (documental.getID_COBERTURA_ESPACIAL() == -1) {
                            arrayErrores.add("No ha seleccionado una cobertura espacial.");
                            execJs.execute("validarCampo('.cboCobertura')");
                        } else {
                            execJs.execute("$('.cboCobertura').style('border','','important');");
                        }
                    }
                    break;
                case "COBERTURA_TEMPORAL":
                    if (pd.isVISTA() && pd.isREQUERIDO()) {
                        if (documental.getID_COBERTURA_TEMPORAL() == -1) {
                            arrayErrores.add("No ha seleccionado una cobertura temporal.");
                            execJs.execute("validarCampo('.cboCoberturaTemporal')");
                        } else {
                            execJs.execute("$('.cboCoberturaTemporal').style('border','','important');");
                        }
                    }
                    break;
                case "FECHA_DISPONIBLE":
                    if (pd.isVISTA() && pd.isREQUERIDO()) {
                    }
                    break;
                case "FECHA_PUBLICACION":
                    if (pd.isVISTA() && pd.isREQUERIDO()) {
                    }
                    break;
                case "FECHA_ACEPTACION":
                    if (pd.isVISTA() && pd.isREQUERIDO()) {
                    }
                    break;
                case "FECHA_COPYRIGHT":
                    if (pd.isVISTA() && pd.isREQUERIDO()) {
                    }
                    break;
                case "ID_FORMATO":
                    if (pd.isVISTA() && pd.isREQUERIDO()) {
                        if (documental.getID_FORMATO().equals("-1")) {
                            arrayErrores.add("No ha seleccionado un formato.");
                            execJs.execute("validarCampo('.cboFormato')");
                        } else {
                            execJs.execute("$('.cboFormato').style('border','','important');");
                        }
                    }
                    break;
                case "FORMATO_EXTENSION":
                    if (pd.isVISTA() && pd.isREQUERIDO()) {

                        if (documental.getFORMATO_EXTENSION().trim().length() == 0) {
                            arrayErrores.add("No ha seleccionado un formato extension");
                            execJs.execute("validarCampo('.txtFormatoExtension');validarCampo('.txtTamañoNumero');validarCampo('.cboExTemporal');");
                        } else {
                            execJs.execute("$('.txtFormatoExtension').style('border','','important');");
                        }
                    }
                    break;
                case "FORMATO_MEDIO":
                    if (pd.isVISTA() && pd.isREQUERIDO()) {
                    }
                    break;
                case "ID_EDITORIAL":
                    if (pd.isVISTA() && pd.isREQUERIDO()) {
                        if (documental.getID_EDITORIAL() == -1) {
                            arrayErrores.add("No ha seleccionado una editorial.");
                            execJs.execute("validarCampo('.cboEditorial')");
                        } else {
                            execJs.execute("$('.cboEditorial').style('border','','important');");
                        }
                    }
                    break;
                case "DERECHOS":
                    if (pd.isVISTA() && pd.isREQUERIDO()) {
                    }
                    break;
                case "DERECHOS_ACCESO":
                    if (pd.isVISTA() && pd.isREQUERIDO()) {
                    }
                    break;
                case "ID_AUDIENCIA":
                    if (pd.isVISTA() && pd.isREQUERIDO()) {
                    }
                    break;
                case "OTRO":
                    if (idTipOtro != 5) {
                        if (pd.isVISTA() && pd.isREQUERIDO()) {
                            if (documental.getOTRO().length() == 0) {

                                arrayErrores.add("Ingrese Codigo de barras u otro identificador unico.");
                                execJs.execute("validarCampo('.txtOtro')");
                            } else {
                                execJs.execute("$('.txtOtro').style('border','','important');");
                            }
                        }
                    }

                    break;
                case "URL":
                    if (pd.isVISTA() && pd.isREQUERIDO()) {
                    }
                    break;
                case "ISBN":
                    if (pd.isVISTA() && pd.isREQUERIDO()) {
                    }
                    break;
                case "CONTRIBUIDOR":
                    if (pd.isVISTA() && pd.isREQUERIDO()) {
                    }
                    break;
                case "TABLA_CONTENIDO":
                    if (pd.isVISTA() && pd.isREQUERIDO()) {
                    }
                    break;
                case "AUTOR":
                    if (pd.isVISTA() && pd.isREQUERIDO()) {
                        if (selectedAutor.isEmpty()) {
                            arrayErrores.add("Debe seleccionar por lo menos un autor.");
                            execJs.execute("validarCampo('.cboAutor')");
                        } else {
                            execJs.execute("$('.cboAutor').style('border','','important');");
                        }
                    }
                    break;
                case "COLECCION":
                    if (pd.isVISTA() && pd.isREQUERIDO()) {
                        if (selectedColeccion.isEmpty()) {
                            arrayErrores.add("Debe seleccionar por lo menos una colección.");
                            execJs.execute("validarCampo('.cboColeccion')");
                        } else {
                            execJs.execute("$('.cboColeccion').style('border','','important');");
                        }
                    }
                    break;
                case "LENGUAJE":
                    if (pd.isVISTA() && pd.isREQUERIDO()) {
                    }
                    break;
                case "NUMERO_PAGINAS":
                    if (pd.isVISTA() && pd.isREQUERIDO()) {
                    }
                    break;
                case "ALBUM":
                    if (pd.isVISTA() && pd.isREQUERIDO()) {
                        if (documental.getID_ALBUM() == -1) {
                            arrayErrores.add("No ha seleccionado un album.");
                            execJs.execute("validarCampo('.cboAlbum')");
                        } else {
                            execJs.execute("$('.cboAlbum').style('border','','important');");
                        }
                    }
                    break;
                default:
                    break;
            }
        }
        return arrayErrores;
    }

    public void insertDocumentalXperfil() throws SQLException, IOException {
        String idBib = String.valueOf(FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("personalidBibliotecaFuente"));
        cargoUpdate = 1;
        if (documental.getFORMATO_EXTENSION().equals("Pulse acá para cambiar el peso del archivo.") || documental.getFORMATO_EXTENSION() == null) {
            documental.setFORMATO_EXTENSION("");
        }
        if (documental.getES_PARTE_DE().equals("Pulse acá para cambiar el campo.") || documental.getES_PARTE_DE() == null) {

            documental.setES_PARTE_DE("");
        }
        if (documental.getID_BIBLIOTECA_DOCUMENTAL() == null) {

            documental.setID_BIBLIOTECA_DOCUMENTAL(idBib);
        }
        if (ID_DOCUMENTAL.equals("")) {
            String ID_DOCUMENTAL_INSERT = documentalDao.generarIdDocumental(documental.getID_TIPO());

            int ID_BIBLIOTECA_FUENTE = Integer.parseInt(FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("personalidBibliotecaFuente").toString());
            //seteo de campos
            documental.setID_DOCUMENTAL(ID_DOCUMENTAL_INSERT);
            documental.setID_BIBLIOTECA_FUENTE(ID_BIBLIOTECA_FUENTE);
            ArrayList<String> arrayErrores;
            //lstPerfilDocumentalDetalle
            arrayErrores = listValidacion();
            if (arrayErrores.isEmpty()) {
                generaInsert();
            } else {
                String mensaje = "No se pudo insertar el documento.\nPor los motivos:<br/>";
                for (int i = 0; i < arrayErrores.size(); i++) {
                    String motivo = "-" + arrayErrores.get(i) + "<br/>";
                    mensaje = mensaje + motivo;
                }
                msjError("gMensaje", mensaje);
                RequestContext.getCurrentInstance().update("gMensaje");
            }

        } else {
            // ID_DOCUMENTAL = documentalDao.generarIdDocumental(documental.getID_TIPO());
            int ID_BIBLIOTECA_FUENTE = Integer.parseInt(FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("personalidBibliotecaFuente").toString());
            //seteo de campos
            documental.setID_DOCUMENTAL(ID_DOCUMENTAL);
            documental.setID_BIBLIOTECA_FUENTE(ID_BIBLIOTECA_FUENTE);
            ArrayList<String> arrayErrores;
            //lstPerfilDocumentalDetalle
            arrayErrores = listValidacion();
            if (arrayErrores.isEmpty()) {
                //logica para actualizar
                modificarDocumental();
            } else {
                String mensaje = "No se pudo insertar el documento.\nPor los motivos:<br/>";
                for (int i = 0; i < arrayErrores.size(); i++) {
                    String motivo = "-" + arrayErrores.get(i) + "<br/>";
                    mensaje = mensaje + motivo;
                }
                msjError("gMensaje", mensaje);
                RequestContext.getCurrentInstance().update("gMensaje");
            }
        }
    }

    public void delDocumental(Documental documental) throws SQLException {
        int ID_USUARIO = Integer.parseInt(FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("personalIdUsuario").toString());
        String insert = tranDao.deleteTransaccion(documental.getID_DOCUMENTAL(), ID_USUARIO);
        if (insert.equals("1")) {
            FacesContext.getCurrentInstance().addMessage("gMensaje", new FacesMessage(FacesMessage.SEVERITY_INFO, "Éxito!", "Documental eliminado exitosamente."));
        } else {
            msjError("gMensaje", "No se pudo eliminar el documento por: " + insert);
            RequestContext.getCurrentInstance().update("gMensaje");
        }
    }

    //////////////////////////////////////////////////////////////////
    //////////////////////DETALLE DOCUMENTAL//////////////////////////
    //////////////////////////////////////////////////////////////////
    private Documental dgn = new Documental();
    private List<Autor> listdetautores = new ArrayList<>();
    private List<Coleccion> listdetcoleccion = new ArrayList<>();
    private List<Serie> listdetserie = new ArrayList<>();
    private List<Lenguaje> listdetlenguaje = new ArrayList<>();
    private List<Tema> listdettema = new ArrayList<>();
    private List<Contribuidor> listdetcontribuidor = new ArrayList<>();

    //GETTER Y SETTERS
    public Documental getDgn() {
        return dgn;
    }

    public void setDgn(Documental dgn) {
        this.dgn = dgn;
    }

    public List<Autor> getListdetautores() {
        return listdetautores;
    }

    public void setListdetautores(List<Autor> listdetautores) {
        this.listdetautores = listdetautores;
    }

    public List<Coleccion> getListdetcoleccion() {
        return listdetcoleccion;
    }

    public void setListdetcoleccion(List<Coleccion> listdetcoleccion) {
        this.listdetcoleccion = listdetcoleccion;
    }

    public List<Serie> getListdetserie() {
        return listdetserie;
    }

    public void setListdetserie(List<Serie> listdetserie) {
        this.listdetserie = listdetserie;
    }

    public List<Lenguaje> getListdetlenguaje() {
        return listdetlenguaje;
    }

    public void setListdetlenguaje(List<Lenguaje> listdetlenguaje) {
        this.listdetlenguaje = listdetlenguaje;
    }

    public List<Tema> getListdettema() {
        return listdettema;
    }

    public void setListdettema(List<Tema> listdettema) {
        this.listdettema = listdettema;
    }

    public List<Contribuidor> getListdetcontribuidor() {
        return listdetcontribuidor;
    }

    public void setListdetcontribuidor(List<Contribuidor> listdetcontribuidor) {
        this.listdetcontribuidor = listdetcontribuidor;
    }

    public void cargaData(String idDocumental) {
        dgn = documentalDao.listarDocumentalDetalle(idDocumental);
        listdetautores = autorDao.listarAutorIdDocumental(idDocumental);
        listdetcoleccion = coleccionDao.listarColeccionIdDocumental(idDocumental);
        listdetserie = serieDao.listarSerieIdDocumental(idDocumental);
        listdetlenguaje = lenguajeDao.listarSerieIdDocumental(idDocumental);
        listdettema = temaDao.listarSerieIdDocumental(idDocumental);
        listdetcontribuidor = conntribuidorDao.listarContribuidorIdDocumental(idDocumental);
        RequestContext.getCurrentInstance().update("dlgDocumental");
        RequestContext.getCurrentInstance().execute("PF('dlgDocumental').show();");
    }

    public void cargaDataControl(String idDocumental) {
        dgn = documentalDao.listarDocumentalDetalleControl(idDocumental);
        listdetautores = autorDao.listarAutorIdDocumental(idDocumental);
        listdetcoleccion = coleccionDao.listarColeccionIdDocumental(idDocumental);
        listdetserie = serieDao.listarSerieIdDocumental(idDocumental);
        listdetlenguaje = lenguajeDao.listarSerieIdDocumental(idDocumental);
        listdettema = temaDao.listarSerieIdDocumental(idDocumental);
        listdetcontribuidor = conntribuidorDao.listarContribuidorIdDocumental(idDocumental);

        RequestContext.getCurrentInstance().update("dlgDocumental");
        RequestContext.getCurrentInstance().execute("PF('dlgDocumental').show();");
    }

    public void redirectUrlControlPublicacion() throws IOException {

        ExternalContext ext = FacesContext.getCurrentInstance().getExternalContext();
        String rutaServidorArchivos = ext.getInitParameter("rutaServidorArchivos");
        Biblioteca bib = obtenerServidorBiblioteca();
        String url = bib.getURL() + bib.getDIRECTORIO() + documental.getURL();
        System.out.println("URL :" + url);
        boolean existe = documentalDao.validarFichero(rutaServidorArchivos + bib.getDIRECTORIO(), documental.getURL());
        if (!existe) {
            String mensaje = "El Fichero no existe en el Servidor de Archivos";
            msjError("gMensaje", mensaje);
            RequestContext.getCurrentInstance().update("gMensaje");
        } else {
            RequestContext.getCurrentInstance().execute("pasarPagina('" + url + "')");
            //RequestContext.getCurrentInstance().update("frmDlgControl:grdControl:link");
        }
    }

    public Biblioteca obtenerServidorBiblioteca() {
        int ID_BIBLIOTECA_FUENTE = Integer.parseInt(FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("personalidBibliotecaFuente").toString());
        String idBiblioteca = String.valueOf(ID_BIBLIOTECA_FUENTE);

        Biblioteca bib = bDao.oobtenerServidorBiblioteca(idBiblioteca);
        return bib;

    }

    private List<Documental> filtroDocumental;
    private LazyDataModel<Documental> lazymodel;
    private String palabra = "";
    private int numeroRegistros = 0;

    private int i = 0;
    private String palabraaux = "";

    @PostConstruct
    public void init() {
        lazymodel = new LazyDataModel<Documental>() {
            @Override
            public List<Documental> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {

                int tipoUsuario = Integer.parseInt(FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("personalTipoUsuario").toString());
                int idUsuario = (Integer) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("personalIdUsuario");
                int ID_BIBLIOTECA_FUENTE = Integer.parseInt(FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("personalidBibliotecaFuente").toString());

                Documental doc = documentalDao.listarDocumentalFiltroYConteo(perfil, tipoUsuario, idUsuario, ID_BIBLIOTECA_FUENTE, first, pageSize, palabra.trim());
                List<Documental> lstDocumental = doc.getLstDocumental();
                int countQuery = doc.getCountQuery();
                // List<Documental> lstDocumental = documentalDao.listarDocumentalFiltro(perfil, tipoUsuario, idUsuario, ID_BIBLIOTECA_FUENTE, first, pageSize, palabra.trim());

//                if (palabraaux.isEmpty()) {
//                    if (i == 0 && palabra.equals("")) {
//                        i = documentalDao.contarDocumentalFiltro(perfil, tipoUsuario, idUsuario, ID_BIBLIOTECA_FUENTE, first, pageSize, palabra.trim());
//                    } else if (!palabra.equals("")) {
//                        i = documentalDao.contarDocumentalFiltro(perfil, tipoUsuario, idUsuario, ID_BIBLIOTECA_FUENTE, first, pageSize, palabra.trim());
//                        palabraaux = palabra;
//                    }
//                }
//                if (!palabraaux.equals(palabra)) {
//                    i = documentalDao.contarDocumentalFiltro(perfil, tipoUsuario, idUsuario, ID_BIBLIOTECA_FUENTE, first, pageSize, palabra.trim());
//                    palabraaux = "";
//                }
                lazymodel.setRowCount(countQuery);
                setNumeroRegistros(countQuery);
                return lstDocumental;
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

    public LazyDataModel<Documental> getLazymodel() {
        return lazymodel;
    }

    public void setLazymodel(LazyDataModel<Documental> lazymodel) {
        this.lazymodel = lazymodel;
    }

    public void accionFiltrar() {
        RequestContext.getCurrentInstance().update("frmListDocumental:tblDocumental");
    }

    //FIN PAGINADOR
}
