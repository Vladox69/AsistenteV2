package com.example.pruebas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.pruebas.entidades.Producto;

import java.util.ArrayList;

public class productosLista extends AppCompatActivity {

    ConexionSQLiteHelper conn;
    RecyclerView recyclerTusProductos;
    ArrayList<Producto> tusProductos;
    String listIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productos_lista);

        conn=new ConexionSQLiteHelper(getApplicationContext());

        Bundle parametros = getIntent().getExtras();
        listIndex = parametros.getString("iddetalle");

        recyclerTusProductos=(RecyclerView)findViewById(R.id.rvTusProductos);
        recyclerTusProductos.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false));

        cargarTusProductos();

        adapterProductos adapter=new adapterProductos(tusProductos);
        recyclerTusProductos.setAdapter(adapter);

    }

    public void cargarTusProductos(){
        SQLiteDatabase db=conn.getReadableDatabase();
        Producto producto=null;
        tusProductos=new ArrayList<Producto>();
        Bundle parametros = getIntent().getExtras();
        Toast.makeText(getApplicationContext(),"Selección:"+parametros.getInt("iddetalle"),Toast.LENGTH_SHORT).show();
        Cursor cursor =db.rawQuery("SELECT * FROM DETALLE_COMPRAS WHERE NUM_COM_DET='"+listIndex+"'",null);
        while (cursor.moveToNext()){
            producto=new Producto();
            producto.setNombreProducto(cursor.getString(1));
            producto.setCategoriProducto(null);
            //Log.i("Cate",producto.getCategoriProducto());
            tusProductos.add(producto);
        }
        conn.close();
    }

}