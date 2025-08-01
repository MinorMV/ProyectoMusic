package View;

import javax.swing.JOptionPane;

public class ViewCancion {
    private String vtitulo;
    private String valbum;
    private String vduracion;
    private String vgenero;
    private String vartista;

    public void capturaTitulo() {
        vtitulo = JOptionPane.showInputDialog("Ingrese el título de la canción:");
    }

    public void capturaAlbum() {
        valbum = JOptionPane.showInputDialog("Ingrese el nombre del álbum:");
    }

    public void capturaDuracion() {
        vduracion = JOptionPane.showInputDialog("Ingrese la duración (mm:ss):");
    }

    public void capturaGenero() {
        vgenero = JOptionPane.showInputDialog("Ingrese el género de la canción:");
    }

    public void capturaArtista() {
        vartista = JOptionPane.showInputDialog("Ingrese el nombre del artista:");
    }

    public String getTitulo() {
        return vtitulo;
    }

    public String getAlbum() {
        return valbum;
    }

    public String getDuracion() {
        return vduracion;
    }

    public String getGenero() {
        return vgenero;
    }

    public String getArtista() {
        return vartista;
    }

    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(null, mensaje);
    }
}
