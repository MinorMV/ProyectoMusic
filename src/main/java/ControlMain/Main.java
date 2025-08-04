package ControlMain;

import Logic.Album;
import Logic.Artista;
import Logic.Cancion;
import Logic.Playlist;
import Logic.Usuario;
import View.ViewAlbum;
import View.ViewArtista;
import View.ViewCancion;
import View.ViewPlaylist;
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
                "Agregar álbum",
                "Buscar álbum por nombre",
                "Modificar álbum por nombre",
                "Eliminar álbum por nombre",
                "Agregar playlist",
                "Buscar playlist por nombre",
                "Modificar playlist por nombre",
                "Eliminar playlist por nombre",
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
                case "Agregar álbum" -> {
                    ViewAlbum va = new ViewAlbum();
                    Album ma = new Album();
                    try {
                        Connection con = Conexion.getConexion();
                        ControlAlbum ca = new ControlAlbum();
                        ca.capturardatosAlbum(ma, va);

                        DAO.AlbumDAO dao = new DAO.AlbumDAO(con);
                        for (var c : ma.getCanciones()) {
                            dao.obtenerIdCancionPorTitulo(c.getTitulo());
                        }

                        dao.insertarAlbum(ma);
                        Conexion.cerrarConexion();
                        JOptionPane.showMessageDialog(null, "¡Álbum guardado correctamente!");

                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(null, "Error al guardar el álbum: " + e.getMessage());
                    }
                }
                case "Buscar álbum por nombre" -> {
                    ViewAlbum vaBuscar = new ViewAlbum();
                    try {
                        Connection con = Conexion.getConexion();
                        String nombre = JOptionPane.showInputDialog("Digite el nombre del álbum:");
                        DAO.AlbumDAO dao = new DAO.AlbumDAO(con);
                        Album album = dao.buscarAlbumPorNombre(nombre);
                        if (album != null) {
                            vaBuscar.setValbumNombre(album.getAlbumNombre());
                            vaBuscar.setVfechaCreacion(album.getFechaCreacion());
                            vaBuscar.setVgenero(album.getGenero());
                            vaBuscar.setVdescargas(album.getDescargas());
                            vaBuscar.setVartista(album.getArtista());
                            vaBuscar.mostrarinformacionAlbum();
                        } else {
                            JOptionPane.showMessageDialog(null, "Álbum no encontrado");
                        }
                        Conexion.cerrarConexion();
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(null, "Error al buscar álbum: " + e.getMessage());
                    }
                }
                case "Modificar álbum por nombre" -> {
                    try {
                        Connection con = Conexion.getConexion();
                        String nombre = JOptionPane.showInputDialog("Digite el nombre del álbum a modificar:");
                        DAO.AlbumDAO dao = new DAO.AlbumDAO(con);
                        Album album = dao.buscarAlbumPorNombre(nombre);
                        if (album != null) {
                            ViewAlbum vaModificar = new ViewAlbum();
                            ControlAlbum caModificar = new ControlAlbum();
                            caModificar.capturardatosAlbum(album, vaModificar);
                            dao.modificarAlbum(album);
                            JOptionPane.showMessageDialog(null, "Álbum modificado correctamente.");
                        } else {
                            JOptionPane.showMessageDialog(null, "Álbum no encontrado");
                        }
                        Conexion.cerrarConexion();
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(null, "Error al modificar álbum: " + e.getMessage());
                    }
                }
                case "Eliminar álbum por nombre" -> {
                    try {
                        Connection con = Conexion.getConexion();
                        String nombre = JOptionPane.showInputDialog("Digite el nombre del álbum a eliminar:");
                        DAO.AlbumDAO dao = new DAO.AlbumDAO(con);
                        dao.eliminarAlbumPorNombre(nombre);
                        Conexion.cerrarConexion();
                        JOptionPane.showMessageDialog(null, "Álbum eliminado correctamente.");
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(null, "Error al eliminar álbum: " + e.getMessage());
                    }
                }
                case "Agregar playlist" -> {
                    ViewPlaylist vp = new ViewPlaylist();
                    try {
                        Connection con = Conexion.getConexion();
                        ControlPlaylist cp = new ControlPlaylist(con);
                        cp.insertarPlaylist(vp);
                        Conexion.cerrarConexion();
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(null, "Error al agregar playlist: " + e.getMessage());
                    }
                }
                case "Buscar playlist por nombre" -> {
                    ViewPlaylist vp = new ViewPlaylist();
                    try {
                        Connection con = Conexion.getConexion();
                        ControlPlaylist cp = new ControlPlaylist(con);
                        cp.buscarPlaylist(vp);
                        Conexion.cerrarConexion();
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(null, "Error al buscar playlist: " + e.getMessage());
                    }
                }
                case "Modificar playlist por nombre" -> {
                    ViewPlaylist vp = new ViewPlaylist();
                    try {
                        Connection con = Conexion.getConexion();
                        ControlPlaylist cp = new ControlPlaylist(con);
                        cp.modificarPlaylist(con, vp);
                        Conexion.cerrarConexion();
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(null, "Error al modificar playlist: " + e.getMessage());
                    }
                }
                case "Eliminar playlist por nombre" -> {
                    ViewPlaylist vp = new ViewPlaylist();
                    try {
                        Connection con = Conexion.getConexion();
                        ControlPlaylist cp = new ControlPlaylist(con);
                        cp.eliminarPlaylist(vp);
                        Conexion.cerrarConexion();
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(null, "Error al eliminar playlist: " + e.getMessage());
                    }
                }
            }
        }
    }
}
