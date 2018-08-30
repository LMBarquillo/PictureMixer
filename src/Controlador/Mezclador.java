/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelos.Archivo;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 *
 * @author Luismi
 */
public class Mezclador {
    public static final int CORRECTO = 0;
    public static final int LISTA_VACIA = -1;
    public static final int ERROR_INDEFINIDO = -99;
    
    private ArrayList<Archivo> archivos;
    
    public Mezclador() {
        archivos = new ArrayList<Archivo>();
    }
    
    public int mezclar(String salida) {
        if(this.archivos.size() > 0) {
            archivos.sort(null);    // Ordenamos usando la interfaz Comparable
            for(int i=0; i<archivos.size(); i++) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
                // Generamos un nuevo nombre con la ruta completa, según su fecha y un número consecutivo de 4 cifras
                String nombre = String.format("%s%s%s_%04d%s", 
                        salida,
                        System.getProperty("os.name").startsWith("Win") ? "\\" : "/",
                        sdf.format(archivos.get(i).lastModified()),
                        i,
                        getFileExtension(archivos.get(i))
                );
                // System.out.println(nombre);                 
                        
                // Copiamos el archivo
                copiarArchivo(archivos.get(i), new File(nombre));
            }
        } else {
            return LISTA_VACIA;
        }
        
        return CORRECTO;
    }
    
    public void addArchivo(Archivo archivo) {
        this.archivos.add(archivo);
    }
    
    public void addDirectorio(String ruta) {
        File directorio = new File(ruta);
        if(directorio.isDirectory()) {
            String archivos[] = directorio.list();
            for(String s : archivos) {
                String archivo = String.format("%s%s%s",
                        ruta,
                        System.getProperty("os.name").startsWith("Win") ? "\\" : "/",
                        s
                );
                Archivo a = new Archivo(archivo);
                if(a.isFile()) addArchivo(a);
            }
        }
    }
    
    private String getFileExtension(File file) {
        String name = file.getName();
        int lastIndexOf = name.lastIndexOf(".");
        if (lastIndexOf == -1) {
            return "";
        }
        return name.substring(lastIndexOf);
    }
    
    private boolean copiarArchivo(File src, File dst) {
        try {
            FileInputStream fis = new FileInputStream(src);
            FileOutputStream fos = new FileOutputStream(dst);
            byte[] buffer = new byte[1024];
            int length;
            while ((length = fis.read(buffer)) > 0) {
                fos.write(buffer, 0, length);
            }
            fis.close();
            fos.close();
            return true;
        } catch(IOException ioe) {
            return false;
        }        
    }
}
