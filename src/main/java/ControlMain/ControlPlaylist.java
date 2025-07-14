/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ControlMain;

import Logic.Playlist;
import Logic.Usuario;
import View.ViewPlaylist;

/**
 *
 * @author sebas
 */
public class ControlPlaylist {

    public void capturarDatosPlaylist(ViewPlaylist vp, Playlist mp) {
        
        // Captura nombre de la playlist desde la vista
        vp.capturaNombrePlaylist();
        mp.setNombre(vp.getVnombrePlaylist());

        // Captura nombre del usuario (solo nombre, no el objeto completo)
        vp.capturaNombreUsuario();

        // Creamos el modelo de Usuario y le asignamos el nombre
        Usuario nuevoUsuario = new Usuario();
        nuevoUsuario.setNombre(vp.getVnombreUsuario());
        mp.setUsuario(nuevoUsuario);

        // Mostrar la información completa de la playlist
        vp.mostrarInformacionCompletaPlaylist();
    }
}