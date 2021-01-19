package com.example.pruebas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.pruebas.entidades.Usuario;

public class MainActivity extends AppCompatActivity {
    ConexionSQLiteHelper conn;
    EditText cedula;
    Usuario usuario;
    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        conn=new ConexionSQLiteHelper(MainActivity.this);
        cedula=(EditText)findViewById(R.id.txtUsuario);
        preferences=getSharedPreferences("Preferences",MODE_PRIVATE);
        conn.addEntry2();
        verificarSesion();
    }

    public void abrirIntRegistrarUsuario(View view){
        Intent intRegistrarUsuario =new Intent(this,RegistroUsuario.class);
        startActivity(intRegistrarUsuario);
    }

    public void abrirIntPrincipal(View view){
        if(logueoUsuario()) {
        SharedPreferences.Editor editor=preferences.edit();
        editor.putString("CED_USU",usuario.getCedula());
        editor.putString("NOM_USU",usuario.getNombre());
        editor.commit();
        irPantallaPrincipal();
        }
    }

    public boolean logueoUsuario(){
    String auxCedula =cedula.getText().toString();
    if(!auxCedula.isEmpty()){
        usuario=new Usuario();
        conn = new ConexionSQLiteHelper(getApplicationContext() );
        SQLiteDatabase baseDatos = conn.getWritableDatabase();
        Cursor fila = baseDatos.rawQuery("select * from USUARIOS where CED_USU=" + auxCedula, null);
        if(fila.moveToFirst()){
            usuario.setCedula(fila.getString(0));
            usuario.setNombre(fila.getString(1));
            return true;
        }else{
            Toast.makeText(this, "El usuario no existe", Toast.LENGTH_SHORT).show();
        }
    }else {
        Toast.makeText(this, "Debe llenar los campos", Toast.LENGTH_SHORT).show();
    }
    conn.close();
    return false;
    }

    public void irPantallaPrincipal(){
        Intent intPrincipal = new Intent(this, Principal.class);
        startActivity(intPrincipal);
    }

    public void verificarSesion(){
        String cedula=preferences.getString("CED_USU",null);
        String nombre=preferences.getString("NOM_USU",null);
        if(cedula!=null && nombre!=null){
        irPantallaPrincipal();
        }
    }


}