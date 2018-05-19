package com.aprendiz.ragp.strooperpro.controllers;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.aprendiz.ragp.strooperpro.R;
import com.aprendiz.ragp.strooperpro.models.GestorDB;
import com.aprendiz.ragp.strooperpro.models.Score;

public class Resumen extends AppCompatActivity implements View.OnClickListener{
    TextView txtcorrectas, txtincorrectas, txtintentos;
    Button btnregresar;
    ImageButton btnregresarI, btntwitter,btnfacebook;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resumen);
        inizialite();
        inputData();
        inputBaseData();
        btnregresar.setOnClickListener(this);
        btnregresarI.setOnClickListener(this);
        btntwitter.setOnClickListener(this);
        btnfacebook.setOnClickListener(this);

    }

    private void inizialite() {
        txtcorrectas = findViewById(R.id.txtcorrectasR);
        txtincorrectas = findViewById(R.id.txtincorrectasR);
        txtintentos = findViewById(R.id.txtintentosR);
        btnregresar = findViewById(R.id.btnregresarC);
        btnregresarI = findViewById(R.id.btnregresarIC);
        btntwitter = findViewById(R.id.btntwitter);
        btnfacebook = findViewById(R.id.btnfacebook);
    }

    public void inputData(){
        txtcorrectas.setText(Integer.toString(Juego.ccorrecto));
        txtincorrectas.setText(Integer.toString(Juego.cincorrecto));
        txtintentos.setText(Integer.toString(Juego.cintentos));


    }

    public void inputBaseData(){
        GestorDB gestorDB = new GestorDB(this);
        SQLiteDatabase db = gestorDB.getWritableDatabase();
        Score score;
        try {
           score= new Score();

           score.setCorrectas(Integer.toString(Juego.ccorrecto));
           score.setPorcentaje(Integer.toString(Juego.caciertos));

           ContentValues values = new ContentValues();

           values.put("CORRECTAS",score.getCorrectas());
           values.put("PORCENTAJE",score.getPorcentaje());
           db.insert("DATOS",null,values);

        }catch (Exception e){

        }
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.btnregresarC:
                Intent intent = new Intent(Resumen.this, Menu.class);
                startActivity(intent);
                break;

            case R.id.btnregresarIC:
                Intent intent1 = new Intent(Resumen.this, Menu.class);
                startActivity(intent1);
                break;

            case R.id.btntwitter:

                break;
            case R.id.btnfacebook:

                break;


        }
    }
}
