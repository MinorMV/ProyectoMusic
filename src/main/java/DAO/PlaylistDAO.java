/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
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

/**
 *
 * @author MINOR
 */
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

        for (Cancion cancion : playlist.getCanciones()) {
            insertarCancionEnPlaylist(playlist.getNombre(), cancion.getTitulo());
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
        String sqlIntermedia = "DELETE FROM PLAYLIST_CANCIONES WHERE NOMBRE_PLAYLIST = ?";
        try (PreparedStatement ps = conexion.prepareStatement(sqlIntermedia)) {
            ps.setString(1, nombrePlaylist);
            ps.executeUpdate();
        }

        String sql = "DELETE FROM PLAYLISTS WHERE NOMBRE = ?";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, nombrePlaylist);
            ps.executeUpdate();
        }
    }

    // Métodos auxiliares

    private int obtenerIdUsuarioPorNombre(String nombreUsuario) throws SQLException {
        String sql = "SELECT ID_USUARIO FROM USUARIOS WHERE NOMBRE = ?";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, nombreUsuario);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("ID_USUARIO");
                } else {
                    throw new SQLException("Usuario no encontrado: " + nombreUsuario);
                }
            }
        }
    }

    private void insertarCancionEnPlaylist(String nombrePlaylist, String tituloCancion) throws SQLException {
        String sql = "INSERT INTO PLAYLIST_CANCIONES (NOMBRE_PLAYLIST, TITULO_CANCION) VALUES (?, ?)";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, nombrePlaylist);
            ps.setString(2, tituloCancion);
            ps.executeUpdate();
        }
    }

    private ArrayList<Cancion> obtenerCancionesDePlaylist(String nombrePlaylist) throws SQLException {
        ArrayList<Cancion> canciones = new ArrayList<>();
        String sql = """
            SELECT C.TITULO, C.ALBUM, C.DURACION, C.GENERO, A.NOMBRE AS NOMBRE_ARTISTA
            FROM PLAYLIST_CANCIONES PC
            JOIN CANCIONES C ON PC.TITULO_CANCION = C.TITULO
            JOIN ARTISTAS A ON C.ID_ARTISTA = A.ID_ARTISTA
            WHERE PC.NOMBRE_PLAYLIST = ?
        """;
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, nombrePlaylist);
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
