/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
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
import java.util.Set;
import javax.swing.JOptionPane;

/**
 *
 * @author sebas
 */
public class Main {

    public static void main(String[] args) {

        boolean continuar = true;

        while (continuar) {
            String[] opciones = {
                "\nAgregar canción",
                "\nAgregar artista",
                "\nAgregar álbum",
                "\nAgregar usuario",
                "\nAgregar playlist",
                "\nSalir"
            };

            int seleccion = JOptionPane.showOptionDialog(
                    null,
                    "¿Qué deseas hacer?",
                    "Menú Principal",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.INFORMATION_MESSAGE,
                    null,
                    opciones,
                    opciones[0]
            );

            switch (seleccion) {
                case 0:
                    /* Agregar canción */
                    ViewCancion vc = new ViewCancion();
                    Cancion mc = new Cancion();

                    ControlCancion cc = new ControlCancion();
                    cc.capturardatosCancion(vc, mc);
                    break;
                case 1:
                    /* Agregar artista */
                    ViewArtista va  = new ViewArtista();
                    Artista ma = new Artista();

                    ControlArtista ca = new ControlArtista();
                    ca.capturarDatosArtista(va, ma);
                    break;
                case 2:
                    /* Agregar álbum */
                    ViewAlbum val = new ViewAlbum();
                    Album mal = new Album();

                    ControlAlbum cal = new ControlAlbum();
                    cal.capturardatosAlbum(mal, val);
                    break;
                case 3:
                    /* Agregar usuario */
                    ViewUsuario vu = new ViewUsuario();
                    Usuario mu = new Usuario();

                    ControlUsuario cu = new ControlUsuario();
                    cu.capturarDatosUsuario(vu, mu);
                    break;
                case 4:
                    /* Agregar playlist */
                    ViewPlaylist vp = new ViewPlaylist();
                    Playlist mp = new Playlist();

                    ControlPlaylist cp = new ControlPlaylist();
                    cp.capturarDatosPlaylist(vp, mp);
                    break;

                case 5:
                /* Salir */
                default:
                    continuar = false;
                    break;
            }
        }
    }
}

//     flujo 1. vista - control - modelo
//            ve.capturaId();
//            me.setId(ve.getVid());           
//     flujo 2. modelo - control - vista
//            ve.setVid(me.getId());
//            ve.mostrarId(); */
