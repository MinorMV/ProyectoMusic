package View;

import javax.swing.JOptionPane;

public class ViewUsuario {

    private String vnombre;
    private String vcorreo;
    private String vcontrasena;

    public void mostrarNombre() {
        JOptionPane.showMessageDialog(null, "El nombre elegido para el usuario es: " + getVnombre());
    }

    public void mostrarCorreo() {
        JOptionPane.showMessageDialog(null, "El correo de la cuenta es: " + getVcorreo());
    }

    public void mostrarContrasena() {
        JOptionPane.showMessageDialog(null, "La contraseña ha sido elegida exitosamente.");
    }

    public void capturaNombre() {
        setVnombre(JOptionPane.showInputDialog(null, "Ingrese el nombre del Usuario:", "Usuario", JOptionPane.INFORMATION_MESSAGE));
    }

    public void capturaCorreo() {
        setVcorreo(JOptionPane.showInputDialog(null, "Ingrese el correo del Usuario:", "Usuario", JOptionPane.INFORMATION_MESSAGE));
    }

    public void capturaContrasena() {
        setVcontrasena(JOptionPane.showInputDialog(null, "Ingrese la contraseña del Usuario:", "Usuario", JOptionPane.INFORMATION_MESSAGE));
    }

    public void mostrarInformacionUsuario() {
        String informacion = """
                             --------USUARIO------
                             Nombre de Usuario: """ + vnombre +
                             "\nCorreo de Usuario: " + vcorreo +
                             "\n----------------------------";

        JOptionPane.showMessageDialog(null, informacion, "Datos de Usuario", JOptionPane.INFORMATION_MESSAGE);
    }

    public String getVnombre() {
        return vnombre;
    }

    public void setVnombre(String vnombre) {
        this.vnombre = vnombre;
    }

    public String getVcorreo() {
        return vcorreo;
    }

    public void setVcorreo(String vcorreo) {
        this.vcorreo = vcorreo;
    }

    public String getVcontrasena() {
        return vcontrasena;
    }

    public void setVcontrasena(String vcontrasena) {
        this.vcontrasena = vcontrasena;
    }
}
