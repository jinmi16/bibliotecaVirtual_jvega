/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bv.util;

import java.io.File;
import java.util.List;

/**
 *
 * @author virtual
 */
public class CambiarNombre {

    /**
     * @param args the command line arguments
     */ 
    public static void main(String[] args) {

        File dir = new File("\\\\eudora\\bnp\\recursos\\2\\flippingbook");
        String[] ficheros = dir.list();
        if (ficheros == null) {
            System.out.println("No hay ficheros en el directorio especificado");
        } else {
            for (int x = 0; x < ficheros.length; x++) {
                System.out.println(ficheros[x]);
            }
        }
     
/*
        sql cn = new sql();
        List<Object[]> list = cn.consulta("SELECT ID_DOCUMENTAL, OTRO FROM BV.DOCUMENTAL WHERE ACTIVO=1");
        for (Object[] data : list) {
            File f1 = new File("Z:\\virtual\\" + data[0]);
            File f2 = new File("Z:\\virtual\\" + data[1].toString());
            if (f1.exists()) {
                boolean correcto = f1.renameTo(f2);
                if (correcto) {
                    System.out.println("Se cambió nombre del archivo: " + data[0]);
                } else {
                    System.out.println("Ocurrió un error al cambiar el nombre: " + data[0] + " por el nombre: " + data[1]);
                }
            } else {
                System.out.println("No se encontro la carpeta con el nombre: " + data[0]);
            }
        }
*/




    }

}
