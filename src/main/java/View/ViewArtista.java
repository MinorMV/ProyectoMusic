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
        JOptionPane.showMessageDialog(null, artista.toString());
    }
}
