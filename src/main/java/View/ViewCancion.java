/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View;

import Logic.Artista;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import javax.swing.JOptionPane;

/**
 *
 * @author sebas
 */
public class ViewCancion {
    private String vtitulo;
    private String valbum;
    private LocalTime vduracion;
    private String vgenero;
    private Artista vartista;
    
    public void mostrarTitulo(){
        JOptionPane.showMessageDialog(null, "Titulo:"+ getVtitulo());
    }
    
    public void mostrarAlbum(){
        JOptionPane.showMessageDialog(null, "Album:"+ getValbum());
    }
    public void mostrarDuracion(){
        JOptionPane.showMessageDialog(null, "Duracion:"+ getVduracion());
    }
    public void mostrarGenero(){
        JOptionPane.showMessageDialog(null, "Genero:"+ getVgenero());
    }
    public void mostrarArtista(){
        JOptionPane.showMessageDialog(null, "Artista:"+ vartista);
    }
    
    public void capturaTitulo(){
        vtitulo= JOptionPane.showInputDialog(null, "Ingrese el Titulo de la cancion:  ", "Cancion",JOptionPane.INFORMATION_MESSAGE);
    }
    public void capturaAlbum(){
        valbum= JOptionPane.showInputDialog(null, "Ingrese el Album al que pertenece la cancion:  ", "Cancion",JOptionPane.INFORMATION_MESSAGE);
    }
    public void capturaDuracion(){
    String minStr = JOptionPane.showInputDialog(null, "Ingrese los minutos de duración:", "Cancion", JOptionPane.INFORMATION_MESSAGE);
    String segStr = JOptionPane.showInputDialog(null, "Ingrese los segundos de duración:", "Cancion", JOptionPane.INFORMATION_MESSAGE);
    int minutos = Integer.parseInt(minStr);  // ⚠️ puede fallar si no es número
    int segundos = Integer.parseInt(segStr);
    vduracion = LocalTime.of(0,minutos,segundos); // 0 horas, X minutos, Y segundos
    }
    public void capturaGenero(){
        vgenero= JOptionPane.showInputDialog(null, "Ingrese el genero de la cancion:  ", "Cancion",JOptionPane.INFORMATION_MESSAGE);
    }
    
    public void mostrarInformacionCompleta() {
    DateTimeFormatter formato = DateTimeFormatter.ofPattern("mm 'min' ss 'seg'");
    String duracionFormateada = vduracion.format(formato);

    String mensaje = """
                     ----CANCION----
                     Titulo: """ + vtitulo +
                     "\nÁlbum: " + valbum +
                     "\nDuración: " + duracionFormateada +
                     "\nGénero: " + vgenero +
                     "\nArtista: " + (vartista != null ? vartista.getNombre() : "No asignado")+
                     "\n ---------------------"
                     ;

    
    JOptionPane.showMessageDialog(null, mensaje, "Información de la Canción", JOptionPane.INFORMATION_MESSAGE);
}

    /**
     * @return the vtitulo
     */
    public String getVtitulo() {
        return vtitulo;
    }

    /**
     * @param vtitulo the vtitulo to set
     */
    public void setVtitulo(String vtitulo) {
        this.vtitulo = vtitulo;
    }

    /**
     * @return the valbum
     */
    public String getValbum() {
        return valbum;
    }

    /**
     * @param valbum the valbum to set
     */
    public void setValbum(String valbum) {
        this.valbum = valbum;
    }

    /**
     * @return the vduracion
     */
    public LocalTime getVduracion() {
        return vduracion;
    }

    /**
     * @param vduracion the vduracion to set
     */
    public void setVduracion(LocalTime vduracion) {
        this.vduracion = vduracion;
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
     * @param vartista the vartista to set
     */
    public void setVartista(Artista vartista) {
        this.vartista = vartista;
    }
    
    
    
}
