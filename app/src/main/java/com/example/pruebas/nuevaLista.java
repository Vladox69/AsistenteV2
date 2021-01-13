package com.example.pruebas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class nuevaLista extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nueva_lista);
    }

    public void abrirIntAgregarProductosListaVacia(View view){
        Intent intNuevosProd =new Intent(this,AgregarProductosListaVacia.class);
        startActivity(intNuevosProd);
    }



}