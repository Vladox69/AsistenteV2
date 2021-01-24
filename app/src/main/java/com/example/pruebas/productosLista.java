package com.example.pruebas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
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

        adapterProductosLista adapter=new adapterProductosLista(tusProductos,productosLista.this);
        recyclerTusProductos.setAdapter(adapter);

    }

    public void cargarTusProductos(){
        SQLiteDatabase db=conn.getReadableDatabase();
        Producto producto=null;
        tusProductos=new ArrayList<Producto>();
        //Toast.makeText(getApplicationContext(),"Selección:"+listIndex,Toast.LENGTH_SHORT).show();
        Cursor cursor =db.rawQuery("SELECT * FROM PRODUCTOS WHERE NOM_PRO IN (SELECT NOM_PRO_DET FROM DETALLE_COMPRAS WHERE NUM_COM_DET ='"+listIndex+"')",null);
        //String sql="SELECT * FROM PRODUCTOS WHERE NOM_PRO =(SELECT NOM_PRO_DET FROM DETALLE_COMPRAS WHERE NUM_COM_DET =')"+listIndex+"'";
        while (cursor.moveToNext()){
            producto=new Producto();
            producto.setNombreProducto(cursor.getString(0));
            producto.setCategoriProducto(null);
            //Log.i("Cate",producto.getCategoriProducto());
            tusProductos.add(producto);
        }
        conn.close();
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

    public void agregarProductosLista(View view){
        Intent intProductos =new Intent(this,Productos.class);
        intProductos.putExtra("idcompra",listIndex);
        startActivity(intProductos);
    }


}