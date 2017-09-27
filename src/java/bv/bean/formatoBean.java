package bv.bean;

import bv.dao.FormatoDao;
import bv.dao.impl.DaoFactory;
import static bv.util.Constantes.FORMATO;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import vb.entidad.Formato;

/**
 *
 * @author virtual
 */
@ManagedBean
@ViewScoped
public class formatoBean {

    private final FormatoDao objFormatoDao;
    private List<Formato> cboFormato;

    public formatoBean() {
        DaoFactory factory = DaoFactory.getInstance();
        objFormatoDao = factory.getFormatoDao(FORMATO);
    }

    public List<Formato> getCboFormato() {
        List<Object[]> lista = objFormatoDao.llenaComboFormato();
        cboFormato = new ArrayList<>();
        if (lista != null) {
            for (Object[] fila : lista) {
                Formato formato = new Formato();
                formato.setID_FORMATO(fila[0].toString());
                formato.setTIPO(fila[1].toString());
                cboFormato.add(formato);
            }
        }
        return cboFormato;
    }

    public void setCboFormato(List<Formato> cboFormato) {
        this.cboFormato = cboFormato;
    }

}
