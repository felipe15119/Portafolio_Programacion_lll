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


        tbhConvertidor=(TabHost)findViewById(R.id.tbhConvertir1y2);
        tbhConvertidor.setup();
        tbhConvertidor.addTab(tbhConvertidor.newTabSpec("MA").setContent(R.id.tabMasa).setIndicator("MASA")); //Monedas
        tbhConvertidor.addTab(tbhConvertidor.newTabSpec("T").setContent(R.id.tabTiempo).setIndicator("TIEMPO")); //Longitud

    }

    public void Calcular3y4 (View view){
        try {
            TextView tmpVal = (TextView) findViewById(R.id.txtCantidad3y4);
            double cantidad = Double.parseDouble(tmpVal.getText().toString());

            Spinner spn;
            double valores[][]={
                    new double[]{1, 0.00045359, 0.4535923, 453.5923, 453592.37, 0.00000004535923, 0.0004464286, 0.0005, 0.0089285714, 0.01, 16}, //masa
                    new double[]{1, 1000000000, 1000000, 1000, 0.0166666667, 0.0002777778, 0.0000115741, 0.0000016534, 0.0000003802570, 0.00000003168808, 0.000000003169, 0.0000000003169}, //Tiempo

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