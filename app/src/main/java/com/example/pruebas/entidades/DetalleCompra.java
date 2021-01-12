package com.example.pruebas.entidades;

public class DetalleCompra {
    String idDetalle;
    String numeroCompra;
    String nombreProducto;

    public DetalleCompra() {

    }

    public DetalleCompra(String idDetalle, String numeroCompra, String nombreProducto) {
        this.idDetalle = idDetalle;
        this.numeroCompra = numeroCompra;
        this.nombreProducto = nombreProducto;
    }

    public String getIdDetalle() {
        return idDetalle;
    }

    public void setIdDetalle(String idDetalle) {
        this.idDetalle = idDetalle;
    }

    public String getNumeroCompra() {
        return numeroCompra;
    }

    public void setNumeroCompra(String numeroCompra) {
        this.numeroCompra = numeroCompra;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }
}
