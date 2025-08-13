package DAO;

import Logic.Usuario;
import java.sql.Connection;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

// CAMBIO: imports de excepciones
import Excepciones.ValidacionException;           // CAMBIO
import Excepciones.AccesoDatosException;          // CAMBIO
import Excepciones.RecursoNoEncontradoException;  // CAMBIO

public class UsuarioDAO {

    private Connection conexion;

    public UsuarioDAO(Connection conexion) {
        this.conexion = conexion;
    }

    // CAMBIO: throws específicos
    public void insertar(Usuario usuario) throws ValidacionException, AccesoDatosException { // CAMBIO
        try {
            // (Deja tu implementación real aquí)
            CallableStatement cs = conexion.prepareCall("{ call INSERTAR_USUARIO(?, ?, ?, ?) }");
            cs.setString(1, usuario.getNombre());
            cs.setString(2, usuario.getCorreo());
            cs.setString(3, usuario.getContrasena());
            cs.setString(4, usuario.getPais());
            cs.executeUpdate();
        } catch (SQLException e) {
            throw new AccesoDatosException("Error al insertar usuario", e); // CAMBIO
        }
    }

    // CAMBIO: throws específicos
    public Usuario buscarPorCorreo(String correo)
            throws AccesoDatosException, RecursoNoEncontradoException { // CAMBIO
        try {
            CallableStatement cs = conexion.prepareCall("{ call BUSCAR_USUARIO_POR_CORREO(?) }");
            cs.setString(1, correo);
            ResultSet rs = cs.executeQuery();
            if (rs.next()) {
                Usuario u = new Usuario();
                u.setNombre(rs.getString("NOMBRE"));
                u.setCorreo(rs.getString("CORREO"));
                u.setContrasena(rs.getString("CONTRASENA"));
                u.setPais(rs.getString("PAIS"));
                return u;
            } else {
                throw new RecursoNoEncontradoException("Usuario no encontrado"); // CAMBIO
            }
        } catch (SQLException e) {
            throw new AccesoDatosException("Error al buscar usuario por correo", e); // CAMBIO
        }
    }

    // CAMBIO: throws específicos
    public int modificarPorCorreo(Usuario usuario) throws ValidacionException, AccesoDatosException { // CAMBIO
        try {
            CallableStatement cs = conexion.prepareCall("{ call MODIFICAR_USUARIO_POR_CORREO(?, ?, ?, ?) }");
            cs.setString(1, usuario.getNombre());
            cs.setString(2, usuario.getCorreo());
            cs.setString(3, usuario.getContrasena());
            cs.setString(4, usuario.getPais());
            return cs.executeUpdate();
        } catch (SQLException e) {
            throw new AccesoDatosException("Error al modificar usuario por correo", e); // CAMBIO
        }
    }
}
