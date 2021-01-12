package com.example.pruebas;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.pruebas.entidades.Producto;
import com.example.pruebas.utilidades.Utilidades;

import java.util.ArrayList;

public class Productos extends AppCompatActivity {

    ArrayList<String> nombresProductosSnacks,nombresProductosPreces,nombresProductosLegumbres,nombresProductosLacteos;
    ArrayList<Producto> listaProductos;
    ConexionSQLiteHelper conn;
    Spinner cbProductosSnacks,cbProductosPeces,cbProductosLegumbres,cbProductosLacteos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productos);

        conn=new ConexionSQLiteHelper(getApplicationContext());

        cbProductosSnacks=(Spinner)findViewById(R.id.cbSnakcs);
        cbProductosPeces=(Spinner)findViewById(R.id.cbPeces);
        cbProductosLegumbres=(Spinner)findViewById(R.id.cbLegumbres);
        cbProductosLacteos=(Spinner)findViewById(R.id.cbLacteos);
        consultarListaProductos();
        ArrayAdapter<CharSequence> adaptadorSnacks,adaptadorPeces,adaptadorLegumbres,adaptadorLacteos;
        adaptadorSnacks=new ArrayAdapter(this, android.R.layout.simple_spinner_item,nombresProductosSnacks);
        adaptadorPeces=new ArrayAdapter(this, android.R.layout.simple_spinner_item,nombresProductosPreces);
        adaptadorLegumbres=new ArrayAdapter(this, android.R.layout.simple_spinner_item,nombresProductosLegumbres);
        adaptadorLacteos=new ArrayAdapter(this, android.R.layout.simple_spinner_item,nombresProductosLacteos);

        cbProductosSnacks.setAdapter(adaptadorSnacks);
        cbProductosPeces.setAdapter(adaptadorPeces);
        cbProductosLegumbres.setAdapter(adaptadorLegumbres);
        cbProductosLacteos.setAdapter(adaptadorLacteos);

    }



    private void consultarListaProductos(){
        SQLiteDatabase db=conn.getReadableDatabase();
        Producto producto=null;
        listaProductos=new ArrayList<Producto>();
        Cursor cursor =db.rawQuery("SELECT * FROM PRODUCTOS",null);

        while (cursor.moveToNext()){
            producto=new Producto();
            producto.setNombreProducto(cursor.getString(0));
            producto.setCategoriProducto(cursor.getString(1));
            //Log.i("Nombre Producto:",producto.getNombreProducto());
            listaProductos.add(producto);
        }
        obtenerProductosSnnipers();
    }

    public void obtenerProductosSnnipers(){
        nombresProductosSnacks=new ArrayList<>();
        nombresProductosPreces=new ArrayList<>();
        nombresProductosLegumbres=new ArrayList<>();
        nombresProductosLacteos=new ArrayList<>();
        for (int i=0;i<listaProductos.size();i++){
            if(listaProductos.get(i).getCategoriProducto().equals("SNACK")){
                nombresProductosSnacks.add(listaProductos.get(i).getNombreProducto());
            }else if(listaProductos.get(i).getCategoriProducto().equals("PECES")){
                nombresProductosPreces.add(listaProductos.get(i).getNombreProducto());
            }else if(listaProductos.get(i).getCategoriProducto().equals("LEGUMBRE")){
                nombresProductosLegumbres.add(listaProductos.get(i).getNombreProducto());
            }else if(listaProductos.get(i).getCategoriProducto().equals("LACTEO")){
                nombresProductosLacteos.add(listaProductos.get(i).getNombreProducto());
            }
        }
    }
    public void obtenerProductosDetalle(){
        
    }
}