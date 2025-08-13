// File: src/main/java/ControlMain/Menu.java
package ControlMain;

import Logic.Album;
import Logic.Usuario;
import View.ViewAlbum;
import View.ViewArtista;
import View.ViewCancion;
import View.ViewPlaylist;
import View.ViewUsuario;
import javax.swing.JOptionPane;
import java.sql.Connection;
import Database.Conexion;

/**
 * Menú separado del Main, replicando exactamente el flujo del Main original:
 * - Inicio de sesión / Registro
 * - Menú ADMIN vs Menú Usuario según rol
 * - Submenú de administración de roles
 *
 * Cada operación abre su propia conexión con try-with-resources,
 * igual que tu implementación original en Main.
 */
public class Menu {

    /**
     * Muestra el flujo de autenticación y, según el rol, redirige a los menús.
     */
    public void mostrarInicio() {
        boolean seguir = true;
        while (seguir) {
            String[] authOps = {"Iniciar sesión", "Registrarme", "Salir"};
            String authSel = (String) JOptionPane.showInputDialog(
                    null, "Bienvenido a la Plataforma Musical",
                    "Inicio",
                    JOptionPane.PLAIN_MESSAGE, null, authOps, authOps[0]);

            if (authSel == null || "Salir".equals(authSel)) {
                break;
            }

            try (Connection con = Conexion.getConexion()) {
                ControlUsuario ctrlUsuario = new ControlUsuario(con);
                ViewUsuario viewUsuario = new ViewUsuario();

                Usuario usuarioActual = null;
                switch (authSel) {
                    case "Iniciar sesión" -> {
                        usuarioActual = ctrlUsuario.iniciarSesion(viewUsuario);
                        if (usuarioActual == null) {
                            continue; // vuelve al login
                        }
                    }
                    case "Registrarme" -> {
                        ctrlUsuario.insertarUsuario(viewUsuario); // registra USER
                        usuarioActual = ctrlUsuario.iniciarSesion(viewUsuario);
                        if (usuarioActual == null) {
                            continue;
                        }
                    }
                }

                if ("ADMIN".equalsIgnoreCase(usuarioActual.getRol())) {
                    menuAdmin();
                } else {
                    menuUsuario();
                }

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
            }
        }
    }

    // ===================== Menú ADMIN =====================
    private void menuAdmin() {
        boolean continuar = true;
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
                "Administración",
                "Cerrar sesión"
            };

            String seleccion = (String) JOptionPane.showInputDialog(
                    null, "¿Qué deseas hacer?", "Menú ADMIN",
                    JOptionPane.PLAIN_MESSAGE, null, opciones, opciones[0]);

            if (seleccion == null || "Cerrar sesión".equals(seleccion)) {
                break;
            }

            try (Connection con = Conexion.getConexion()) {
                switch (seleccion) {
                    // ===== Usuario =====
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

                    // ===== Artista =====
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

                    // ===== Canción =====
                    case "Agregar canción" -> {
                        ControlCancion control = new ControlCancion(con);
                        control.insertarCancion();
                    }
                    case "Buscar canción por título" -> {
                        ControlCancion control = new ControlCancion(con);
                        control.buscarCancion();
                    }
                    case "Modificar canción por título" -> {
                        ControlCancion control = new ControlCancion(con);
                        control.modificarCancion();
                    }
                    case "Eliminar canción por título" -> {
                        ControlCancion control = new ControlCancion(con);
                        control.eliminarCancion();
                    }

                    // ===== Álbum =====
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

                    // ===== Playlist =====
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
                        control.modificarPlaylist(view);
                    }
                    case "Eliminar playlist por nombre" -> {
                        ViewPlaylist view = new ViewPlaylist();
                        ControlPlaylist control = new ControlPlaylist(con);
                        control.eliminarPlaylist(view);
                    }

                    case "Administración" -> submenuAdministracion();

                    default -> JOptionPane.showMessageDialog(null, "Opción no reconocida");
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
            }
        }
    }

    // ===================== Menú USUARIO =====================
    private void menuUsuario() {
        boolean continuar = true;
        while (continuar) {
            String[] opciones = {
                "Buscar canción por título",
                "Agregar playlist",
                "Buscar playlist por nombre",
                "Modificar playlist por nombre",
                "Eliminar playlist por nombre",
                "Cerrar sesión"
            };

            String seleccion = (String) JOptionPane.showInputDialog(
                    null, "Elige una opción", "Menú Usuario",
                    JOptionPane.PLAIN_MESSAGE, null, opciones, opciones[0]);

            if (seleccion == null || "Cerrar sesión".equals(seleccion)) {
                break;
            }

            try (Connection con = Conexion.getConexion()) {
                switch (seleccion) {
                    case "Buscar canción por título" -> {
                        ControlCancion control = new ControlCancion(con);
                        control.buscarCancion();
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
                        control.modificarPlaylist(view);
                    }
                    case "Eliminar playlist por nombre" -> {
                        ViewPlaylist view = new ViewPlaylist();
                        ControlPlaylist control = new ControlPlaylist(con);
                        control.eliminarPlaylist(view);
                    }
                    default -> JOptionPane.showMessageDialog(null, "Opción no reconocida");
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
            }
        }
    }

    // ===================== Submenú Administración de roles =====================
    private void submenuAdministracion() {
        String[] adminOps = {
            "Registrar admin",
            "Promover a ADMIN (por correo)",
            "Volver"
        };

        String opAdmin = (String) JOptionPane.showInputDialog(
                null, "Administración", "Roles",
                JOptionPane.PLAIN_MESSAGE, null, adminOps, adminOps[0]);

        if (opAdmin == null || "Volver".equals(opAdmin)) {
            return;
        }

        try (Connection con = Conexion.getConexion()) {
            ViewUsuario view = new ViewUsuario();
            ControlUsuario control = new ControlUsuario(con);

            switch (opAdmin) {
                case "Registrar admin" -> control.registrarAdmin(view);
                case "Promover a ADMIN (por correo)" -> control.promoverAAdmin(view);
                default -> JOptionPane.showMessageDialog(null, "Opción no reconocida");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
    }
}
