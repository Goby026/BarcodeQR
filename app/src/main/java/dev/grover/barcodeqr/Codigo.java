package dev.grover.barcodeqr;

public class Codigo {

    String codigo;
    int estado;

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public Codigo(){}

    public Codigo(String codigo, int estado) {
        this.codigo = codigo;
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Codigo{" +
                "codigo='" + codigo + '\'' +
                ", estado=" + estado +
                '}';
    }
}
