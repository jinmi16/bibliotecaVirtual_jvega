package bv.bean;

import bv.dao.ReporteDao;
import bv.dao.UsuarioDao;
import bv.dao.impl.DaoFactory;
import static bv.util.Constantes.REPORTE;
import static bv.util.Constantes.USUARIO;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.CategoryAxis;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.LegendPlacement;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.PieChartModel;
import vb.entidad.reporteUsuarioDocumental;
import vb.entidad.Usuario;

/**
 *
 * @author Renato Vásquez Tejada - renatovt11@gmail.com
 */
@ManagedBean
@RequestScoped
public class reporteBean implements Serializable {

    String idBiblioteca;
    private BarChartModel barModel;
    private final ReporteDao rDao;
    private Date fechaReporte = new Date();
    private reporteUsuarioDocumental rUDoc;
    private List<reporteUsuarioDocumental> listRUDoc;
    private int sumaDocumentales;

    //PERIODO TRABAJADORES
    private int ptAno = 2016;
    private int ptAnoFin = 2017;
    private int anoActual;
    private int ptMesIni = 1;
    private int ptMesFin = 12;
    private BarChartModel barReporteTrabajadores;
    private List<reporteUsuarioDocumental> objPT;

    //jinmi
    private PieChartModel pieModel;
    List<Object[]> lstpie = new ArrayList<>();

    private LineChartModel lineModel1  = new LineChartModel();
    private Usuario auxUsuario;
   // private String anio = "2017";

    public reporteBean() {
        DaoFactory factory = DaoFactory.getInstance();
        rDao = factory.getReporteDao(REPORTE);
        rUDoc = new reporteUsuarioDocumental();
        auxUsuario = new Usuario();
        anoActual=getYearActual();
        idBiblioteca = FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("personalidBibliotecaFuente").toString();
        crearModeloLineal();
    }

    public List<Object[]> getLstpie() {
        return lstpie;
    }
    

    public void setLstpie(List<Object[]> lstpie) {
        this.lstpie = lstpie;
    }

    public PieChartModel getPieModel() {
        int tipoUsuario = Integer.parseInt(FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("personalTipoUsuario").toString());
        int idUsuario = (Integer) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("personalIdUsuario");
        pieModel = new PieChartModel();
        lstpie = rDao.listaInserUpdTotales(ptAno, ptMesIni, ptMesFin, idUsuario, tipoUsuario, idBiblioteca,ptAnoFin);
        for (Object[] ob : lstpie) {
            pieModel.set(ob[0].toString(), Integer.parseInt(ob[1].toString()));
        }
        pieModel.setExtender("skinPie");
        pieModel.setTitle("TITULO");
        pieModel.setLegendPosition("e");
        pieModel.setFill(true);
        pieModel.setShowDataLabels(true);
        pieModel.setDiameter(200);
        RequestContext.getCurrentInstance().update("formReportepie");
        return pieModel;
    }

    public void setPieModel(PieChartModel pieModel) {
        this.pieModel = pieModel;
    }

    public int getAnoActual() {
        return anoActual;
    }

    public void setAnoActual(int anoActual) {
        this.anoActual = anoActual;
    }

    public void cambiaFecha() {
        getBarModel();
    }

