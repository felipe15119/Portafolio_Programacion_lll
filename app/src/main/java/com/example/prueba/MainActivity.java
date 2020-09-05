package com.example.prueba;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final EditText eNombre =
                (EditText)findViewById(R.id.etNombre);
        final EditText eEdad = (EditText)findViewById(R.id.etEdad);
        Button bEnviar = (Button)findViewById(R.id.btEnviarDatos);
        bEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nombre = eNombre.getText().toString();
                String edad = eEdad.getText().toString();
                Bundle pasarDatos = new Bundle();
                pasarDatos.putString("pNombre",nombre);
                pasarDatos.putString("pEdad",edad);
                Intent siga = new
                        Intent(MainActivity.this,Determinar.class);
                siga.putExtras(pasarDatos);
                startActivity(siga);
            }
        });
    }
}
