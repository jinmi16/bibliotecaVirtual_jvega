package bv.bean;

import bv.dao.LenguajeDao;
import bv.dao.impl.DaoFactory;
import static bv.util.Constantes.LENGUAJE;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.model.SelectItem;
import vb.entidad.Lenguaje;

/**
 *
 * @author Renato Vásquez Tejada - renatovt11@gmail.com
 */
@ManagedBean
@RequestScoped
public class lenguajeBean {

    private Lenguaje lenguaje;
    private final LenguajeDao lenguajeDao;
    private ArrayList<Lenguaje> cboLenguaje;
    private List<SelectItem> selectedLenguaje;

    public lenguajeBean() {
        DaoFactory factory = DaoFactory.getInstance();
        lenguaje = new Lenguaje();
        lenguajeDao = factory.getLenguajeDao(LENGUAJE);
    }

    public Lenguaje getLenguaje() {
        return lenguaje;
    }

    public void setLenguaje(Lenguaje lenguaje) {
        this.lenguaje = lenguaje;
    }

    public ArrayList<Lenguaje> getCboLenguaje() {
        List<Object[]> lista = lenguajeDao.llenaComboLenguaje();
        cboLenguaje = new ArrayList<>();
        if (lista != null) {
            for (Object[] fila : lista) {
                Lenguaje len = new Lenguaje();
                len.setID_LENGUAJE(fila[0].toString());
                len.setLENGUAJE(fila[1].toString());
                cboLenguaje.add(len);
            }
        }
        return cboLenguaje;
    }

    public void setCboLenguaje(ArrayList<Lenguaje> cboLenguaje) {
        this.cboLenguaje = cboLenguaje;
    }

    public List<SelectItem> getSelectedLenguaje() {
        return selectedLenguaje;
    }

    public void setSelectedLenguaje(List<SelectItem> selectedLenguaje) {
        this.selectedLenguaje = selectedLenguaje;
    }
}
