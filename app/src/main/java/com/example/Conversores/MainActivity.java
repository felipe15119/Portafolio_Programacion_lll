package com.example.Conversores;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;



public class MainActivity extends Activity {
private Button button1,button2,button3,button4;
private Context  Acti=this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button1=(Button)findViewById(R.id.btMonedas_Longitud);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Acti,Conversores1y2.class);
                startActivity(intent);
            }
        });
        Button button2=(Button)findViewById(R.id.btMasa_Tiempo);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Acti,Conversores3y4.class);
                startActivity(intent);
            }
        });
        Button button3=(Button) findViewById(R.id.btAlmacenamiento_Volumen);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Acti,Conversores5y6.class);
                startActivity(intent);
            }
        });
        Button button4=(Button)findViewById(R.id.btTransferencias_Datos);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Acti,Conversor7.class);
                startActivity(intent);
            }
        });
    }
}