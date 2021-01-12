package com.example.pruebas.entidades;

public class Producto {
    String nombreProducto;
    String categoriProducto;

    public Producto() {
    }

    public Producto(String nombreProducto, String categoriProducto) {
        this.nombreProducto = nombreProducto;
        this.categoriProducto = categoriProducto;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public String getCategoriProducto() {
        return categoriProducto;
    }

    public void setCategoriProducto(String categoriProducto) {
        this.categoriProducto = categoriProducto;
    }
}
