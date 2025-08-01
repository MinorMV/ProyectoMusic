package DAO;

import Logic.Cancion;
import java.sql.*;
import javax.swing.JOptionPane;

public class CancionDAO {
    private Connection conexion;

    public CancionDAO(Connection conexion) {
        this.conexion = conexion;
    }

    public void insertarCancion(Cancion cancion) throws SQLException {
        String sql = "INSERT INTO CANCIONES (TITULO, ALBUM, DURACION, GENERO, ID_ARTISTA) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, cancion.getTitulo());
            ps.setString(2, cancion.getAlbum());
            ps.setString(3, cancion.getDuracion()); // ahora es String
            ps.setString(4, cancion.getGenero());

            int idArtista = obtenerIdArtistaPorNombre(cancion.getArtista().getNombre());
            ps.setInt(5, idArtista);

            ps.executeUpdate();
        }
    }

    public Cancion buscarPorTitulo(String titulo) throws SQLException {
        String sql = "SELECT C.TITULO, C.ALBUM, C.DURACION, C.GENERO, A.NOMBRE AS NOMBRE_ARTISTA FROM CANCIONES C JOIN ARTISTAS A ON C.ID_ARTISTA = A.ID_ARTISTA WHERE C.TITULO = ?";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, titulo);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Cancion c = new Cancion();
                    c.setTitulo(rs.getString("TITULO"));
                    c.setAlbum(rs.getString("ALBUM"));
                    c.setDuracion(rs.getString("DURACION")); // ahora es String
                    c.setGenero(rs.getString("GENERO"));
                    c.setArtista(new Logic.Artista(rs.getString("NOMBRE_ARTISTA")));
                    return c;
                } else {
                    return null;
                }
            }
        }
    }

    public void modificarCancion(Cancion cancion) throws SQLException {
        String sql = "UPDATE CANCIONES SET ALBUM = ?, DURACION = ?, GENERO = ?, ID_ARTISTA = ? WHERE TITULO = ?";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, cancion.getAlbum());
            ps.setString(2, cancion.getDuracion());
            ps.setString(3, cancion.getGenero());
            int idArtista = obtenerIdArtistaPorNombre(cancion.getArtista().getNombre());
            ps.setInt(4, idArtista);
            ps.setString(5, cancion.getTitulo());
            ps.executeUpdate();
        }
    }

    public void eliminarCancion(String titulo) throws SQLException {
        String sql = "DELETE FROM CANCIONES WHERE TITULO = ?";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, titulo);
            ps.executeUpdate();
        }
    }

    private int obtenerIdArtistaPorNombre(String nombre) throws SQLException {
        String sql = "SELECT ID_ARTISTA FROM ARTISTAS WHERE NOMBRE = ?";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, nombre);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("ID_ARTISTA");
                } else {
                    throw new SQLException("No se encontró el artista con nombre: " + nombre);
                }
            }
        }
    }
}
