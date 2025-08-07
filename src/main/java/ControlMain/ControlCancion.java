package ControlMain;

import DAO.CancionDAO;
import Logic.Artista;
import Logic.Cancion;
import View.ViewCancion;
import java.sql.Connection;
import java.sql.SQLException;

public class ControlCancion {
    private final CancionDAO dao;
    private final ViewCancion view;

    public ControlCancion(Connection conexion) {
        this.dao = new CancionDAO(conexion);
        this.view = new ViewCancion();
    }

    public void insertarCancion() {
        try {
            view.capturaTitulo();
            view.capturaDuracion();
            view.capturaGenero();
            view.capturaAlbum();
            view.capturaArtista();

            Artista artista = new Artista();
            artista.setNombre(view.getArtista());

            Cancion c = new Cancion(
                view.getTitulo(),
                view.getDuracion(),
                artista,
                view.getGenero(),
                view.getAlbum()
            );

            dao.insertarCancion(c);
            view.mostrarMensajeExito("Canción insertada correctamente.");
        } catch (SQLException e) {
            view.mostrarMensajeError("Error al insertar la canción: " + e.getMessage());
        }
    }

    public void buscarCancion() {
        try {
            view.capturaTitulo();
            Cancion c = dao.buscarCancionPorTitulo(view.getTitulo());

            if (c != null) {
                view.mostrarMensajeExito("Canción encontrada:\n" + c.toString());
            } else {
                view.mostrarMensajeError("No se encontró ninguna canción con ese título.");
            }
        } catch (SQLException e) {
            view.mostrarMensajeError("Error al buscar la canción: " + e.getMessage());
        }
    }

    public void modificarCancion() {
        try {
            view.capturaTitulo();
            Cancion c = dao.buscarCancionPorTitulo(view.getTitulo());

            if (c != null) {
                view.capturaDuracion();
                view.capturaGenero();
                view.capturaAlbum();
                view.capturaArtista();

                Artista artista = new Artista();
                artista.setNombre(view.getArtista());

                c.setDuracion(view.getDuracion());
                c.setGenero(view.getGenero());
                c.setAlbum(view.getAlbum());
                c.setArtista(artista);

                dao.modificarCancion(c);
                view.mostrarMensajeExito("Canción modificada correctamente.");
            } else {
                view.mostrarMensajeError("No se encontró la canción a modificar.");
            }
        } catch (SQLException e) {
            view.mostrarMensajeError("Error al modificar la canción: " + e.getMessage());
        }
    }

    public void eliminarCancion() {
        try {
            view.capturaTitulo();
            if (view.confirmarAccion("¿Desea eliminar esta canción?")) {
                dao.eliminarCancion(view.getTitulo());
                view.mostrarMensajeExito("Canción eliminada correctamente.");
            }
        } catch (SQLException e) {
            view.mostrarMensajeError("Error al eliminar la canción: " + e.getMessage());
        }
    }
}
