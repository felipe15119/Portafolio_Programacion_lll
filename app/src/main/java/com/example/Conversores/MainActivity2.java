package com.example.Conversores;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Spinner;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity2 extends AppCompatActivity {
    TabHost tbhconvertir;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);



        tbhconvertir=(TabHost)findViewById(R.id.tbhConvertir7);
        tbhconvertir.setup();
        tbhconvertir.addTab(tbhconvertir.newTabSpec("TD").setContent(R.id.tabTransferencia_Datos).setIndicator("TRANSFERENCIA DE DATOS")); //Tranferencia de Datos.......

    }

    public void Calcular7 (View view){
        try {
            TextView tmpVal = (TextView) findViewById(R.id.txtCantidad7);
            double Cantidad7 = Double.parseDouble(tmpVal.getText().toString());


            Spinner spn;
            double valores[][]={
                    new double[]{1, 0.001, 0.000125, 1e-6, 1.25e-7, 1e-9, 1.25e-10, 1e-12, 1.25e-13} //Transferencia de Datos arreglado
            };


            int de = 0, a = 0;
            double resp = 0;
            switch (tbhconvertir.getCurrentTabTag()) {

                case "TD":
                    spn = (Spinner) findViewById(R.id.cboDeTransferencia_Datos);
                    de = spn.getSelectedItemPosition();
                    spn = (Spinner) findViewById(R.id.cboATransferencia_Datos);
                    a = spn.getSelectedItemPosition();
                    resp = valores[0][a] / valores[0][de] * Cantidad7;
                    break;


            }
            tmpVal = (TextView) findViewById(R.id.lblrespuesta7);
            tmpVal.setText("Respuesta: " + resp);
        }catch (Exception err){
            TextView temp = (TextView) findViewById(R.id.lblrespuesta7);
            temp.setText("Porfavor Ingrese solo Numeros.");

            Toast.makeText(getApplicationContext(),"Por Favor Ingrese Solamente Numeros", Toast.LENGTH_LONG).show();
        }
    }
}