package com.example.Conversores;

import android.os.Bundle;
import android.view.View;
import android.widget.Spinner;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Conversores3y4 extends AppCompatActivity {
TabHost tbhConvertidor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conversores3y4);


        tbhConvertidor=(TabHost)findViewById(R.id.tbhConvertir3y4);
        tbhConvertidor.setup();
        tbhConvertidor.addTab(tbhConvertidor.newTabSpec("MA").setContent(R.id.tabMasa).setIndicator("MASA")); //Masa
        tbhConvertidor.addTab(tbhConvertidor.newTabSpec("T").setContent(R.id.tabTiempo).setIndicator("TIEMPO")); //Tiempo

    }

    public void Calcular3y4 (View view){
        try {
            TextView tmpVal = (TextView) findViewById(R.id.txtCantidad3y4);
            double cantidad = Double.parseDouble(tmpVal.getText().toString());

            Spinner spn;
            double valores[][]={
                    new double[]{1, 0.000453592, 0.453592, 453.592, 453592, 4.536e+8, 0.000446429, 0.0005, 0.0089285714, 0.01, 16}, //masa corregido
                    new double[]{1, 1e+9, 1e+6, 1000, 0.0166667, 0.000277778, 1.1574e-5, 1.6534e-6, 3.8052e-7, 3.171e-8, 3.171e-9, 3.171e-10}, //Tiempo corregido

            };
            int de = 0, a = 0;
            double resp = 0;
            switch (tbhConvertidor.getCurrentTabTag()) {

                case "MA":
                    spn = (Spinner) findViewById(R.id.cboDeMa);
                    de = spn.getSelectedItemPosition();
                    spn = (Spinner) findViewById(R.id.cboAMa);
                    a = spn.getSelectedItemPosition();
                    resp = valores[0][a] / valores[0][de] * cantidad;
                    break;
                case "T":
                    spn = (Spinner) findViewById(R.id.cboDeT);
                    de = spn.getSelectedItemPosition();
                    spn = (Spinner) findViewById(R.id.cboAT);
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