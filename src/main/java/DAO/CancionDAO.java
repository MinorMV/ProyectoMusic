package DAO;

import Logic.Cancion;
import Logic.Artista;
import java.sql.*;
import oracle.jdbc.OracleTypes;

public class CancionDAO {
    private final Connection conexion;

    public CancionDAO(Connection conexion) {
        this.conexion = conexion;
    }

    public void insertarCancion(Cancion c) throws SQLException {
        String sql = "{call insertar_cancion(?, ?, ?, ?, ?)}";
        CallableStatement cs = conexion.prepareCall(sql);
        cs.setString(1, c.getTitulo());
        cs.setString(2, c.getDuracion()); // OK
        cs.setString(3, c.getGenero());
        cs.setString(4, c.getAlbum());

        int idArtista = obtenerIdArtistaPorNombre(c.getArtista().getNombre());
        cs.setInt(5, idArtista);

        cs.execute();
        cs.close();
    }

    public Cancion buscarCancionPorTitulo(String titulo) throws SQLException {
        String sql = "{? = call buscar_cancion(?)}";
        CallableStatement cs = conexion.prepareCall(sql);
        cs.registerOutParameter(1, OracleTypes.CURSOR);
        cs.setString(2, titulo);
        cs.execute();

        ResultSet rs = (ResultSet) cs.getObject(1);
        Cancion c = null;
        if (rs.next()) {
            c = new Cancion();
            c.setTitulo(rs.getString("TITULO"));
            c.setDuracion(rs.getString("DURACION"));
            c.setGenero(rs.getString("GENERO"));
            c.setAlbum(rs.getString("ALBUM"));

            Artista artista = new Artista();
            artista.setNombre(rs.getString("NOMBRE_ARTISTA")); // Asegúrate de que la vista incluya esto
            c.setArtista(artista);
        }

        rs.close();
        cs.close();
        return c;
    }

    public void modificarCancion(Cancion c) throws SQLException {
        String sql = "{call modificar_cancion(?, ?, ?, ?, ?)}";
        CallableStatement cs = conexion.prepareCall(sql);
        cs.setString(1, c.getTitulo());
        cs.setString(2, c.getDuracion());
        cs.setString(3, c.getGenero());
        cs.setString(4, c.getAlbum());

        int idArtista = obtenerIdArtistaPorNombre(c.getArtista().getNombre());
        cs.setInt(5, idArtista);

        cs.execute();
        cs.close();
    }

    public void eliminarCancion(String titulo) throws SQLException {
        String sql = "{call eliminar_cancion(?)}";
        CallableStatement cs = conexion.prepareCall(sql);
        cs.setString(1, titulo);
        cs.execute();
        cs.close();
    }

    private int obtenerIdArtistaPorNombre(String nombre) throws SQLException {
        String sql = "SELECT ID_ARTISTA FROM ARTISTAS WHERE NOMBRE = ?";
        PreparedStatement ps = conexion.prepareStatement(sql);
        ps.setString(1, nombre);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            int id = rs.getInt("ID_ARTISTA");
            rs.close();
            ps.close();
            return id;
        } else {
            rs.close();
            ps.close();
            throw new SQLException("No se encontró un artista con ese nombre.");
        }
    }
}
