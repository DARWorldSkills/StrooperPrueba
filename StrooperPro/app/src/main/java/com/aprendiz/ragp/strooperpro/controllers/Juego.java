package com.aprendiz.ragp.strooperpro.controllers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.aprendiz.ragp.strooperpro.R;

import java.util.ArrayList;
import java.util.List;

public class Juego extends AppCompatActivity {
    TextView txtcorrectas, txtincorrectas, txtintentos, txtaciertos, txtcrono, txtpalabra;
    ImageButton btncorrecto, btnincorrecto;
    List<String> listpalabra= new ArrayList<>();
    List<Integer> listcolor= new ArrayList<>();
    int ipj, icj, pcorrecto, pincorrecto, aciertos, intentos;
    public static boolean bandera=true;
    public static int ccorrecto, cincorrecto, caciertos, cintentos;
    int [] segundos ={30, 0};



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_juego);
        inizialite();
        listar();
        intentos+=1;
        segundos [0]=30;
        segundos [1]=0;
        bandera=true;
        randomizar();
        inputDatos();

        btncorrecto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isCorrecto();
                inputDatos();
                intentos+=1;
                segundos[1]=0;
                randomizar();



            }
        });
        btnincorrecto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isIncorrecto();
                inputDatos();
                intentos+=1;
                segundos[1]=0;
                randomizar();
            }
        });
        endGame();
    }

    private void endGame() {
        Thread tiempo = new Thread(new Runnable() {
            @Override
            public void run() {
                while (bandera) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            segundos[0] = segundos[0]-1;
                            segundos[1] = segundos[1]+1;
                            txtcrono.setText(Integer.toString(segundos[0])+ "seg");
                            if (segundos[0]==0){
                                Intent intent = new Intent(Juego.this, Resumen.class);
                                finish();
                                inputDatos();
                                startActivity(intent);
                                bandera=false;

                            }

                            if (segundos[1] == 3){
                                intentos+=1;
                                randomizar();
                                inputDatos();
                                segundos[1]=0;
                            }


                        }
                    });
                }
            }
        });

        tiempo.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        bandera=false;
    }

    @Override
    protected void onStop() {
        super.onStop();
        bandera=false;
    }

    @Override
    protected void onPause() {
        super.onPause();
        bandera=false;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        bandera=false;
    }

    private void isCorrecto() {
        if (ipj==icj){
            pcorrecto+=1;
        }else {
            pincorrecto+=1;
        }
    }

    private void isIncorrecto() {
        if (ipj!=icj){
            pcorrecto+=1;
        }else {
            pincorrecto+=1;
        }
    }


    private void inizialite() {
        txtcorrectas = findViewById(R.id.txtcorrectas);
        txtincorrectas = findViewById(R.id.txtincorrectas);
        txtintentos = findViewById(R.id.txtintentosJ);
        txtaciertos = findViewById(R.id.txtaciertos);
        txtcrono = findViewById(R.id.txtcrono);
        txtpalabra = findViewById(R.id.txtpalabra);
        btncorrecto = findViewById(R.id.btncorrecto);
        btnincorrecto = findViewById(R.id.btnincorrecto);

        txtcrono.setText("00:30");
    }
    public void listar(){
        listpalabra.add("AMARRILLO");
        listcolor.add(getColor(R.color.colorAmarilloj));
        listpalabra.add("AZUL");
        listcolor.add(getColor(R.color.colorAzulj));
        listpalabra.add("NARANJA");
        listcolor.add(getColor(R.color.colorNaranja));
        listpalabra.add("BLANCO");
        listcolor.add(getColor(R.color.colorBlanco));
        listpalabra.add("ROJO");
        listcolor.add(getColor(R.color.colorRojo));
        listpalabra.add("VERDE");
        listcolor.add(getColor(R.color.colorVerde));
        listpalabra.add("PURPURA");
        listcolor.add(getColor(R.color.colorPurpura));

    }

    public void randomizar(){
        ipj = (int) (Math.random()*7);
        icj = (int) (Math.random()*7);
        txtpalabra.setText(listpalabra.get(ipj));
        txtpalabra.setTextColor(listcolor.get(icj));

    }

    public void inputDatos(){
        if (pcorrecto>0) {
            double tmp1 = pcorrecto, tmp2 = intentos - 1;

            float tmpP = (float) (tmp1 / tmp2) * 100;
            aciertos = (int) tmpP;
        }else{
            aciertos = 100;
        }

        caciertos = aciertos;
        ccorrecto = pcorrecto;
        cincorrecto = pincorrecto;
        cintentos = intentos;

        txtcorrectas.setText("CORRECTAS: "+Integer.toString(pcorrecto));
        txtincorrectas.setText("INCORRECTAS: "+Integer.toString(pincorrecto));
        txtintentos.setText("INTENTOS: "+Integer.toString(intentos));
        txtaciertos.setText("ACIERTOS: "+Integer.toString(aciertos)+"%");

    }







}
