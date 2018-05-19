package com.aprendiz.ragp.strooperpro.controllers;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.aprendiz.ragp.strooperpro.R;
import com.aprendiz.ragp.strooperpro.models.GestorDB;

import java.util.ArrayList;
import java.util.List;

public class Puntuacion extends AppCompatActivity {
    TextView txtprimero, txtsegundo,txttercero;
    List<String> listresultados = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_puntuacion);
        inizialite();
        inputData();
    }

    private void inizialite() {
        txtprimero= findViewById(R.id.txtprimero);
        txtsegundo= findViewById(R.id.txtsegundo);
        txttercero= findViewById(R.id.txttercero);
    }
    public void inputData(){
        GestorDB gestorDB = new GestorDB(this);
        SQLiteDatabase db = gestorDB.getReadableDatabase();
        try{


        Cursor cursor = db.rawQuery("SELECT * FROM DATOS ORDER BY CORRECTAS DESC ;",null);
        if (cursor.moveToFirst()){
            do {
                listresultados.add("Correctas: "+cursor.getString(0)+"\n"+" Acierto: "+cursor.getString(1)+"%");
            }while (cursor.moveToNext());
        }
        }catch (Exception e) {
            Toast.makeText(this, "No hay datos", Toast.LENGTH_SHORT).show();
        }

        try {
            txtprimero.setText(listresultados.get(0));
        }catch (Exception e){

        }

        try {
            txtsegundo.setText(listresultados.get(1));
        }catch (Exception e){

        }

        try {
            txttercero.setText(listresultados.get(2));
        }catch (Exception e){

        }




    }


    public void cerrar(View view) {
        finish();
    }
}
