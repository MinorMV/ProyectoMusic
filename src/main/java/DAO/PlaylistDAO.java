package DAO;

import Logic.Playlist;
import java.sql.Connection;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

// CAMBIO: excepciones
import Excepciones.ValidacionException;           // CAMBIO
import Excepciones.AccesoDatosException;          // CAMBIO
import Excepciones.RecursoNoEncontradoException;  // CAMBIO

public class PlaylistDAO {

    private Connection conexion;

    public PlaylistDAO(Connection conexion) {
        this.conexion = conexion;
    }

    public void insertarPlaylist(Playlist p) throws ValidacionException, AccesoDatosException { // CAMBIO
        try {
            CallableStatement cs = conexion.prepareCall("{ call INSERTAR_PLAYLIST(?, ?, ?) }");
            cs.setString(1, p.getNombre());
            cs.setString(2, p.getDescripcion());
            cs.setInt(3, p.getIdUsuario()); // ajusta si usas otro campo
            cs.executeUpdate();
        } catch (SQLException e) {
            throw new AccesoDatosException("Error al insertar playlist", e); // CAMBIO
        }
    }

    public Playlist buscarPlaylistPorNombre(String nombre)
            throws AccesoDatosException, RecursoNoEncontradoException { // CAMBIO
        try {
            CallableStatement cs = conexion.prepareCall("{ call BUSCAR_PLAYLIST_POR_NOMBRE(?) }");
            cs.setString(1, nombre);
            ResultSet rs = cs.executeQuery();
            if (rs.next()) {
                Playlist p = new Playlist();
                p.setNombre(rs.getString("NOMBRE"));
                p.setDescripcion(rs.getString("DESCRIPCION"));
                p.setIdUsuario(rs.getInt("ID_USUARIO"));
                return p;
            } else {
                throw new RecursoNoEncontradoException("Playlist no encontrada"); // CAMBIO
            }
        } catch (SQLException e) {
            throw new AccesoDatosException("Error al buscar playlist", e); // CAMBIO
        }
    }

    public int modificarPlaylistPorNombre(Playlist p)
            throws ValidacionException, AccesoDatosException { // CAMBIO
        try {
            CallableStatement cs = conexion.prepareCall("{ call MODIFICAR_PLAYLIST_POR_NOMBRE(?, ?, ?) }");
            cs.setString(1, p.getNombre());
            cs.setString(2, p.getDescripcion());
            cs.setInt(3, p.getIdUsuario());
            return cs.executeUpdate();
        } catch (SQLException e) {
            throw new AccesoDatosException("Error al modificar playlist", e); // CAMBIO
        }
    }
}
