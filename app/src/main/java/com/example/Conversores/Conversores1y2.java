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
                new double[]{1, 0.84, 7.67169, 24.5016, 34.76, 8.75, 22.03, 6.91, 802.50, 3784.05, 73.43, 1.39, 1.32, 105.98, 6949.57, 74.78, 42.51},//monedas
                new double[]{1, 0.001, 100, 1000, 1000000, 1000000000, 0.0006, 1.0936, 3.2808, 39.9701, 0.00053996}, //longitud

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