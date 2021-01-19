package com.example.pruebas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.pruebas.utilidades.Utilidades;

public class nuevaLista extends AppCompatActivity {
    EditText nombreCompra, cedUsuCompra;
    Integer numCompra = 0;
    ConexionSQLiteHelper conn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nueva_lista);
        numCompra = numCompra+1;
        nombreCompra = findViewById(R.id.NombreListaNueva);
        cedUsuCompra = findViewById(R.id.txtCedula);

    }



    public void onClick(View view){
        registrarLista();
    }

    private void registrarLista() {
        conn = new ConexionSQLiteHelper(getApplicationContext());
        SQLiteDatabase db = conn.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Utilidades.NUMERO_COMPRA,numCompra);
        values.put(Utilidades.NOMBRE_COMPRA,nombreCompra.getText().toString());
        values.put(Utilidades.CEDULA_USUARIO_COMPRA,cedUsuCompra.getText().toString());

        Long idResultante = db.insert("COMPRAS_PLANIFICADAS","NUM_COM",values);

        Toast.makeText(getApplicationContext(), "Lista Numero: " + idResultante,Toast.LENGTH_SHORT).show();
    }

    public void inicio(){
        Intent inicio = new Intent(this, Principal.class);
        startActivity(inicio);
    }

    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menuinicio, menu);
        return true;
    }

    //metodo para asiganr funciones correspondintes a las opciones
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        if( id== R.id.inicio ){
            inicio();
        }
        return super.onOptionsItemSelected(item);
    }

}