package com.example.pruebas.entidades;

import java.util.Date;

public class Lista {
    private String nombreLista;
    private Date fechaLista;

    public Lista() {
    }

    public Lista(String nombreLista, Date fechaLista) {
        this.setNombreLista(nombreLista);
        this.setFechaLista(fechaLista);
    }

    public String getNombreLista() {
        return nombreLista;
    }

    public void setNombreLista(String nombreLista) {
        this.nombreLista = nombreLista;
    }

    public Date getFechaLista() {
        return fechaLista;
    }

    public void setFechaLista(Date fechaLista) {
        this.fechaLista = fechaLista;
    }
}
