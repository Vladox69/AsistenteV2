package com.example.pruebas.entidades;

public class ComprasPlanificadas {
    String numeroCompra;
    String nombreCompra;
    String cedulaUsuario;

    public ComprasPlanificadas() {

    }

    public ComprasPlanificadas(String numeroCompra, String nombreCompra, String cedulaUsuario) {
        this.numeroCompra = numeroCompra;
        this.nombreCompra = nombreCompra;
        this.cedulaUsuario = cedulaUsuario;
    }

    public String getNumeroCompra() {
        return numeroCompra;
    }

    public void setNumeroCompra(String numeroCompra) {
        this.numeroCompra = numeroCompra;
    }

    public String getNombreCompra() {
        return nombreCompra;
    }

    public void setNombreCompra(String nombreCompra) {
        this.nombreCompra = nombreCompra;
    }

    public String getCedulaUsuario() {
        return cedulaUsuario;
    }

    public void setCedulaUsuario(String cedulaUsuario) {
        this.cedulaUsuario = cedulaUsuario;
    }
}
