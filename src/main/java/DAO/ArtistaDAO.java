package DAO;

import Logic.Artista;
import java.sql.Connection;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

// CAMBIO: excepciones
import Excepciones.ValidacionException;           // CAMBIO
import Excepciones.AccesoDatosException;          // CAMBIO
import Excepciones.RecursoNoEncontradoException;  // CAMBIO

public class ArtistaDAO {

    private Connection conexion;

    public ArtistaDAO(Connection conexion) {
        this.conexion = conexion;
    }

    public void insertarArtista(Artista artista) throws ValidacionException, AccesoDatosException { // CAMBIO
        try {
            CallableStatement cs = conexion.prepareCall("{ call INSERTAR_ARTISTA(?, ?, ?) }");
            cs.setString(1, artista.getNombre());
            cs.setString(2, artista.getGenero());
            cs.setString(3, artista.getPais());
            cs.executeUpdate();
        } catch (SQLException e) {
            throw new AccesoDatosException("Error al insertar artista", e); // CAMBIO
        }
    }

    public Artista buscarArtistaPorNombre(String nombre)
            throws AccesoDatosException, RecursoNoEncontradoException { // CAMBIO
        try {
            CallableStatement cs = conexion.prepareCall("{ call BUSCAR_ARTISTA_POR_NOMBRE(?) }");
            cs.setString(1, nombre);
            ResultSet rs = cs.executeQuery();
            if (rs.next()) {
                Artista a = new Artista();
                a.setNombre(rs.getString("NOMBRE"));
                a.setGenero(rs.getString("GENERO"));
                a.setPais(rs.getString("PAIS"));
                return a;
            } else {
                throw new RecursoNoEncontradoException("Artista no encontrado"); // CAMBIO
            }
        } catch (SQLException e) {
            throw new AccesoDatosException("Error al buscar artista", e); // CAMBIO
        }
    }

    public int modificarArtistaPorNombre(Artista artista)
            throws ValidacionException, AccesoDatosException { // CAMBIO
        try {
            CallableStatement cs = conexion.prepareCall("{ call MODIFICAR_ARTISTA_POR_NOMBRE(?, ?, ?) }");
            cs.setString(1, artista.getNombre());
            cs.setString(2, artista.getGenero());
            cs.setString(3, artista.getPais());
            return cs.executeUpdate();
        } catch (SQLException e) {
            throw new AccesoDatosException("Error al modificar artista", e); // CAMBIO
        }
    }
}
