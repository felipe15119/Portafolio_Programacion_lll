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
TabHost tbhConvertir;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conversores5y6);
        tbhConvertir=(TabHost)findViewById(R.id.thbconversor5y6);
        tbhConvertir.setup();
        tbhConvertir.addTab(tbhConvertir.newTabSpec("A").setContent(R.id.tbAlmacenamiento).setIndicator("ALMACENAMIENTO"));
        tbhConvertir.addTab(tbhConvertir.newTabSpec("V").setContent(R.id.tbhVolumen).setIndicator("VOLUMEN"));
    }

}