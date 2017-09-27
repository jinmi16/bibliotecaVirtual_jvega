package bv.bean;

import bv.dao.TemporalDao;
import bv.dao.impl.DaoFactory;
import static bv.util.Constantes.TEMPORAL;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;

/**
 *
 * @author Renato Vásquez Tejada - renatovt11@gmail.com
 */
@ManagedBean
@ViewScoped
public class coberturaTemporalBean {

    private List<SelectItem> cboCoberturaTemporal;
    private final TemporalDao coDao;

    public coberturaTemporalBean() {
        DaoFactory factory = DaoFactory.getInstance();
        coDao = factory.getTemporalDao(TEMPORAL);
    }

    public List<SelectItem> getCboCoberturaTemporal() {
        List<Object[]> lista = coDao.llenaComboCoberturaTemporal();
        cboCoberturaTemporal = new ArrayList<>();
        if (lista != null) {
            for (Object[] fila : lista) {
                cboCoberturaTemporal.add(new SelectItem(fila[0], fila[1].toString()));
            }
        }
        return cboCoberturaTemporal;
    }

    public void setCboCoberturaTemporal(List<SelectItem> cboCoberturaTemporal) {
        this.cboCoberturaTemporal = cboCoberturaTemporal;
    }
}
