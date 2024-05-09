public class Usuario {
    int idUsuario;
    String nombreUsuario;
    String passwd;

    //Constructor
    public Usuario(int idUsuario, String nombreUsuario, String passwd) {
        this.idUsuario = idUsuario;
        this.nombreUsuario = nombreUsuario;
        //passwd
        this.passwd = passwd;
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
}


