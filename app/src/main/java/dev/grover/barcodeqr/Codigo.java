package dev.grover.barcodeqr;

public class Codigo {

    String codigo;
    int estado;
    String tipo;

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

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Codigo(){}

    public Codigo(String codigo, int estado, String tipo) {
        this.codigo = codigo;
        this.estado = estado;
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return "Codigo{" +
                "codigo='" + codigo + '\'' +
                ", estado=" + estado +
                ", tipo='" + tipo + '\'' +
                '}';
    }
}
