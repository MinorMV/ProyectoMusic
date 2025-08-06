// ===== ControlMain/ControlUsuario.java =====
package ControlMain;

import Logic.Usuario;
import View.ViewUsuario;
import DAO.UsuarioDAO;
import java.sql.Connection;
import java.sql.SQLException;

public class ControlUsuario {
    private final Connection con;

    public ControlUsuario(Connection con) {
        this.con = con;
    }

    public void insertarUsuario(ViewUsuario view) {
        try {
            view.capturaNombre();
            view.capturaCorreo();
            view.capturaContrasena();
            view.capturaPais();

            Usuario u = new Usuario(view.getVnombre(), view.getVcorreo(), view.getVcontrasena(), view.getVpais());
            UsuarioDAO dao = new UsuarioDAO(con);
            dao.insertar(u);
            view.mostrarMensajeExito("Usuario insertado correctamente.");
        } catch (SQLException e) {
            view.mostrarError("Error al insertar usuario: " + e.getMessage());
        }
    }

    public void buscarUsuario(ViewUsuario view) {
        try {
            view.capturaCorreo();
            UsuarioDAO dao = new UsuarioDAO(con);
            Usuario u = dao.buscar(view.getVcorreo());
            if (u != null) {
                view.mostrarDatos(u);
            } else {
                view.mostrarMensajeError("Usuario no encontrado.");
            }
        } catch (SQLException e) {
            view.mostrarError("Error al buscar usuario: " + e.getMessage());
        }
    }

    public void modificarUsuario(ViewUsuario view) {
        try {
            view.capturaCorreo();
            UsuarioDAO dao = new UsuarioDAO(con);
            Usuario u = dao.buscar(view.getVcorreo());

            if (u != null) {
                view.capturaNombre();
                view.capturaContrasena();
                view.capturaPais();

                u.setNombre(view.getVnombre());
                u.setContrasena(view.getVcontrasena());
                u.setPais(view.getVpais());

                dao.modificar(u);
                view.mostrarMensajeExito("Usuario modificado correctamente.");
            } else {
                view.mostrarMensajeError("No se encontró un usuario con ese correo.");
            }
        } catch (SQLException e) {
            view.mostrarError("Error al modificar usuario: " + e.getMessage());
        }
    }

    public void eliminarUsuario(ViewUsuario view) {
        try {
            view.capturaCorreo();
            UsuarioDAO dao = new UsuarioDAO(con);
            Usuario u = dao.buscar(view.getVcorreo());

            if (u != null) {
                dao.eliminar(view.getVcorreo());
                view.mostrarMensajeExito("Usuario eliminado correctamente.");
            } else {
                view.mostrarMensajeError("No se encontró un usuario con ese correo.");
            }
        } catch (SQLException e) {
            view.mostrarError("Error al eliminar usuario: " + e.getMessage());
        }
    }
}
