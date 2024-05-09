public class Usuario {
    private int idUsuario;
    private String nombreUsuario;
    private String passwd;

    //Constructor
    public Usuario(String nombreUsuario, String passwd){
        calcularIdUsuario();
        if (!(comprobarContraseña(passwd))) {
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

    public boolean comprobarContraseña(String passwd) {
        if (passwd.length() < 6 || //Que tenga menos de 6 caracteres
                !passwd.matches(".*[a-z].*") || //Que NO contenga una letra minúscula
                !passwd.matches(".*[A-Z].*") || //Que NO contenga una letra mayúscula
                !passwd.matches(".*[^a-zA-Z\\d].*")) {
            return false;
        }
        return true;
    }
    public void calcularIdUsuario() {
        if (idUsuario == -1) {
            idUsuario = 0;
        } else {
            idUsuario++;
        }
    } //Método para asignar un id a cada grupo
}


