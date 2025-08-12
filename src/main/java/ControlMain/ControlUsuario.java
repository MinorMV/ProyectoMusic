// src/main/java/ControlMain/ControlUsuario.java
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

    public void asegurarAdmin(View.ViewUsuario view) {
        try {
            int created = new DAO.UsuarioDAO(con).asegurarAdmin();
            if (created == 1) {
                view.mostrarMensajeInfo("Admin por defecto creado/promovido.");
            }

        } catch (Exception e) {

        }
    }

    public void registrarAdmin(ViewUsuario view) {
        try {
            view.capturaNombre();
            view.capturaCorreo();
            view.capturaContrasena();
            view.capturaPais();

            Usuario admin = new Usuario(view.getVnombre(), view.getVcorreo(), view.getVcontrasena(), view.getVpais(), "ADMIN");
            new UsuarioDAO(con).registrarAdmin(admin);
            view.mostrarMensajeExito("Administrador registrado correctamente.");
        } catch (SQLException e) {
            view.mostrarError("Error al registrar admin: " + e.getMessage());
        }
    }

    public void promoverAAdmin(ViewUsuario view) {
        try {
            view.capturaCorreo();
            new UsuarioDAO(con).promoverAAdmin(view.getVcorreo());
            view.mostrarMensajeExito("Usuario promovido a ADMIN correctamente.");
        } catch (SQLException e) {
            view.mostrarError("Error al promover a ADMIN: " + e.getMessage());
        }
    }


    public Usuario iniciarSesion(ViewUsuario view) {
        try {
            view.capturaCorreo();
            view.capturaContrasena();

            UsuarioDAO dao = new UsuarioDAO(con);

            Usuario u = dao.buscar(view.getVcorreo());
            if (u == null) {
                view.mostrarMensajeError("Usuario no encontrado.");
                return null;
            }


            if (!u.getContrasena().equals(view.getVcontrasena())) {
                view.mostrarMensajeError("Contraseña incorrecta.");
                return null;
            }

            String rol = dao.obtenerRol(u.getCorreo()); 
            u.setRol(rol != null ? rol : "USER");

            view.mostrarMensajeExito("Bienvenido, " + u.getNombre() + " (" + u.getRol() + ")");
            return u;

        } catch (Exception e) {
            view.mostrarError("Error al iniciar sesión: " + e.getMessage());
            return null;
        }
    }
}
