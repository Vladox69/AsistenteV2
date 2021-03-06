package com.example.pruebas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.pruebas.entidades.Producto;
import com.example.pruebas.utilidades.Utilidades;

import java.util.ArrayList;

public class Productos extends AppCompatActivity {

    ArrayList<Producto> listaProductos;
    ArrayList<Producto> productosSeleccionados;
    ArrayList<String> listCategorias;
    ConexionSQLiteHelper conn;
    RecyclerView recyclerProductos;
    Spinner comboCategorias;
    int listIndex;
    String listaAgregar;

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

        Bundle parametros = getIntent().getExtras();
        listIndex = parametros.getInt("idlista");

        Bundle parametrosAgregar = getIntent().getExtras();
        listaAgregar = parametrosAgregar.getString("idcompra");

        comboCategorias.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position!=0){
                    Producto producto=null;
                    ArrayList<Producto> filtrados =new ArrayList<>();
                    String nombreCategoria=comboCategorias.getSelectedItem().toString();
                    for(int i=0;i<listaProductos.size();i++){
                        if(listaProductos.get(i).getCategoriProducto().equals(nombreCategoria)){
                            producto=listaProductos.get(i);
                            //Log.i("Categorias",listaProductos.get(i).getCategoriProducto());
                            filtrados.add(producto);
                        }
                    }
                    adapterProductos adapter=new adapterProductos(filtrados,Productos.this);
                    recyclerProductos.setAdapter(adapter);
                }else{
                    adapterProductos adapter=new adapterProductos(listaProductos,Productos.this);
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
            //Log.i("Cate",producto.getCategoriProducto());
            listaProductos.add(producto);
        }
        conn.close();
    }

    public void listaProductosMarcados(){
        productosSeleccionados=new ArrayList<Producto>();
        for(int i=0;i<listaProductos.size();i++){
            if(listaProductos.get(i).isSeleccion()){
                productosSeleccionados.add(listaProductos.get(i));

            }
        }

    }

    public void guardarProductos(View view){
        listaProductosMarcados();
        conn=new ConexionSQLiteHelper(getApplicationContext());

        SQLiteDatabase db=conn.getWritableDatabase();

        if(listaAgregar==null){
            for(int i=0;i<productosSeleccionados.size();i++){
                ContentValues values=new ContentValues();
                values.put("NOM_PRO_DET",productosSeleccionados.get(i).getNombreProducto());
                values.put("NUM_COM_DET",listIndex);

                Long idResultante=db.insert("DETALLE_COMPRAS","ID_DET",values);

                //Toast.makeText(getApplicationContext(),"PRODUCTOS: "+productosSeleccionados.get(i).getNombreProducto(),Toast.LENGTH_SHORT).show();

            }
        }else{
            for(int i=0;i<productosSeleccionados.size();i++){

                ContentValues values=new ContentValues();
                values.put("NOM_PRO_DET",productosSeleccionados.get(i).getNombreProducto());
                values.put("NUM_COM_DET",listaAgregar);

                Long idResultante=db.insert("DETALLE_COMPRAS","ID_DET",values);

                //Toast.makeText(getApplicationContext(),"PRODUCTOS: "+productosSeleccionados.get(i).getNombreProducto(),Toast.LENGTH_SHORT).show();
            }

        }
        regresarIntListas();
        conn.close();
    }

    public void regresarIntListas(){
        Intent intListasUsuario =new Intent(this,listasUsuario.class);
        startActivity(intListasUsuario);
    }

    //metodos del muenu
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