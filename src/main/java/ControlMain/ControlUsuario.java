package ControlMain;

import Logic.Usuario;
import View.ViewUsuario;
import DAO.UsuarioDAO;
import java.sql.Connection;
import javax.swing.JOptionPane;

public class ControlUsuario {

    private final UsuarioDAO usuarioDAO;

    public ControlUsuario(Connection con) {
        this.usuarioDAO = new UsuarioDAO(con);
    }

    public void capturarDatosUsuario(ViewUsuario vu, Usuario mu) {
        vu.capturaNombre();
        mu.setNombre(vu.getVnombre());

        vu.capturaCorreo();
        mu.setCorreo(vu.getVcorreo());

        vu.capturaContrasena();
        mu.setContrasena(vu.getVcontrasena());

        vu.mostrarInformacionUsuario();

        boolean exito = usuarioDAO.insertarUsuario(mu);
        if (exito) {
            System.out.println("Usuario guardado exitosamente en la base de datos.");
        } else {
            System.out.println("Hubo un error al guardar el usuario.");
        }
    }

    public void buscarYMostrarUsuarioPorCorreo(ViewUsuario vu) {
        vu.capturaCorreo();
        Usuario encontrado = usuarioDAO.buscarUsuarioPorCorreo(vu.getVcorreo());

        if (encontrado != null) {
            JOptionPane.showMessageDialog(null,
                "Usuario encontrado:\n" +
                "Nombre: " + encontrado.getNombre() + "\n" +
                "Correo: " + encontrado.getCorreo() + "\n" +
                "Contraseña: " + encontrado.getContrasena());
        } else {
            JOptionPane.showMessageDialog(null, "Usuario no encontrado.");
        }
    }

    public void modificarUsuarioPorCorreo(ViewUsuario vu) {
        vu.capturaCorreo();
        String correoBuscar = vu.getVcorreo();

        Usuario usuarioExistente = usuarioDAO.buscarUsuarioPorCorreo(correoBuscar);

        if (usuarioExistente != null) {
            JOptionPane.showMessageDialog(null, "Usuario encontrado. Ingresa los nuevos datos:");

            vu.capturaNombre();
            vu.capturaContrasena();

            usuarioExistente.setNombre(vu.getVnombre());
            usuarioExistente.setContrasena(vu.getVcontrasena());

            boolean actualizado = usuarioDAO.modificarUsuario(usuarioExistente);
            if (actualizado) {
                JOptionPane.showMessageDialog(null, "Usuario modificado correctamente.");
            } else {
                JOptionPane.showMessageDialog(null, "Error al modificar el usuario.");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Usuario no encontrado.");
        }
    }

    public void eliminarUsuarioPorCorreo(ViewUsuario vu) {
        vu.capturaCorreo();
        String correoEliminar = vu.getVcorreo();

        int confirmacion = JOptionPane.showConfirmDialog(null,
                "¿Estás seguro que deseas eliminar el usuario con correo " + correoEliminar + "?",
                "Confirmar eliminación",
                JOptionPane.YES_NO_OPTION);

        if (confirmacion == JOptionPane.YES_OPTION) {
            boolean eliminado = usuarioDAO.eliminarUsuarioPorCorreo(correoEliminar);
            if (eliminado) {
                JOptionPane.showMessageDialog(null, " Usuario eliminado correctamente.");
            } else {
                JOptionPane.showMessageDialog(null, " No se pudo eliminar el usuario.");
            }
        }
    }
}
