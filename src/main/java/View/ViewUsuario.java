package View;

import javax.swing.JOptionPane;
import ControlMain.ControlUsuario;
import Logic.Usuario;

// CAMBIO: imports de excepciones
import Excepciones.ValidacionException;           // CAMBIO
import Excepciones.AccesoDatosException;          // CAMBIO
import Excepciones.RecursoNoEncontradoException;  // CAMBIO

public class ViewUsuario {

    private final ControlUsuario control;

    public ViewUsuario(ControlUsuario control) {
        this.control = control;
    }

    public void crearUsuario(Usuario u) {
        try {
            control.crear(u); // (tu llamada existente)
            JOptionPane.showMessageDialog(null, "Usuario registrado correctamente.");
        } catch (ValidacionException e) { // CAMBIO
            JOptionPane.showMessageDialog(null, "Error de validación: " + e.getMessage());
        } catch (AccesoDatosException e) { // CAMBIO
            JOptionPane.showMessageDialog(null, "Error de base de datos: " + e.getMessage());
        }
    }

    public void buscarPorCorreo(String correo) {
        try {
            Usuario u = control.buscarPorCorreo(correo); // (tu llamada existente)
            JOptionPane.showMessageDialog(null, "Encontrado: " + u.getNombre() + " (" + u.getCorreo() + ")");
        } catch (RecursoNoEncontradoException e) { // CAMBIO
            JOptionPane.showMessageDialog(null, e.getMessage());
        } catch (AccesoDatosException e) { // CAMBIO
            JOptionPane.showMessageDialog(null, "Error de base de datos: " + e.getMessage());
        }
    }

    public void actualizarPorCorreo(Usuario u) {
        try {
            int filas = control.actualizarPorCorreo(u); // (tu llamada existente)
            JOptionPane.showMessageDialog(null, "Filas afectadas: " + filas);
        } catch (ValidacionException e) { // CAMBIO
            JOptionPane.showMessageDialog(null, "Error de validación: " + e.getMessage());
        } catch (AccesoDatosException e) { // CAMBIO
            JOptionPane.showMessageDialog(null, "Error de base de datos: " + e.getMessage());
        }
    }
}
