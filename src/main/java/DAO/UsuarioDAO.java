package DAO;

import Logic.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UsuarioDAO {

    private final Connection con;

    public UsuarioDAO(Connection con) {
        this.con = con;
    }

    public boolean insertarUsuario(Usuario usuario) {
        String sql = "INSERT INTO USUARIOS (nombre, correo, contrasena) VALUES (?, ?, ?)";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, usuario.getNombre());
            ps.setString(2, usuario.getCorreo());
            ps.setString(3, usuario.getContrasena());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            System.out.println("Error al insertar usuario: " + e.getMessage());
            return false;
        }
    }

    public Usuario buscarUsuarioPorCorreo(String correo) {
        String sql = "SELECT * FROM USUARIOS WHERE correo = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, correo);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Usuario u = new Usuario();
                u.setNombre(rs.getString("nombre"));
                u.setCorreo(rs.getString("correo"));
                u.setContrasena(rs.getString("contrasena"));
                return u;
            }
        } catch (Exception e) {
            System.out.println("Error al buscar usuario: " + e.getMessage());
        }
        return null;
    }

    public boolean modificarUsuario(Usuario usuario) {
        String sql = "UPDATE USUARIOS SET nombre = ?, contrasena = ? WHERE correo = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, usuario.getNombre());
            ps.setString(2, usuario.getContrasena());
            ps.setString(3, usuario.getCorreo());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            System.out.println("Error al modificar usuario: " + e.getMessage());
            return false;
        }
    }

    public boolean eliminarUsuarioPorCorreo(String correo) {
        String sql = "DELETE FROM USUARIOS WHERE correo = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, correo);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            System.out.println("Error al eliminar usuario: " + e.getMessage());
            return false;
        }
    }
}
