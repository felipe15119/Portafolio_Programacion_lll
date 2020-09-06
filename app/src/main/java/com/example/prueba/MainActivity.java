package com.example.prueba;


import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button iniciar, parar;
    MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        iniciar = (Button)findViewById(R.id.btIniciar);
        parar = (Button)findViewById(R.id.btDetener);
        mediaPlayer = MediaPlayer.create(this,R.raw.jerry);

                iniciar.setOnClickListener(this);
        parar.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.btIniciar:
                play();
                break;
            case R.id.btDetener:
                stop();
                break;

        }
}

    private void play(){
        mediaPlayer.start();
    }

    private void stop(){
        mediaPlayer.stop();
    }
}