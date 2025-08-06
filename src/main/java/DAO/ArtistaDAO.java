package DAO;

import Logic.Artista;
import java.sql.*;
import oracle.jdbc.OracleTypes;

public class ArtistaDAO {

    private final Connection conexion;

    public ArtistaDAO(Connection conexion) {
        this.conexion = conexion;
    }

    public boolean insertarArtista(Artista artista) {
        try (CallableStatement cs = conexion.prepareCall("{call insertar_artista(?, ?, ?)}")) {
            cs.setString(1, artista.getNombre());
            cs.setString(2, artista.getGenero());
            cs.setString(3, artista.getPais());
            cs.execute();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    public Artista buscarArtistaPorNombre(String nombre) {
        try (CallableStatement cs = conexion.prepareCall("{ ? = call buscar_artista(?) }")) {
            cs.registerOutParameter(1, OracleTypes.CURSOR);
            cs.setString(2, nombre);
            cs.execute();

            ResultSet rs = (ResultSet) cs.getObject(1);
            if (rs.next()) {
                Artista artista = new Artista();
                artista.setIdArtista(rs.getInt("ID_ARTISTA"));
                artista.setNombre(rs.getString("NOMBRE"));
                artista.setGenero(rs.getString("GENERO"));
                artista.setPais(rs.getString("PAIS"));
                return artista;
            }
        } catch (SQLException e) {
            return null;
        }
        return null;
    }

    public boolean modificarArtista(Artista artista) {
        try (CallableStatement cs = conexion.prepareCall("{call modificar_artista(?, ?, ?)}")) {
            cs.setString(1, artista.getNombre());
            cs.setString(2, artista.getGenero());
            cs.setString(3, artista.getPais());
            cs.execute();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    public boolean eliminarArtistaPorNombre(String nombre) {
        try (CallableStatement cs = conexion.prepareCall("{call eliminar_artista(?)}")) {
            cs.setString(1, nombre);
            cs.execute();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }
}
