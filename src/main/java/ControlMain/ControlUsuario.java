package ControlMain;

import DAO.UsuarioDAO;
import Logic.Usuario;

// CAMBIO: imports de excepciones
import Excepciones.ValidacionException;           // CAMBIO
import Excepciones.AccesoDatosException;          // CAMBIO
import Excepciones.RecursoNoEncontradoException;  // CAMBIO

public class ControlUsuario {

    private final UsuarioDAO dao;

    public ControlUsuario(UsuarioDAO dao) {
        this.dao = dao;
    }

    // CAMBIO: throws específicos
    public void crear(Usuario u) throws ValidacionException, AccesoDatosException { // CAMBIO
        dao.insertar(u);
    }

    // CAMBIO: throws específicos
    public Usuario buscarPorCorreo(String correo)
            throws AccesoDatosException, RecursoNoEncontradoException { // CAMBIO
        return dao.buscarPorCorreo(correo);
    }

    // CAMBIO: throws específicos
    public int actualizarPorCorreo(Usuario u)
            throws ValidacionException, AccesoDatosException { // CAMBIO
        return dao.modificarPorCorreo(u);
    }
}
