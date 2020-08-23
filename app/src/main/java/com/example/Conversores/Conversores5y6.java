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
                    new double[]{1, 4, 8, 15.7725, 128, 256, 768, 0.00378541, 3.78541, 3785.41, 0.832674, 3.3307, 6.66139, 13.3228, 133.228, 213.165, 639.494, 0.133681, 231}, //Volumen...

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