public class Division_Gastos {
    //Atributos de la clase
    int idDivision; //Identificador de la division
    Gasto gasto; //Gasto al que pertenece la division
    Usuario usuarioPagador; //Usuario que pagó el gasto
    Usuario usuarioDebedor; //Usuario que debe pagar el gasto
    double montoPagado; //Monto pagado por el usuario pagador
    double montoDebido; //Monto que debe pagar el usuario debedor

    //Constructor
    public Division_Gastos(int idDivision, Gasto gasto, Usuario usuarioPagador, Usuario usuarioDebedor, double montoPagado, double montoDebido) {
        this.idDivision = idDivision;
        this.gasto = gasto;
        this.usuarioPagador = usuarioPagador;
        this.usuarioDebedor = usuarioDebedor;
        this.montoPagado = montoPagado;
        this.montoDebido = montoDebido;
    } //Fin del constructor

    //Getters y Setters

    public int getIdDivision() {
        return idDivision;
    } //Obtener el id de la division

    public void setIdDivision(int idDivision) {
        this.idDivision = idDivision;
    } //Cambiar el id de la division

    public Gasto getGasto() {
        return gasto;
    } //Obtener el gasto

    public void setGasto(Gasto gasto) {
        this.gasto = gasto;
    } //Cambiar el gasto

    public Usuario getUsuarioPagador() {
        return usuarioPagador;
    } //Obtener el usuario pagador

    public void setUsuarioPagador(Usuario usuarioPagador) {
        this.usuarioPagador = usuarioPagador;
    } //Cambiar el usuario pagador

    public Usuario getUsuarioDebedor() {
        return usuarioDebedor;
    } //Obtener el usuario debedor

    public void setUsuarioDebedor(Usuario usuarioDebedor) {
        this.usuarioDebedor = usuarioDebedor;
    } //Cambiar el usuario debedor

    public double getMontoPagado() {
        return montoPagado;
    } //Obtener el monto pagado

    public void setMontoPagado(double montoPagado) {
        this.montoPagado = montoPagado;
    } //Cambiar el monto pagado

    public double getMontoDebido() {
        return montoDebido;
    } //Obtener el monto debido

    public void setMontoDebido(double montoDebido) {
        this.montoDebido = montoDebido;
    } //Cambiar el monto debido

    @Override
    public String toString() {
        return "Division_Gastos{" +
                "idDivision=" + idDivision +
                ", gasto=" + gasto +
                ", usuarioPagador=" + usuarioPagador +
                ", usuarioDebedor=" + usuarioDebedor +
                ", montoPagado=" + montoPagado +
                ", montoDebido=" + montoDebido +
                '}';
    }
}
