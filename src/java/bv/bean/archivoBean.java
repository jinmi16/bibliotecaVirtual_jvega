package bv.bean;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import javax.faces.application.FacesMessage;
import javax.faces.view.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;
import org.apache.commons.io.FilenameUtils;

@Named(value = "archivoBean")
@ViewScoped
public class archivoBean implements Serializable {

    private final String rutaServidorArchivos;

    private ArrayList<UploadedFile> lstImagenes = new ArrayList<UploadedFile>();

    public archivoBean() {
        ExternalContext ext = FacesContext.getCurrentInstance().getExternalContext();
        rutaServidorArchivos = ext.getInitParameter("rutaServidorArchivos");
    }

    public ArrayList<UploadedFile> getLstImagenes() {
        return lstImagenes;
    }

    public void setLstImagenes(ArrayList<UploadedFile> lstImagenes) {
        this.lstImagenes = lstImagenes;
    }

    public void handleFileUpload(FileUploadEvent event) throws IOException {
        UploadedFile archivo = (UploadedFile) event.getFile();
        Integer a = 0;
        if (!lstImagenes.isEmpty()) {
            for (UploadedFile lst : lstImagenes) {
                if ((archivo.getFileName()).equals(lst.getFileName())) {
                    FacesContext.getCurrentInstance().addMessage("gmsj", new FacesMessage(FacesMessage.SEVERITY_INFO, "Error", "Este archivo ya se añadio"));
                    RequestContext.getCurrentInstance().update("frmNovedad");
                    a = 1;
                    break;
                }
            }
            if (a == 0) {
                lstImagenes.add(archivo);
                grabrArchivoTemporal(archivo.getFileName(), archivo.getContents(), rutaServidorArchivos);
                FacesContext.getCurrentInstance().addMessage("gmsj", new FacesMessage(FacesMessage.SEVERITY_INFO, "Exito", "Imagen se agrego a la lista"));
                RequestContext.getCurrentInstance().update("frmNovedad");
            }
        } else {
            lstImagenes.add(archivo);
            grabrArchivoTemporal(archivo.getFileName(), archivo.getContents(), rutaServidorArchivos);
            FacesContext.getCurrentInstance().addMessage("gmsj", new FacesMessage(FacesMessage.SEVERITY_INFO, "Exito", "Imagen se agrego a la lista"));
            RequestContext.getCurrentInstance().update("frmNovedad");
        }
    }

    //con comons fileupload
    private void grabrArchivoTemporal(String nombreArchivo, byte[] contenido, String dirFinal) {
        try {
            String filename = FilenameUtils.getName(nombreArchivo);
            OutputStream out = new FileOutputStream(new File(dirFinal + filename));
            try (BufferedOutputStream stream = new BufferedOutputStream(out)) {
                stream.write(contenido);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage()+" method : grabrArchivoTemporal");
        }
    }

    public void closeDialog() {
        RequestContext.getCurrentInstance().closeDialog(lstImagenes);
    }

    public void handleFileUpload2(FileUploadEvent event) throws IOException {
        UploadedFile archivo = (UploadedFile) event.getFile();
        Integer a = 0;
        if (!lstImagenes.isEmpty()) {
            for (UploadedFile lst : lstImagenes) {
                if ((archivo.getFileName()).equals(lst.getFileName())) {
                    FacesContext.getCurrentInstance().addMessage("gmsj", new FacesMessage(FacesMessage.SEVERITY_INFO, "Error", "Este archivo ya se añadio"));
                    RequestContext.getCurrentInstance().update("frmNovedad");
                    a = 1;
                    break;
                }
            }
            if (a == 0) {
                lstImagenes.add(archivo);
                grabrArchivoTemporal2(archivo.getFileName(), archivo.getContents(), rutaServidorArchivos);
                FacesContext.getCurrentInstance().addMessage("gmsj", new FacesMessage(FacesMessage.SEVERITY_INFO, "Exito", "Se agrego a la lista"));
                RequestContext.getCurrentInstance().update("frmNovedad");
            }
        } else {
            lstImagenes.add(archivo);
            grabrArchivoTemporal2(archivo.getFileName(), archivo.getContents(), rutaServidorArchivos);
            FacesContext.getCurrentInstance().addMessage("gmsj", new FacesMessage(FacesMessage.SEVERITY_INFO, "Exito", "Se agrego a la lista"));
            RequestContext.getCurrentInstance().update("frmNovedad");
        }
    }

    //con comons fileupload
    private void grabrArchivoTemporal2(String nombreArchivo, byte[] contenido, String dirFinal) {
        try {
            String filename = FilenameUtils.getName(nombreArchivo);
            OutputStream out = new FileOutputStream(new File(dirFinal + filename));
            try (BufferedOutputStream stream = new BufferedOutputStream(out)) {
                stream.write(contenido);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage()+" method : grabrArchivoTemporal2");
        }
    }

}
