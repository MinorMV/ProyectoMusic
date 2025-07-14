package Logic;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Clase Album Representa un álbum musical, que contiene información básica, un
 * artista y una colección de canciones.
 *
 * @author sebas
 */
public class Album {

    private String albumNombre;
    private LocalDate fechaCreacion;
    private String genero;
    private int descargas;
    private Artista artista;
    private ArrayList<Cancion> canciones;

//Constructor por defecto.
//Inicializa los atributos con valores predeterminados.
    public Album() {
        albumNombre = "";
        fechaCreacion = LocalDate.of(1000, 1, 1);
        genero = "";
        descargas = 0;
        artista = new Artista();               // Se crea una instancia de Artista por defecto
        canciones = new ArrayList<>();         // Se inicializa la lista de canciones vacía
    }

    /**
     * Constructor con parámetros. Permite crear un álbum con información
     * personalizada.
     *
     * @param albumNombre Nombre del álbum
     * @param fechaCreacion Año de creación
     * @param genero Género musical
     * @param descargas Cantidad de descargas
     * @param artista Artista vinculado al álbum
     */
    public Album(String albumNombre, LocalDate fechaCreacion, String genero, int descargas, Artista artista) {
        this.albumNombre = albumNombre;
        this.fechaCreacion = fechaCreacion;
        this.genero = genero;
        this.descargas = descargas;
        this.artista = artista;
        this.canciones = new ArrayList<>();
    }

    // Métodos getter y setter de cada atributo para acceso y modificación controlada
    //Nombre del Album
    public String getAlbumNombre() {
        return albumNombre;
    }

    public void setAlbumNombre(String albumNombre) {
        this.albumNombre = albumNombre;
    }

    //Fecha de Creacion
    public LocalDate getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDate fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    //Genero del Album
    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    //Cantidad de Descargas
    public int getDescargas() {
        return descargas;
    }

    public void setDescargas(int descargas) {
        this.descargas = descargas;
    }

    // Artista del Album
    public Artista getArtista() {
        return artista;
    }

    public void setArtista(Artista artista) {
        this.artista = artista;
    }

    // Array de Canciones del Album
    public ArrayList<Cancion> getCanciones() {
        return canciones;
    }

    public void setCanciones(ArrayList<Cancion> canciones) {
        this.canciones = canciones;
    }

    
// Método para agregar una canción al álbum.
// Si la canción no tiene artista asignado, se asigna el artista del álbum automáticamente.
// 
//@param cancion  Canción a agregar   
    public void agregarCancion(Cancion cancion) {
        if (cancion != null) {                              // Se verifica que la canción no sea nula
            if (cancion.getArtista() == null) {             // Si la canción no tiene artista, se le asigna el del álbum
                cancion.setArtista(this.artista);
            }
            this.canciones.add(cancion);                    // Se añade la canción a la lista
        }
    }

//
//Representación textual del álbum.
//Muestra toda la información del álbum junto con sus canciones.
//     
// @return Cadena que resume los datos del álbum
    @Override
    public String toString() {
        String result = """
                        ------ALBUM------
                Album: """ + albumNombre + "\n"
                + "Genero: " + genero + "\n"
                + "Fecha de creacion: " + fechaCreacion + "\n"
                + "Descargas: " + descargas + "\n"
                + "Artista:\n" + artista.toString() + "\n"
                + "Canciones:\n" + "\n----------------------";

//        for (Cancion c : canciones) {
//            result += "- " + c.getTitulo()
//                    + " (" + c.getDuracion() + " min)";      // Se muestra la duración en minutos
//
//            if (c.getArtista() != null) {
//                result += " - Artista: " + c.getArtista().getNombre();  // Se muestra el nombre del artista si está asignado
//            }
//
//            result += "No encontrado";
//        }

        return result;
  }
}
