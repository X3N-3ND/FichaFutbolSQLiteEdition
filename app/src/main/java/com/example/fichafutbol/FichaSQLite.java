package com.example.fichafutbol;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Senén Córdoba on 29/01/2015.
 */

public class FichaSQLite extends SQLiteOpenHelper  {
    
	//Sentencia SQL para crear la tabla de Usuarios
    String sqlCreate = "CREATE TABLE Ficha (nombre TEXT, apellido1 TEXT, apellido2 TEXT, dni TEXT, edad  INTEGER, sexo TEXT, categoria TEXT)";
	
	public FichaSQLite(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);
		// TODO Auto-generated constructor stub
	}

 
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(sqlCreate);
    }
 
    @Override
    public void onUpgrade(SQLiteDatabase db, int versionAnterior, int versionNueva) {

        db.execSQL("DROP TABLE IF EXISTS Ficha");

        db.execSQL(sqlCreate);
    }
}