    public int getYearActual(){
        int aa;
    Calendar fecha = new GregorianCalendar();
    aa=fecha.get(Calendar.YEAR);
    return aa;
    
    }
    public BarChartModel getBarModel() {
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        String date = dateFormat.format(fechaReporte);
        List<Object[]> lista = rDao.listUsuarioDocumental(date, idBiblioteca);
        if (lista.size() > 0) {
            barModel = new BarChartModel();
            for (Object[] list : lista) {
                ChartSeries usuario = new ChartSeries();
                usuario.setLabel(list[0].toString());
                usuario.set(date, Integer.parseInt(list[1].toString()));
                barModel.addSeries(usuario);
            }
            barModel.setTitle("Reporte de descripcion de metadatos por Usuario");
            barModel.setLegendPosition("ne");
            barModel.setLegendPlacement(LegendPlacement.OUTSIDEGRID);
            barModel.setExtender("skinBar");
            RequestContext.getCurrentInstance().update("frmReporte");
        } else {
            barModel = new BarChartModel();
            ChartSeries usuario = new ChartSeries();
            usuario.setLabel("No hay registros");
            usuario.set(date, 0);
            barModel.addSeries(usuario);
            barModel.setTitle("Reporte de descripcion de metadatos por Usuario");
            barModel.setLegendPosition("ne");
            barModel.setLegendPlacement(LegendPlacement.OUTSIDEGRID);
            barModel.setExtender("skinBar");
            RequestContext.getCurrentInstance().update("frmReporte");
            FacesContext.getCurrentInstance().addMessage("gMensaje", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No hay documentales registrados en el día seleccionado."));
            RequestContext.getCurrentInstance().update("gMensaje");
        }
        return barModel;
    }

    public int getPtAnoFin() {
        return ptAnoFin;
    }

    public void setPtAnoFin(int ptAnoFin) {
        this.ptAnoFin = ptAnoFin;
    }

    
    public void setBarModel(BarChartModel barModel) {
        this.barModel = barModel;
    }

    public Date getFechaReporte() {
        return fechaReporte;
    }

    public void setFechaReporte(Date fechaReporte) {
        this.fechaReporte = fechaReporte;
    }

    public reporteUsuarioDocumental getrUDoc() {
        return rUDoc;
    }

    public void setrUDoc(reporteUsuarioDocumental rUDoc) {
        this.rUDoc = rUDoc;
    }

    public List<reporteUsuarioDocumental> getListRUDoc() {
        sumaDocumentales = 0;
        listRUDoc = new ArrayList<>();
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        String date = dateFormat.format(fechaReporte);
        listRUDoc = rDao.obtenerEntidades(date, idBiblioteca);
        for (int i = 0; i < listRUDoc.size(); i++) {
            sumaDocumentales = sumaDocumentales + listRUDoc.get(i).getCONTEO();
        }
        RequestContext.getCurrentInstance().update("frmReporte");
        return listRUDoc;
    }

    public void setListRUDoc(List<reporteUsuarioDocumental> listRUDoc) {
        this.listRUDoc = listRUDoc;
    }

    public int getSumaDocumentales() {
        return sumaDocumentales;
    }

    public void setSumaDocumentales(int sumaDocumentales) {
        this.sumaDocumentales = sumaDocumentales;
    }

    public int getPtAno() {
        return ptAno;
    }

    public void setPtAno(int ptAno) {
        this.ptAno = ptAno;
    }

    public int getPtMesIni() {
        return ptMesIni;
    }

    public void setPtMesIni(int ptMesIni) {
        this.ptMesIni = ptMesIni;
    }

    public int getPtMesFin() {
        return ptMesFin;
    }

    public void setPtMesFin(int ptMesFin) {
        this.ptMesFin = ptMesFin;
    }

    public void generaReporteTrabajadores() {
        getBarReporteTrabajadores();
        getPieModel();
    }

    public BarChartModel getBarReporteTrabajadores() {
        int tipoUsuario = Integer.parseInt(FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("personalTipoUsuario").toString());
        int idUsuario = (Integer) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("personalIdUsuario");
        List<Object[]> lista = rDao.listPeriodoTrabajadores(ptAno, ptMesIni, ptMesFin, idUsuario, tipoUsuario, idBiblioteca,ptAnoFin);
        barReporteTrabajadores = new BarChartModel();
        if (lista.size() > 0) {
            for (Object[] listado : lista) {
                ChartSeries usuario = new ChartSeries();
                usuario.setLabel(listado[0].toString());
                String rango=ptAno+" - "+ptAnoFin;
                usuario.set(rango, Integer.parseInt(listado[1].toString()));
                barReporteTrabajadores.addSeries(usuario);
            }
        } else {
            ChartSeries usuario = new ChartSeries();
            usuario.setLabel("No hay registros");
            String rango=ptAno+" - "+ptAnoFin;
            usuario.set(rango, 0);
            barReporteTrabajadores.addSeries(usuario);
        }
        barReporteTrabajadores.setTitle("Reporte de descripcion de metadatos por periodo de tiempo.");
        barReporteTrabajadores.setLegendPosition("ne");
        barReporteTrabajadores.setLegendPlacement(LegendPlacement.OUTSIDEGRID);
        barReporteTrabajadores.setExtender("skinBar");
        RequestContext.getCurrentInstance().update("frmReporte");
        return barReporteTrabajadores;
    }

    public void setBarReporteTrabajadores(BarChartModel barReporteTrabajadores) {
        this.barReporteTrabajadores = barReporteTrabajadores;
    }

    public List<reporteUsuarioDocumental> getObjPT() {
        int tipoUsuario = Integer.parseInt(FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("personalTipoUsuario").toString());
        int idUsuario = (Integer) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("personalIdUsuario");
        sumaDocumentales = 0;
        objPT = new ArrayList<>();
        objPT = rDao.obtenerPorPeriodo(ptAno, ptMesIni, ptMesFin, idUsuario, tipoUsuario, idBiblioteca,ptAnoFin);
        for (int i = 0; i < objPT.size(); i++) {
            sumaDocumentales = sumaDocumentales + objPT.get(i).getCONTEO();
        }
        RequestContext.getCurrentInstance().update("frmReporte");
        return objPT;
    }

    public void setObjPT(List<reporteUsuarioDocumental> objPT) {
        this.objPT = objPT;
    }

    public Usuario getAuxUsuario() {
        return auxUsuario;
    }

//    public void setAnio(String anio) {
//        this.anio = anio;
//    }

    public LineChartModel getLineModel1() {
        return lineModel1;
    }

    public void setLineModel1(LineChartModel lineModel1) {
        this.lineModel1 = lineModel1;
    }

    public void crearModeloLineal() {
       // int ianio = Integer.parseInt(anio);
       lineModel1.clear();
        ArrayList<Object[]> listaUsuarios = rDao.listaUsusariosMensual(anoActual, Integer.parseInt(idBiblioteca));
        ArrayList<Object[]> listaDatos = rDao.listaConsolidadoMensual(anoActual, Integer.parseInt(idBiblioteca));
        
        for (Object[] usuario : listaUsuarios) {
            String usu = usuario[0].toString();
            String idusu = usuario[1].toString();
            ChartSeries serie = new ChartSeries(usu);
            for (Object[] dato : listaDatos) {
                if (idusu.equals(dato[0].toString())) {
                    serie.set(dato[4].toString(), Integer.parseInt(dato[2].toString()));
                }
            }
            lineModel1.addSeries(serie);
        }
        lineModel1.setTitle("Registros Mensuales");
        lineModel1.setLegendPosition("nw");
        lineModel1.setShowPointLabels(true);
        lineModel1.setExtender("skinChart");
        lineModel1.getAxes().put(AxisType.X, new CategoryAxis("Meses"));
        Axis EjeY = lineModel1.getAxis(AxisType.Y);
        EjeY.setLabel("Cantidad");
        EjeY.setMin(0);
         RequestContext.getCurrentInstance().update("frmReporteAnual");
    }

    //jinmi
    private void createPieModel() {
        int tipoUsuario = Integer.parseInt(FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("personalTipoUsuario").toString());
        int idUsuario = (Integer) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("personalIdUsuario");
        pieModel = new PieChartModel();
        lstpie = rDao.listaInserUpdTotales(ptAno, ptMesIni, ptMesFin, idUsuario, tipoUsuario, idBiblioteca,ptAnoFin);
        for (Object[] ob : lstpie) {
            pieModel.set(ob[0].toString(), Integer.parseInt(ob[1].toString()));
        }
        pieModel.setExtender("skinPie");

        pieModel.setTitle("TITULO");
        pieModel.setLegendPosition("e");
        pieModel.setFill(true);
        pieModel.setShowDataLabels(true);
        pieModel.setDiameter(200);
        RequestContext.getCurrentInstance().update("formReportepie");
    }
    
}
