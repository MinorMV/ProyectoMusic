package ControlMain;

import DAO.ArtistaDAO;
import Logic.Artista;
import View.ViewArtista;
import java.sql.Connection;

public class ControlArtista {

    private final ArtistaDAO dao;

    public ControlArtista(Connection conexion) {
        dao = new ArtistaDAO(conexion);
    }

    public void insertarArtista(ViewArtista vista) {
        try {
            Artista artista = new Artista(
                vista.pedirNombre(),
                vista.pedirGenero(),
                vista.pedirPais()
            );
            boolean exito = dao.insertarArtista(artista);
            if (exito) {
                vista.mostrarMensajeExito("Artista insertado correctamente.");
            } else {
                vista.mostrarMensajeError("No se pudo insertar el artista.");
            }
        } catch (Exception e) {
            vista.mostrarError(e);
        }
    }

    public void buscarArtista(ViewArtista vista) {
        try {
            String nombre = vista.pedirNombre();
            Artista artista = dao.buscarArtistaPorNombre(nombre);
            if (artista != null) {
                vista.mostrarDatos(artista);
            } else {
                vista.mostrarMensajeError("No se encontró el artista.");
            }
        } catch (Exception e) {
            vista.mostrarError(e);
        }
    }

    public void modificarArtista(ViewArtista vista) {
        try {
            Artista artista = new Artista(
                vista.pedirNombre(),
                vista.pedirGenero(),
                vista.pedirPais()
            );
            boolean exito = dao.modificarArtista(artista);
            if (exito) {
                vista.mostrarMensajeExito("Artista modificado correctamente.");
            } else {
                vista.mostrarMensajeError("No se pudo modificar el artista.");
            }
        } catch (Exception e) {
            vista.mostrarError(e);
        }
    }

    public void eliminarArtista(ViewArtista vista) {
        try {
            String nombre = vista.pedirNombre();
            boolean exito = dao.eliminarArtistaPorNombre(nombre);
            if (exito) {
                vista.mostrarMensajeExito("Artista eliminado correctamente.");
            } else {
                vista.mostrarMensajeError("No se pudo eliminar el artista.");
            }
        } catch (Exception e) {
            vista.mostrarError(e);
        }
    }
}
