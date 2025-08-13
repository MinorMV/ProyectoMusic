package DAO;

import Logic.Cancion;
import java.sql.Connection;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

// CAMBIO: excepciones
import Excepciones.ValidacionException;           // CAMBIO
import Excepciones.AccesoDatosException;          // CAMBIO
import Excepciones.RecursoNoEncontradoException;  // CAMBIO

public class CancionDAO {

    private Connection conexion;

    public CancionDAO(Connection conexion) {
        this.conexion = conexion;
    }

    public void insertarCancion(Cancion c) throws ValidacionException, AccesoDatosException { // CAMBIO
        try {
            CallableStatement cs = conexion.prepareCall("{ call INSERTAR_CANCION(?, ?, ?, ?) }");
            cs.setString(1, c.getNombre());
            cs.setInt(2, c.getDuracionSeg());    // ajusta si tu atributo difiere
            cs.setString(3, c.getGenero());
            cs.setInt(4, c.getIdAlbum());
            cs.executeUpdate();
        } catch (SQLException e) {
            throw new AccesoDatosException("Error al insertar canción", e); // CAMBIO
        }
    }

    public Cancion buscarCancionPorNombre(String nombre)
            throws AccesoDatosException, RecursoNoEncontradoException { // CAMBIO
        try {
            CallableStatement cs = conexion.prepareCall("{ call BUSCAR_CANCION_POR_NOMBRE(?) }");
            cs.setString(1, nombre);
            ResultSet rs = cs.executeQuery();
            if (rs.next()) {
                Cancion c = new Cancion();
                c.setNombre(rs.getString("NOMBRE"));
                c.setGenero(rs.getString("GENERO"));
                c.setDuracionSeg(rs.getInt("DURACION_SEG"));
                c.setIdAlbum(rs.getInt("ID_ALBUM"));
                return c;
            } else {
                throw new RecursoNoEncontradoException("Canción no encontrada"); // CAMBIO
            }
        } catch (SQLException e) {
            throw new AccesoDatosException("Error al buscar canción", e); // CAMBIO
        }
    }

    public int modificarCancionPorNombre(Cancion c)
            throws ValidacionException, AccesoDatosException { // CAMBIO
        try {
            CallableStatement cs = conexion.prepareCall("{ call MODIFICAR_CANCION_POR_NOMBRE(?, ?, ?, ?) }");
            cs.setString(1, c.getNombre());
            cs.setInt(2, c.getDuracionSeg());
            cs.setString(3, c.getGenero());
            cs.setInt(4, c.getIdAlbum());
            return cs.executeUpdate();
        } catch (SQLException e) {
            throw new AccesoDatosException("Error al modificar canción", e); // CAMBIO
        }
    }
}
