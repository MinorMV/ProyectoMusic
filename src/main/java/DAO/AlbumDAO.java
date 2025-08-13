package DAO;

import Logic.Album;
import java.sql.Connection;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

// CAMBIO: excepciones
import Excepciones.ValidacionException;           // CAMBIO
import Excepciones.AccesoDatosException;          // CAMBIO
import Excepciones.RecursoNoEncontradoException;  // CAMBIO

public class AlbumDAO {

    private Connection conexion;

    public AlbumDAO(Connection conexion) {
        this.conexion = conexion;
    }

    public void insertarAlbum(Album album) throws ValidacionException, AccesoDatosException { // CAMBIO
        try {
            CallableStatement cs = conexion.prepareCall("{ call INSERTAR_ALBUM(?, ?, ?, ?, ?) }");
            cs.setString(1, album.getAlbumNombre());
            cs.setDate(2, java.sql.Date.valueOf(album.getFechaCreacion()));
            cs.setString(3, album.getGenero());
            cs.setInt(4, album.getDescargas());
            cs.setInt(5, album.getArtista().getIdArtista()); // ajusta si usas otro campo
            cs.executeUpdate();
        } catch (SQLException e) {
            throw new AccesoDatosException("Error al insertar álbum", e); // CAMBIO
        }
    }

    public Album buscarAlbumPorNombre(String nombre)
            throws AccesoDatosException, RecursoNoEncontradoException { // CAMBIO
        try {
            CallableStatement cs = conexion.prepareCall("{ call BUSCAR_ALBUM_POR_NOMBRE(?) }");
            cs.setString(1, nombre);
            ResultSet rs = cs.executeQuery();
            if (rs.next()) {
                Album al = new Album();
                al.setAlbumNombre(rs.getString("NOMBRE"));
                al.setGenero(rs.getString("GENERO"));
                al.setDescargas(rs.getInt("DESCARGAS"));
                al.setFechaCreacion(rs.getDate("FECHA_CREACION").toLocalDate());
                return al;
            } else {
                throw new RecursoNoEncontradoException("Álbum no encontrado"); // CAMBIO
            }
        } catch (SQLException e) {
            throw new AccesoDatosException("Error al buscar álbum", e); // CAMBIO
        }
    }

    public int modificarAlbumPorNombre(Album album)
            throws ValidacionException, AccesoDatosException { // CAMBIO
        try {
            CallableStatement cs = conexion.prepareCall("{ call MODIFICAR_ALBUM_POR_NOMBRE(?, ?, ?, ?) }");
            cs.setString(1, album.getAlbumNombre());
            cs.setDate(2, java.sql.Date.valueOf(album.getFechaCreacion()));
            cs.setString(3, album.getGenero());
            cs.setInt(4, album.getDescargas());
            return cs.executeUpdate();
        } catch (SQLException e) {
            throw new AccesoDatosException("Error al modificar álbum", e); // CAMBIO
        }
    }
}
