package bv.bean;

import bv.dao.BibliotecaDao;
import bv.dao.PerfilDocumentalDetalleDao;
import bv.dao.TransaccionDao;
import bv.dao.impl.DaoFactory;
import static bv.util.Constantes.BIBLIOTECA;
import static bv.util.Constantes.PERFIL_DOCUMENTAL_DETALLE;
import static bv.util.Constantes.TRANSACCION;
import java.io.IOException;
import java.io.Serializable;
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
import org.primefaces.event.SelectEvent;
import org.primefaces.event.map.GeocodeEvent;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.GeocodeResult;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;
import vb.entidad.Biblioteca;
import vb.entidad.PerfilDocumental;
import vb.entidad.PerfilDocumentalDetalle;

/**
 *
 * @author Renato Vásquez Tejada - renatovt11@gmail.com
 */
@ViewScoped
@ManagedBean
public class bibliotecaBean implements Serializable {

    private int cargoUpdate;
    private Biblioteca biblioteca;
    private Biblioteca bibliotecaUpd;
    private final BibliotecaDao bibliotecaDao;
    private final TransaccionDao transaccionDao;
    private List<SelectItem> comboRegion;
    private List<SelectItem> comboProvincia;
    private List<SelectItem> comboDistrito;
    private List<Biblioteca> listBiblioteca;
    private List<Biblioteca> filterListBiblioteca;
    private List<SelectItem> updRegion;
    private List<SelectItem> updProvincia;
    private List<SelectItem> updDistrito;
    private boolean mostrarPestaniaListado = true;
    private String upd = "################";
    private String titulo = "..:: REGISTRO DE BIBLIOTECAS ::..";
    private String boton = "REGISTRAR BIBLIOTECA";
    ///--------------------------
    private String centerGeoMap = "-12.087347, -77.004756";
    private MapModel emptyModel;
    private String title;
    private String zoom = "5";
    private double lat;
    private double lng;
    int idUsuario;
    private final PerfilDocumentalDetalleDao objPerfilDocumentalDetalleDao;
    private List<PerfilDocumental> cboPerfiles;
    private ArrayList<String> selectedPerfiles = new ArrayList<>();
    private ArrayList<String> selectedPerfilesOld = new ArrayList<>();

    public String getZoom() {
        return zoom;
    }

    public void setZoom(String zoom) {
        this.zoom = zoom;
    }

    public bibliotecaBean() {
        DaoFactory factory = DaoFactory.getInstance();
        idUsuario = (Integer) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("personalIdUsuario");
        bibliotecaDao = factory.getBibliotecaDao(BIBLIOTECA);
        transaccionDao = factory.getTransaccionDao(TRANSACCION);
        biblioteca = new Biblioteca();
        bibliotecaUpd = new Biblioteca();
        emptyModel = new DefaultMapModel();
        objPerfilDocumentalDetalleDao = factory.getPerfilDocumentalDetalleDao(PERFIL_DOCUMENTAL_DETALLE);
        cargoUpdate = 0;
    }

    public void mostrarMarcador() {
        if (biblioteca.getLATITUD().trim().length() > 0 && biblioteca.getLONGITUD().trim().length() > 0) {
            lat = Double.parseDouble(biblioteca.getLATITUD());
            lng = Double.parseDouble(biblioteca.getLONGITUD());;
            LatLng coord1 = new LatLng(lat, lng);
            String titulo = biblioteca.getTITULO_MAPA();
            emptyModel.addOverlay(new Marker(coord1, titulo));
            zoom = "9";
        }
    }

    public List<SelectItem> getComboProvincia() {
        return comboProvincia;
    }

    public void setComboProvincia(List<SelectItem> comboProvincia) {
        this.comboProvincia = comboProvincia;
    }

    public String verGeomap() {
        String verGeomap = "false";

        if (upd.equals("1")) {
            verGeomap = "true";

        }
        return verGeomap;
    }

    public ArrayList<String> getSelectedPerfiles() {
        return selectedPerfiles;
    }

