/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bv.util;

import java.io.File;

/**
 *
 * @author virtual
 */
public class tamañoDirectorio {
    
    int tamañototal;
    File fichero = new File("D:\\Jinmi Vega\\AUDIOS_BV");
    public tamañoDirectorio(){
        String[] listaArchivos=fichero.list();
        for(int i=0; i<listaArchivos.length; i++){
            if (fichero.isDirectory()) {
                String[] directorio = fichero.list();
                for (int x=0; x<directorio.length; x++) {
                    tamañototal = tamañototal + directorio[x].length();
                }
            }else{
                tamañototal = tamañototal + listaArchivos[i].length();
            }
        }
        System.out.println("El tamaño total de la base de datos es: "+tamañototal+" kb.");
    }
    
    public static void main(String args[]){
        tamañoDirectorio tamaño =  new tamañoDirectorio();
    }
    
}
