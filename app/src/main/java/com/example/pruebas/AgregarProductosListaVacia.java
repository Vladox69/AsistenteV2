package com.example.pruebas;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class AgregarProductosListaVacia extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_productos_lista_vacia);
    }

    public void abrirIntAgregarProductos(View view){
        Intent intProductos =new Intent(this,Productos.class);
        startActivity(intProductos);
    }
}
