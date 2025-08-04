package View;

import Logic.Artista;
import Logic.Cancion;
import java.time.LocalDate;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class ViewAlbum {

    private String valbumNombre;
    private LocalDate vfechaCreacion;
    private String vgenero;
    private int vdescargas;
    private Artista vartista;
    private ArrayList<Cancion> vcanciones;

    public ViewAlbum() {
        vcanciones = new ArrayList<>();
    }

    public void mostrarAlbumNombre() {
        JOptionPane.showMessageDialog(null, "Nombre del Album: " + getValbumNombre());
    }

    public void mostrarFechaCreacion() {
        JOptionPane.showMessageDialog(null, "Fecha de creación: " + getVfechaCreacion());
    }

    public void mostrarGenero() {
        JOptionPane.showMessageDialog(null, "Género del Album: " + getVgenero());
    }

    public void mostrarDescargas() {
        JOptionPane.showMessageDialog(null, "Cantidad de Descargas: " + getVdescargas());
    }

    public void mostrarArtista() {
        JOptionPane.showMessageDialog(null, "Artista del Album: " + (vartista != null ? vartista.getNombre() : "No definido"));
    }

    public void mostrarCanciones() {
        StringBuilder canciones = new StringBuilder("Canciones del álbum:\n");
        for (Cancion c : vcanciones) {
            canciones.append("- ").append(c.getTitulo()).append("\n");
        }
        JOptionPane.showMessageDialog(null, canciones.toString());
    }

    public void capturaAlbumNombre() {
        do {
            valbumNombre = JOptionPane.showInputDialog(null, "Digite el nombre del Álbum:", "Álbum", JOptionPane.INFORMATION_MESSAGE);
        } while (valbumNombre == null || valbumNombre.trim().isEmpty());
    }

    public void capturaFechaCreacion() {
        try {
            int dia = Integer.parseInt(JOptionPane.showInputDialog(null, "Día de creación:", "Fecha", JOptionPane.INFORMATION_MESSAGE));
            int mes = Integer.parseInt(JOptionPane.showInputDialog(null, "Mes de creación:", "Fecha", JOptionPane.INFORMATION_MESSAGE));
            int anio = Integer.parseInt(JOptionPane.showInputDialog(null, "Año de creación:", "Fecha", JOptionPane.INFORMATION_MESSAGE));
            vfechaCreacion = LocalDate.of(anio, mes, dia);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Fecha inválida. Se usará la fecha actual.");
            vfechaCreacion = LocalDate.now();
        }
    }

    public void capturaGenero() {
        vgenero = JOptionPane.showInputDialog(null, "Digite el género del Álbum:", "Álbum", JOptionPane.INFORMATION_MESSAGE);
    }

    public void capturaDescargas() {
        try {
            vdescargas = Integer.parseInt(JOptionPane.showInputDialog(null, "Cantidad de descargas:", "Álbum", JOptionPane.INFORMATION_MESSAGE));
        } catch (Exception e) {
            vdescargas = 0;
        }
    }

    public void capturaArtista() {
        String nombreArtista = JOptionPane.showInputDialog(null, "Digite el nombre del artista:", "Artista", JOptionPane.INFORMATION_MESSAGE);
        if (nombreArtista != null && !nombreArtista.trim().isEmpty()) {
            this.vartista = new Artista(nombreArtista.trim());
        } else {
            this.vartista = new Artista("No definido");
        }
    }

    public void capturaCanciones() {
    boolean seguir = true;

    while (seguir) {
        String titulo = JOptionPane.showInputDialog(null, "Ingrese el título de la canción:", "Canción del Álbum", JOptionPane.INFORMATION_MESSAGE);

        if (titulo != null && !titulo.trim().isEmpty()) {
            Cancion nueva = new Cancion();      
            nueva.setTitulo(titulo.trim());     
            vcanciones.add(nueva);              
        }

        int opcion = JOptionPane.showConfirmDialog(null, "¿Desea agregar otra canción?", "Continuar", JOptionPane.YES_NO_OPTION);
        if (opcion != JOptionPane.YES_OPTION) {
            seguir = false;
        }
    }
}


    public void mostrarinformacionAlbum() {
        StringBuilder mensaje = new StringBuilder();
        mensaje.append("------ ALBUM ------\n");
        mensaje.append("Álbum: ").append(valbumNombre).append("\n");
        mensaje.append("Género: ").append(vgenero).append("\n");
        mensaje.append("Fecha de creación: ").append(vfechaCreacion).append("\n");
        mensaje.append("Descargas: ").append(vdescargas).append("\n");
        mensaje.append("Artista: ").append(vartista != null ? vartista.getNombre() : "No definido").append("\n");
        mensaje.append("Canciones:\n");
        for (Cancion c : vcanciones) {
            mensaje.append("- ").append(c.getTitulo()).append("\n");
        }
        mensaje.append("--------------------");
        JOptionPane.showMessageDialog(null, mensaje.toString(), "Información del Álbum", JOptionPane.INFORMATION_MESSAGE);
    }

    public String getValbumNombre() {
        return valbumNombre;
    }

    public LocalDate getVfechaCreacion() {
        return vfechaCreacion;
    }

    public String getVgenero() {
        return vgenero;
    }

    public int getVdescargas() {
        return vdescargas;
    }

    public Artista getVartista() {
        return vartista;
    }

    public ArrayList<Cancion> getVcanciones() {
        return vcanciones;
    }

    public void setValbumNombre(String valbumNombre) {
        this.valbumNombre = valbumNombre;
    }

    public void setVfechaCreacion(LocalDate vfechaCreacion) {
        this.vfechaCreacion = vfechaCreacion;
    }

    public void setVgenero(String vgenero) {
        this.vgenero = vgenero;
    }

    public void setVdescargas(int vdescargas) {
        this.vdescargas = vdescargas;
    }

    public void setVartista(Artista vartista) {
        this.vartista = vartista;
    }

    public void setVcanciones(ArrayList<Cancion> vcanciones) {
        this.vcanciones = vcanciones;
    }
}
