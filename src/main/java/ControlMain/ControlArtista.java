package ControlMain;

import DAO.ArtistaDAO;
import Logic.Artista;

// CAMBIO: excepciones
import Excepciones.ValidacionException;           // CAMBIO
import Excepciones.AccesoDatosException;          // CAMBIO
import Excepciones.RecursoNoEncontradoException;  // CAMBIO

public class ControlArtista {

    private final ArtistaDAO dao;

    public ControlArtista(java.sql.Connection conexion) {
        this.dao = new ArtistaDAO(conexion);
    }

    public void crear(Artista artista) throws ValidacionException, AccesoDatosException { // CAMBIO
        dao.insertarArtista(artista);
    }

    public Artista buscarPorNombre(String nombre)
            throws AccesoDatosException, RecursoNoEncontradoException { // CAMBIO
        return dao.buscarArtistaPorNombre(nombre);
    }

    public int actualizarPorNombre(Artista artista)
            throws ValidacionException, AccesoDatosException { // CAMBIO
        return dao.modificarArtistaPorNombre(artista);
    }
}
