package com.example.prueba;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {


    Conversor conversor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TabHost tbhConversor = (TabHost) findViewById(R.id.tbhParcial1);
        tbhConversor.setup();
        String str = "Universal";
        tbhConversor.addTab(tbhConversor.newTabSpec(str).setIndicator(str, null).setContent(R.id.tabConversorUniversal));
        String str2 = "Area";
        tbhConversor.addTab(tbhConversor.newTabSpec(str2).setIndicator(str2, null).setContent(R.id.tabAreaSuperficie));

    }


    public void procesar(View v) {
        try {
            TextView temp = (TextView) findViewById(R.id.txtunidades);
            String str = "";
            int unidades = temp.getText().toString().equals(str) ? 1 : Integer.parseInt(temp.getText().toString());
            TextView temp2 = (TextView) findViewById(R.id.txtcantidad);
            TextView valor = (TextView) findViewById(R.id.txtvalor);
            String str2 = "/";
            if (!temp2.getText().toString().equals(str)) {
                int cantidad = Integer.parseInt(temp2.getText().toString());
                int cajas = cantidad / unidades;
                StringBuilder sb = new StringBuilder();
                sb.append(cajas);
                sb.append(str2);
                sb.append(cantidad % unidades);
                valor.setText(sb.toString());
            } else if (!valor.getText().toString().equals(str)) {
                String[] data = valor.getText().toString().split(str2);
                StringBuilder sb2 = new StringBuilder();
                sb2.append(str);
                sb2.append((Integer.parseInt(data[0]) * unidades) + Integer.parseInt(data[1]));
                temp2.setText(sb2.toString());
            }
        } catch (Exception err) {
            TextView temp = (TextView) findViewById(R.id.lblRespuesta);
            temp.setText("Porfavor Ingrese solo Numeros.");

            Toast.makeText(getApplicationContext(), "Por Favor Ingrese Solamente Numeros", Toast.LENGTH_LONG).show();
        }
    }

    public void convertir(View v) {

        try {


            this.conversor = new Conversor();
            int de = ((Spinner) findViewById(R.id.spnDe)).getSelectedItemPosition();
            int a = ((Spinner) findViewById(R.id.spnA)).getSelectedItemPosition();
            double cantidad = Double.parseDouble(((TextView) findViewById(R.id.txtcan)).getText().toString());
            TextView tempVal = (TextView) findViewById(R.id.lblRespuesta);
            StringBuilder sb = new StringBuilder();
            sb.append("Respuesta: ");
            sb.append(this.conversor.convertir_area(de, a, cantidad));
            tempVal.setText(sb.toString());


        } catch (Exception err) {
            TextView temp = (TextView) findViewById(R.id.lblRespuesta);
            temp.setText("Porfavor Ingrese solo Numeros.");

            Toast.makeText(getApplicationContext(), "Por Favor Ingrese Solamente Numeros", Toast.LENGTH_LONG).show();
        }

    }
}


