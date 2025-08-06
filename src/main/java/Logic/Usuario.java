package Logic;

public class Usuario {

    private String nombre;
    private String correo;
    private String contrasena;
    private String pais;

    public Usuario() {
        nombre = "";
        correo = "";
        contrasena = "";
        pais = "";
    }

    public Usuario(String nombre, String correo, String contrasena, String pais) {
        this.nombre = nombre;
        this.correo = correo;
        this.contrasena = contrasena;
        this.pais = pais;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    @Override
    public String toString() {
        return "Nombre: " + nombre +
               "\nCorreo: " + correo +
               "\nContrasena: " + contrasena +
               "\nPaís: " + pais + "\n";
    }
}
