package View;

import javax.swing.JOptionPane;
import ControlMain.ControlPlaylist;
import Logic.Playlist;

// CAMBIO: excepciones
import Excepciones.ValidacionException;           // CAMBIO
import Excepciones.AccesoDatosException;          // CAMBIO
import Excepciones.RecursoNoEncontradoException;  // CAMBIO

public class ViewPlaylist {

    private final ControlPlaylist control;

    public ViewPlaylist(ControlPlaylist control) {
        this.control = control;
    }

    public void crear(Playlist p) {
        try {
            control.crear(p);
            JOptionPane.showMessageDialog(null, "Playlist creada.");
        } catch (ValidacionException e) {
            JOptionPane.showMessageDialog(null, "Validación: " + e.getMessage());
        } catch (AccesoDatosException e) {
            JOptionPane.showMessageDialog(null, "BD: " + e.getMessage());
        }
    }

    public void buscarPorNombre(String nombre) {
        try {
            Playlist p = control.buscarPorNombre(nombre);
            JOptionPane.showMessageDialog(null, "Encontrada: " + p.getNombre());
        } catch (RecursoNoEncontradoException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } catch (AccesoDatosException e) {
            JOptionPane.showMessageDialog(null, "BD: " + e.getMessage());
        }
    }

    public void actualizarPorNombre(Playlist p) {
        try {
            int filas = control.actualizarPorNombre(p);
            JOptionPane.showMessageDialog(null, "Filas afectadas: " + filas);
        } catch (ValidacionException e) {
            JOptionPane.showMessageDialog(null, "Validación: " + e.getMessage());
        } catch (AccesoDatosException e) {
            JOptionPane.showMessageDialog(null, "BD: " + e.getMessage());
        }
    }
}
