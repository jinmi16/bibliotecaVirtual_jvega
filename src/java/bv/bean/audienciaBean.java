package bv.bean;

import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.model.SelectItem;
import bv.dao.AudienciaDao;
import bv.dao.impl.DaoFactory;
import static bv.util.Constantes.AUDIENCIA;
import vb.entidad.Audiencia;

/**
 *
 * @author Renato Vásquez Tejada - renatovt11@gmail.com
 */
@ManagedBean
@RequestScoped
public class audienciaBean {

    private Audiencia audiencia;
    private final AudienciaDao audDao;
    private List<SelectItem> cboAudiencia;
    
    public audienciaBean() {
        audiencia = new Audiencia();
        DaoFactory factory = DaoFactory.getInstance();
        audDao = factory.getAudienciaDao(AUDIENCIA);
    }

    public Audiencia getAudiencia() {
        return audiencia;
    }

    public void setAudiencia(Audiencia audiencia) {
        this.audiencia = audiencia;
    }

    public List<SelectItem> getCboAudiencia() {
        List<Object[]> lista = audDao.llenaComboAudiencia();
        cboAudiencia = new ArrayList<>();
        if(lista!=null){
            for (Object[] fila : lista) {
                cboAudiencia.add(new SelectItem(fila[0],fila[1].toString()));
            }
        }
        return cboAudiencia;
    }

    public void setCboAudiencia(List<SelectItem> cboAudiencia) {
        this.cboAudiencia = cboAudiencia;
    }
}
