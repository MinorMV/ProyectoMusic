package ControlMain;

import javax.swing.JOptionPane;
import java.sql.Connection;
import Database.Conexion;
import View.ViewUsuario;
import java.awt.HeadlessException;

/**
 * Main reducido: valida conexión, asegura admin y lanza el menú separado.
 */
public class Main {
    public static void main(String[] args) {
        // 1) Probar conexión (como en tu Main original)
        try {
            Connection con = Conexion.getConexion();
            if (con != null) {
                JOptionPane.showMessageDialog(null, "¡Conexión a la base de datos exitosa!");
                Conexion.cerrarConexion();
            }
        } catch (HeadlessException e) {
            JOptionPane.showMessageDialog(null, "Error de conexión: " + e.getMessage());
            return;
        }

        // 2) Asegurar admin al inicio (manteniendo tu flujo)
        try (Connection con = Conexion.getConexion()) {
            ControlUsuario ctrlUsuario = new ControlUsuario(con);
            ViewUsuario viewUsuario = new ViewUsuario();
            ctrlUsuario.asegurarAdmin(viewUsuario);
        } catch (Exception e) {
            // Silenciado como en tu código original
        }

        // 3) Lanzar el menú separado
        new Menu().mostrarInicio();
    }
}
