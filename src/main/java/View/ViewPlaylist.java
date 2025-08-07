package View;

import Logic.Cancion;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class ViewPlaylist {
    private String vnombrePlaylist;
    private String vdescripcion;
    private String vnombreUsuario;
    private ArrayList<Cancion> vcanciones = new ArrayList<>();

    public void capturaNombrePlaylist() {
        vnombrePlaylist = JOptionPane.showInputDialog("Ingrese el nombre de la playlist:");
        if (vnombrePlaylist != null) {
            vnombrePlaylist = vnombrePlaylist.trim();
        }
    }

    public void capturaDescripcion() {
        vdescripcion = JOptionPane.showInputDialog("Ingrese una descripción para la playlist:");
        if (vdescripcion != null) {
            vdescripcion = vdescripcion.trim();
        }
    }

    public void capturaNombreUsuario() {
        vnombreUsuario = JOptionPane.showInputDialog("Ingrese el nombre del usuario:");
        if (vnombreUsuario != null) {
            vnombreUsuario = vnombreUsuario.trim();
        }
    }

    public void capturaCanciones() {
        vcanciones.clear();
        boolean seguir = true;
        while (seguir) {
            String titulo = JOptionPane.showInputDialog("Ingrese el título de la canción:");
            if (titulo != null && !titulo.trim().isEmpty()) {
                Cancion c = new Cancion();
                c.setTitulo(titulo.trim());
                vcanciones.add(c);
            }
            int op = JOptionPane.showConfirmDialog(null, "¿Agregar otra canción?", "Agregar", JOptionPane.YES_NO_OPTION);
            seguir = (op == JOptionPane.YES_OPTION);
        }
    }

    public void mostrarPlaylistCompleta() {
        StringBuilder sb = new StringBuilder();
        sb.append("Nombre: ").append(vnombrePlaylist).append("\n");
        sb.append("Descripción: ").append(vdescripcion).append("\n");
        sb.append("Usuario: ").append(vnombreUsuario).append("\n");
        sb.append("Canciones:\n");

        for (Cancion c : vcanciones) {
            sb.append("- ").append(c.getTitulo()).append("\n");
        }

        JOptionPane.showMessageDialog(null, sb.toString(), "Detalle de Playlist", JOptionPane.INFORMATION_MESSAGE);
    }

    public void mensajeExito(String msg) {
        JOptionPane.showMessageDialog(null, msg, "Éxito", JOptionPane.INFORMATION_MESSAGE);
    }

    public void mensajeError(String msg) {
        JOptionPane.showMessageDialog(null, msg, "Error", JOptionPane.ERROR_MESSAGE);
    }

    public String getVnombrePlaylist() {
        return vnombrePlaylist;
    }

    public String getVdescripcion() {
        return vdescripcion;
    }

    public String getVnombreUsuario() {
        return vnombreUsuario;
    }

    public ArrayList<Cancion> getVcanciones() {
        return vcanciones;
    }

    public void setVnombrePlaylist(String vnombrePlaylist) {
        this.vnombrePlaylist = vnombrePlaylist;
    }

    public void setVdescripcion(String vdescripcion) {
        this.vdescripcion = vdescripcion;
    }

    public void setVnombreUsuario(String vnombreUsuario) {
        this.vnombreUsuario = vnombreUsuario;
    }

    public void setVcanciones(ArrayList<Cancion> vcanciones) {
        this.vcanciones = vcanciones;
    }
}
