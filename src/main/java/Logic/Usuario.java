/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logic;

/**
 *
 * @author sebas
 */
public class Usuario {

        private String nombre;
        private String correo;
        private String contrasena;

        public Usuario() {
            nombre = "";
            correo = "";
            contrasena = "";
        }

        public Usuario(String nombre, String correo, String contrasena) {
            this.nombre = nombre;
            this.correo = correo;
            this.contrasena = contrasena;
        }

        public String getNombre() {
            return nombre;
        }

        public void setNombre(String nombre) {
            this.nombre = nombre;
        }

        public String getCorreo() {
            return correo;
        }

        public void setCorreo(String correo) {
            this.correo = correo;
        }

        public String getContrasena() {
            return contrasena;
        }

        public void setContrasena(String contrasena) {
            this.contrasena = contrasena;
        }

        @Override
        public String toString() {
            return "Nombre: " + nombre + "\nCorreo: " + correo + "\nContrasena: " + contrasena;
        }
 }

