package View;

import javax.swing.JOptionPane;
import ControlMain.ControlAlbum;
import Logic.Album;

// CAMBIO: excepciones
import Excepciones.ValidacionException;           // CAMBIO
import Excepciones.AccesoDatosException;          // CAMBIO
import Excepciones.RecursoNoEncontradoException;  // CAMBIO

public class ViewAlbum {

    private final ControlAlbum control;

    public ViewAlbum(ControlAlbum control) {
        this.control = control;
    }

    public void crear(Album a) {
        try {
            control.crear(a);
            JOptionPane.showMessageDialog(null, "Álbum insertado.");
        } catch (ValidacionException e) {
            JOptionPane.showMessageDialog(null, "Validación: " + e.getMessage());
        } catch (AccesoDatosException e) {
            JOptionPane.showMessageDialog(null, "BD: " + e.getMessage());
        }
    }

    public void buscarPorNombre(String nombre) {
        try {
            Album a = control.buscarPorNombre(nombre);
            JOptionPane.showMessageDialog(null, "Encontrado: " + a.getAlbumNombre());
        } catch (RecursoNoEncontradoException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } catch (AccesoDatosException e) {
            JOptionPane.showMessageDialog(null, "BD: " + e.getMessage());
        }
    }

    public void actualizarPorNombre(Album a) {
        try {
            int filas = control.actualizarPorNombre(a);
            JOptionPane.showMessageDialog(null, "Filas afectadas: " + filas);
        } catch (ValidacionException e) {
            JOptionPane.showMessageDialog(null, "Validación: " + e.getMessage());
        } catch (AccesoDatosException e) {
            JOptionPane.showMessageDialog(null, "BD: " + e.getMessage());
        }
    }
}
