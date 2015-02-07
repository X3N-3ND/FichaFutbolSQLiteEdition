package com.example.fichafutbol;


import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

/**
 * Created by Senén Córdoba on 30/01/2015.
 */

public class MainActivity extends Activity {

    Button btnConsultar, btnInsertar, btnEditar, btnEliminar;
    FichaSQLite usdbh;
    SQLiteDatabase db;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
        btnConsultar = (Button) findViewById(R.id.botonConsultar);
        btnInsertar = (Button) findViewById(R.id.botonInsertar);
        btnEditar = (Button) findViewById(R.id.botonEditar);
        btnEliminar = (Button) findViewById(R.id.botonEliminar);
        usdbh = new FichaSQLite(this, "DBUsuarios", null, 1);
        db = usdbh.getWritableDatabase();
	}

    public void accionConsultar(View view){

    }

    public void accionInsertar(View view){
        Intent i = new Intent(this, ControlInsercion.class);
        startActivity(i);
    }

    public void accionEditar(View view){
        Intent i = new Intent(this, ControlEdicion1.class);
        startActivity(i);
    }

    public void accionEliminar(View view){

    }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
}
