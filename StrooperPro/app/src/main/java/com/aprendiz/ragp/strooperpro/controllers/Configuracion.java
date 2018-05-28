package com.aprendiz.ragp.strooperpro.controllers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.Toast;

import com.aprendiz.ragp.strooperpro.R;

import java.util.ArrayList;
import java.util.List;

public class Configuracion extends AppCompatActivity implements View.OnClickListener{
    public static int modo_juego;
    public static int intentos;
    public static int duracion;
    public static List<String> listpalabra= new ArrayList<>();
    public static List<Integer> listcolor= new ArrayList<>();

    RadioButton btnrTJ1;
    RadioButton btnrTJ2;

    RadioButton btnri1;
    RadioButton btnri2;
    RadioButton btnri3;

    RadioButton btnrD1;
    RadioButton btnrD2;
    RadioButton btnrD3;

    RadioButton btnrAzul;
    RadioButton btnrAmarillo;
    RadioButton btnrRojo;
    RadioButton btnrVerde;
    RadioButton btnrNarajanja;
    RadioButton btnrPurpura;
    RadioButton btnrBlanco;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuracion);
        inizialite();

    }

    private void inizialite() {
        btnrTJ1 = findViewById(R.id.btnrTP1);
        btnrTJ2 = findViewById(R.id.btnrTP2);

        btnrTJ1.setOnClickListener(this);
        btnrTJ2.setOnClickListener(this);

        btnri1 = findViewById(R.id.btnri1);
        btnri2 = findViewById(R.id.btnri2);
        btnri3 = findViewById(R.id.btnri3);

        btnrD1 = findViewById(R.id.btnrD1);
        btnrD2 = findViewById(R.id.btnrD2);
        btnrD3 = findViewById(R.id.btnrD3);


        btnrAzul = findViewById(R.id.btnrAzul);
        btnrAmarillo = findViewById(R.id.btnrAmarillo);
        btnrRojo = findViewById(R.id.btnrRojo);
        btnrVerde = findViewById(R.id.btnrVerde);
        btnrNarajanja = findViewById(R.id.btnrNaranja);
        btnrPurpura = findViewById(R.id.btnrPurpura);
        btnrBlanco = findViewById(R.id.btnrBlanco);




    }


    public int input_mode_game(){
        int tmp=0;
        if (btnrTJ1.isChecked()){
            tmp=1;
        }

        if (btnrTJ2.isChecked()){
            tmp=2;
        }

        return tmp;
    }

    public int input_intentos(){
        int tmp=0;
        if(btnri1.isChecked()){
            tmp=3;
        }
        if(btnri2.isChecked()){
            tmp=5;
        }
        if(btnri3.isChecked()){
            tmp=10;
        }

        return tmp;
    }

    public int input_duracion(){
        int tmp=0;
        if(btnrD1.isChecked()){
            tmp=3;
        }
        if(btnrD2.isChecked()){
            tmp=5;
        }
        if(btnrD3.isChecked()){
            tmp=10;
        }

        return tmp;
    }

    public void input_list(){
        if (btnrAzul.isChecked()){

            listpalabra.add("AZUL");
            listcolor.add(getColor(R.color.colorAzulj));

        }
        if (btnrAmarillo.isChecked()){

            listpalabra.add("AMARRILLO");
            listcolor.add(getColor(R.color.colorAmarilloj));

        }
        if (btnrRojo.isChecked()){

            listpalabra.add("ROJO");
            listcolor.add(getColor(R.color.colorRojo));

        }
        if (btnrVerde.isChecked()){

            listpalabra.add("VERDE");
            listcolor.add(getColor(R.color.colorVerde));

        }
        if (btnrNarajanja.isChecked()){

            listpalabra.add("NARANJA");
            listcolor.add(getColor(R.color.colorNaranja));
        }
        if (btnrPurpura.isChecked()){


            listpalabra.add("PURPURA");
            listcolor.add(getColor(R.color.colorPurpura));

        }
        if (btnrBlanco.isChecked()){

            listpalabra.add("BLANCO");
            listcolor.add(getColor(R.color.colorBlanco));


        }

    }



    public void enviar(View view) {
        listpalabra.clear();
        listcolor.clear();
        input_list();
        modo_juego = input_mode_game();
        intentos = input_intentos();
        duracion = input_duracion();
        if (listcolor.size()>=2 && modo_juego>0 && intentos>0 && duracion>0 ) {
            Intent intent = new Intent(Configuracion.this, JuegoC.class);
            startActivity(intent);
            finish();
        }else{
            Toast.makeText(this, "Error, faltan datos por seleccionar", Toast.LENGTH_SHORT).show();
            listpalabra.clear();
            listcolor.clear();
        }
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnrTP1:
                btnrTJ2.setSelected(false);
                break;

            case R.id.btnrTP2:
                btnrTJ1.setSelected(false);
                break;




        }
    }
}
