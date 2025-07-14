/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View;

import Logic.Artista;
import Logic.Cancion;
import java.time.LocalDate;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author sebas
 */
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
        JOptionPane.showMessageDialog(null, "Nombre del Album:..." + getValbumNombre());
    }

    public void mostrarFechaCreacion() {
        JOptionPane.showMessageDialog(null, "Fecha de creacion:..." + getVfechaCreacion());
    }

    public void mostrarGenero() {
        JOptionPane.showMessageDialog(null, "Genero del Album:..." + getVgenero());
    }

    public void mostrarDescargas() {
        JOptionPane.showMessageDialog(null, "Cantidad de Descargas:..." + getVdescargas());
    }

    public void mostrarArtista() {
        JOptionPane.showMessageDialog(null, "Artista del Album:..." + getVartista());
    }

    public void mostrarCanciones() {
        JOptionPane.showMessageDialog(null, "Digite las canciones del album:..." + getVcanciones());
    }

    public void capturaAlbumNombre() {
        valbumNombre= JOptionPane.showInputDialog(null, "Digite el nombre del Album ", "Album", JOptionPane.INFORMATION_MESSAGE);
    }

    public void capturaFechaCreacion() {
        String diaStr = JOptionPane.showInputDialog(null, "Digite el dia de creacion:", "Fecha de Creacion", JOptionPane.INFORMATION_MESSAGE);
        String mesStr = JOptionPane.showInputDialog(null, "Digite el mes de creacion:", "Fecha de Creacion", JOptionPane.INFORMATION_MESSAGE);
        String anioStr = JOptionPane.showInputDialog(null, "Digite el año de creacion::", "Fecha de Creacion", JOptionPane.INFORMATION_MESSAGE);

        int dia = Integer.parseInt(diaStr);
        int mes = Integer.parseInt(mesStr);
        int anio = Integer.parseInt(anioStr);

        vfechaCreacion = LocalDate.of(anio, mes, dia);
    }

    public void capturaGenero() {
       vgenero= JOptionPane.showInputDialog(null, "Digite el genero del album:...", "Album", JOptionPane.INFORMATION_MESSAGE);
    }

    public void capturaDescargas() {
        String input = JOptionPane.showInputDialog(null, "Ingrese la cantidad de descargas:","Album",JOptionPane.INFORMATION_MESSAGE);
        vdescargas = Integer.parseInt(input); // convierte el texto en número
    }

//    public void capturaArtista() {
//        JOptionPane.showInputDialog(null, "Digite el artista del album", vartista, JOptionPane.INFORMATION_MESSAGE);
//    }
    
    public void capturaCanciones() {
        boolean seguir = true;

        while (seguir) {
            String titulo = JOptionPane.showInputDialog(null, "Ingrese el título de la canción:", "Canción del album", JOptionPane.INFORMATION_MESSAGE);

            if (titulo != null && !titulo.isEmpty()) {
                Cancion nueva = new Cancion();      // Se crea una canción nueva
                nueva.setTitulo(titulo);            // Se asigna el título ingresado
                vcanciones.add(nueva);              // Se agrega a la lista
            }

            int opcion = JOptionPane.showConfirmDialog(null, "¿Desea agregar otra canción?", "Continuar", JOptionPane.YES_NO_OPTION);
            if (opcion != JOptionPane.YES_OPTION) {
                seguir = false;
            }
        }
    }
    
    public void mostrarinformacionAlbum(){
        String mensaje = """
                         ------ALBUM------
                Album: """ + valbumNombre + "\n"
                + "Genero: " + vgenero + "\n"
                + "Fecha de creacion: " + vfechaCreacion + "\n"
                + "Descargas: " + vdescargas + "\n"
                + "Artista:\n" + "\n"
                + "Canciones:\n"+ vcanciones 
                + "\n----------------------";

    
    JOptionPane.showMessageDialog(null, mensaje, "Información de la Canción", JOptionPane.INFORMATION_MESSAGE);
}
 

    /**
     * @return the valbumNombre
     */
    public String getValbumNombre() {
        return valbumNombre;
    }

    /**
     * @param valbumNombre the valbumNombre to set
     */
    public void setValbumNombre(String valbumNombre) {
        this.valbumNombre = valbumNombre;
    }

    /**
     * @return the vfechaCreacion
     */
    public LocalDate getVfechaCreacion() {
        return vfechaCreacion;
    }

    /**
     * @param vfechaCreacion the vfechaCreacion to set
     */
    public void setVfechaCreacion(LocalDate vfechaCreacion) {
        this.vfechaCreacion = vfechaCreacion;
    }

    /**
     * @return the vgenero
     */
    public String getVgenero() {
        return vgenero;
    }

    /**
     * @param vgenero the vgenero to set
     */
    public void setVgenero(String vgenero) {
        this.vgenero = vgenero;
    }

    /**
     * @return the vdescargas
     */
    public int getVdescargas() {
        return vdescargas;
    }

    /**
     * @param vdescargas the vdescargas to set
     */
    public void setVdescargas(int vdescargas) {
        this.vdescargas = vdescargas;
    }

    /**
     * @return the vartista
     */
    public Artista getVartista() {
        return vartista;
    }

    /**
     * @param vartista the vartista to set
     */
    public void setVartista(Artista vartista) {
        this.vartista = vartista;
    }

    /**
     * @return the vcanciones
     */
    public ArrayList<Cancion> getVcanciones() {
        return vcanciones;
    }

    /**
     * @param vcanciones the vcanciones to set
     */
    public void setVcanciones(ArrayList<Cancion> vcanciones) {
        this.vcanciones = vcanciones;
    }

}
