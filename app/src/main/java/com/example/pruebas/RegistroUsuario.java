package com.example.pruebas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
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

    public void llamadoMetodoRegistro(View view){
        registrarUsuarios();
    }

    public void registrarUsuarios(){
        conn=new ConexionSQLiteHelper(getApplicationContext());

        SQLiteDatabase db=conn.getWritableDatabase();

        ContentValues values=new ContentValues();
        values.put("CED_USU",cedula.getText().toString());
        values.put("NOM_USU",nombre.getText().toString());

        Long idResultante=db.insert("USUARIOS","CED_USU",values);

        Toast.makeText(getApplicationContext(),"Usuario: "+idResultante,Toast.LENGTH_SHORT).show();


    }

}