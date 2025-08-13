package ControlMain;

import DAO.AlbumDAO;
import Logic.Album;

// CAMBIO: excepciones
import Excepciones.ValidacionException;           // CAMBIO
import Excepciones.AccesoDatosException;          // CAMBIO
import Excepciones.RecursoNoEncontradoException;  // CAMBIO

public class ControlAlbum {

    private final AlbumDAO dao;

    public ControlAlbum(java.sql.Connection conexion) {
        this.dao = new AlbumDAO(conexion);
    }

    public void crear(Album album) throws ValidacionException, AccesoDatosException { // CAMBIO
        dao.insertarAlbum(album);
    }

    public Album buscarPorNombre(String nombre)
            throws AccesoDatosException, RecursoNoEncontradoException { // CAMBIO
        return dao.buscarAlbumPorNombre(nombre);
    }

    public int actualizarPorNombre(Album album)
            throws ValidacionException, AccesoDatosException { // CAMBIO
        return dao.modificarAlbumPorNombre(album);
    }
}
