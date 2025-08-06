package View;

import Logic.Artista;
import javax.swing.JOptionPane;

public class ViewArtista {

    public String pedirNombre() {
        return JOptionPane.showInputDialog("Ingrese el nombre del artista:");
    }

    public String pedirGenero() {
        return JOptionPane.showInputDialog("Ingrese el género del artista:");
    }

    public String pedirPais() {
        return JOptionPane.showInputDialog("Ingrese el país del artista:");
    }

    public void mostrarDatos(Artista artista) {
        String info = "----- ARTISTA -----\n"
                + "ID: " + artista.getIdArtista() + "\n"
                + "Nombre: " + artista.getNombre() + "\n"
                + "Género: " + artista.getGenero() + "\n"
                + "País: " + artista.getPais();
        JOptionPane.showMessageDialog(null, info, "Información del Artista", JOptionPane.INFORMATION_MESSAGE);
    }

    public void mostrarMensajeExito(String mensaje) {
        JOptionPane.showMessageDialog(null, mensaje, "Éxito", JOptionPane.INFORMATION_MESSAGE);
    }

    public void mostrarMensajeError(String mensaje) {
        JOptionPane.showMessageDialog(null, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
    }

    public void mostrarError(Exception e) {
        JOptionPane.showMessageDialog(null, "Error inesperado: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }

    public boolean confirmarAccion(String mensaje) {
        int opcion = JOptionPane.showConfirmDialog(null, mensaje, "Confirmar", JOptionPane.YES_NO_OPTION);
        return opcion == JOptionPane.YES_OPTION;
    }
}
