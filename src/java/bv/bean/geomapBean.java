package bv.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import javax.faces.bean.ViewScoped;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;
import org.primefaces.event.map.GeocodeEvent;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.GeocodeResult;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;

@ManagedBean
@ViewScoped
public class geomapBean implements Serializable {

    private String centerGeoMap = "-12.087347, -77.004756";
    private MapModel emptyModel;
    private String title;
    private double lat;
    private double lng;
    //--------
    private ArrayList<String> ListaStrings = new ArrayList<>();

    @PostConstruct
    public void init() {
        emptyModel = new DefaultMapModel();
    }

    //--------------------------------
    public void onGeocode(GeocodeEvent event) {
        List<GeocodeResult> results = event.getResults();
        if (results != null && !results.isEmpty()) {
            LatLng center = results.get(0).getLatLng();
            centerGeoMap = center.getLat() + "," + center.getLng();
            for (int i = 0; i < results.size(); i++) {
                GeocodeResult result = results.get(i);
                emptyModel.addOverlay(new Marker(result.getLatLng(), result.getAddress()));
            }
        }
    }

    public ArrayList<String> getListaStrings() {
        return ListaStrings;
    }

    public void setListaStrings(ArrayList<String> ListaStrings) {
        this.ListaStrings = ListaStrings;
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
        Marker marker = new Marker(new LatLng(lat, lng), title);
        emptyModel.addOverlay(marker);

        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Marker Added", "Lat:" + lat + ", Lng:" + lng));
    }
    
     public void closeDialog() {
        ListaStrings.add(title);
        ListaStrings.add(String.valueOf(lat));
        ListaStrings.add(String.valueOf(lng));
        RequestContext.getCurrentInstance().update("frmAddBiblioteca:grdMap");
        RequestContext.getCurrentInstance().closeDialog(ListaStrings);
        
    }

}
