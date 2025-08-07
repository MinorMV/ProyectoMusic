package ControlMain;

import DAO.PlaylistDAO;
import Logic.Playlist;
import Logic.Usuario;
import View.ViewPlaylist;
import java.sql.Connection;

public class ControlPlaylist {
    private Connection con;

    public ControlPlaylist(Connection con) {
        this.con = con;
    }

    public void insertarPlaylist(ViewPlaylist vp) {
        try {
            Playlist p = new Playlist();

            vp.capturaNombrePlaylist();
            p.setNombre(vp.getVnombrePlaylist());

            vp.capturaDescripcion();
            p.setDescripcion(vp.getVdescripcion());

            vp.capturaNombreUsuario();
            Usuario u = new Usuario();
            u.setNombre(vp.getVnombreUsuario());
            p.setUsuario(u);

            vp.capturaCanciones();
            p.setCanciones(vp.getVcanciones());

            PlaylistDAO dao = new PlaylistDAO(con);
            dao.insertarPlaylist(p);
            vp.mensajeExito("Playlist insertada correctamente.");
        } catch (Exception e) {
            vp.mensajeError("Error al insertar playlist: " + e.getMessage());
        }
    }

    public void buscarPlaylist(ViewPlaylist vp) {
        try {
            vp.capturaNombrePlaylist();
            String nombre = vp.getVnombrePlaylist();
            PlaylistDAO dao = new PlaylistDAO(con);
            Playlist p = dao.buscarPorNombre(nombre);
            if (p != null) {
                vp.setVnombreUsuario(p.getUsuario().getNombre());
                vp.setVcanciones(p.getCanciones());
                vp.setVdescripcion(p.getDescripcion());
                vp.mostrarPlaylistCompleta();
            } else {
                vp.mensajeError("Playlist no encontrada.");
            }
        } catch (Exception e) {
            vp.mensajeError("Error al buscar playlist: " + e.getMessage());
        }
    }

    public void modificarPlaylist(ViewPlaylist vp) {
        try {
            vp.capturaNombrePlaylist();
            PlaylistDAO dao = new PlaylistDAO(con);
            String nombre = vp.getVnombrePlaylist();
            Playlist p = dao.buscarPorNombre(nombre);
            if (p != null) {
                dao.eliminarPlaylist(nombre);
                insertarPlaylist(vp);
                vp.mensajeExito("Playlist modificada correctamente.");
            } else {
                vp.mensajeError("Playlist no encontrada.");
            }
        } catch (Exception e) {
            vp.mensajeError("Error al modificar playlist: " + e.getMessage());
        }
    }

    public void eliminarPlaylist(ViewPlaylist vp) {
        try {
            vp.capturaNombrePlaylist();
            String nombre = vp.getVnombrePlaylist();
            PlaylistDAO dao = new PlaylistDAO(con);
            dao.eliminarPlaylist(nombre);
            vp.mensajeExito("Playlist eliminada correctamente.");
        } catch (Exception e) {
            vp.mensajeError("Error al eliminar playlist: " + e.getMessage());
        }
    }
}
