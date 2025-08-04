package Logic;

public class Cancion {
    private String titulo;
    private String album;
    private String duracion; 
    private String genero;
    private Artista artista;

    public Cancion() {
        titulo = "";
        album = "";
        duracion = "00:00";
        genero = "";
        artista = null;
    }

    public Cancion(String titulo, String album, String duracion, String genero) {
        this.titulo = titulo;
        this.album = album;
        this.duracion = duracion;
        this.genero = genero;
        this.artista = null;
    }

    public Cancion(String titulo, String album, String duracion, String genero, Artista artista) {
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

    public String getDuracion() {
        return duracion;
    }

    public void setDuracion(String duracion) {
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
               "\nAlbum: " + album +
               "\nDuracion: " + duracion +
               "\nGenero: " + genero +
               (artista != null ? "\nArtista: " + artista.getNombre() : "");
    }
}