    public void setSelectedPerfiles(ArrayList<String> selectedPerfiles) {
        this.selectedPerfiles = selectedPerfiles;
    }

    public List<SelectItem> getComboRegion() {
        List<Object[]> lista = bibliotecaDao.obtenerRegiones();
        comboRegion = new ArrayList<>();
        if (lista != null) {
            for (Object[] fila : lista) {
                comboRegion.add(new SelectItem(fila[0], fila[1].toString()));
            }
        }
        return comboRegion;
    }

    public void setComboRegion(List<SelectItem> comboRegion) {
        this.comboRegion = comboRegion;
    }

    public List<PerfilDocumental> getCboPerfiles() {
        List<Object[]> lista = objPerfilDocumentalDetalleDao.obtenerPerfiles();
        cboPerfiles = new ArrayList<>();
        if (lista != null) {
            for (Object[] fila : lista) {
                PerfilDocumental p = new PerfilDocumental();
                p.setID_PERFIL_DOCUMENTAL(Integer.parseInt(fila[0].toString()));
                p.setPERFIL_DOCUMENTAL(fila[1].toString());
                cboPerfiles.add(p);
            }
        }

        return cboPerfiles;
    }

    public void setCboPerfiles(List<PerfilDocumental> cboPerfiles) {
        this.cboPerfiles = cboPerfiles;
    }

    public List<SelectItem> getComboDistrito() {
        return comboDistrito;
    }

    public void setComboDistrito(List<SelectItem> comboDistrito) {
        this.comboDistrito = comboDistrito;
    }

    public List<SelectItem> getUpdRegion() {
        List<Object[]> lista = bibliotecaDao.obtenerRegiones();
        updRegion = new ArrayList<>();
        if (lista != null) {
            for (Object[] fila : lista) {
                updRegion.add(new SelectItem(fila[0], fila[1].toString()));
            }
        }
        return updRegion;
    }

    public void setUpdRegion(List<SelectItem> updRegion) {
        this.updRegion = updRegion;
    }

    public List<SelectItem> getUpdProvincia() {
        updProvincia = new ArrayList<>();
        List<Object[]> dataProvincia = bibliotecaDao.obtenerProvincias(bibliotecaUpd.getID_REGION() + "");
        for (Object[] datProvincia : dataProvincia) {
            updProvincia.add(new SelectItem(datProvincia[1].toString(), datProvincia[2].toString()));
        }
        return updProvincia;
    }

    public void setUpdProvincia(List<SelectItem> updProvincia) {
        this.updProvincia = updProvincia;
    }

    public List<SelectItem> getUpdDistrito() {
        updDistrito = new ArrayList<>();
        List<Object[]> dataDistrito = bibliotecaDao.obtenerDistritos(bibliotecaUpd.getID_REGION() + "", bibliotecaUpd.getID_PROVINCIA() + "");
        for (Object[] datDistrito : dataDistrito) {
            updDistrito.add(new SelectItem(datDistrito[0].toString(), datDistrito[1].toString()));
        }
        return updDistrito;
    }

    public void setUpdDistrito(List<SelectItem> updDistrito) {
        this.updDistrito = updDistrito;
    }

    public List<Biblioteca> getListBiblioteca() {
        listBiblioteca = bibliotecaDao.obtenerEntidades();
        return listBiblioteca;
    }

    public void setListBiblioteca(List<Biblioteca> listBiblioteca) {
        this.listBiblioteca = listBiblioteca;
    }

    public List<Biblioteca> getFilterListBiblioteca() {
        return filterListBiblioteca;
    }

    public void setFilterListBiblioteca(List<Biblioteca> filterListBiblioteca) {
        this.filterListBiblioteca = filterListBiblioteca;
    }

    public void cambiaRegion() {
        comboProvincia = new ArrayList<>();
        List<Object[]> dataProvincia = bibliotecaDao.obtenerProvincias(biblioteca.getID_REGION() + "");
        for (Object[] datProvincia : dataProvincia) {
            comboProvincia.add(new SelectItem(datProvincia[1].toString(), datProvincia[2].toString()));
        }
        biblioteca.setID_PROVINCIA("-1");
        biblioteca.setID_DISTRITO("-1");
    }

