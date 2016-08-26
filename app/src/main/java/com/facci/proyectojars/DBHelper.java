package com.facci.proyectojars;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;



/**
 * Created by zoila on 25/8/2016.
 */
public class DBHelper extends SQLiteOpenHelper {

    public static final String DB_NOMBRE="CNT_JARS.db";
    public static final String TABLA_NOMBRE="VOTANTES_JARS";
    public static final String COL1="ID";
    public static final String COL2="Nombre";
    public static final String COL3="Apellido";
    public static final String COL4="Recinto Electoral";
    public static final String COL5="Año de nacimiento";

    public DBHelper(Context context) {
        super(context, DB_NOMBRE, null, 1);
        SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(String.format("create table %s (ID INTEGER PRIMARY KEY AUTOINCREMENT, %s TEXT, %s TEXT, %s TEXT, %s INTEGER)",TABLA_NOMBRE,COL2,COL3,COL4,COL5));
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL(String.format("DROP TABLE IF EXIST %s",TABLA_NOMBRE));
        onCreate(db);
    }
    public boolean insertar(String nombre, String apellido, String recinto, int ano){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(COL2,nombre);
        contentValues.put(COL3,apellido);
        contentValues.put(COL4,recinto);
        contentValues.put(COL5,ano);
        long resultado = db.insert(TABLA_NOMBRE,null,contentValues);

        if(resultado == -1)
            return false;
        else
            return true;

    }
    public Cursor selectVerTodos(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery(String.format("select * from %s",TABLA_NOMBRE),null);
        return  res;
    }
    public boolean modificarRegistro(String id,String nombre,String apellido,String recinto,Integer ano){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL1,id);
        contentValues.put(COL2,nombre);
        contentValues.put(COL3,apellido);
        contentValues.put(COL4,recinto);
        contentValues.put(COL5,ano);
        db.update(TABLA_NOMBRE,contentValues,"id = ?",new String[]{id});
        return true;
    }
    public Integer eliminarRegistro(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLA_NOMBRE,"id = ?",new String[]{id});

    }

}
