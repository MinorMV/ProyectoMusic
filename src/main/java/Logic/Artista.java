/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logic;

/**
 *
 * @author sebas
 */
public class Artista {

    private String nombre;
    private String genero;
    private String pais;

    public Artista() {
        nombre = "";
        genero = "";
        pais = "";
    }

    public Artista(String nombre, String genero, String pais) {
        this.nombre = nombre;
        this.genero = genero;
        this.pais = pais;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    @Override
    public String toString() {
        return "Nombre: " + this.getNombre()
      + "\n" + "Genero: " + this.getGenero()
           + "\n" + "Pais: " + this.getPais();

    }
}
