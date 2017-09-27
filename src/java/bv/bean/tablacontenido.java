package bv.bean;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.RowEditEvent;
import org.primefaces.model.UploadedFile;
import vb.entidad.AuxContenido;

@ManagedBean(name = "tcBean")
@ViewScoped
public class tablacontenido {

    private AuxContenido aux = new AuxContenido();

    private ArrayList<AuxContenido> listaTemas = new ArrayList<>();
    private ArrayList<AuxContenido> dctemas = new ArrayList<>();
    private String disabled = "true";
    //*******************************metodos del Importara excel
    private Workbook wb;
    private ArrayList<Object[]> lista = new ArrayList<>();
    private UploadedFile file;
    private AuxContenido tc;
    private ArrayList<AuxContenido> lstTc;
    private ArrayList<String> ListaStrings = new ArrayList<>();

    public ArrayList<AuxContenido> getLstTc() {
        return lstTc;
    }

    public void setLstTc(ArrayList<AuxContenido> lstTc) {
        this.lstTc = lstTc;
    }

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

    public ArrayList<String> getListaStrings() {
        return ListaStrings;
    }

    public void setListaStrings(ArrayList<String> ListaStrings) {
        this.ListaStrings = ListaStrings;
    }

    public void closeDialog() {
        
      
        RequestContext.getCurrentInstance().closeDialog(ListaStrings);
        
    }

    public void handleFileUpload(FileUploadEvent event) {
        if (event.getFile() != null) {
            file = event.getFile();
            String msg = Importar();
            FacesMessage message = new FacesMessage("Correcto", file.getFileName());
            FacesContext.getCurrentInstance().addMessage("msgExcel", message);
            RequestContext.getCurrentInstance().update("msgExcel");
            RequestContext.getCurrentInstance().update("frmTablaContenido:tablaContenido");
            
        }
    }

    public String Importar() {
        lstTc = new ArrayList<>();
        lista = new ArrayList<>();

        String respuesta = "No se pudo realizar la importación.";
        try {

            wb = WorkbookFactory.create(file.getInputstream());

            Sheet hoja = wb.getSheetAt(0);
            Iterator filaIterator = hoja.rowIterator();
            int indiceFila = -1;
            while (filaIterator.hasNext()) {
                indiceFila++;
                Row fila = (Row) filaIterator.next();
                Iterator columnaIterator = fila.cellIterator();
                Object[] listaColumna = new Object[1];
                int indiceColumna = -1;
                while (columnaIterator.hasNext()) {
                    indiceColumna++;
                    Cell celda = (Cell) columnaIterator.next();

                    if (celda != null) {
                        switch (celda.getCellType()) {
                            case Cell.CELL_TYPE_NUMERIC:
                                listaColumna[indiceColumna] = (int) Math.round(celda.getNumericCellValue());
                                break;
                            case Cell.CELL_TYPE_STRING:
                                listaColumna[indiceColumna] = celda.getStringCellValue();
                                break;
                            case Cell.CELL_TYPE_BOOLEAN:
                                listaColumna[indiceColumna] = celda.getBooleanCellValue();
                                break;
                            default:
                                listaColumna[indiceColumna] = celda.getDateCellValue();
                                break;
                        }
                        lista.add(listaColumna);

                    }
                }
            }
            int cont = 1;
            for (Object[] ob : lista) {
                tc = new AuxContenido();
                ListaStrings.add(ob[0].toString());
                tc.setIndice(String.valueOf(cont));
                tc.setTema(ob[0].toString());

                lstTc.add(tc);
                cont++;
            }
            respuesta = "Importación exitosa";
        } catch (IOException | InvalidFormatException | EncryptedDocumentException e) {
            System.out.println(e.getMessage()+" method : Importar()");
        }
        return respuesta;
    }

    //------**********************************************
    public String getDisabled() {
        return disabled;
    }

    public void setDisabled(String disabled) {
        this.disabled = disabled;
    }

    public tablacontenido() {
    }

    public AuxContenido getAux() {
        if (aux.getIndice().equals("0")) {
            aux.setIndice("1");
        }
        return aux;
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

    public void limpiar() {
        listaTemas = new ArrayList<>();
    }

    public void insertar() {
        if (aux.getTema().trim().length() < 1) {
            msjError("gMensaje", "El campo de tema es obligatorio");
        } else {
            Integer a = 0;
            if (!listaTemas.isEmpty()) {
                for (int x = 0; x < listaTemas.size(); x++) {
                    AuxContenido con = listaTemas.get(x);
                    if (con.getIndice() == aux.getIndice()) {
                        msjError("gMensaje", "Este indice ya se añadio!!");
                        aux = new AuxContenido();
                        a = 1;
                        break;
                    }
                }
                if (a == 0) {
                    listaTemas.add(aux);
                    msjCorrecto("gMensaje", "Se inserto registro");
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

    public void sacar(AuxContenido x) {
        listaTemas.remove(Integer.parseInt(x.getIndice()) - 1);
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

    //contador
    public void contar() {
        aux = new AuxContenido();
        int n = listaTemas.size();
        aux.setIndice(n + 1 + "");
    }

    //accesorios
    private void msjError(String m) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", m));
    }

    private void msjError(String growl, String m) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(growl, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", m));
    }

    private void msjCorrecto(String m) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Éxito!", m));
    }

    private void msjCorrecto(String growl, String m) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(growl, new FacesMessage(FacesMessage.SEVERITY_INFO, "Éxito!", m));
    }

    private void limpiarEntidad(AuxContenido t) {
        t.setIndice("1");
        t.setTema("");
    }

}
