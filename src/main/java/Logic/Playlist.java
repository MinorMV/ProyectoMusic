/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logic;

import java.util.ArrayList;

/**
 *
 * @author sebas
 */
public class Playlist {

    private String nombre;
    private Usuario usuario;
    private final ArrayList<Cancion> canciones;

    public Playlist() {
        this.nombre = "";
        this.usuario = new Usuario();
        this.canciones = new ArrayList<>();
    }

    public Playlist(String nombre, Usuario usuario) {
        this.nombre = nombre;
        this.usuario = usuario;
        this.canciones = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public ArrayList<Cancion> getCanciones() {
        return canciones;
    }

    public void agregarCancion(Cancion cancion) {
        if (cancion != null) {
            canciones.add(cancion);
        } else {
            System.out.println("Error: Canción inválida.");
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Playlist: ").append(nombre).append("\n");
        sb.append("Usuario: ").append(usuario.getNombre()).append("\n");
        sb.append("Canciones:\n");

        for (Cancion c : canciones) {
            sb.append("---------------\n");
            sb.append(c.toString()).append("\n");
        }

        return sb.toString();
    }
}
