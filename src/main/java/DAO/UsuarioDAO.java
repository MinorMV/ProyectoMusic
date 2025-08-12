package DAO;

import Logic.Usuario;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

public class UsuarioDAO {

    private final Connection con;

    public UsuarioDAO(Connection con) {
        this.con = con;
    }

    public void insertar(Usuario usuario) throws SQLException {
        String sql = "{call INSERTAR_USUARIO(?, ?, ?, ?)}";
        try (CallableStatement cs = con.prepareCall(sql)) {
            cs.setString(1, usuario.getNombre());
            cs.setString(2, usuario.getCorreo());
            cs.setString(3, usuario.getContrasena());
            cs.setString(4, usuario.getPais());
            cs.execute();
        }
    }

    public Usuario buscar(String correo) throws SQLException {
        String sql = "{call BUSCAR_USUARIO_POR_CORREO(?, ?, ?, ?)}";
        try (CallableStatement cs = con.prepareCall(sql)) {
            cs.setString(1, correo);
            cs.registerOutParameter(2, java.sql.Types.VARCHAR);
            cs.registerOutParameter(3, java.sql.Types.VARCHAR);
            cs.registerOutParameter(4, java.sql.Types.VARCHAR);
            cs.execute();

            String nombre = cs.getString(2);
            String contrasena = cs.getString(3);
            String pais = cs.getString(4);

            if (nombre == null) {
                return null;
            }

            Usuario u = new Usuario();
            u.setCorreo(correo);
            u.setNombre(nombre);
            u.setContrasena(contrasena);
            u.setPais(pais);
            return u;
        }
    }

    public void modificar(Usuario usuario) throws SQLException {
        String sql = "{call MODIFICAR_USUARIO(?, ?, ?, ?)}";
        try (CallableStatement cs = con.prepareCall(sql)) {
            cs.setString(1, usuario.getCorreo());
            cs.setString(2, usuario.getNombre());
            cs.setString(3, usuario.getContrasena());
            cs.setString(4, usuario.getPais());
            cs.execute();
        }
    }

    public void eliminar(String correo) throws SQLException {
        String sql = "{call ELIMINAR_USUARIO(?)}";
        try (CallableStatement cs = con.prepareCall(sql)) {
            cs.setString(1, correo);
            cs.execute();
        }
    }

    public int asegurarAdmin() throws SQLException {
        try (CallableStatement cs = con.prepareCall("{ call ASEGURAR_ADMIN(?, ?, ?, ?, ?) }")) {
            cs.setString(1, "admin@app.com");
            cs.setString(2, "Admin");
            cs.setString(3, "admin123");
            cs.setString(4, "NA");
            cs.registerOutParameter(5, java.sql.Types.NUMERIC);
            cs.execute();
            return cs.getInt(5);
        }
    }

    public void registrarAdmin(Usuario admin) throws SQLException {
        String sql = "{call REGISTRAR_ADMIN(?, ?, ?, ?)}";
        try (CallableStatement cs = con.prepareCall(sql)) {
            cs.setString(1, admin.getNombre());
            cs.setString(2, admin.getCorreo());
            cs.setString(3, admin.getContrasena());
            cs.setString(4, admin.getPais());
            cs.execute();
        }
    }

    public void promoverAAdmin(String correo) throws SQLException {
        String sql = "{call PROMOVER_A_ADMIN(?)}";
        try (CallableStatement cs = con.prepareCall(sql)) {
            cs.setString(1, correo);
            cs.execute();
        }
    }

    public String obtenerRol(String correo) throws SQLException {
        String sql = "{call OBTENER_ROL(?, ?)}";
        try (CallableStatement cs = con.prepareCall(sql)) {
            cs.setString(1, correo);
            cs.registerOutParameter(2, java.sql.Types.VARCHAR);
            cs.execute();
            return cs.getString(2);
        }
    }

}
