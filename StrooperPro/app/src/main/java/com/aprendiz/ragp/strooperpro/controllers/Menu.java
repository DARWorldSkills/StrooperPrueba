package com.aprendiz.ragp.strooperpro.controllers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.aprendiz.ragp.strooperpro.R;

public class Menu extends AppCompatActivity implements View.OnClickListener{
    Button btnjuego, btnpuntaje,btnconfiguracion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        inizialite();
        btnjuego.setOnClickListener(this);
        btnpuntaje.setOnClickListener(this);
        btnconfiguracion.setOnClickListener(this);
    }

    private void inizialite() {
        btnjuego = findViewById(R.id.btnjuegoM);
        btnpuntaje = findViewById(R.id.btnpuntuacionM);
        btnconfiguracion = findViewById(R.id.btnconfiguracionM);
    }


    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()){
            case R.id.btnjuegoM:
                intent = new Intent(Menu.this,Juego.class);
                startActivity(intent);
                break;

            case R.id.btnpuntuacionM:
                intent = new Intent(Menu.this,Puntuacion.class);
                startActivity(intent);
                break;

            case R.id.btnconfiguracionM:
                intent = new Intent(Menu.this,Configuracion.class);
                startActivity(intent);
                break;
        }
    }

    public void enviar(View view) {
        Intent intent = new Intent(Menu.this,Juego.class);
        startActivity(intent);
    }

    public void enviar2(View view) {

        Intent intent = new Intent(Menu.this,Puntuacion.class);
        startActivity(intent);
    }

    public void enviar3(View view) {

        Intent intent = new Intent(Menu.this,Configuracion.class);
        startActivity(intent);
    }
}
