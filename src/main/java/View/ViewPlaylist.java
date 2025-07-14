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
public class ViewPlaylist {
    private String vnombrePlaylist;
    private String vnombreUsuario;

    public void capturaNombrePlaylist() {
        vnombrePlaylist = JOptionPane.showInputDialog(null, 
            "Ingrese el nombre de la playlist:", 
            "Playlist", 
            JOptionPane.INFORMATION_MESSAGE);
    }

    public void capturaNombreUsuario() {
        vnombreUsuario = JOptionPane.showInputDialog(null, 
            "Ingrese el nombre del usuario que crea la playlist:", 
            "Usuario", 
            JOptionPane.INFORMATION_MESSAGE);
    }

    public void mostrarNombrePlaylist() {
        JOptionPane.showMessageDialog(null, 
            "Nombre de Playlist: " + getVnombrePlaylist(), 
            "Playlist", 
            JOptionPane.INFORMATION_MESSAGE);
    }

    public void mostrarNombreUsuario() {
        JOptionPane.showMessageDialog(null, 
            "Usuario: " + getVnombreUsuario(), 
            "Playlist", 
            JOptionPane.INFORMATION_MESSAGE);
    }

    public void mostrarInformacionCompletaPlaylist() {
        String info = """
            ----- PLAYLIST -----
            Nombre: """ + vnombrePlaylist +
            "\nUsuario: " + vnombreUsuario +
            "\n---------------------";
        JOptionPane.showMessageDialog(null, info, "Información Playlist", JOptionPane.INFORMATION_MESSAGE);
    }

    // Getter y Setter de vnombrePlaylist
    public String getVnombrePlaylist() {
        return vnombrePlaylist;
    }

    public void setVnombrePlaylist(String vnombrePlaylist) {
        this.vnombrePlaylist = vnombrePlaylist;
    }

    // Getter y Setter de vnombreUsuario
    public String getVnombreUsuario() {
        return vnombreUsuario;
    }

    public void setVnombreUsuario(String vnombreUsuario) {
        this.vnombreUsuario = vnombreUsuario;
    }
}
