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

            try (Connection con = Conexion.getConexion()) {
                switch (seleccion) {

                    case "Agregar usuario" -> {
                        ViewUsuario view = new ViewUsuario();
                        ControlUsuario control = new ControlUsuario(con);
                        control.insertarUsuario(view);
                    }
                    case "Buscar usuario por correo" -> {
                        ViewUsuario view = new ViewUsuario();
                        ControlUsuario control = new ControlUsuario(con);
                        control.buscarUsuario(view);
                    }
                    case "Modificar usuario por correo" -> {
                        ViewUsuario view = new ViewUsuario();
                        ControlUsuario control = new ControlUsuario(con);
                        control.modificarUsuario(view);
                    }
                    case "Eliminar usuario por correo" -> {
                        ViewUsuario view = new ViewUsuario();
                        ControlUsuario control = new ControlUsuario(con);
                        control.eliminarUsuario(view);
                    }

                    case "Agregar artista" -> {
                        ViewArtista view = new ViewArtista();
                        ControlArtista control = new ControlArtista(con);
                        control.insertarArtista(view);
                    }
                    case "Buscar artista por nombre" -> {
                        ViewArtista view = new ViewArtista();
                        ControlArtista control = new ControlArtista(con);
                        control.buscarArtista(view);
                    }
                    case "Modificar artista por nombre" -> {
                        ViewArtista view = new ViewArtista();
                        ControlArtista control = new ControlArtista(con);
                        control.modificarArtista(view);
                    }
                    case "Eliminar artista por nombre" -> {
                        ViewArtista view = new ViewArtista();
                        ControlArtista control = new ControlArtista(con);
                        control.eliminarArtista(view);
                    }

                    case "Agregar canción" -> {
                        ViewCancion view = new ViewCancion();
                        ControlCancion control = new ControlCancion(con);
                        control.insertarCancion(view);
                    }
                    case "Buscar canción por título" -> {
                        ViewCancion view = new ViewCancion();
                        ControlCancion control = new ControlCancion(con);
                        control.buscarCancion(view);
                    }
                    case "Modificar canción por título" -> {
                        ViewCancion view = new ViewCancion();
                        ControlCancion control = new ControlCancion(con);
                        control.modificarCancion(view);
                    }
                    case "Eliminar canción por título" -> {
                        ViewCancion view = new ViewCancion();
                        ControlCancion control = new ControlCancion(con);
                        control.eliminarCancion(view);
                    }

                    case "Agregar álbum" -> {
                        ViewAlbum view = new ViewAlbum();
                        ControlAlbum control = new ControlAlbum(con);
                        control.insertarAlbum(view);
                    }
                    case "Buscar álbum por nombre" -> {
                        ViewAlbum view = new ViewAlbum();
                        ControlAlbum control = new ControlAlbum(con);
                        Album album = control.buscarAlbum(view); 
                        if (album != null) {
                            view.setValbumNombre(album.getAlbumNombre());
                            view.setVfechaCreacion(album.getFechaCreacion());
                            view.setVgenero(album.getGenero());
                            view.setVdescargas(album.getDescargas());
                            view.setVartista(album.getArtista());
                            view.mostrarinformacionAlbum();
                        } else {
                            view.mostrarMensajeError("Álbum no encontrado.");
                        }
                    }

                    case "Modificar álbum por nombre" -> {
                        ViewAlbum view = new ViewAlbum();
                        ControlAlbum control = new ControlAlbum(con);
                        control.modificarAlbum(view);
                    }
                    case "Eliminar álbum por nombre" -> {
                        ViewAlbum view = new ViewAlbum();
                        ControlAlbum control = new ControlAlbum(con);
                        control.eliminarAlbum(view);
                    }

                    case "Agregar playlist" -> {
                        ViewPlaylist view = new ViewPlaylist();
                        ControlPlaylist control = new ControlPlaylist(con);
                        control.insertarPlaylist(view);
                    }
                    case "Buscar playlist por nombre" -> {
                        ViewPlaylist view = new ViewPlaylist();
                        ControlPlaylist control = new ControlPlaylist(con);
                        control.buscarPlaylist(view);
                    }
                    case "Modificar playlist por nombre" -> {
                        ViewPlaylist view = new ViewPlaylist();
                        ControlPlaylist control = new ControlPlaylist(con);
                        control.modificarPlaylist(con, view);
                    }
                    case "Eliminar playlist por nombre" -> {
                        ViewPlaylist view = new ViewPlaylist();
                        ControlPlaylist control = new ControlPlaylist(con);
                        control.eliminarPlaylist(view);
                    }
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
            }
        }
    }
}
