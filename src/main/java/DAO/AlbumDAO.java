/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Logic.Album;
import Logic.Artista;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author MINOR
 */
public class AlbumDAO {

    private Connection conexion;

    public AlbumDAO(Connection conexion) {
        this.conexion = conexion;
    }

    public void insertarAlbum(Album album) throws Exception {
        String sql = "INSERT INTO ALBUMES (NOMBRE, FECHA_CREACION, GENERO, DESCARGAS, ID_ARTISTA) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement ps = conexion.prepareStatement(sql);
        ps.setString(1, album.getAlbumNombre());
        ps.setDate(2, Date.valueOf(album.getFechaCreacion()));
        ps.setString(3, album.getGenero());
        ps.setInt(4, album.getDescargas());

        int idArtista = obtenerIdArtistaPorNombre(album.getArtista().getNombre());
        ps.setInt(5, idArtista);

        ps.executeUpdate();
    }

    public Album buscarAlbumPorNombre(String nombre) throws Exception {
        String sql = "SELECT A.NOMBRE, A.FECHA_CREACION, A.GENERO, A.DESCARGAS, AR.NOMBRE AS NOMBRE_ARTISTA "
                   + "FROM ALBUMES A JOIN ARTISTAS AR ON A.ID_ARTISTA = AR.ID_ARTISTA WHERE A.NOMBRE = ?";
        PreparedStatement ps = conexion.prepareStatement(sql);
        ps.setString(1, nombre);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            Album album = new Album();
            album.setAlbumNombre(rs.getString("NOMBRE"));
            album.setFechaCreacion(rs.getDate("FECHA_CREACION").toLocalDate());
            album.setGenero(rs.getString("GENERO"));
            album.setDescargas(rs.getInt("DESCARGAS"));
            album.setArtista(new Artista(rs.getString("NOMBRE_ARTISTA")));
            return album;
        } else {
            return null;
        }
    }

    public void modificarAlbum(Album album) throws Exception {
        String sql = "UPDATE ALBUMES SET FECHA_CREACION = ?, GENERO = ?, DESCARGAS = ?, ID_ARTISTA = ? WHERE NOMBRE = ?";
        PreparedStatement ps = conexion.prepareStatement(sql);
        ps.setDate(1, Date.valueOf(album.getFechaCreacion()));
        ps.setString(2, album.getGenero());
        ps.setInt(3, album.getDescargas());

        int idArtista = obtenerIdArtistaPorNombre(album.getArtista().getNombre());
        ps.setInt(4, idArtista);

        ps.setString(5, album.getAlbumNombre());

        ps.executeUpdate();
    }

    public void eliminarAlbumPorNombre(String nombre) throws Exception {
        String sql = "DELETE FROM ALBUMES WHERE NOMBRE = ?";
        PreparedStatement ps = conexion.prepareStatement(sql);
        ps.setString(1, nombre);
        ps.executeUpdate();
    }

    private int obtenerIdArtistaPorNombre(String nombre) throws Exception {
        String sql = "SELECT ID_ARTISTA FROM ARTISTAS WHERE NOMBRE = ?";
        PreparedStatement ps = conexion.prepareStatement(sql);
        ps.setString(1, nombre);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            return rs.getInt("ID_ARTISTA");
        } else {
            throw new Exception("No se encontró el artista con nombre: " + nombre);
        }
    }
}
