/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelos;

import java.io.File;

/**
 * Extensión de la clase File con un comparable por fechas
 * @author Luismi
 */
public class Archivo extends File implements Comparable<File> {
    private long modificador;
    
    public Archivo(String pathname, long modificador) {
        super(pathname);
        this.modificador = modificador;
    }

    public long getModificado() {
        return this.lastModified() + this.modificador;
    }

    @Override
    public int compareTo(File o) {
        if(o instanceof Archivo) {
            if(this.getModificado() < ((Archivo) o).getModificado()) return -1;
            else if(this.getModificado() > ((Archivo) o).getModificado()) return 1;
            else return 0;    
        } else return super.compareTo(o);
    }    
}
