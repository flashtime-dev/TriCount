import java.util.TreeSet;

public class Grupo {
    int idGrupo;
    String nombreGrupo;
    Usuario usuarioAdmin;
    TreeSet usuarios;

    //Constructor
    public Grupo(int idGrupo, String nombreGrupo, Usuario usuarioAdmin, TreeSet usuarios) {
        this.idGrupo = idGrupo;
        this.nombreGrupo = nombreGrupo;
        this.usuarioAdmin = usuarioAdmin;
        this.usuarios = usuarios;
    }

    //Getters y Setters

    public int getIdGrupo() {
        return idGrupo;
    }

    public void setIdGrupo(int idGrupo) {
        this.idGrupo = idGrupo;
    }

    public String getNombreGrupo() {
        return nombreGrupo;
    }

    public void setNombreGrupo(String nombreGrupo) {
        this.nombreGrupo = nombreGrupo;
    }

    public Usuario getUsuarioAdmin() {
        return usuarioAdmin;
    }

    public void setUsuarioAdmin(Usuario usuarioAdmin) {
        this.usuarioAdmin = usuarioAdmin;
    }

    public TreeSet getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(TreeSet usuarios) {
        this.usuarios = usuarios;
    }

    //Métodos
    public void anadirUsuario() {

    }

    public void eliminarUsuario() {

    }

    public void cambiarAdmin() {

    }
}
