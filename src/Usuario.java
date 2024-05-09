public class Usuario {
    private int idUsuario;
    private String nombreUsuario;
    private String passwd;

    //Constructor
    public Usuario(String nombreUsuario, String passwd){
        this.idUsuario = idUsuario;

        //El siguiente while controla cuando las condiciones no se cumplen)
        boolean condicionPasswd = passwd.length() < 6 || //Que tenga menos de 6 caracteres
                !passwd.matches(".*[a-z].*") || //Que NO contenga una letra minúscula
                !passwd.matches(".*[A-Z].*") || //Que NO contenga una letra mayúscula
                !passwd.matches(".*[^a-zA-Z\\d].*");

        if (condicionPasswd) {
            throw new RuntimeException("La contraseña no cumple los requisitos");
        }
        this.nombreUsuario = nombreUsuario;
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


