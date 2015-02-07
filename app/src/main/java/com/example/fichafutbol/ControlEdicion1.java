package com.example.fichafutbol;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Senén Córdoba on 30/01/2015.
 */
public class ControlEdicion1 extends MainActivity{
    EditText etDni;
    Button buscar;
    Cursor curs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_edit1);
        etDni = (EditText) findViewById(R.id.dniABuscar);
        buscar = (Button) findViewById(R.id.btnBuscarEdit);
        buscar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                accionBuscar();
            }
        });
    }
    private void accionBuscar(){
        String strrrrr = etDni.getText().toString();
        String[] arg = new String[] {strrrrr};
        curs = db.rawQuery("SELECT * FROM Ficha WHERE dni=?", arg);
        if (curs.moveToFirst()){
            Intent i = new Intent(this, ControlEdicion2.class);
            i.putExtra("dniBuscado",etDni.getText().toString());
            etDni.setText("");
            startActivity(i);
        } else {
            Toast.makeText(this, "El DNI introducido no esta registrado.", Toast.LENGTH_SHORT).show();
            etDni.setText("");
        }
    }
}