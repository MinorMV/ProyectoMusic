/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ControlMain;

import DAO.AlbumDAO;
import Logic.Album;
import Logic.Artista;
import View.ViewAlbum;
import java.sql.Connection;
import javax.swing.JOptionPane;

/**
 *
 * @author sebas
 */
public class ControlAlbum {

    private AlbumDAO dao;

    public ControlAlbum(Connection conexion) {
        dao = new AlbumDAO(conexion);
    }


    public void capturarDatosAlbum(ViewAlbum vista, Album album) {
        album.setAlbumNombre(vista.getValbumNombre());
        album.setFechaCreacion(vista.getVfechaCreacion());
        album.setGenero(vista.getVgenero());
        album.setDescargas(vista.getVdescargas());
        album.setArtista(vista.getVartista()); 
        album.setCanciones(vista.getVcanciones());

        try {
            dao.insertarAlbum(album);
            JOptionPane.showMessageDialog(null, "Álbum insertado correctamente.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al insertar álbum: " + e.getMessage());
        }
    }


    public void buscarYMostrarAlbumPorNombre(ViewAlbum vista) {
        String nombre = vista.getValbumNombre(); 
        try {
            Album album = dao.buscarAlbumPorNombre(nombre);
            if (album != null) {
                vista.setValbumNombre(album.getAlbumNombre());
                vista.setVfechaCreacion(album.getFechaCreacion());
                vista.setVgenero(album.getGenero());
                vista.setVdescargas(album.getDescargas());
                vista.setVartista(album.getArtista()); 
                vista.setVcanciones(album.getCanciones());

                vista.mostrarinformacionAlbum();
            } else {
                JOptionPane.showMessageDialog(null, "No se encontró un álbum con ese nombre.");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al buscar álbum: " + e.getMessage());
        }
    }


    public void modificarAlbumPorNombre(ViewAlbum vista) {
        String nombre = vista.getValbumNombre();
        try {
            Album album = dao.buscarAlbumPorNombre(nombre);
            if (album != null) {
                album.setFechaCreacion(vista.getVfechaCreacion());
                album.setGenero(vista.getVgenero());
                album.setDescargas(vista.getVdescargas());
                album.setArtista(vista.getVartista()); 
                album.setCanciones(vista.getVcanciones());

                dao.modificarAlbum(album);
                JOptionPane.showMessageDialog(null, "Álbum modificado correctamente.");
            } else {
                JOptionPane.showMessageDialog(null, "No se encontró el álbum para modificar.");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al modificar álbum: " + e.getMessage());
        }
    }


    public void eliminarAlbumPorNombre(ViewAlbum vista) {
        String nombre = vista.getValbumNombre();
        try {
            dao.eliminarAlbumPorNombre(nombre);
            JOptionPane.showMessageDialog(null, "Álbum eliminado correctamente.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al eliminar álbum: " + e.getMessage());
        }
    }
}