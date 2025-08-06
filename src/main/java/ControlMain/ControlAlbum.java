package ControlMain;

import DAO.AlbumDAO;
import Logic.Album;
import View.ViewAlbum;

import java.sql.Connection;
import java.sql.SQLException;

public class ControlAlbum {

    private final AlbumDAO albumDAO;

    public ControlAlbum(Connection con) {
        this.albumDAO = new AlbumDAO(con);
    }

    public void insertarAlbum(ViewAlbum view) {
        try {
            Album album = new Album();
            capturardatosAlbum(album, view);
            albumDAO.insertarAlbum(album);
            view.mostrarMensajeExito("Álbum insertado correctamente.");
        } catch (Exception e) {
            view.mostrarError(e);
        }
    }

    public void modificarAlbum(ViewAlbum view) {
        try {
            Album album = new Album();
            capturardatosAlbum(album, view);
            albumDAO.modificarAlbum(album);
            view.mostrarMensajeExito("Álbum modificado correctamente.");
        } catch (Exception e) {
            view.mostrarError(e);
        }
    }

    public Album buscarAlbum(ViewAlbum view) {
        try {
            view.capturaAlbumNombre();
            String nombre = view.getValbumNombre();
            Album album = albumDAO.buscarAlbumPorNombre(nombre);

            if (album != null) {
                view.setValbumNombre(album.getAlbumNombre());
                view.setVfechaCreacion(album.getFechaCreacion());
                view.setVgenero(album.getGenero());
                view.setVdescargas(album.getDescargas());
                view.setVartista(album.getArtista());
                view.mostrarinformacionAlbum();
            } else {
                view.mostrarMensajeError("No se encontró ningún álbum con ese nombre.");
            }

            return album;
        } catch (Exception e) {
            view.mostrarError(e);
            return null;
        }
    }

    public void eliminarAlbum(ViewAlbum view) {
        try {
            view.capturaAlbumNombre();
            if (view.confirmarAccion("¿Está seguro que desea eliminar este álbum?")) {
                String nombre = view.getValbumNombre();
                albumDAO.eliminarAlbumPorNombre(nombre);
                view.mostrarMensajeExito("Álbum eliminado correctamente.");
            }
        } catch (Exception e) {
            view.mostrarError(e);
        }
    }

    private void capturardatosAlbum(Album album, ViewAlbum view) {
        view.capturaAlbumNombre();
        view.capturaGenero();
        view.capturaFechaCreacion();
        view.capturaDescargas();
        view.capturaArtista();
        view.capturaCanciones();

        album.setAlbumNombre(view.getValbumNombre());
        album.setGenero(view.getVgenero());
        album.setFechaCreacion(view.getVfechaCreacion());
        album.setDescargas(view.getVdescargas());
        album.setArtista(view.getVartista());
        album.setCanciones(view.getVcanciones());
    }
}
