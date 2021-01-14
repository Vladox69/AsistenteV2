package com.example.pruebas;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.pruebas.utilidades.Utilidades;

public class ConexionSQLiteHelper extends SQLiteOpenHelper {

    final String CREAR_TABLA_USUARIO="CREATE TABLE USUARIOS (CED_USU TEXT PRIMARY KEY ,NOM_USU TEXT)";
    final String CREAR_TABLA_COMPRA_PLANIFICADA="CREATE TABLE COMPRAS_PLANIFICADAS (NUM_COM INTEGER PRIMARY KEY AUTOINCREMENT, NOM_COM TEXT,CED_USU_COM TEXT, FOREIGN KEY (CED_USU_COM) REFERENCES USUARIOS(CED_USU))";
    final String CREAR_TABLA_PRODUCTOS="CREATE TABLE PRODUCTOS(NOM_PRO TEXT PRIMARY KEY,CAT_PRO TEXT)";
    final String CREAR_TABLA_DETALLE_COMPRA="CREATE TABLE DETALLE_COMPRAS (ID_DET INTEGER PRIMARY KEY AUTOINCREMENT,NOM_PRO_DET TEXT,NUM_COM_DET INTEGER, FOREIGN KEY (NUM_COM_DET) REFERENCES COMPRAS_PLANIFICADAS(NUM_COM),FOREIGN KEY (NOM_PRO_DET) REFERENCES PRODUCTOS(NOM_PRO))";
    Context context;
    public ConexionSQLiteHelper(@Nullable Context context) {
        super(context, "USUARIOS.db",null,1);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    //CREANDO LA BASE DE DATOS RELACIONAL
    db.execSQL(CREAR_TABLA_USUARIO);
    db.execSQL(CREAR_TABLA_COMPRA_PLANIFICADA);
    db.execSQL(CREAR_TABLA_PRODUCTOS);
    db.execSQL(CREAR_TABLA_DETALLE_COMPRA);

    //INSERCIONES EN LA BASE DE DATOS
    //SNACKS
        db.execSQL("INSERT INTO PRODUCTOS (NOM_PRO,CAT_PRO) VALUES('GALLETAS AMOR','SNACKS')");
        db.execSQL("INSERT INTO PRODUCTOS (NOM_PRO,CAT_PRO) VALUES('DORITOS','SNACKS')");
        db.execSQL("INSERT INTO PRODUCTOS (NOM_PRO,CAT_PRO) VALUES('GALLETAS OREO','SNACKS')");
        db.execSQL("INSERT INTO PRODUCTOS (NOM_PRO,CAT_PRO) VALUES('COCA COLA','SNACKS')");
        db.execSQL("INSERT INTO PRODUCTOS (NOM_PRO,CAT_PRO) VALUES('GOMINAT DE TIBURÓN','SNACKS')");
     //PECES
        db.execSQL("INSERT INTO PRODUCTOS (NOM_PRO,CAT_PRO) VALUES('SALMON','PECES')");
        db.execSQL("INSERT INTO PRODUCTOS (NOM_PRO,CAT_PRO) VALUES('TILAPIA','PECES')");
        db.execSQL("INSERT INTO PRODUCTOS (NOM_PRO,CAT_PRO) VALUES('ATUN','PECES')");
        db.execSQL("INSERT INTO PRODUCTOS (NOM_PRO,CAT_PRO) VALUES('BAGRE','PECES')");
        db.execSQL("INSERT INTO PRODUCTOS (NOM_PRO,CAT_PRO) VALUES('TRUCHA','PECES')");
     //LEGUMBRES
        db.execSQL("INSERT INTO PRODUCTOS (NOM_PRO,CAT_PRO) VALUES('FREJOL','LEGUMBRES')");
        db.execSQL("INSERT INTO PRODUCTOS (NOM_PRO,CAT_PRO) VALUES('ARVEJA','LEGUMBRES')");
        db.execSQL("INSERT INTO PRODUCTOS (NOM_PRO,CAT_PRO) VALUES('CACAHUATE','LEGUMBRES')");
        db.execSQL("INSERT INTO PRODUCTOS (NOM_PRO,CAT_PRO) VALUES('SOJA','LEGUMBRES')");
        db.execSQL("INSERT INTO PRODUCTOS (NOM_PRO,CAT_PRO) VALUES('LENTEJA','LEGUMBRES')");
     //LACTEOS
        db.execSQL("INSERT INTO PRODUCTOS (NOM_PRO,CAT_PRO) VALUES('LECHE','LACTEOS')");
        db.execSQL("INSERT INTO PRODUCTOS (NOM_PRO,CAT_PRO) VALUES('YOGURT','LACTEOS')");
        db.execSQL("INSERT INTO PRODUCTOS (NOM_PRO,CAT_PRO) VALUES('QUESO','LACTEOS')");
        db.execSQL("INSERT INTO PRODUCTOS (NOM_PRO,CAT_PRO) VALUES('CREMA DE LECHE','LACTEOS')");
        db.execSQL("INSERT INTO PRODUCTOS (NOM_PRO,CAT_PRO) VALUES('MANTEQUILLA','LACTEOS')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    db.execSQL("DROP TABLE IF EXISTS "+Utilidades.TABLA_USUARIOS );
    db.execSQL("DROP TABLE IF EXISTS "+Utilidades.TABLA_COMPRAS_PLANIFICADAS);
    db.execSQL("DROP TABLE IF EXISTS "+ Utilidades.TABLA_PRODUCTOS);
    db.execSQL("DROP TABLE IF EXISTS "+ Utilidades.TABLA_DETALLE);
    onCreate(db);
    }

    public boolean addEntry2() {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        //cv.put(ID_PRO, 1);
        cv.put("NOM_PRO", "PAPAS ");
        cv.put("CAT_PRO", "SNACK");

        long insert = db.insert("PRODUCTOS", null, cv);
        if (insert < 0) {
            return false;
        } else {
            return true;
        }
    }

}
