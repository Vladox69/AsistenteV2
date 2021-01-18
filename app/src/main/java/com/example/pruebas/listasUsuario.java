package com.example.pruebas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pruebas.entidades.ComprasPlanificadas;
import com.example.pruebas.entidades.Producto;

import java.util.ArrayList;

public class listasUsuario extends AppCompatActivity {
    ConexionSQLiteHelper conn;
    ArrayList<ComprasPlanificadas> listaCompras;
    RecyclerView recyclerListas;
    SharedPreferences preferences;
    String cedula;
    String nombre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listas_usuario);

        conn = new ConexionSQLiteHelper(getApplicationContext());

        recyclerListas = (RecyclerView) findViewById(R.id.rvListasUsuario);
        recyclerListas.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        preferences = getSharedPreferences("Preferences", MODE_PRIVATE);
        cedula = preferences.getString("CED_USU", null);
        nombre = preferences.getString("NOM_USU", null);

        cargarListaCompras();

        adapterListas adapter = new adapterListas(listaCompras, this);

        recyclerListas.setAdapter(adapter);


    }

    public void cargarListaCompras(){
        SQLiteDatabase db=conn.getReadableDatabase();
        ComprasPlanificadas compra=null;
        listaCompras=new ArrayList<ComprasPlanificadas>();
        Cursor cursor =db.rawQuery("SELECT * FROM COMPRAS_PLANIFICADAS WHERE CED_USU_COM='"+cedula+"'",null);
        while (cursor.moveToNext()){
            compra=new ComprasPlanificadas();
            compra.setNumeroCompra(cursor.getString(0));
            compra.setNombreCompra(cursor.getString(1));
            compra.setCedulaUsuario(cursor.getString(2));
            Log.i("Cate",compra.getNombreCompra());
            listaCompras.add(compra);
        }
        conn.close();
    }

}