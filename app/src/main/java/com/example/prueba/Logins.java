package com.example.prueba;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Logins extends AppCompatActivity {

    Button CrearCuenta;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logins);

        CrearCuenta = (Button)findViewById(R.id.btn_ir_crearCuenta);
        CrearCuenta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent crear = new  Intent(Logins.this, SignUps.class);
                startActivity(crear);
            }
        });


    }

}