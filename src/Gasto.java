import java.time.LocalDateTime;

public class Gasto {
    //Atributos de la clase
    int idGasto; //Identificador del gasto
    Usuario usuarioPagador; //Usuario que pagó el gasto
    Grupo grupo; //Grupo al que pertenece el gasto
    String descripcion; //Descripción del gasto
    LocalDateTime fecha; //Fecha en la que se realizó el gasto
    double monto;

    //Constructor
    public Gasto(int idGasto, Usuario usuarioPagador, Grupo grupo, String descripcion, LocalDateTime fecha, double monto) {
        this.idGasto = idGasto;
        this.usuarioPagador = usuarioPagador;
        this.grupo = grupo;
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.monto = monto;
    } //Fin del constructor

    //Getters y Setters

    public int getIdGasto() {
        return idGasto;
    } //Obtener el identificador del gasto

    public void setIdGasto(int idGasto) {
        this.idGasto = idGasto;
    } //Establecer el identificador del gasto

    public Usuario getUsuarioPagador() {
        return usuarioPagador;
    } //Obtener el usuario que pagó el gasto

    public void setUsuarioPagador(Usuario usuarioPagador) {
        this.usuarioPagador = usuarioPagador;
    } //Establecer el usuario que pagó el gasto

    public Grupo getGrupo() {
        return grupo;
    } //Obtener el grupo al que pertenece el gasto

    public void setGrupo(Grupo grupo) {
        this.grupo = grupo;
    } //Establecer el grupo al que pertenece el gasto

    public String getDescripcion() {
        return descripcion;
    } //Obtener la descripción del gasto

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    } //Establecer la descripción del gasto

    public LocalDateTime getFecha() {
        return fecha;
    } //Obtener la fecha en la que se realizó el gasto

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    } //Establecer la fecha en la que se realizó el gasto

    public double getMonto() {
        return monto;
    } //Obtener el monto del gasto

    public void setMonto(double monto) {
        this.monto = monto;
    } //Establecer el monto del gasto
}
