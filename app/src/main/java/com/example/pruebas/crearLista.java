package com.example.pruebas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pruebas.entidades.ComprasPlanificadas;
import com.example.pruebas.entidades.Producto;

import java.util.ArrayList;

public class crearLista extends AppCompatActivity {
    ConexionSQLiteHelper conn;
    EditText nombreLista;
    TextView txtPrueba;
    SharedPreferences preferences;
    String cedula,nombre;
    int ultimoIndice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_lista);
        nombreLista=(EditText) findViewById(R.id.txtNombreLista);
        txtPrueba=(TextView) findViewById(R.id.idUsuarioPrueba);
        preferences=getSharedPreferences("Preferences",MODE_PRIVATE);
        cedula=preferences.getString("CED_USU",null);
        nombre=preferences.getString("NOM_USU",null);
        if(cedula!=null && nombre!=null){
            txtPrueba.setText("Bienvenido "+nombre+" "+cedula);
        }
    }

    //"CREATE TABLE COMPRAS_PLANIFICADAS (NUM_COM INTEGER PRIMARY KEY AUTOINCREMENT, NOM_COM TEXT,CED_USU_COM TEXT, FOREIGN KEY (CED_USU_COM) REFERENCES USUARIOS(CED_USU))";
    public void registrarLista(){
        conn=new ConexionSQLiteHelper(getApplicationContext());

        SQLiteDatabase db=conn.getWritableDatabase();

        /*String sql="INSERT INTO COMPRAS_PLANIFICADAS(NUM_COM, NOM_COM, CED_USU_COM) " +
                "VALUES("+1+",'"+nombreLista.getText().toString()+"',"+"'"+cedula+"')";*/
        ContentValues values=new ContentValues();
        values.put("CED_USU_COM",cedula);
        values.put("NOM_COM",nombreLista.getText().toString());

        Long idResultante=db.insert("COMPRAS_PLANIFICADAS","NUM_COM",values);

        //db.execSQL(sql);

        Toast.makeText(getApplicationContext(),"COMPRA: "+nombreLista.getText().toString(),Toast.LENGTH_SHORT).show();

        conn.close();
    }

    public void crearLista(View view ){

        registrarLista();
        obtenerUltimaInsercion();
        Intent intProductos =new Intent(this,Productos.class);
        intProductos.putExtra("idlista",ultimoIndice);
        startActivity(intProductos);

    }

    public void obtenerUltimaInsercion(){

        SQLiteDatabase db=conn.getReadableDatabase();
        Cursor cursor =db.rawQuery("SELECT * FROM COMPRAS_PLANIFICADAS ",null);

        while (cursor.moveToNext()){
            ultimoIndice =cursor.getInt(0);
        }
    }

    public void inicio(){
        Intent inicio = new Intent(this, Principal.class);
        inicio.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(inicio);
    }

    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menuinicio, menu);
        return true;
    }

    //metodo para asiganr funciones correspondintes a las opciones
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        if( id== R.id.cerrarSesion ){
            inicio();
        }
        return super.onOptionsItemSelected(item);
    }

}