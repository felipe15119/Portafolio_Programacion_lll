package com.example.Conversores;

import android.os.Bundle;
import android.widget.TabHost;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Button;
import android.widget.Toast;
import android.view.View;


import androidx.appcompat.app.AppCompatActivity;

public class Conversores5y6 extends AppCompatActivity {
TabHost tbhConvertire;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conversores5y6);



        tbhConvertire=(TabHost)findViewById(R.id.thbconversor5y6);
        tbhConvertire.setup();
        tbhConvertire.addTab(tbhConvertire.newTabSpec("A").setContent(R.id.tbAlmacenamiento).setIndicator("ALMACENAMIENTO")); //almacenamiento
        tbhConvertire.addTab(tbhConvertire.newTabSpec("V").setContent(R.id.tbhVolumen).setIndicator("VOLUMEN")); //volumne
    }

    public void Calcular5y6 (View view){
        try {
            TextView tmpVal = (TextView) findViewById(R.id.txtcantidad5y6);
            double cantidad5y6 = Double.parseDouble(tmpVal.getText().toString());

            Spinner spn;
            double valores[][]={
                    new double[]{1, 0.125, 0.008, 8e-6, 8e-9, 8e-12, 8e-15, 0.001, 1e-6, 1e-9, 1e-12}, //Almacenamiento corregido
                    new double[]{1, 0.264172, 1.05669, 2.11338, 4.16667, 33.814, 67.628, 202.884, 0.001, 1000, 0.219969, 0.879877, 1.75975, 3.51951, 35.1951, 56.3121, 168.936, 0.0353147, 61.0237}, //Volumen corregido

            };
            int de = 0, a = 0;
            double resp = 0;
            switch (tbhConvertire.getCurrentTabTag()) {

                case "A":
                    spn = (Spinner) findViewById(R.id.cboDEA);
                    de = spn.getSelectedItemPosition();
                    spn = (Spinner) findViewById(R.id.cboAA);
                    a = spn.getSelectedItemPosition();
                    resp = valores[0][a] / valores[0][de] * cantidad5y6;
                    break;
                case "V":
                    spn = (Spinner) findViewById(R.id.cboDEV);
                    de = spn.getSelectedItemPosition();
                    spn = (Spinner) findViewById(R.id.cboAV);
                    a = spn.getSelectedItemPosition();
                    resp = valores[1][a] / valores[1][de] * cantidad5y6;
                    break;

            }
            tmpVal = (TextView) findViewById(R.id.lblrespuesta5y6);
            tmpVal.setText("Respuesta: " + resp);
        }catch (Exception err){
            TextView temp = (TextView) findViewById(R.id.lblrespuesta5y6);
            temp.setText("Porfavor Ingrese solo Numeros.");

            Toast.makeText(getApplicationContext(),"Por Favor Ingrese Solamente Numeros", Toast.LENGTH_LONG).show();
        }
    }
}