/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ControlMain;

import Logic.Cancion;
import View.ViewCancion;

/**
 *
 * @author sebas
 */
public class ControlCancion {

    public void capturardatosCancion(ViewCancion vc, Cancion mc) {

        vc.capturaTitulo();
        mc.setTitulo(vc.getVtitulo());
        vc.capturaAlbum();
        mc.setAlbum(vc.getValbum());
        vc.capturaDuracion();
        mc.setDuracion(vc.getVduracion());
        vc.capturaGenero();
        mc.setGenero(vc.getVgenero());
//                    vc.setVartista(ma);

        vc.mostrarInformacionCompleta();
    }

}
