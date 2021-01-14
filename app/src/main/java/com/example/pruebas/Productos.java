package com.example.pruebas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.pruebas.entidades.Producto;
import com.example.pruebas.utilidades.Utilidades;

import java.util.ArrayList;

public class Productos extends AppCompatActivity {

    ArrayList<Producto> listaProductos;
    ArrayList<Producto> listaProductosFiltrados;
    ArrayList<String> listCategorias;
    ConexionSQLiteHelper conn;
    RecyclerView recyclerProductos;
    Spinner comboCategorias;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productos);
        conn=new ConexionSQLiteHelper(getApplicationContext());

        comboCategorias=(Spinner)findViewById(R.id.comboCategorias);

        recyclerProductos=(RecyclerView)findViewById(R.id.rvProductos);
        recyclerProductos.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false));

        cargarComboCategorias();

        consultarListaProductos();

        comboCategorias.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position!=0){
                    listaProductosFiltrada();
                    adapterProductos adapter=new adapterProductos(listaProductosFiltrados);
                    recyclerProductos.setAdapter(adapter);
                }else{
                    adapterProductos adapter=new adapterProductos(listaProductos);
                    recyclerProductos.setAdapter(adapter);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void cargarComboCategorias() {
    listCategorias=new ArrayList<>();
    listCategorias.add("CATEGORIAS");
    listCategorias.add("LEGUMBRES");
    listCategorias.add("LACTEOS");
    listCategorias.add("PECES");
    listCategorias.add("SNACKS");
    ArrayAdapter<CharSequence> adapterCategorias=new ArrayAdapter(this, android.R.layout.simple_spinner_item,listCategorias);
    comboCategorias.setAdapter(adapterCategorias);
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
            Log.i("Cate",producto.getCategoriProducto());
            listaProductos.add(producto);
        }
    }

    private void listaProductosFiltrada(){
    String nombreCategoria=comboCategorias.getSelectedItem().toString();
    Log.i("diferenciar",nombreCategoria);
    Producto producto=null;
    listaProductosFiltrados=new ArrayList<Producto>();
    for(int i=0;i<listaProductos.size();i++){
        if(listaProductos.get(i).getCategoriProducto().equals(nombreCategoria)){
            producto=new Producto();
            producto.setNombreProducto(listaProductos.get(i).getNombreProducto());
            producto.setCategoriProducto(listaProductos.get(i).getCategoriProducto());
            Log.i("Categorias",listaProductos.get(i).getCategoriProducto());
            listaProductosFiltrados.add(producto);
        }
    }

    }

}