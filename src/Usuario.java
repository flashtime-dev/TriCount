import java.util.Objects;

public class Usuario {
    private int idUsuario;
    private String nombreUsuario;
    private String passwd;

    //Constructor
    public Usuario(int idUsuario, String nombreUsuario, String passwd){
        this.idUsuario = idUsuario;
        if (!(comprobarPasswd(passwd))) {
            throw new RuntimeException("La contraseña no cumple con los requisitos mínimos");
        } else {
            this.passwd = passwd;
        }
        this.nombreUsuario = nombreUsuario;

    }

    //Getters y Setters
    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public boolean comprobarPasswd(String passwd) {
        if (passwd.length() < 6 || //Que tenga menos de 6 caracteres
                !passwd.matches(".*[a-z].*") || //Que NO contenga una letra minúscula
                !passwd.matches(".*[A-Z].*") || //Que NO contenga una letra mayúscula
                !passwd.matches(".*[^a-zA-Z\\d].*")) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return idUsuario + " - " + nombreUsuario;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return idUsuario == usuario.idUsuario;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(idUsuario);
    }
}


