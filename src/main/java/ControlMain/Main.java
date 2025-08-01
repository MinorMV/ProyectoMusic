package ControlMain;

import Logic.Artista;
import Logic.Usuario;
import View.ViewArtista;
import View.ViewCancion;
import View.ViewUsuario;
import java.sql.Connection;
import javax.swing.JOptionPane;
import Database.Conexion;

public class Main {

    public static void main(String[] args) {

        boolean continuar = true;

        try {
            Connection con = Conexion.getConexion();
            if (con != null) {
                JOptionPane.showMessageDialog(null, "¡Conexión a la base de datos exitosa!");
                Conexion.cerrarConexion();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error de conexión: " + e.getMessage());
            return;
        }

        while (continuar) {
            String[] opciones = {
                "Agregar usuario",
                "Buscar usuario por correo",
                "Modificar usuario por correo",
                "Eliminar usuario por correo",
                "Agregar artista",
                "Buscar artista por nombre",
                "Modificar artista por nombre",
                "Eliminar artista por nombre",
                "Agregar canción",
                "Buscar canción por título",
                "Modificar canción por título",
                "Eliminar canción por título",
                "Salir"
            };

            String seleccion = (String) JOptionPane.showInputDialog(
                    null,
                    "¿Qué deseas hacer?",
                    "Menú Principal",
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    opciones,
                    opciones[0]
            );

            if (seleccion == null || seleccion.equals("Salir")) {
                continuar = false;
                continue;
            }

            switch (seleccion) {
                case "Agregar usuario" -> {
                    ViewUsuario vu = new ViewUsuario();
                    Usuario mu = new Usuario();
                    try {
                        Connection con = Conexion.getConexion();
                        ControlUsuario cu = new ControlUsuario(con);
                        cu.capturarDatosUsuario(vu, mu);
                        Conexion.cerrarConexion();
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(null, "Error al guardar el usuario: " + e.getMessage());
                    }
                }
                case "Buscar usuario por correo" -> {
                    ViewUsuario vuBuscar = new ViewUsuario();
                    try {
                        Connection con = Conexion.getConexion();
                        ControlUsuario cuBuscar = new ControlUsuario(con);
                        cuBuscar.buscarYMostrarUsuarioPorCorreo(vuBuscar);
                        Conexion.cerrarConexion();
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(null, "Error al buscar usuario: " + e.getMessage());
                    }
                }
                case "Modificar usuario por correo" -> {
                    ViewUsuario vuModificar = new ViewUsuario();
                    try {
                        Connection con = Conexion.getConexion();
                        ControlUsuario cuModificar = new ControlUsuario(con);
                        cuModificar.modificarUsuarioPorCorreo(vuModificar);
                        Conexion.cerrarConexion();
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(null, "Error al modificar usuario: " + e.getMessage());
                    }
                }
                case "Eliminar usuario por correo" -> {
                    ViewUsuario vuEliminar = new ViewUsuario();
                    try {
                        Connection con = Conexion.getConexion();
                        ControlUsuario cuEliminar = new ControlUsuario(con);
                        cuEliminar.eliminarUsuarioPorCorreo(vuEliminar);
                        Conexion.cerrarConexion();
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(null, "Error al eliminar usuario: " + e.getMessage());
                    }
                }
                case "Agregar artista" -> {
                    ViewArtista va = new ViewArtista();
                    Artista ma = new Artista();
                    try {
                        Connection con = Conexion.getConexion();
                        ControlArtista ca = new ControlArtista(con);
                        ca.capturarDatosArtista(va, ma);
                        Conexion.cerrarConexion();
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(null, "Error al guardar el artista: " + e.getMessage());
                    }
                }
                case "Buscar artista por nombre" -> {
                    ViewArtista vaBuscar = new ViewArtista();
                    try {
                        Connection con = Conexion.getConexion();
                        ControlArtista caBuscar = new ControlArtista(con);
                        caBuscar.buscarYMostrarArtistaPorNombre(vaBuscar);
                        Conexion.cerrarConexion();
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(null, "Error al buscar artista: " + e.getMessage());
                    }
                }
                case "Modificar artista por nombre" -> {
                    ViewArtista vaModificar = new ViewArtista();
                    try {
                        Connection con = Conexion.getConexion();
                        ControlArtista caModificar = new ControlArtista(con);
                        caModificar.modificarArtistaPorNombre(vaModificar);
                        Conexion.cerrarConexion();
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(null, "Error al modificar artista: " + e.getMessage());
                    }
                }
                case "Eliminar artista por nombre" -> {
                    ViewArtista vaEliminar = new ViewArtista();
                    try {
                        Connection con = Conexion.getConexion();
                        ControlArtista caEliminar = new ControlArtista(con);
                        caEliminar.eliminarArtistaPorNombre(vaEliminar);
                        Conexion.cerrarConexion();
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(null, "Error al eliminar artista: " + e.getMessage());
                    }
                }
                case "Agregar canción" -> {
                    ViewCancion vc = new ViewCancion();
                    try {
                        Connection con = Conexion.getConexion();
                        ControlCancion cc = new ControlCancion(con);
                        cc.insertarCancion(vc);
                        Conexion.cerrarConexion();
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
                    }
                }
                case "Buscar canción por título" -> {
                    ViewCancion vc = new ViewCancion();
                    try {
                        Connection con = Conexion.getConexion();
                        ControlCancion cc = new ControlCancion(con);
                        cc.buscarCancion(vc);
                        Conexion.cerrarConexion();
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
                    }
                }
                case "Modificar canción por título" -> {
                    ViewCancion vc = new ViewCancion();
                    try {
                        Connection con = Conexion.getConexion();
                        ControlCancion cc = new ControlCancion(con);
                        cc.modificarCancion(vc);
                        Conexion.cerrarConexion();
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
                    }
                }
                case "Eliminar canción por título" -> {
                    ViewCancion vc = new ViewCancion();
                    try {
                        Connection con = Conexion.getConexion();
                        ControlCancion cc = new ControlCancion(con);
                        cc.eliminarCancion(vc);
                        Conexion.cerrarConexion();
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
                    }
                }
            }
        }
    }
}
