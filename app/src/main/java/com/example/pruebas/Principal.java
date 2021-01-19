package com.example.pruebas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
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
            txtPrueba.setText("Bienvenido "+nombre+" "+cedula);
        }
    }

    public void abrirIntProductos(View view){
        Intent intProductos =new Intent(this,Productos.class);
        startActivity(intProductos);
    }

    public void abrirIntNuevaLista(View view){
        Intent crearNuevaLista = new Intent(this,crearLista.class);
        startActivity(crearNuevaLista);
    }

    public void abrirIntListas(View view){
        Intent intListasUsuario=new Intent(this,listasUsuario.class);
        startActivity(intListasUsuario);
    }

    public void cerraSesion(){
        preferences.edit().clear().apply();
        Intent inicio = new Intent(this, MainActivity.class);
        inicio.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(inicio);
    }

    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.overflow, menu);
        return true;
    }

    //metodo para asiganr funciones correspondintes a las opciones
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        if( id== R.id.cerrarSesion ){
            cerraSesion();
        }
        return super.onOptionsItemSelected(item);

    }

}