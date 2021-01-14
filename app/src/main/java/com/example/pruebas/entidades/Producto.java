package com.example.pruebas.entidades;

public class Producto {
    String nombreProducto;
    String categoriProducto;
    boolean seleccion;

    public boolean isSeleccion() {
        return seleccion;
    }

    public void setSeleccion(boolean seleccion) {
        this.seleccion = seleccion;
    }


    public Producto() {
    }

    public Producto(String nombreProducto, String categoriProducto) {
        this.nombreProducto = nombreProducto;
        this.categoriProducto = categoriProducto;
        this.seleccion=false;
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
