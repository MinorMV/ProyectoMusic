/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logic;

import java.time.LocalTime;

/**
 *
 * @author sebas
 */
public class Cancion {
    private String titulo;
    private String album;
    private LocalTime duracion;
    private String genero;
    private Artista artista;

    public Cancion() {
        titulo = "";
        album = "";
        duracion = LocalTime.of(0, 0, 0);
        genero = "";
        artista = null;
    }

    public Cancion(String titulo, String album, LocalTime duracion, String genero) {
        this.titulo = titulo;
        this.album = album;
        this.duracion = duracion;
        this.genero = genero;
        this.artista = null;
    }

    public Cancion(String titulo, String album, LocalTime duracion, String genero, Artista artista) {
        this.titulo = titulo;
        this.album = album;
        this.duracion = duracion;
        this.genero = genero;
        this.artista = artista;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public LocalTime getDuracion() {
        return duracion;
    }

    public void setDuracion(LocalTime duracion) {
        this.duracion = duracion;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public Artista getArtista() {
        return artista;
    }

    public void setArtista(Artista artista) {
        this.artista = artista;
    }

    @Override
    public String toString() {
        return "Titulo: " + titulo + 
                "\nAlbum: " + album+ 
                "\nDuracion: " + duracion + " minutos"+ 
                "\nGenero: " + genero + 
                (artista != null ? "\nArtista: " + artista.getNombre() : "");
    }
}

