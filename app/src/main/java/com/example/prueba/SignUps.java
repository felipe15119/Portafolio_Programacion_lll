package com.example.prueba;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SignUps extends AppCompatActivity {

    Button Login;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_ups);

        Login = (Button)findViewById(R.id.btn_ir_inicioSesion);
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent loginss = new  Intent(SignUps.this, Logins.class);
                startActivity(loginss);
            }
        });

    }
}