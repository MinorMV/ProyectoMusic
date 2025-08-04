package View;

import Logic.Cancion;
import javax.swing.JOptionPane;
import java.util.ArrayList;

public class ViewPlaylist {
    private String vnombrePlaylist;
    private String vnombreUsuario;
    private ArrayList<Cancion> vcanciones = new ArrayList<>();

    public void capturaNombrePlaylist() {
        vnombrePlaylist = JOptionPane.showInputDialog(null, "Ingrese el nombre de la playlist:", "Playlist", JOptionPane.INFORMATION_MESSAGE);
    }

    public void capturaNombreUsuario() {
        vnombreUsuario = JOptionPane.showInputDialog(null, "Ingrese el nombre del usuario:", "Usuario", JOptionPane.INFORMATION_MESSAGE);
    }

    public void capturaCanciones() {
        boolean seguir = true;
        while (seguir) {
            String titulo = JOptionPane.showInputDialog(null, "Ingrese el título de la canción:", "Canción", JOptionPane.INFORMATION_MESSAGE);
            if (titulo != null && !titulo.trim().isEmpty()) {
                Cancion c = new Cancion();
                c.setTitulo(titulo.trim());
                vcanciones.add(c);
            }
            int opcion = JOptionPane.showConfirmDialog(null, "¿Desea agregar otra canción?", "Continuar", JOptionPane.YES_NO_OPTION);
            if (opcion != JOptionPane.YES_OPTION) {
                seguir = false;
            }
        }
    }

    public void mostrarInformacionCompletaPlaylist() {
        StringBuilder info = new StringBuilder("----- PLAYLIST -----\n");
        info.append("Nombre: ").append(vnombrePlaylist).append("\n");
        info.append("Usuario: ").append(vnombreUsuario).append("\n");
        info.append("Canciones:\n");
        for (Cancion c : vcanciones) {
            info.append("- ").append(c.getTitulo()).append("\n");
        }
        info.append("---------------------");
        JOptionPane.showMessageDialog(null, info.toString(), "Información Playlist", JOptionPane.INFORMATION_MESSAGE);
    }

    // Getters y Setters
    public String getVnombrePlaylist() { return vnombrePlaylist; }
    public void setVnombrePlaylist(String vnombrePlaylist) { this.vnombrePlaylist = vnombrePlaylist; }

    public String getVnombreUsuario() { return vnombreUsuario; }
    public void setVnombreUsuario(String vnombreUsuario) { this.vnombreUsuario = vnombreUsuario; }

    public ArrayList<Cancion> getVcanciones() { return vcanciones; }
    public void setVcanciones(ArrayList<Cancion> vcanciones) { this.vcanciones = vcanciones; }
}
