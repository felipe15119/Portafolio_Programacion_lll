package com.example.Conversores;

import android.os.Bundle;
import android.view.View;
import android.widget.Spinner;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class Conversores1y2 extends AppCompatActivity {
TabHost tbhConvertir;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conversores1y2);

        tbhConvertir=(TabHost)findViewById(R.id.tbhConvertir1y2);
        tbhConvertir.setup();
        tbhConvertir.addTab(tbhConvertir.newTabSpec("M").setContent(R.id.tabMoneda).setIndicator("MONEDAS")); //Monedas
        tbhConvertir.addTab(tbhConvertir.newTabSpec("L").setContent(R.id.tabLongitud).setIndicator("LONGITUD")); //Longitud

    }

    public void Calcular (View view){
        try {
        TextView tmpVal = (TextView) findViewById(R.id.txtCantidad);
        double cantidad = Double.parseDouble(tmpVal.getText().toString());

        Spinner spn;
        double valores[][]={
                new double[]{1, 0.84, 7.68, 24.58, 34.75, 8.72, 22.08, 6.88, 786.40, 3784.00, 73.92, 1.39, 1.32, 105.70, 6937.32, 14702.32, 42.73},//monedas corregido
                new double[]{1, 0.001, 100, 1000, 1e+6, 1e+9, 0.000621371, 1.09361, 3.28083, 39.3701, 0.000539957}, //longitud corregido

        };
        int de = 0, a = 0;
        double resp = 0;
        switch (tbhConvertir.getCurrentTabTag()) {

            case "M":
                spn = (Spinner) findViewById(R.id.cboDeM);
                de = spn.getSelectedItemPosition();
                spn = (Spinner) findViewById(R.id.cboAM);
                a = spn.getSelectedItemPosition();
                resp = valores[0][a] / valores[0][de] * cantidad;
                break;
            case "L":
                spn = (Spinner) findViewById(R.id.cboDeL);
                de = spn.getSelectedItemPosition();
                spn = (Spinner) findViewById(R.id.cboAL);
                a = spn.getSelectedItemPosition();
                resp = valores[1][a] / valores[1][de] * cantidad;
                break;

    }
        tmpVal = (TextView) findViewById(R.id.lblrespuesta);
        tmpVal.setText("Respuesta: " + resp);
    }catch (Exception err){
        TextView temp = (TextView) findViewById(R.id.lblrespuesta);
        temp.setText("Porfavor Ingrese solo Numeros.");

        Toast.makeText(getApplicationContext(),"Por Favor Ingrese Solamente Numeros", Toast.LENGTH_LONG).show();
    }
}
}