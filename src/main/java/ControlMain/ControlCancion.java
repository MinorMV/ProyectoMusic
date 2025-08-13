package ControlMain;

import DAO.CancionDAO;
import Logic.Cancion;

// CAMBIO: excepciones
import Excepciones.ValidacionException;           // CAMBIO
import Excepciones.AccesoDatosException;          // CAMBIO
import Excepciones.RecursoNoEncontradoException;  // CAMBIO

public class ControlCancion {

    private final CancionDAO dao;

    public ControlCancion(java.sql.Connection conexion) {
        this.dao = new CancionDAO(conexion);
    }

    public void crear(Cancion c) throws ValidacionException, AccesoDatosException { // CAMBIO
        dao.insertarCancion(c);
    }

    public Cancion buscarPorNombre(String nombre)
            throws AccesoDatosException, RecursoNoEncontradoException { // CAMBIO
        return dao.buscarCancionPorNombre(nombre);
    }

    public int actualizarPorNombre(Cancion c)
            throws ValidacionException, AccesoDatosException { // CAMBIO
        return dao.modificarCancionPorNombre(c);
    }
}
