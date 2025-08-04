package ControlMain;

import DAO.PlaylistDAO;
import Logic.Playlist;
import Logic.Usuario;
import View.ViewPlaylist;
import java.sql.Connection;
import javax.swing.JOptionPane;

public class ControlPlaylist {

    private Connection con;

    public ControlPlaylist(Connection con) {
        this.con = con;
    }

    public void capturarDatosPlaylist(ViewPlaylist vp, Playlist mp) {
        vp.capturaNombrePlaylist();
        mp.setNombre(vp.getVnombrePlaylist());

        vp.capturaNombreUsuario();
        Usuario nuevoUsuario = new Usuario();
        nuevoUsuario.setNombre(vp.getVnombreUsuario());
        mp.setUsuario(nuevoUsuario);

        vp.capturaCanciones();
        mp.setCanciones(vp.getVcanciones());

        vp.mostrarInformacionCompletaPlaylist();
    }

    public void insertarPlaylist(ViewPlaylist vp) {
        try {
            Playlist playlist = new Playlist();
            capturarDatosPlaylist(vp, playlist);
            PlaylistDAO dao = new PlaylistDAO(con);
            dao.insertarPlaylist(playlist);
            JOptionPane.showMessageDialog(null, "Playlist insertada correctamente.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al insertar playlist: " + e.getMessage());
        }
    }

    public void buscarPlaylist(ViewPlaylist vp) {
        try {
            String nombre = JOptionPane.showInputDialog("Digite el nombre de la playlist:");
            PlaylistDAO dao = new PlaylistDAO(con);
            Playlist p = dao.buscarPorNombre(nombre);
            if (p != null) {
                vp.setVnombrePlaylist(p.getNombre());
                vp.setVnombreUsuario(p.getUsuario().getNombre());
                vp.setVcanciones(p.getCanciones());
                vp.mostrarInformacionCompletaPlaylist();
            } else {
                JOptionPane.showMessageDialog(null, "Playlist no encontrada");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al buscar playlist: " + e.getMessage());
        }
    }

    public void modificarPlaylist(Connection con, ViewPlaylist vp) {
        try {
            String nombreOriginal = JOptionPane.showInputDialog("Digite el nombre de la playlist a modificar:");
            PlaylistDAO dao = new PlaylistDAO(con);
            Playlist playlistOriginal = dao.buscarPorNombre(nombreOriginal);

            if (playlistOriginal != null) {
                capturarDatosPlaylist(vp, playlistOriginal);
                dao.eliminarPlaylist(nombreOriginal);
                dao.insertarPlaylist(playlistOriginal);
                JOptionPane.showMessageDialog(null, "Playlist modificada correctamente.");
            } else {
                JOptionPane.showMessageDialog(null, "Playlist no encontrada");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al modificar playlist: " + e.getMessage());
        }
    }

    public void eliminarPlaylist(ViewPlaylist vp) {
        try {
            String nombre = JOptionPane.showInputDialog("Digite el nombre de la playlist a eliminar:");
            PlaylistDAO dao = new PlaylistDAO(this.con);
            dao.eliminarPlaylist(nombre);
            JOptionPane.showMessageDialog(null, "Playlist eliminada correctamente.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al eliminar playlist: " + e.getMessage());
        }
    }
}