    //METODO PARA ACTUALIZAR EL COMBO DE PROVINCIA DESDE EL DIALOG DE EDITAR BIBLIOTECA
    public void cambiaUpdRegion() {
        updProvincia = new ArrayList<>();
        List<Object[]> dataProvincia = bibliotecaDao.obtenerProvincias(bibliotecaUpd.getID_REGION() + "");
        for (Object[] datProvincia : dataProvincia) {
            updProvincia.add(new SelectItem(datProvincia[1].toString(), datProvincia[2].toString()));
        }
        bibliotecaUpd.setID_PROVINCIA("-1");
        bibliotecaUpd.setID_DISTRITO("-1");
    }

    public void cambiaProvincia() {
        comboDistrito = new ArrayList<>();
        List<Object[]> dataDistrito = bibliotecaDao.obtenerDistritos(biblioteca.getID_REGION() + "", biblioteca.getID_PROVINCIA() + "");
        for (Object[] datDistrito : dataDistrito) {
            comboDistrito.add(new SelectItem(datDistrito[0].toString(), datDistrito[1].toString()));
        }
        biblioteca.setID_DISTRITO("-1");
    }

    public void cambiaUpdProvincia() {
        updDistrito = new ArrayList<>();
        List<Object[]> dataDistrito = bibliotecaDao.obtenerDistritos(bibliotecaUpd.getID_REGION() + "", bibliotecaUpd.getID_PROVINCIA() + "");
        for (Object[] datDistrito : dataDistrito) {
            updDistrito.add(new SelectItem(datDistrito[0].toString(), datDistrito[1].toString()));
        }
        bibliotecaUpd.setID_DISTRITO("-1");
    }

    public void cambiaRegionB() {
        comboProvincia = new ArrayList<>();
        List<Object[]> dataProvincia = bibliotecaDao.obtenerProvincias(biblioteca.getID_REGION() + "");
        for (Object[] datProvincia : dataProvincia) {
            comboProvincia.add(new SelectItem(datProvincia[1].toString(), datProvincia[2].toString()));
        }
    }

    public void cambiaProvinciaB() {
        comboDistrito = new ArrayList<>();
        List<Object[]> dataDistrito = bibliotecaDao.obtenerDistritos(biblioteca.getID_REGION() + "", biblioteca.getID_PROVINCIA() + "");
        for (Object[] datDistrito : dataDistrito) {
            comboDistrito.add(new SelectItem(datDistrito[0].toString(), datDistrito[1].toString()));
        }
    }

    public Biblioteca getBiblioteca() {
        return biblioteca;
    }

    public void setBiblioteca(Biblioteca biblioteca) {
        this.biblioteca = biblioteca;
    }

