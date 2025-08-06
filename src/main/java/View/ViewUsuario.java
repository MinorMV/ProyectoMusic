// ===== View/ViewUsuario.java =====
package View;

import Logic.Usuario;
import javax.swing.JOptionPane;

public class ViewUsuario {

    private String vnombre;
    private String vcorreo;
    private String vcontrasena;
    private String vpais;

    public void capturaNombre() {
        vnombre = JOptionPane.showInputDialog(null, "Ingrese el nombre del Usuario:");
    }

    public void capturaCorreo() {
        vcorreo = JOptionPane.showInputDialog(null, "Ingrese el correo del Usuario:");
    }

    public void capturaContrasena() {
        vcontrasena = JOptionPane.showInputDialog(null, "Ingrese la contraseña del Usuario:");
    }

    public void capturaPais() {
        vpais = JOptionPane.showInputDialog(null, "Ingrese el país del Usuario:");
    }

    public void mostrarDatos(Usuario u) {
        String datos = "----- USUARIO -----\n"
                + "Nombre: " + u.getNombre() + "\n"
                + "Correo: " + u.getCorreo() + "\n"
                + "Contraseña: " + u.getContrasena() + "\n"
                + "País: " + u.getPais();
        JOptionPane.showMessageDialog(null, datos, "Datos encontrados", JOptionPane.INFORMATION_MESSAGE);
    }

    public void mostrarMensajeExito(String mensaje) {
        JOptionPane.showMessageDialog(null, mensaje, "Éxito", JOptionPane.INFORMATION_MESSAGE);
    }

    public void mostrarMensajeError(String mensaje) {
        JOptionPane.showMessageDialog(null, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
    }

    public void mostrarError(String mensaje) {
        JOptionPane.showMessageDialog(null, mensaje, "Error técnico", JOptionPane.ERROR_MESSAGE);
    }

    public String getVnombre() {
        return vnombre;
    }

    public String getVcorreo() {
        return vcorreo;
    }

    public String getVcontrasena() {
        return vcontrasena;
    }

    public String getVpais() {
        return vpais;
    }
}
