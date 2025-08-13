package ControlMain;

import DAO.PlaylistDAO;
import Logic.Playlist;

// CAMBIO: excepciones
import Excepciones.ValidacionException;           // CAMBIO
import Excepciones.AccesoDatosException;          // CAMBIO
import Excepciones.RecursoNoEncontradoException;  // CAMBIO

public class ControlPlaylist {

    private final PlaylistDAO dao;

    public ControlPlaylist(java.sql.Connection conexion) {
        this.dao = new PlaylistDAO(conexion);
    }

    public void crear(Playlist p) throws ValidacionException, AccesoDatosException { // CAMBIO
        dao.insertarPlaylist(p);
    }

    public Playlist buscarPorNombre(String nombre)
            throws AccesoDatosException, RecursoNoEncontradoException { // CAMBIO
        return dao.buscarPlaylistPorNombre(nombre);
    }

    public int actualizarPorNombre(Playlist p)
            throws ValidacionException, AccesoDatosException { // CAMBIO
        return dao.modificarPlaylistPorNombre(p);
    }
}
