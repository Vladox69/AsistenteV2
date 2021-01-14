package com.example.pruebas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Principal extends AppCompatActivity {
    SharedPreferences preferences;
    TextView txtPrueba;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        preferences=getSharedPreferences("Preferences",MODE_PRIVATE);
        txtPrueba=(TextView)findViewById(R.id.txtVPrueba);
        String cedula=preferences.getString("CED_USU",null);
        String nombre=preferences.getString("NOM_USU",null);
        if(cedula!=null && nombre!=null){
            txtPrueba.setText("Bienvenido "+nombre);
        }
    }

    public void abrirIntProductos(View view){
        Intent intProductos =new Intent(this,Productos.class);
        startActivity(intProductos);
    }

}