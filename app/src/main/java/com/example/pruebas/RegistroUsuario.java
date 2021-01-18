package com.example.pruebas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class RegistroUsuario extends AppCompatActivity {
    ConexionSQLiteHelper conn;
    EditText cedula,nombre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_usuario);
        cedula=(EditText)findViewById(R.id.txtCedula);
        nombre=(EditText)findViewById(R.id.txtNombre);
    }


    public boolean userExit(){
        conn =new ConexionSQLiteHelper(getApplicationContext());
        SQLiteDatabase baseDatos = conn.getWritableDatabase();
        String ced = cedula.getText().toString();
        Cursor fila = baseDatos.rawQuery("select * from USUARIOS where CED_USU =" + ced, null);
        if ( fila.moveToFirst() ){
            return true;
        }else{
            return false;
        }
    }

    public void registrarUsuarios(){
        conn=new ConexionSQLiteHelper(getApplicationContext());

        SQLiteDatabase db=conn.getWritableDatabase();

        ContentValues values=new ContentValues();
        values.put("CED_USU",cedula.getText().toString());
        values.put("NOM_USU",nombre.getText().toString());

        Long idResultante=db.insert("USUARIOS","CED_USU",values);

        Toast.makeText(getApplicationContext(),"Usuario: "+idResultante,Toast.LENGTH_SHORT).show();
        //conn.close();
    }

    public void guardarUsuarios(View view){
        String ced = cedula.getText().toString();
        String nom = nombre.getText().toString();
        if( !ced.isEmpty() || !nom.isEmpty()  ){
            //usuario.add(cedula.getText().toString());
            if( userExit() == true ){
                Toast.makeText(this, "EL usuario ya existe", Toast.LENGTH_SHORT).show();
                conn.close();
            }else {
                registrarUsuarios();
                Intent categorias = new Intent(this, MainActivity.class);
                startActivity(categorias);
                conn.close();
            }
        }else{
            Toast.makeText(this, "Llene los campos", Toast.LENGTH_SHORT).show();
        }
    }

    //Method insert opcional
    /*public void registrar(){
        conn=new ConexionSQLiteHelper(getApplicationContext());
        SQLiteDatabase baseDatos = conn.getWritableDatabase();
        String ced = cedula.getText().toString();
        String nom = nombre.getText().toString();
        ContentValues registro = new ContentValues();
        registro.put("CEDULA", ced);
        registro.put("NOMBRE", nom);

        baseDatos.insert("USUARIOS", null, registro);
        baseDatos.close();
        cedula.setText("");
        nombre.setText("");
        //mensage de registro existo;
        Toast.makeText(this, "registro existo", Toast.LENGTH_SHORT).show();
    }*/


}