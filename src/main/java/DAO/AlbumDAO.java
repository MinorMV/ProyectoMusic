package DAO;

import Logic.Album;
import Logic.Artista;
import Logic.Cancion;
import java.sql.*;
import java.util.ArrayList;
import oracle.jdbc.OracleTypes;

public class AlbumDAO {

    private final Connection conexion;

    public AlbumDAO(Connection conexion) {
        this.conexion = conexion;
    }

    public void insertarAlbum(Album album) throws SQLException {
        CallableStatement cs = conexion.prepareCall("{call insertar_album(?, ?, ?, ?, ?)}");
        cs.setString(1, album.getAlbumNombre());
        cs.setDate(2, Date.valueOf(album.getFechaCreacion()));
        cs.setString(3, album.getGenero());
        cs.setInt(4, album.getDescargas());

        int idArtista = obtenerIdArtistaPorNombre(album.getArtista().getNombre());
        cs.setInt(5, idArtista);
        cs.execute();
        cs.close();

        int idAlbum = obtenerIdAlbumPorNombre(album.getAlbumNombre());

        for (Cancion cancion : album.getCanciones()) {
            int idCancion = obtenerIdCancionPorTitulo(cancion.getTitulo());
            insertarRelacionAlbumCancion(idAlbum, idCancion);
        }
    }

    public Album buscarAlbumPorNombre(String nombre) throws SQLException {
        CallableStatement cs = conexion.prepareCall("{ ? = call buscar_album(?) }");
        cs.registerOutParameter(1, OracleTypes.CURSOR);
        cs.setString(2, nombre);
        cs.execute();

        ResultSet rs = (ResultSet) cs.getObject(1);
        if (rs.next()) {
            Album album = new Album();
            album.setAlbumNombre(rs.getString("NOMBRE"));
            album.setFechaCreacion(rs.getDate("FECHA_CREACION").toLocalDate());
            album.setGenero(rs.getString("GENERO"));
            album.setDescargas(rs.getInt("DESCARGAS"));

            Artista artista = new Artista();
            artista.setIdArtista(rs.getInt("ID_ARTISTA"));
            album.setArtista(artista);
            return album;
        } else {
            return null;
        }
    }

    public void modificarAlbum(Album album) throws SQLException {
        CallableStatement cs = conexion.prepareCall("{call modificar_album(?, ?, ?, ?, ?)}");
        cs.setString(1, album.getAlbumNombre());
        cs.setDate(2, Date.valueOf(album.getFechaCreacion()));
        cs.setString(3, album.getGenero());
        cs.setInt(4, album.getDescargas());

        int idArtista = obtenerIdArtistaPorNombre(album.getArtista().getNombre());
        cs.setInt(5, idArtista);
        cs.execute();
        cs.close();

        int idAlbum = obtenerIdAlbumPorNombre(album.getAlbumNombre());
        eliminarRelacionesAlbumCancion(idAlbum);
        for (Cancion cancion : album.getCanciones()) {
            int idCancion = obtenerIdCancionPorTitulo(cancion.getTitulo());
            insertarRelacionAlbumCancion(idAlbum, idCancion);
        }
    }

    public void eliminarAlbumPorNombre(String nombre) throws SQLException {
        CallableStatement cs = conexion.prepareCall("{call eliminar_album(?)}");
        cs.setString(1, nombre);
        cs.execute();
        cs.close();
    }

    private int obtenerIdArtistaPorNombre(String nombre) throws SQLException {
        String sql = "SELECT ID_ARTISTA FROM ARTISTAS WHERE NOMBRE = ?";
        PreparedStatement ps = conexion.prepareStatement(sql);
        ps.setString(1, nombre);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            return rs.getInt("ID_ARTISTA");
        } else {
            throw new SQLException("Artista no encontrado con nombre: " + nombre);
        }
    }

    private int obtenerIdAlbumPorNombre(String nombre) throws SQLException {
        String sql = "SELECT ID_ALBUM FROM ALBUMES WHERE NOMBRE = ?";
        PreparedStatement ps = conexion.prepareStatement(sql);
        ps.setString(1, nombre);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            return rs.getInt("ID_ALBUM");
        } else {
            throw new SQLException("Álbum no encontrado con nombre: " + nombre);
        }
    }

    private int obtenerIdCancionPorTitulo(String titulo) throws SQLException {
        String sql = "SELECT ID_CANCION FROM CANCIONES WHERE TRIM(UPPER(TITULO)) = TRIM(UPPER(?))";
        PreparedStatement ps = conexion.prepareStatement(sql);
        ps.setString(1, titulo);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            return rs.getInt("ID_CANCION");
        } else {
            throw new SQLException("Canción no encontrada con título: " + titulo);
        }
    }

    private void insertarRelacionAlbumCancion(int idAlbum, int idCancion) throws SQLException {
        String sql = "INSERT INTO ALBUM_CANCION (ID_ALBUM, ID_CANCION) VALUES (?, ?)";
        PreparedStatement ps = conexion.prepareStatement(sql);
        ps.setInt(1, idAlbum);
        ps.setInt(2, idCancion);
        ps.executeUpdate();
    }

    private void eliminarRelacionesAlbumCancion(int idAlbum) throws SQLException {
        String sql = "DELETE FROM ALBUM_CANCION WHERE ID_ALBUM = ?";
        PreparedStatement ps = conexion.prepareStatement(sql);
        ps.setInt(1, idAlbum);
        ps.executeUpdate();
    }
}
