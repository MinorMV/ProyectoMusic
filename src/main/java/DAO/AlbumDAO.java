package DAO;

import Logic.Album;
import Logic.Artista;
import Logic.Cancion;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

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
        ps.close();

        int idAlbum = obtenerIdAlbumPorNombre(album.getAlbumNombre());

        for (Cancion cancion : album.getCanciones()) {
            int idCancion = obtenerIdCancionPorTitulo(cancion.getTitulo());
            insertarRelacionAlbumCancion(idAlbum, idCancion);
        }
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
        // 1. Actualizar datos del álbum
        String sql = "UPDATE ALBUMES SET FECHA_CREACION = ?, GENERO = ?, DESCARGAS = ?, ID_ARTISTA = ? WHERE NOMBRE = ?";
        PreparedStatement ps = conexion.prepareStatement(sql);
        ps.setDate(1, Date.valueOf(album.getFechaCreacion()));
        ps.setString(2, album.getGenero());
        ps.setInt(3, album.getDescargas());

        int idArtista = obtenerIdArtistaPorNombre(album.getArtista().getNombre());
        ps.setInt(4, idArtista);
        ps.setString(5, album.getAlbumNombre());
        ps.executeUpdate();
        ps.close();

        // 2. Obtener el ID del álbum
        int idAlbum = obtenerIdAlbumPorNombre(album.getAlbumNombre());

        // 3. Eliminar canciones antiguas del álbum
        eliminarRelacionesAlbumCancion(idAlbum);

        // 4. Insertar nuevas canciones asociadas
        for (Cancion cancion : album.getCanciones()) {
            int idCancion = obtenerIdCancionPorTitulo(cancion.getTitulo());
            insertarRelacionAlbumCancion(idAlbum, idCancion);
        }
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

    private int obtenerIdAlbumPorNombre(String nombre) throws Exception {
        String sql = "SELECT ID_ALBUM FROM ALBUMES WHERE NOMBRE = ?";
        PreparedStatement ps = conexion.prepareStatement(sql);
        ps.setString(1, nombre);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            return rs.getInt("ID_ALBUM");
        } else {
            throw new Exception("No se encontró el álbum con nombre: " + nombre);
        }
    }

    public int obtenerIdCancionPorTitulo(String titulo) throws Exception {
        String sql = "SELECT ID_CANCION FROM CANCIONES WHERE TRIM(UPPER(TITULO)) = TRIM(UPPER(?))";
        PreparedStatement ps = conexion.prepareStatement(sql);
        ps.setString(1, titulo);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            return rs.getInt("ID_CANCION");
        } else {
            throw new Exception("No se encontró la canción con título: " + titulo);
        }
    }

    private void insertarRelacionAlbumCancion(int idAlbum, int idCancion) throws Exception {
        String sql = "INSERT INTO ALBUM_CANCION (ID_ALBUM, ID_CANCION) VALUES (?, ?)";
        PreparedStatement ps = conexion.prepareStatement(sql);
        ps.setInt(1, idAlbum);
        ps.setInt(2, idCancion);
        ps.executeUpdate();
    }

    private void eliminarRelacionesAlbumCancion(int idAlbum) throws Exception {
        String sql = "DELETE FROM ALBUM_CANCION WHERE ID_ALBUM = ?";
        PreparedStatement ps = conexion.prepareStatement(sql);
        ps.setInt(1, idAlbum);
        ps.executeUpdate();
    }
}
