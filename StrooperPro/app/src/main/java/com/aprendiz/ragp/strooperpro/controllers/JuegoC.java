package com.aprendiz.ragp.strooperpro.controllers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.aprendiz.ragp.strooperpro.R;

import java.util.ArrayList;
import java.util.List;

public class JuegoC extends AppCompatActivity {

    TextView txtcorrectas, txtincorrectas, txtintentos, txtaciertos, txtcrono, txtpalabra;
    ImageButton btncorrecto, btnincorrecto;
    List<String> listpalabra= Configuracion.listpalabra;
    List<Integer> listcolor= Configuracion.listcolor;
    int ipj, icj, pcorrecto, pincorrecto, aciertos, intentos;
    int ab=0;
    public static int NoData;
    public static boolean bandera=true;
    public static int ccorrecto, cincorrecto, caciertos, cintentos;
    int [] segundos ={30, 0};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_juego_c);
        inizialite();
        NoData=1;
        bandera=true;
        ab=0;
        intentos+=1;
        segundos [0]=30;
        segundos [1]=0;
        bandera=true;
        randomizar();
        inputDatos();

        if (Configuracion.modo_juego==1){
            segundos[0]=0;
        }
        txtcrono.setText(Integer.toString(segundos[0])+ "seg");
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
        inputDatos();
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
                            if (Configuracion.modo_juego==1){
                                segundos[0] = segundos[0]+1;
                            }
                            if (Configuracion.modo_juego==2){
                                segundos[0] = segundos[0]-1;
                            }
                            segundos[1] = segundos[1]+1;
                            txtcrono.setText(Integer.toString(segundos[0])+ "seg");
                            if (segundos[0]==0 && Configuracion.modo_juego==2){
                                Intent intent = new Intent(JuegoC.this, Resumen.class);
                                bandera=false;
                                finish();
                                inputDatos();
                                startActivity(intent);


                            }else {
                                if ((ab==0 && Configuracion.intentos<pincorrecto && segundos[0]>0 && Configuracion.modo_juego==2) || ( ab==0 &&Configuracion.intentos<pincorrecto && Configuracion.modo_juego==1)){
                                    ab=1;
                                    Intent intent = new Intent(JuegoC.this, Resumen.class);
                                    bandera=false;
                                    finish();
                                    inputDatos();
                                    startActivity(intent);


                                }
                            }

                            if (segundos[1] == Configuracion.duracion){
                                intentos+=1;
                                pincorrecto+=1;
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




    private void inizialite() {

        txtcorrectas = findViewById(R.id.txtcorrectasC);
        txtincorrectas = findViewById(R.id.txtincorrectasC);
        txtintentos = findViewById(R.id.txtintentosJC);
        txtaciertos = findViewById(R.id.txtaciertosC);
        txtcrono = findViewById(R.id.txtcronoC);
        txtpalabra = findViewById(R.id.txtpalabraC);
        btncorrecto = findViewById(R.id.btncorrectoC);
        btnincorrecto = findViewById(R.id.btnincorrectoC);


    }

    public void randomizar(){
        ipj = (int) (Math.random()*listpalabra.size());
        icj = (int) (Math.random()*listcolor.size());
        txtpalabra.setText(listpalabra.get(ipj));
        txtpalabra.setTextColor(listcolor.get(icj));

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

    public void inputDatos() {
        if (pcorrecto > 0) {
            double tmp1 = pcorrecto, tmp2 = intentos;

            float tmpP = (float) (tmp1 / tmp2) * 100;
            aciertos = (int) tmpP;
        } else {
            if (intentos == 1 && pincorrecto<0) {
                aciertos = 100;
            } else {
                aciertos = 0;
            }
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



}
