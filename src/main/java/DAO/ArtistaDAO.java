package DAO;

import Logic.Artista;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ArtistaDAO {

    private Connection conexion;

    public ArtistaDAO(Connection conexion) {
        this.conexion = conexion;
    }

    public void insertarArtista(Artista artista) throws Exception {
        String sql = "INSERT INTO ARTISTAS (NOMBRE, GENERO, PAIS) VALUES (?, ?, ?)";
        PreparedStatement ps = conexion.prepareStatement(sql);
        ps.setString(1, artista.getNombre());
        ps.setString(2, artista.getGenero());
        ps.setString(3, artista.getPais());
        ps.executeUpdate();
    }

    public Artista buscarArtistaPorNombre(String nombre) throws Exception {
        String sql = "SELECT * FROM ARTISTAS WHERE NOMBRE = ?";
        PreparedStatement ps = conexion.prepareStatement(sql);
        ps.setString(1, nombre);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            Artista artista = new Artista();
            artista.setNombre(rs.getString("nombre"));
            artista.setGenero(rs.getString("genero"));
            artista.setPais(rs.getString("pais"));
            return artista;
        } else {
            return null;
        }
    }

    public void modificarArtista(Artista artista) throws Exception {
        String sql = "UPDATE ARTISTAS SET GENERO = ?, PAIS = ? WHERE NOMBRE = ?";
        PreparedStatement ps = conexion.prepareStatement(sql);
        ps.setString(1, artista.getGenero());
        ps.setString(2, artista.getPais());
        ps.setString(3, artista.getNombre());
        ps.executeUpdate();
    }

    public void eliminarArtistaPorNombre(String nombre) throws Exception {
        String sql = "DELETE FROM ARTISTAS WHERE NOMBRE = ?";
        PreparedStatement ps = conexion.prepareStatement(sql);
        ps.setString(1, nombre);
        ps.executeUpdate();
    }
}
