package Logic;

public class Artista {
    private String nombre;
    private String genero;
    private String pais;

    public Artista() {}

    public Artista(String nombre, String genero, String pais) {
        this.nombre = nombre;
        this.genero = genero;
        this.pais = pais;
    }
    
public Artista(String nombre) {
    this.nombre = nombre;
    this.pais = "";
    this.genero = "";
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
        return "Nombre: " + nombre + "\nGénero: " + genero + "\nPaís: " + pais + "\n";
    }
}
