/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelos;

/**
 *
 * @author Luismi
 */
public class Directorio {
    private String ruta;
    private long modificador;

    public Directorio(String ruta, int modificador) {
        this.ruta = ruta;
        this.modificador = modificador;
    }
    
    /**
     * Aumenta o disminuye el tiempo de la creación real de las imágenes del directorio.
     * Esto se vuelve útil cuando has usado varios dispositivos con distintas horas.
     * @param minutos 
     */
    public void modificarTiempo(int horas, int minutos) {
        this.modificador += (horas * 60 * 60 * 1000) + (minutos * 60 * 1000);    // a milisegundos
    }

    public String getRuta() {
        return ruta;
    }

    public long getModificador() {
        return modificador;
    }
}
