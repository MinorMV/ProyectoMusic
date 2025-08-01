package ControlMain;

import DAO.ArtistaDAO;
import Logic.Artista;
import View.ViewArtista;
import java.sql.Connection;
import javax.swing.JOptionPane;

public class ControlArtista {

    private ArtistaDAO dao;

    public ControlArtista(Connection conexion) {
        dao = new ArtistaDAO(conexion);
    }

    public void capturarDatosArtista(ViewArtista vista, Artista artista) {
        artista.setNombre(vista.pedirNombre());
        artista.setGenero(vista.pedirGenero());
        artista.setPais(vista.pedirPais());
        try {
            dao.insertarArtista(artista);
            JOptionPane.showMessageDialog(null, "Artista insertado correctamente.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al insertar artista: " + e.getMessage());
        }
    }

    public void buscarYMostrarArtistaPorNombre(ViewArtista vista) {
        String nombre = vista.pedirNombre();
        try {
            Artista artista = dao.buscarArtistaPorNombre(nombre);
            if (artista != null) {
                vista.mostrarDatos(artista);
            } else {
                JOptionPane.showMessageDialog(null, "No se encontró un artista con ese nombre.");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al buscar artista: " + e.getMessage());
        }
    }

    public void modificarArtistaPorNombre(ViewArtista vista) {
        Artista artista = new Artista();
        artista.setNombre(vista.pedirNombre());
        artista.setGenero(vista.pedirGenero());
        artista.setPais(vista.pedirPais());
        try {
            dao.modificarArtista(artista);
            JOptionPane.showMessageDialog(null, "Artista modificado correctamente.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al modificar artista: " + e.getMessage());
        }
    }

    public void eliminarArtistaPorNombre(ViewArtista vista) {
        String nombre = vista.pedirNombre();
        try {
            dao.eliminarArtistaPorNombre(nombre);
            JOptionPane.showMessageDialog(null, "Artista eliminado correctamente.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al eliminar artista: " + e.getMessage());
        }
    }
}
