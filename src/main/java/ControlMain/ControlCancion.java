package ControlMain;

import DAO.CancionDAO;
import Logic.Artista;
import Logic.Cancion;
import View.ViewCancion;
import java.sql.Connection;

public class ControlCancion {
    private Connection conexion;

    public ControlCancion(Connection conexion) {
        this.conexion = conexion;
    }

    public void insertarCancion(ViewCancion vc) throws Exception {
        vc.capturaTitulo();
        vc.capturaAlbum();
        vc.capturaDuracion();
        vc.capturaGenero();
        vc.capturaArtista();

        Cancion cancion = new Cancion();
        cancion.setTitulo(vc.getTitulo());
        cancion.setAlbum(vc.getAlbum());
        cancion.setDuracion(vc.getDuracion());
        cancion.setGenero(vc.getGenero());
        cancion.setArtista(new Artista(vc.getArtista()));

        CancionDAO dao = new CancionDAO(conexion);
        dao.insertarCancion(cancion);

        vc.mostrarMensaje("Canción insertada correctamente.");
    }

    public void buscarCancion(ViewCancion vc) throws Exception {
        vc.capturaTitulo();
        CancionDAO dao = new CancionDAO(conexion);
        Cancion cancion = dao.buscarPorTitulo(vc.getTitulo());

        if (cancion != null) {
            vc.mostrarMensaje(cancion.toString());
        } else {
            vc.mostrarMensaje("Canción no encontrada.");
        }
    }

    public void modificarCancion(ViewCancion vc) throws Exception {
        vc.capturaTitulo();
        CancionDAO dao = new CancionDAO(conexion);
        Cancion cancion = dao.buscarPorTitulo(vc.getTitulo());

        if (cancion != null) {
            vc.capturaAlbum();
            vc.capturaDuracion();
            vc.capturaGenero();
            vc.capturaArtista();

            cancion.setAlbum(vc.getAlbum());
            cancion.setDuracion(vc.getDuracion());
            cancion.setGenero(vc.getGenero());
            cancion.setArtista(new Artista(vc.getArtista()));

            dao.modificarCancion(cancion);
            vc.mostrarMensaje("Canción modificada correctamente.");
        } else {
            vc.mostrarMensaje("No se encontró la canción para modificar.");
        }
    }

    public void eliminarCancion(ViewCancion vc) throws Exception {
        vc.capturaTitulo();
        CancionDAO dao = new CancionDAO(conexion);
        dao.eliminarCancion(vc.getTitulo());
        vc.mostrarMensaje("Canción eliminada correctamente.");
    }
}
