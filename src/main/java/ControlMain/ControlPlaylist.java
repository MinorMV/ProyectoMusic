/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ControlMain;

import DAO.PlaylistDAO;
import Logic.Playlist;
import Logic.Usuario;
import View.ViewPlaylist;
import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author sebas
 */
public class ControlPlaylist {

    private PlaylistDAO dao;

    public ControlPlaylist(Connection conexion) {
        dao = new PlaylistDAO(conexion);
    }

    public void capturarDatosPlaylist(ViewPlaylist vista, Playlist playlist) {
        vista.capturaNombrePlaylist();
        vista.capturaNombreUsuario();

        // Construcción de objeto Usuario asociado
        Usuario usuario = new Usuario();
        usuario.setNombre(vista.getVnombreUsuario());

        // Seteo de atributos en la playlist
        playlist.setNombre(vista.getVnombrePlaylist());
        playlist.setUsuario(usuario);

        try {
            dao.insertarPlaylist(playlist);
            JOptionPane.showMessageDialog(null, "Playlist insertada correctamente.");
        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al insertar playlist: " + e.getMessage());
        }
    }

    public void buscarYMostrarPlaylistPorNombre(ViewPlaylist vista) {
        vista.capturaNombrePlaylist();
        String nombre = vista.getVnombrePlaylist();

        try {
            Playlist playlist = dao.buscarPorNombre(nombre);
            if (playlist != null) {
                vista.setVnombrePlaylist(playlist.getNombre());
                vista.setVnombreUsuario(playlist.getUsuario().getNombre());
                vista.mostrarInformacionCompletaPlaylist();
            } else {
                JOptionPane.showMessageDialog(null, "No se encontró una playlist con ese nombre.");
            }
        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al buscar playlist: " + e.getMessage());
        }
    }

    public void eliminarPlaylistPorNombre(ViewPlaylist vista) {
        vista.capturaNombrePlaylist();
        String nombre = vista.getVnombrePlaylist();

        try {
            dao.eliminarPlaylist(nombre);
            JOptionPane.showMessageDialog(null, "Playlist eliminada correctamente.");
        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al eliminar playlist: " + e.getMessage());
        }
    }
}

