package com.example.pruebas.entidades;

import java.util.ArrayList;

public class ComprasPlanificadas {
    String numeroCompra;
    String nombreCompra;
    String cedulaUsuario;
    ArrayList<Producto> listaProductos;

    public ComprasPlanificadas() {

    }

    public ComprasPlanificadas(String numeroCompra, String nombreCompra, String cedulaUsuario) {
        this.numeroCompra = numeroCompra;
        this.nombreCompra = nombreCompra;
        this.cedulaUsuario = cedulaUsuario;
        this.listaProductos=null;
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

    public void setNombreCompra(String nombreCompra) { this.nombreCompra = nombreCompra; }

    public String getCedulaUsuario() {
        return cedulaUsuario;
    }

    public void setCedulaUsuario(String cedulaUsuario) {
        this.cedulaUsuario = cedulaUsuario;
    }

    public ArrayList<Producto> getListaProductos() {
        return listaProductos;
    }

    public void setListaProductos(ArrayList<Producto> listaProductos) { this.listaProductos = listaProductos; }
}
