/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ControlMain;

import Logic.Artista;
import View.ViewArtista;

/**
 *
 * @author sebas
 */
public class ControlArtista {

    public void capturarDatosArtista(ViewArtista va,Artista ma) {

        va.capturaNombre();
        ma.setNombre(va.getVnombre());

        va.capturaGenero();
        ma.setGenero(va.getVgenero());

        va.capturaPais();
        ma.setPais(va.getVpais());

        va.mostrarInformacionCompletaArtista();
                
    }
}
