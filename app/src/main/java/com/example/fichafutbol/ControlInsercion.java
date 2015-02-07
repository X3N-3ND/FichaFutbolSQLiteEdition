package com.example.fichafutbol;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;
import com.example.fichafutbol.OOVVFicha;

/**
 * Created by Senén Córdoba on 29/01/2015.
 */
public class ControlInsercion extends MainActivity {
    private Button insertPatronum;
    private Spinner categoria;
    private EditText nombre;
    private EditText apellido1;
    private EditText apellido2;
    private EditText dni;
    private EditText edad;
    private RadioButton hombre, mujer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_insert);
        //Aquí "atamos" los elementos de la vista a variables.
        insertPatronum = (Button) this.findViewById(R.id.insertYadeYa);
        nombre = (EditText) this.findViewById(R.id.nombre);
        apellido1 = (EditText) this.findViewById(R.id.apellido1);
        apellido2 = (EditText) this.findViewById(R.id.apellido2);
        dni = (EditText) this.findViewById(R.id.dni);
        edad = (EditText) this.findViewById(R.id.edad);
        hombre = (RadioButton) this.findViewById(R.id.hombre);
        mujer = (RadioButton) this.findViewById(R.id.mujer);
        categoria = (Spinner) findViewById(R.id.categoria);
        insertPatronum.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                realizarInsert();
            }
        });
        //Aquí controlamos el Spinner de la vista para que muestre nuestra lista.
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.categoria, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categoria.setAdapter(adapter);
    }

    char calculaLetra(int dni){
        String juegoCaracteres="TRWAGMYFPDXBNJZSQVHLCKET";
        int modulo= dni % 23;
        char letra = juegoCaracteres.charAt(modulo);
        return letra;
    }

    public void  realizarInsert(){
        if (nombre.getText().toString().length()==0){
            Toast.makeText(this, "Nombre inválido.", Toast.LENGTH_SHORT).show();
        }else if
                (apellido1.getText().toString().length()==0) {
            Toast.makeText(this,"Apellido 1 inválido.", Toast.LENGTH_SHORT).show();
        } else if
                (apellido2.getText().toString().length()==0) {
            Toast.makeText(this,"Apellido 2 inválido.", Toast.LENGTH_SHORT).show();
        } else if
                (dni.getText().toString().length()==0) {
            Toast.makeText(this,"DNI inválido", Toast.LENGTH_SHORT).show();
        } else if
                (edad.getText().toString().length()==0) {
            Toast.makeText(this,"Edad inválida.", Toast.LENGTH_SHORT).show();
        } else if
                (!hombre.isChecked()&& !mujer.isChecked()) {
            Toast.makeText(this,"Selecciona sexo.", Toast.LENGTH_SHORT).show();
        } else {
            ContentValues nuevoRegistro = new ContentValues();
            String uno=dni.getText().toString();
            int num=Integer.parseInt(uno);
            char letra=calculaLetra(num);
            nuevoRegistro.put("nombre", nombre.getText().toString());
            nuevoRegistro.put("apellido1", apellido1.getText().toString());
            nuevoRegistro.put("apellido2", apellido2.getText().toString());
            nuevoRegistro.put("dni", uno+letra);
            nuevoRegistro.put("edad", edad.getText().toString());
            if (hombre.isChecked()==true) {
                nuevoRegistro.put("sexo", "Hombre");
            } else {
                nuevoRegistro.put("sexo", "Mujer");
            }
            nuevoRegistro.put("categoria", categoria.getSelectedItem().toString());
            super.db.insert("Ficha", null, nuevoRegistro);
            Toast.makeText(this,"Fila añadida", Toast.LENGTH_SHORT).show();
            finish();
        }
    }
}
