package bv.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.joda.time.DateTime;

public class dateConverter {

    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

    //regresa un Date de una fecha
    public Date convertirStringToDate(String f) {
        Date fecha = null;
        try {
            fecha = sdf.parse(f);
        } catch (ParseException ex) {
            System.out.println("error parse" + ex.getMessage());
        }
        return fecha;
    }

    //regresa un string a partir de un date 
    public String convertirDatetoString(Date d) {
        String fechaString = "";
        try {
            fechaString = sdf.format(d);
        } catch (Exception ex) {
            System.out.println("error error" + ex.getMessage());
        }
        return fechaString;
    }

    public java.sql.Date formatFecha(java.util.Date fecha) {
        java.sql.Date sqlDate = new java.sql.Date(fecha.getTime());
        return sqlDate;
    }

}
