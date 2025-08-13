package View;

import javax.swing.JOptionPane;
import ControlMain.ControlCancion;
import Logic.Cancion;

// CAMBIO: excepciones
import Excepciones.ValidacionException;           // CAMBIO
import Excepciones.AccesoDatosException;          // CAMBIO
import Excepciones.RecursoNoEncontradoException;  // CAMBIO

public class ViewCancion {

    private final ControlCancion control;

    public ViewCancion(ControlCancion control) {
        this.control = control;
    }

    public void crear(Cancion c) {
        try {
            control.crear(c);
            JOptionPane.showMessageDialog(null, "Canción insertada.");
        } catch (ValidacionException e) {
            JOptionPane.showMessageDialog(null, "Validación: " + e.getMessage());
        } catch (AccesoDatosException e) {
            JOptionPane.showMessageDialog(null, "BD: " + e.getMessage());
        }
    }

    public void buscarPorNombre(String nombre) {
        try {
            Cancion c = control.buscarPorNombre(nombre);
            JOptionPane.showMessageDialog(null, "Encontrada: " + c.getNombre());
        } catch (RecursoNoEncontradoException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } catch (AccesoDatosException e) {
            JOptionPane.showMessageDialog(null, "BD: " + e.getMessage());
        }
    }

    public void actualizarPorNombre(Cancion c) {
        try {
            int filas = control.actualizarPorNombre(c);
            JOptionPane.showMessageDialog(null, "Filas afectadas: " + filas);
        } catch (ValidacionException e) {
            JOptionPane.showMessageDialog(null, "Validación: " + e.getMessage());
        } catch (AccesoDatosException e) {
            JOptionPane.showMessageDialog(null, "BD: " + e.getMessage());
        }
    }
}
