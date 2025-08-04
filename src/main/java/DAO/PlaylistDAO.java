package DAO;

import Logic.Artista;
import Logic.Cancion;
import Logic.Playlist;
import Logic.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PlaylistDAO {

    private Connection conexion;

    public PlaylistDAO(Connection conexion) {
        this.conexion = conexion;
    }

    public void insertarPlaylist(Playlist playlist) throws SQLException {
        String sql = "INSERT INTO PLAYLISTS (NOMBRE, ID_USUARIO) VALUES (?, ?)";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, playlist.getNombre());
            int idUsuario = obtenerIdUsuarioPorNombre(playlist.getUsuario().getNombre());
            ps.setInt(2, idUsuario);
            ps.executeUpdate();
        }

        int idPlaylist = obtenerIdPlaylistPorNombre(playlist.getNombre());

        for (Cancion cancion : playlist.getCanciones()) {
            int idCancion = obtenerIdCancionPorTitulo(cancion.getTitulo());
            insertarRelacionCancionPlaylist(idPlaylist, idCancion);
        }
    }

    public Playlist buscarPorNombre(String nombrePlaylist) throws SQLException {
        String sql = """
            SELECT P.NOMBRE, U.NOMBRE AS NOMBRE_USUARIO, U.CORREO, U.CONTRASENA
            FROM PLAYLISTS P
            JOIN USUARIOS U ON P.ID_USUARIO = U.ID_USUARIO
            WHERE P.NOMBRE = ?
        """;
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, nombrePlaylist);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Usuario usuario = new Usuario(
                        rs.getString("NOMBRE_USUARIO"),
                        rs.getString("CORREO"),
                        rs.getString("CONTRASENA")
                    );
                    Playlist p = new Playlist(nombrePlaylist, usuario);
                    p.getCanciones().addAll(obtenerCancionesDePlaylist(nombrePlaylist));
                    return p;
                }
            }
        }
        return null;
    }

    public void eliminarPlaylist(String nombrePlaylist) throws SQLException {
        int idPlaylist = obtenerIdPlaylistPorNombre(nombrePlaylist);

        // Eliminar relaciones con canciones
        String sqlIntermedia = "DELETE FROM CANCIONES_PLAYLIST WHERE ID_PLAYLIST = ?";
        try (PreparedStatement ps = conexion.prepareStatement(sqlIntermedia)) {
            ps.setInt(1, idPlaylist);
            ps.executeUpdate();
        }

        // Eliminar la playlist
        String sql = "DELETE FROM PLAYLISTS WHERE NOMBRE = ?";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, nombrePlaylist);
            ps.executeUpdate();
        }
    }

    // ========================================
    // MÉTODOS AUXILIARES
    // ========================================

    private int obtenerIdUsuarioPorNombre(String nombreUsuario) throws SQLException {
        String sql = "SELECT ID_USUARIO FROM USUARIOS WHERE NOMBRE = ?";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, nombreUsuario);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return rs.getInt("ID_USUARIO");
                else throw new SQLException("Usuario no encontrado: " + nombreUsuario);
            }
        }
    }

    private int obtenerIdPlaylistPorNombre(String nombre) throws SQLException {
        String sql = "SELECT ID_PLAYLIST FROM PLAYLISTS WHERE NOMBRE = ?";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, nombre);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return rs.getInt("ID_PLAYLIST");
                else throw new SQLException("Playlist no encontrada: " + nombre);
            }
        }
    }

    private int obtenerIdCancionPorTitulo(String titulo) throws SQLException {
        String sql = "SELECT ID_CANCION FROM CANCIONES WHERE TRIM(UPPER(TITULO)) = TRIM(UPPER(?))";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, titulo);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return rs.getInt("ID_CANCION");
                else throw new SQLException("Canción no encontrada: " + titulo);
            }
        }
    }

    private void insertarRelacionCancionPlaylist(int idPlaylist, int idCancion) throws SQLException {
        String sql = "INSERT INTO CANCIONES_PLAYLIST (ID_PLAYLIST, ID_CANCION) VALUES (?, ?)";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, idPlaylist);
            ps.setInt(2, idCancion);
            ps.executeUpdate();
        }
    }

    private ArrayList<Cancion> obtenerCancionesDePlaylist(String nombrePlaylist) throws SQLException {
        ArrayList<Cancion> canciones = new ArrayList<>();
        int idPlaylist = obtenerIdPlaylistPorNombre(nombrePlaylist);

        String sql = """
            SELECT C.TITULO, C.ALBUM, C.DURACION, C.GENERO, A.NOMBRE AS NOMBRE_ARTISTA
            FROM CANCIONES_PLAYLIST CP
            JOIN CANCIONES C ON CP.ID_CANCION = C.ID_CANCION
            JOIN ARTISTAS A ON C.ID_ARTISTA = A.ID_ARTISTA
            WHERE CP.ID_PLAYLIST = ?
        """;

        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, idPlaylist);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Cancion c = new Cancion();
                    c.setTitulo(rs.getString("TITULO"));
                    c.setAlbum(rs.getString("ALBUM"));
                    c.setDuracion(rs.getString("DURACION"));
                    c.setGenero(rs.getString("GENERO"));
                    c.setArtista(new Artista(rs.getString("NOMBRE_ARTISTA")));
                    canciones.add(c);
                }
            }
        }

        return canciones;
    }
}
