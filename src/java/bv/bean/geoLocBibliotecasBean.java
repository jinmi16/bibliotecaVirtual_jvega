package bv.bean;

import bv.dao.BibliotecaDao;
import bv.dao.impl.DaoFactory;
import static bv.util.Constantes.BIBLIOTECA;
import java.io.Serializable;
import java.util.ArrayList;
import javax.faces.bean.ViewScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;
import vb.entidad.Biblioteca;

@ManagedBean
@ViewScoped
public class geoLocBibliotecasBean implements Serializable {

    BibliotecaDao bibliotecaDao;
    private MapModel simpleModel;
    String idBiblioteca;
    ArrayList<Biblioteca> lstMapBibliotecas = new ArrayList<>();

    public geoLocBibliotecasBean() {
        DaoFactory factory = DaoFactory.getInstance();
        bibliotecaDao = factory.getBibliotecaDao(BIBLIOTECA);
        simpleModel = new DefaultMapModel();
        idBiblioteca = FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("personalidBibliotecaFuente").toString();
        lstMapBibliotecas();
    }

    public void lstMapBibliotecas() {
        lstMapBibliotecas = bibliotecaDao.lstMapeoBibliotecas(idBiblioteca);
        for (Biblioteca bib : lstMapBibliotecas) {
            double lat = Double.parseDouble(bib.getLATITUD());
            double lng = Double.parseDouble(bib.getLONGITUD());
            LatLng coord = new LatLng(lat, lng);
            simpleModel.addOverlay(new Marker(coord, bib.getTITULO_MAPA()));
        }
    }

    public ArrayList<Biblioteca> getLstMapBibliotecas() {
        return lstMapBibliotecas;
    }

    public void setLstMapBibliotecas(ArrayList<Biblioteca> lstMapBibliotecas) {
        this.lstMapBibliotecas = lstMapBibliotecas;
    }

    public MapModel getSimpleModel() {
        return simpleModel;
    }

    public void setSimpleModel(MapModel simpleModel) {
        this.simpleModel = simpleModel;
    }

}
