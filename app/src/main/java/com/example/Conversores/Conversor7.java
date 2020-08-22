package com.example.Conversores;

import android.os.Bundle;
import android.view.View;
import android.widget.Spinner;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Conversor7 extends AppCompatActivity {
TabHost tbhconvertir;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conversor7);



        tbhconvertir=(TabHost)findViewById(R.id.tbhConvertir1y2);
        tbhconvertir.setup();
        tbhconvertir.addTab(tbhconvertir.newTabSpec("TD").setContent(R.id.tabTransferencia_Datos).setIndicator("TRANSFERENCIA DE DATOS")); //Tranferencia de Datos.......

    }

    public void Calcular (View view){
        try {
            TextView tmpVal = (TextView) findViewById(R.id.txtCantidad);
            double cantidad = Double.parseDouble(tmpVal.getText().toString());


            Spinner spn;
            double valores[][]={
                    new double[]{1, 0.001, 0.000125, 0.000001, 0.000000125, 0.000000001, 0.000000000125, 0.000000000001, 0.000000000000125} //Transferencia de Datos
            };


            int de = 0, a = 0;
            double resp = 0;
            switch (tbhconvertir.getCurrentTabTag()) {

                case "TD":
                    spn = (Spinner) findViewById(R.id.cboDeTransferencia_Datos);
                    de = spn.getSelectedItemPosition();
                    spn = (Spinner) findViewById(R.id.cboATransferencia_Datos);
                    a = spn.getSelectedItemPosition();
                    resp = valores[0][a] / valores[0][de] * cantidad;
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