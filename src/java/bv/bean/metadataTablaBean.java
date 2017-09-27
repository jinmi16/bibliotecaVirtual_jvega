package bv.bean;

import bv.dao.MetadataTablaDao;
import bv.dao.impl.DaoFactory;
import static bv.util.Constantes.METADATA_TABLA;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import vb.entidad.MetadataTabla;

/**
 *
 * @author virtual
 */
@ManagedBean
@ViewScoped
public class metadataTablaBean {

    private List<MetadataTabla> lstmetadatatabla;
    private final MetadataTablaDao metadataTablaDao;
    private MetadataTabla metadatatabla;
    private boolean activarayuda;
    private int longitud;
    private String classActivarAyuda;
    private String descripcion = "";

    public metadataTablaBean() {
        DaoFactory factory = DaoFactory.getInstance();
        metadataTablaDao = factory.getMetadataTablaDao(METADATA_TABLA);
        activarayuda = true;
        lstmetadatatabla = metadataTablaDao.listarMetadataTabla();
    }

    public String getClassActivarAyuda() {
        return classActivarAyuda;
    }

    public void setClassActivarAyuda(String classActivarAyuda) {
        this.classActivarAyuda = classActivarAyuda;
    }

    public int getLongitud() {
        return longitud;
    }

    public void setLongitud(int longitud) {
        this.longitud = longitud;
    }

    public MetadataTabla getMetadatatabla() {
        return metadatatabla;
    }

    public void setMetadatatabla(MetadataTabla metadatatabla) {
        this.metadatatabla = metadatatabla;
    }

    public boolean isActivarayuda() {
        return activarayuda;
    }

    public void setActivarayuda(boolean activarayuda) {

        this.activarayuda = activarayuda;
    }

    public void cambiarEstadoActivar() {
        if (activarayuda) {
            classActivarAyuda = "btnAyuda-off";
            activarayuda = false;
        } else {
            classActivarAyuda = "btnAyuda-on";
            activarayuda = true;
        }
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<MetadataTabla> getLstmetadatatabla() {

        return lstmetadatatabla;
    }

    public void setLstmetadatatabla(List<MetadataTabla> lstmetadatatabla) {
        this.lstmetadatatabla = lstmetadatatabla;
    }

    public String obtenerAyuda(String campo) {
        for (MetadataTabla met : lstmetadatatabla) {
            if (met.getCAMPO().equals(campo)) {
                metadatatabla = met;
                String s = metadatatabla.getDESCRIPCION();
                descripcion = cadenaSL(s);
            }
        }
        return descripcion;
    }

    public int obtenerLongitud(String campo) {
        for (MetadataTabla met : lstmetadatatabla) {
            if (met.getCAMPO().equals(campo)) {
                metadatatabla = met;
                longitud = metadatatabla.getLONGITUD();
            }
        }
        return longitud;
    }

    public String cadenaSL(String texto) {
        String cadenaSL = "";
        String cadenaRes = texto;
        int ltRes = texto.length();
        int longLinea = 40;
        if (texto.length() > longLinea) {
            while (longLinea <= ltRes) {
                cadenaSL = cadenaSL + cadenaRes.substring(0, longLinea) + "<br/>";
                cadenaRes = cadenaRes.substring(longLinea);
                ltRes = cadenaRes.length();
                if (longLinea > ltRes) {
                    cadenaSL = cadenaSL + cadenaRes;
                }
            }
        } else {
            cadenaSL = texto;
        }
        return cadenaSL;
    }

}
