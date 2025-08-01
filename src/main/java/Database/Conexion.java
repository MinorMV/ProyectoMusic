package Database;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexion {
    private static Connection con = null;

    public static Connection getConexion() {
        try {
            if (con == null || con.isClosed()) {
                Class.forName("oracle.jdbc.driver.OracleDriver");
                con = DriverManager.getConnection(
                    "jdbc:oracle:thin:@localhost:1521:xe", // Asegúrate de que el puerto y SID estén correctos
                    "system",                              // ← Usuario de Oracle que contiene tus tablas
                    "amorav"                                 // ← Contraseña correcta del usuario
                );
            }
        } catch (Exception e) {
            System.out.println("Error de conexión: " + e.getMessage());
        }
        return con;
    }

    public static void cerrarConexion() {
        try {
            if (con != null && !con.isClosed()) {
                con.close();
            }
        } catch (Exception e) {
            System.out.println("Error al cerrar conexión: " + e.getMessage());
        }
    }
}
