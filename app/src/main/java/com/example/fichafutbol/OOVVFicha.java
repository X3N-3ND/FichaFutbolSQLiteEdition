package com.example.fichafutbol;

import android.database.Cursor;

/**
 * Created by Senén Córdoba on 30/01/2015.
 */
public class OOVVFicha {
    private String nombre, ap1, ap2, sexo, dni, categoria;
    private int edad;

    public OOVVFicha crearCompleta(String nom, String ape1, String ape2, String dn, int ed, String sex, String cat){
        this.nombre = nom;
        this.ap1 = ape1;
        this.ap2 = ape2;
        this.dni = dn;
        this.edad = ed;
        this.sexo = sex;
        this.categoria = cat;
        return this;
    }

    public void editarFicha(String nom, String ape1, String ape2, String dn, int ed, String sex, String cat){
        if (nom != null && nom != this.nombre){
            this.nombre = nom;
        }
        if (ape1 != null && ape1 != this.ap1){
            this.ap1 = ape1;
        }
        if (ape2 != null && ape2 != this.ap2){
            this.ap2 = ape2;
        }
        if (dn != null && dn != this.dni){
            this.dni = dn;
        }
        if (ed != 0 && ed != this.edad){
            this.edad = ed;
        }
        if (sex != null && sexo != this.sexo){
            this.sexo = sex;
        }
        if (cat != null && cat != this.categoria){
            categoria = cat;
        }
    }

    public OOVVFicha cargaSQL(Cursor elCursor, int posicion){
        if (elCursor.moveToFirst()){
            if (posicion != 0){
                elCursor.moveToPosition(posicion);
                }
            this.nombre = elCursor.getString(0);
            this.ap1 = elCursor.getString(1);
            this.ap2 = elCursor.getString(2);
            this.dni = elCursor.getString(3);
            this.edad = elCursor.getInt(4);
            this.sexo = elCursor.getString(5);
            this.categoria = elCursor.getString(6);
        }
        return this;
    }

    public String getNombre(){
        return this.nombre;
    }

    public String getAp1(){
        return this.ap1;
    }

    public String getAp2() {
        return this.ap2;
    }

    public String getDni() {
        return this.dni;
    }

    public int getEdad() {
        return this.edad;
    }

    public String getSexo() {
        return this.sexo;
    }

    public String getCategoria() {
        return this.categoria;
    }
}
