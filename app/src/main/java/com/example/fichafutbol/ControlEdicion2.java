package com.example.fichafutbol;

import android.content.ContentValues;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

/**
 * Created by Senén Córdoba on 02/02/2015.
 */
public class ControlEdicion2 extends ControlEdicion1 {

    Cursor potato;
    private Bundle extras;
    private Button btnUpdate;
    private EditText name, sn1, sn2, dn, edad;
    private RadioButton hombre, mujer;
    private Spinner categoria;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_edit2);
        name = (EditText) findViewById(R.id.nombreUp);
        sn1 = (EditText) findViewById(R.id.apellido1Up);
        sn2 = (EditText) findViewById(R.id.apellido2Up);
        dn = (EditText) findViewById(R.id.dniUp);
        edad = (EditText) findViewById(R.id.edadUp);
        hombre = (RadioButton) findViewById(R.id.hombreUp);
        mujer = (RadioButton) findViewById(R.id.mujerUp);
        categoria = (Spinner) findViewById(R.id.categoriaUp);
        //CONTROL DEL SPINNER
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.categoria, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categoria.setAdapter(adapter);
        //CONTROL DEL SPINNER
        btnUpdate = (Button) findViewById(R.id.updateYadeYa);
        extras = getIntent().getExtras();
        cargarDatos();
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                accionUpdate();
            }
        });
    }

    private void cargarDatos(){
        String dnitito = extras.getString("dniBuscado");
        String[] args = new String[] {dnitito};
        potato = db.rawQuery("SELECT * FROM Ficha WHERE dni=?", args);
        potato.moveToFirst();
        name.setText(potato.getString(0));
        sn1.setText(potato.getString(1));
        sn2.setText(potato.getString(2));
        dn.setText(potato.getString(3));
        edad.setText(Integer.toString(potato.getInt(4)));
        if ((potato.getString(5)).equals("Hombre")){
            hombre.setChecked(true);
            mujer.setChecked(false);
        } else {
            hombre.setChecked(false);
            mujer.setChecked(true);
        }
        String reg = potato.getString(6);
        String adapt;
        for(int i= 0; i < 6; i++){
            adapt = categoria.getItemAtPosition(i).toString();
            if (reg.equals(adapt)) {
                categoria.setSelection(i);
                break;
            }
        }
    }

    private void accionUpdate(){
        if (name.getText().toString().length()==0){
            Toast.makeText(this, "Nombre inválido.", Toast.LENGTH_SHORT).show();
        }else if
                (sn1.getText().toString().length()==0) {
            Toast.makeText(this,"Apellido 1 inválido.", Toast.LENGTH_SHORT).show();
        } else if
                (sn2.getText().toString().length()==0) {
            Toast.makeText(this,"Apellido 2 inválido.", Toast.LENGTH_SHORT).show();
        } else if
                (dn.getText().toString().length()==0) {
            Toast.makeText(this,"DNI inválido", Toast.LENGTH_SHORT).show();
        } else if
                (edad.getText().toString().length()==0) {
            Toast.makeText(this,"Edad inválida.", Toast.LENGTH_SHORT).show();
        } else if
                (!hombre.isChecked()&& !mujer.isChecked()) {
            Toast.makeText(this,"Selecciona sexo.", Toast.LENGTH_SHORT).show();
        } else {
            ContentValues nuevoRegistro = new ContentValues();
            nuevoRegistro.put("nombre", name.getText().toString());
            nuevoRegistro.put("apellido1", sn1.getText().toString());
            nuevoRegistro.put("apellido2", sn2.getText().toString());
            nuevoRegistro.put("dni", dn.getText().toString());
            nuevoRegistro.put("edad", edad.getText().toString());
            if (hombre.isChecked() && !mujer.isChecked()) {
                nuevoRegistro.put("sexo", "Hombre");
            } else {
                nuevoRegistro.put("sexo", "Mujer");
            }
            nuevoRegistro.put("categoria", categoria.getSelectedItem().toString());
            db.update("Usuarios", nuevoRegistro, "dni='" + extras.getString("dniBuscado")+"'", null);
        }

    }
}
