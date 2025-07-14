/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View;

import javax.swing.JOptionPane;

/**
 *
 * @author sebas
 */
public class ViewArtista {
    private String vnombre;
    private String vgenero;
    private String vpais; 
    
    public void mostrarNombre(){
        JOptionPane.showMessageDialog(null, "Nombre:"+ getVnombre());
    }
    public void mostrarGenero(){
        JOptionPane.showMessageDialog(null, "Genero:"+ getVgenero());
    }
    public void mostrarPais(){
        JOptionPane.showMessageDialog(null, "Pais:"+ getVpais());
    }
    public void capturaNombre(){
        vnombre = JOptionPane.showInputDialog(null, "Ingrese el nombre del Artista:  ", "Artista",JOptionPane.INFORMATION_MESSAGE);
    }
    public void capturaGenero(){
        vgenero = JOptionPane.showInputDialog(null, "Ingrese el genero del Artista:  ", "Artista",JOptionPane.INFORMATION_MESSAGE);
    }
    public void capturaPais(){
        vpais = JOptionPane.showInputDialog(null, "Ingrese el pais del Artista:  ", "Artista",JOptionPane.INFORMATION_MESSAGE);
    }
    public void mostrarInformacionCompletaArtista() {
    String informacion=
                     """
                     -----ARTISTA-----
                     Nombre: """ + vnombre +
                     "\nGenero: " +vgenero +
                     "\nPais: " + vpais +
                     "\n------------------------";

    
    JOptionPane.showMessageDialog(null, informacion, "Información del Artista", JOptionPane.INFORMATION_MESSAGE);
}
    /**
     * @return the vnombre
     */
    public String getVnombre() {
        return vnombre;
    }

    /**
     * @param vnombre the vnombre to set
     */
    public void setVnombre(String vnombre) {
        this.vnombre = vnombre;
    }

    /**
     * @return the vgenero
     */
    public String getVgenero() {
        return vgenero;
    }

    /**
     * @param vgenero the vgenero to set
     */
    public void setVgenero(String vgenero) {
        this.vgenero = vgenero;
    }

    /**
     * @return the vpais
     */
    public String getVpais() {
        return vpais;
    }

    /**
     * @param vpais the vpais to set
     */
    public void setVpais(String vpais) {
        this.vpais = vpais;
    }
    
}
