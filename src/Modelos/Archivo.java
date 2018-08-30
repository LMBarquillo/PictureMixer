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
    public Archivo(String pathname) {
        super(pathname);
    }

    @Override
    public int compareTo(File o) {
        if(this.lastModified() < o.lastModified()) return -1;
        else if(this.lastModified() > o.lastModified()) return 1;
        else return 0;
    }    
}
