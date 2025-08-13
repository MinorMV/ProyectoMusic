package View;

import javax.swing.JOptionPane;
import ControlMain.ControlArtista;
import Logic.Artista;

// CAMBIO: excepciones
import Excepciones.ValidacionException;           // CAMBIO
import Excepciones.AccesoDatosException;          // CAMBIO
import Excepciones.RecursoNoEncontradoException;  // CAMBIO

public class ViewArtista {

    private final ControlArtista control;

    public ViewArtista(ControlArtista control) {
        this.control = control;
    }

    public void crearArtista(Artista a) {
        try {
            control.crear(a);
            JOptionPane.showMessageDialog(null, "Artista insertado correctamente.");
        } catch (ValidacionException e) {
            JOptionPane.showMessageDialog(null, "Validación: " + e.getMessage());
        } catch (AccesoDatosException e) {
            JOptionPane.showMessageDialog(null, "BD: " + e.getMessage());
        }
    }

    public void buscarPorNombre(String nombre) {
        try {
            Artista a = control.buscarPorNombre(nombre);
            JOptionPane.showMessageDialog(null, "Encontrado: " + a.getNombre());
        } catch (RecursoNoEncontradoException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } catch (AccesoDatosException e) {
            JOptionPane.showMessageDialog(null, "BD: " + e.getMessage());
        }
    }

    public void actualizarPorNombre(Artista a) {
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
