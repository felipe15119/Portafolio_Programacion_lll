package com.example.prueba;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Animatable;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class Splash_Screen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash__screen);

        //Agregar Animaciones--------------------------------------------------------------------------------------------
        Animation animacion2 = AnimationUtils.loadAnimation(this,R.anim.desplazamiento_arriba);
        Animation animacion1 = AnimationUtils.loadAnimation(this,R.anim.desplazamiento_abajo);

        ImageView logoview= findViewById(R.id.icon);
        ImageView sloganview= findViewById(R.id.slogan);

        logoview.setAnimation(animacion2);
        sloganview.setAnimation(animacion1);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                    startActivity(new Intent(Splash_Screen.this, SignUps.class));
                finish();
            }
        }, 5000);

    }
}