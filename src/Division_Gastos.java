public class Division_Gastos {
    int idDivision;
    Gasto gasto;
    Usuario usuarioPagador;
    Usuario usuarioDebedor;
    double montoPagado;
    double montoDebido;

    //Constructor
    public Division_Gastos(int idDivision, Gasto gasto, Usuario usuarioPagador, Usuario usuarioDebedor, double montoPagado, double montoDebido) {
        this.idDivision = idDivision;
        this.gasto = gasto;
        this.usuarioPagador = usuarioPagador;
        this.usuarioDebedor = usuarioDebedor;
        this.montoPagado = montoPagado;
        this.montoDebido = montoDebido;
    }

    //Getters y Setters

    public int getIdDivision() {
        return idDivision;
    }

    public void setIdDivision(int idDivision) {
        this.idDivision = idDivision;
    }

    public Gasto getGasto() {
        return gasto;
    }

    public void setGasto(Gasto gasto) {
        this.gasto = gasto;
    }

    public Usuario getUsuarioPagador() {
        return usuarioPagador;
    }

    public void setUsuarioPagador(Usuario usuarioPagador) {
        this.usuarioPagador = usuarioPagador;
    }

    public Usuario getUsuarioDebedor() {
        return usuarioDebedor;
    }

    public void setUsuarioDebedor(Usuario usuarioDebedor) {
        this.usuarioDebedor = usuarioDebedor;
    }

    public double getMontoPagado() {
        return montoPagado;
    }

    public void setMontoPagado(double montoPagado) {
        this.montoPagado = montoPagado;
    }

    public double getMontoDebido() {
        return montoDebido;
    }

    public void setMontoDebido(double montoDebido) {
        this.montoDebido = montoDebido;
    }
}