    public void creaBiblioteca() throws IOException {
        if (upd.equals("1")) {

            biblioteca.setTITULO_MAPA(title);
            biblioteca.setLATITUD(String.valueOf(lat));
            biblioteca.setLONGITUD(String.valueOf(lng));
            biblioteca.setSelectedPerfiles(selectedPerfiles);
            biblioteca.setLstPerfilDocumentalDetalle(cargarPerDocDetalleForUpdate());
           // cargarPerDocDetalleForUpdate();

            //int dataUpdate = bibliotecaDao.actualizarEntidad(biblioteca, idUsuario);
            int dataUpdate = transaccionDao.updateBiblioteca(biblioteca, idUsuario);
            if (dataUpdate == 0) {
                FacesContext.getCurrentInstance().addMessage("gMensaje", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Ocurrió un error al editar."));
            } else {
                FacesContext.getCurrentInstance().addMessage("gMensaje", new FacesMessage(FacesMessage.SEVERITY_INFO, "Modificación Completada", "Biblioteca actualizada con éxito."));
            }

            limpiarCampos();
            upd = "x";
            biblioteca = new Biblioteca();
        } else if (upd.equals("################")) {
            biblioteca.setSelectedPerfiles(selectedPerfiles);
            biblioteca.setLstPerfilDocumentalDetalle(cargarPerDocDetalleForInsert());

            int dataInsert = transaccionDao.crearBiblioteca(biblioteca, idUsuario);
            if (dataInsert == 0) {
                FacesContext.getCurrentInstance().addMessage("gMensaje", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Ocurrió un error al ejecutar la sentencia."));
            } else {
                FacesContext.getCurrentInstance().addMessage("gMensaje", new FacesMessage(FacesMessage.SEVERITY_INFO, "Inserción Completada", "Biblioteca registrada con éxito."));
            }
            biblioteca = new Biblioteca();
            limpiarCampos();
        }
        RequestContext.getCurrentInstance().update("frmAddBiblioteca");
        RequestContext.getCurrentInstance().update("gMensaje");
    }

    public List<PerfilDocumentalDetalle> cargarPerDocDetalleForInsert() {

        List<PerfilDocumentalDetalle> lstPerfilDocumentalDetalle = new ArrayList<>();
        List<PerfilDocumentalDetalle> lstPerfilDocumentalDetalleAux = new ArrayList<>();

        for (String idPerfil : selectedPerfiles) {
            lstPerfilDocumentalDetalleAux.clear();
            lstPerfilDocumentalDetalleAux = objPerfilDocumentalDetalleDao.listarPerfilDocumentalDetalleIdPerfilIdBiblioteca(idPerfil);
            lstPerfilDocumentalDetalle.addAll(lstPerfilDocumentalDetalleAux);
        }
        return lstPerfilDocumentalDetalle;
    }

    public List<PerfilDocumentalDetalle> cargarPerDocDetalleForUpdate() {

        List<PerfilDocumentalDetalle> lstPerfilDocumentalDetalle = new ArrayList<>();
        List<PerfilDocumentalDetalle> lstPerfilDocumentalDetalleOld = biblioteca.getLstPerfilDocumentalDetalle();
        List<PerfilDocumentalDetalle> lstPerfilDocumentalDetalleAux = new ArrayList<>();
        List<PerfilDocumentalDetalle> lstPerfilDocumentalDetalleAuxOld = new ArrayList<>();
        //PerfilDocumentalDetalle pddAuxOld;
        ArrayList<PerfilDocumental> lstPerfilDocumentalOld = biblioteca.getLstPerfilDocumental();

        // generar la nueva lista de perfiles si aumento o disminuyo
        // generar la nueva lista de perfil detalle
        for (String idPerfil : selectedPerfiles) {

            if (selectedPerfilesOld.contains(idPerfil)) {
                lstPerfilDocumentalDetalleAuxOld.clear();
                for (PerfilDocumentalDetalle pdd : lstPerfilDocumentalDetalleOld) {
                    if (pdd.getID_PERFIL_DOCUMENTAL().equals(idPerfil)) {
                        lstPerfilDocumentalDetalleAuxOld.add(pdd);
                    }
                }
                 lstPerfilDocumentalDetalle.addAll(lstPerfilDocumentalDetalleAuxOld);

            }else{
             lstPerfilDocumentalDetalleAux.clear();
            lstPerfilDocumentalDetalleAux = objPerfilDocumentalDetalleDao.listarPerfilDocumentalDetalleIdPerfilIdBiblioteca(idPerfil);
            lstPerfilDocumentalDetalle.addAll(lstPerfilDocumentalDetalleAux);
            
            }

           
        }
        return lstPerfilDocumentalDetalle;
    }

    public void updBiblioteca() {
        int dataUpdate = bibliotecaDao.actualizarEntidad(bibliotecaUpd, idUsuario);
        RequestContext rc = RequestContext.getCurrentInstance();
        if (dataUpdate == 1) {
            rc.execute("PF('tblBiblioteca').clearFilters();");
            rc.execute("PF('dlbUpdBiblioteca').hide();");
            FacesContext.getCurrentInstance().addMessage("gMensaje", new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto", "Registro editado correctamente."));
        } else {
            rc.execute("PF('tblBiblioteca').clearFilters();");
            FacesContext.getCurrentInstance().addMessage("gMensaje", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No se pudo editar este registro."));
        }
    }

    public void delBiblioteca(Biblioteca biblioteca) {
        RequestContext rc = RequestContext.getCurrentInstance();
        if (biblioteca.getNRO_PERSONAL() == 0) {
            int dataDelete = bibliotecaDao.eliminarEntidad(biblioteca.getID_BIBLIOTECA_MEDIADOR(), idUsuario);
            if (dataDelete == 1) {
                rc.execute("PF('tblBiblioteca').clearFilters();");
                FacesContext.getCurrentInstance().addMessage("gMensaje", new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto", "Registro eliminado correctamente."));
            } else {
                rc.execute("PF('tblBiblioteca').clearFilters();");
                FacesContext.getCurrentInstance().addMessage("gMensaje", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No se pudo eliminar este registro."));
            }
        } else {
            rc.execute("PF('tblBiblioteca').clearFilters();");
            FacesContext.getCurrentInstance().addMessage("gMensaje", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Esta biblioteca tiene personal asignado (" + biblioteca.getNRO_PERSONAL() + "), no puede ser eliminada."));
        }
    }

    public void limpiarCampos() {
        biblioteca.setID_INSTITUCION_MEDIADOR(Integer.parseInt("-1"));
        biblioteca.setCODIGO_SNB("");
        biblioteca.setNOMBRE_BIBLIOTECA("");
        biblioteca.setDIRECCION("");
        biblioteca.setEMAIL("");
        biblioteca.setDISTRITO("-1");
        biblioteca.setPROVINCIA("-1");
        biblioteca.setREGION("-1");
        biblioteca.setURL("");
        biblioteca.setDIRECTORIO("");
        title = "";
        lat = 0;
        lng = 0;
        selectedPerfiles.clear();
    }

    public Biblioteca getBibliotecaUpd() {
        return bibliotecaUpd;
    }

    public void setBibliotecaUpd(Biblioteca bibliotecaUpd) {
        this.bibliotecaUpd = bibliotecaUpd;
    }

    public void resetData() {
        listBiblioteca = bibliotecaDao.obtenerEntidades();
    }

    public String getUpd() {
        return upd;
    }

    public void setUpd(String upd) {
        this.upd = upd;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getBoton() {
        return boton;
    }

    public void setBoton(String boton) {
        this.boton = boton;
    }

    public boolean isMostrarPestaniaListado() {
        if (upd.equals("1")) {
            mostrarPestaniaListado = false;
            titulo = "..:: MODIFICACIÓN DE BIBLIOTECA ::..";
            boton = "MODIFICAR BIBLIOTECA";
        } else if (upd.equals("################")) {
            mostrarPestaniaListado = true;
        } else {
            mostrarPestaniaListado = false;
        }
        return mostrarPestaniaListado;
    }

    public void setMostrarPestaniaListado(boolean mostrarPestaniaListado) {
        this.mostrarPestaniaListado = mostrarPestaniaListado;
    }

    public void listarBiblioteca() {
        String idBiblioteca = FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("personalidBibliotecaFuente").toString();
        if (upd.equals("1") && cargoUpdate == 0) {
            biblioteca = bibliotecaDao.cargarEntidad(idBiblioteca);
            PerfilDocumental pd = new PerfilDocumental();
            pd.setID_BIBLIOTECA(Integer.parseInt(idBiblioteca));
            biblioteca.setLstPerfilDocumental(bibliotecaDao.lstPerfilBiblioteca(pd));
            biblioteca.setLstPerfilDocumentalDetalle(bibliotecaDao.lstPerfilDocumentalDetalle(pd));
            for (PerfilDocumental p : biblioteca.getLstPerfilDocumental()) {
                selectedPerfiles.add(String.valueOf(p.getID_PERFIL()));
                selectedPerfilesOld.add(String.valueOf(p.getID_PERFIL()));
            }
            title = biblioteca.getTITULO_MAPA();
            if (biblioteca.getLATITUD().trim().length() > 0 && biblioteca.getLONGITUD().trim().length() > 0) {
                lat = Double.parseDouble(biblioteca.getLATITUD());
                lng = Double.parseDouble(biblioteca.getLONGITUD());
            }
            cambiaRegionB();
            cambiaProvinciaB();
            mostrarMarcador();
            cargoUpdate++;
        }

    }

    public void abrirDlgMapa() {
        Map<String, Object> opciones = new HashMap<>();
        opciones.put("modal", true);
        opciones.put("width", "40%");
        opciones.put("height", "650");
        opciones.put("closable", true);
        opciones.put("headerElement", "customheader");
        RequestContext.getCurrentInstance().openDialog("geomap", opciones, null);

    }

    public void onReturnFrom(SelectEvent event) {

        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Data Returned", event.getObject().toString()));
        ArrayList<String> listaIn = (ArrayList<String>) event.getObject();

        biblioteca.setTITULO_MAPA(listaIn.get(0));
        biblioteca.setLATITUD(listaIn.get(1));
        biblioteca.setLONGITUD(listaIn.get(2));
    }

    ////***************
    public void onGeocode(GeocodeEvent event) {
        List<GeocodeResult> results = event.getResults();
        if (results != null && !results.isEmpty()) {
            LatLng center = results.get(0).getLatLng();
            centerGeoMap = center.getLat() + "," + center.getLng();
            for (int i = 0; i < results.size(); i++) {
                GeocodeResult result = results.get(i);
                emptyModel.addOverlay(new Marker(result.getLatLng(), result.getAddress()));
            }
            zoom = "8";
            RequestContext.getCurrentInstance().update("map");

        }
    }

    public String getCenterGeoMap() {
        return centerGeoMap;
    }

    //--------------------------------   
    public MapModel getEmptyModel() {
        return emptyModel;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public void addMarker() {
        biblioteca.setTITULO_MAPA(title);
        biblioteca.setLATITUD(String.valueOf(lat));
        biblioteca.setLONGITUD(String.valueOf(lng));

        Marker marker = new Marker(new LatLng(lat, lng), title);
        emptyModel.addOverlay(marker);
        zoom = "5";

        RequestContext.getCurrentInstance().update("frmAddBiblioteca:grdMap");

    }
///-----------------------------------------------------------------------------------------

    public void pasar() {
        biblioteca.setTITULO_MAPA(title);
        biblioteca.setLATITUD(String.valueOf(lat));
        biblioteca.setLONGITUD(String.valueOf(lng));
        RequestContext.getCurrentInstance().update("frmAddBiblioteca:grdMap");

    }

    public void abrirDlgMap() {

        RequestContext.getCurrentInstance().openDialog("dlgMap", null, null);

    }

    public void closeDialog() {
        ArrayList<String> ListaStrings = new ArrayList<>();
        ListaStrings.add(title);
        ListaStrings.add(String.valueOf(lat));
        ListaStrings.add(String.valueOf(lng));
        RequestContext.getCurrentInstance().closeDialog(ListaStrings);

    }

    public void onReturnDialog(SelectEvent event) {

        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Data Returned", event.getObject().toString()));
        ArrayList<String> listaIn = (ArrayList<String>) event.getObject();

        biblioteca.setTITULO_MAPA(listaIn.get(0));
        biblioteca.setLATITUD(listaIn.get(1));
        biblioteca.setLONGITUD(listaIn.get(2));
        RequestContext.getCurrentInstance().update("frmAddBiblioteca:grdMap:txtLatitud");
        RequestContext.getCurrentInstance().update("frmAddBiblioteca:grdMap:txtLongitud");
        RequestContext.getCurrentInstance().update("frmAddBiblioteca:txtLatitud");

    }

}
