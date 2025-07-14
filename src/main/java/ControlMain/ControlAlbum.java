/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ControlMain;

import Logic.Album;
import View.ViewAlbum;

/**
 *
 * @author sebas
 */
public class ControlAlbum {

    public void capturardatosAlbum(Album mal, ViewAlbum val) {
        val.capturaAlbumNombre();
        mal.setAlbumNombre(val.getValbumNombre());
        val.capturaGenero();
        mal.setGenero(val.getVgenero());
        val.capturaFechaCreacion();
        mal.setFechaCreacion(val.getVfechaCreacion());
        val.capturaDescargas();
        mal.setDescargas(val.getVdescargas());
//                    val.setVartista(ma);
        val.capturaCanciones();
        mal.setCanciones(val.getVcanciones());

        val.mostrarinformacionAlbum();

    }

}
