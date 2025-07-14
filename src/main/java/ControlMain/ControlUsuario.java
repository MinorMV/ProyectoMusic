/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ControlMain;

import Logic.Usuario;
import View.ViewUsuario;

/**
 *
 * @author sebas
 */
public class ControlUsuario {

    public void capturarDatosUsuario(ViewUsuario vu, Usuario mu) {

        vu.capturaNombre();
        mu.setNombre(vu.getVnombre());
        vu.capturaCorreo();
        mu.setCorreo(vu.getVcorreo());
        vu.capturaContrasena();
        mu.setContrasena(vu.getVcontrasena());
        vu.mostrarInformacionUsuario();
    }

}
