import java.time.LocalDateTime;

public class Gasto {
    int idGasto;
    Usuario usuarioPagador;
    Grupo grupo;
    String descripcion;
    LocalDateTime fecha;
    double monto;

    //Constructor
    public Gasto(int idGasto, Usuario usuarioPagador, Grupo grupo, String descripcion, LocalDateTime fecha, double monto) {
        this.idGasto = idGasto;
        this.usuarioPagador = usuarioPagador;
        this.grupo = grupo;
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.monto = monto;
    }

    //Getters y Setters

    public int getIdGasto() {
        return idGasto;
    }

    public void setIdGasto(int idGasto) {
        this.idGasto = idGasto;
    }

    public Usuario getUsuarioPagador() {
        return usuarioPagador;
    }

    public void setUsuarioPagador(Usuario usuarioPagador) {
        this.usuarioPagador = usuarioPagador;
    }

    public Grupo getGrupo() {
        return grupo;
    }

    public void setGrupo(Grupo grupo) {
        this.grupo = grupo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }
}
