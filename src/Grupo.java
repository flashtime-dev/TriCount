import java.util.TreeSet;

public class Grupo {
    private int idGrupo; //Variable para asignar un id a cada grupo
    private String nombreGrupo; //Nombre del grupo
    private Usuario usuarioAdmin; //Usuario administrador del grupo
    private TreeSet usuarios; //Conjunto de usuarios que pertenecen al grupo

    //Constructor
    public Grupo(int idGrupo, String nombreGrupo, Usuario usuarioAdmin, TreeSet usuarios) {
        this.nombreGrupo = nombreGrupo;
        this.usuarioAdmin = usuarioAdmin;
        this.usuarios = usuarios;
        this.idGrupo = idGrupo;
    } //Fin del constructor

    //Getters y Setters

    public int getIdGrupo() {
        return idGrupo;
    } //Obtener el id del grupo

    public void setIdGrupo(int idGrupo) {
        this.idGrupo = idGrupo;
    } //Cambiar el id del grupo

    public String getNombreGrupo() {
        return nombreGrupo;
    } //Obtener el nombre del grupo

    public void setNombreGrupo(String nombreGrupo) {
        this.nombreGrupo = nombreGrupo;
    } //Cambiar el nombre del grupo

    public Usuario getUsuarioAdmin() {
        return usuarioAdmin;
    } //Obtener el usuario administrador

    public void setUsuarioAdmin(Usuario usuarioAdmin) {
        this.usuarioAdmin = usuarioAdmin;
    } //Cambiar el usuario administrador

    public TreeSet getUsuarios() {
        return usuarios;
    } //Obtener el conjunto de usuarios

    public void setUsuarios(TreeSet usuarios) {
        this.usuarios = usuarios;
    } //Cambiar el conjunto de usuarios

    //Métodos
    public void agregarUsuario(Usuario admin, Usuario usuario) {
        if (usuarios.contains(usuario)) {
            System.out.println("El usuario ya está en el grupo");
        } else {
            if (admin.equals(this.usuarioAdmin)) {
                usuarios.add(usuario);
            } else {
                System.out.println("No tienes permisos para agregar usuarios");
            }
        }
    } //Método para agregar un usuario al grupo

    public void eliminarUsuario(Usuario admin, Usuario usuario) {
        if (admin.equals(this.usuarioAdmin)) {
            usuarios.remove(usuario);
        } else {
            System.out.println("No tienes permisos para agregar usuarios");
        }
    } //Método para eliminar un usuario del grupo

    public void cambiarAdmin(Usuario admin, Usuario usuario) {
        if (admin.equals(this.usuarioAdmin)) {
            System.out.println("Elige el nuevo administrador");
            //Código para cambiar el administrador
                if (usuarios.contains(usuario)) {
                    usuarioAdmin = usuario;
                } else {
                    System.out.println("El usuario no está en el grupo");
                }
        } else {
            System.out.println("No tienes permisos para cambiar el administrador");
        }
    } //Método para cambiar el administrador del grupo
}


