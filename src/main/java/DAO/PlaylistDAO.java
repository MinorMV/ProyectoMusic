package DAO;

import Logic.Cancion;
import Logic.Playlist;
import Logic.Usuario;
import java.sql.*;
import java.util.ArrayList;

public class PlaylistDAO {
    private Connection conexion;

    public PlaylistDAO(Connection conexion) {
        this.conexion = conexion;
    }

    public void insertarPlaylist(Playlist playlist) throws SQLException {
        CallableStatement cs = null;
        try {
            int idUsuario = obtenerIdUsuarioPorNombre(playlist.getUsuario().getNombre());

            cs = conexion.prepareCall("{call insertar_playlist(?, ?)}");
            cs.setString(1, playlist.getNombre());
            cs.setInt(2, idUsuario);
            cs.execute();

            int idPlaylist = obtenerIdPlaylistPorNombre(playlist.getNombre());

            for (Cancion c : playlist.getCanciones()) {
                int idCancion = obtenerIdCancionPorTitulo(c.getTitulo());

                CallableStatement cs2 = conexion.prepareCall("{call insertar_cancion_en_playlist(?, ?)}");
                cs2.setInt(1, idPlaylist);
                cs2.setInt(2, idCancion);
                cs2.execute();
                cs2.close();
            }
        } catch (SQLException e) {
            throw new SQLException("Error al insertar playlist: " + e.getMessage());
        } finally {
            if (cs != null) cs.close();
        }
    }

    public Playlist buscarPorNombre(String nombre) throws SQLException {
        Playlist p = null;
        CallableStatement cs = conexion.prepareCall("{call buscar_playlist(?, ?)}");
        cs.setString(1, nombre);
        cs.registerOutParameter(2, oracle.jdbc.OracleTypes.CURSOR);
        cs.execute();

        ResultSet rs = (ResultSet) cs.getObject(2);
        if (rs.next()) {
            String descripcion = rs.getString("DESCRIPCION");
            Usuario u = new Usuario(rs.getString("NOMBRE_USUARIO"), "", "", "");
            p = new Playlist(nombre, u);
            p.setDescripcion(descripcion);
            p.setCanciones(obtenerCancionesDePlaylist(nombre));
        }
        rs.close();
        cs.close();
        return p;
    }

    public void eliminarPlaylist(String nombre) throws SQLException {
        CallableStatement cs = conexion.prepareCall("{call eliminar_playlist(?)}");
        cs.setString(1, nombre);
        cs.execute();
        cs.close();
    }

    public void modificarPlaylist(Playlist playlist) throws SQLException {
        eliminarPlaylist(playlist.getNombre());
        insertarPlaylist(playlist);
    }

    private ArrayList<Cancion> obtenerCancionesDePlaylist(String nombre) throws SQLException {
        ArrayList<Cancion> canciones = new ArrayList<>();
        CallableStatement cs = conexion.prepareCall("{call buscar_canciones_playlist(?, ?)}");
        cs.setString(1, nombre);
        cs.registerOutParameter(2, oracle.jdbc.OracleTypes.CURSOR);
        cs.execute();

        ResultSet rs = (ResultSet) cs.getObject(2);
        while (rs.next()) {
            Cancion c = new Cancion();
            c.setTitulo(rs.getString("TITULO"));
            c.setGenero(rs.getString("GENERO"));
            c.setAlbum(rs.getString("ALBUM"));
            c.setDuracion(rs.getString("DURACION"));
            canciones.add(c);
        }
        rs.close();
        cs.close();
        return canciones;
    }

    private int obtenerIdUsuarioPorNombre(String nombre) throws SQLException {
        String sql = "SELECT id_usuario FROM usuarios WHERE nombre = ?";
        PreparedStatement ps = conexion.prepareStatement(sql);
        ps.setString(1, nombre);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            return rs.getInt("id_usuario");
        } else {
            throw new SQLException("Usuario no encontrado.");
        }
    }

    private int obtenerIdPlaylistPorNombre(String nombre) throws SQLException {
        String sql = "SELECT id_playlist FROM playlists WHERE nombre = ?";
        PreparedStatement ps = conexion.prepareStatement(sql);
        ps.setString(1, nombre);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            return rs.getInt("id_playlist");
        } else {
            throw new SQLException("Playlist no encontrada.");
        }
    }

    private int obtenerIdCancionPorTitulo(String titulo) throws SQLException {
        String sql = "SELECT id_cancion FROM canciones WHERE titulo = ?";
        PreparedStatement ps = conexion.prepareStatement(sql);
        ps.setString(1, titulo);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            return rs.getInt("id_cancion");
        } else {
            throw new SQLException("Canción no encontrada.");
        }
    }
}
