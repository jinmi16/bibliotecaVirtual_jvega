package bv.bean;

import java.io.InputStream;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

@ManagedBean
@ViewScoped
public final class descargasBean {

    private StreamedContent archivo;
    
    public descargasBean() {
        cargaManual();
    }

    public void cargaManual(){        
        InputStream stream = FacesContext.getCurrentInstance().getExternalContext().getResourceAsStream("/resources/manual/FF.jpg");
        archivo = new DefaultStreamedContent(stream, "image/jpg", "ManualUsuarioBV.jpg");
    }
    
    public StreamedContent getArchivo() {
        return archivo;
    }
    
    
    
    
}
