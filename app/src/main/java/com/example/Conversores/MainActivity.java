package com.example.Conversores;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void Monedas(View view) {
        Intent intento = new Intent(this, Conversores1y2.class);
        startActivity(intento);
    }

    public void Masita(View view) {
        Intent intento2 = new Intent(this, Conversores3y4.class);
        startActivity(intento2);
    }

    public void AlmaVolu(View view) {
        Intent intento3 = new Intent(this, Conversores5y6.class);
        startActivity(intento3);
    }

    public void Transferencias(View view) {
        Intent intento4 = new Intent(this, MainActivity2.class);
        startActivity(intento4);
    }
}
