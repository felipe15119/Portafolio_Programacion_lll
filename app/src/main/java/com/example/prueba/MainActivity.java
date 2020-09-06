package com.example.prueba;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {
    ConstraintLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        layout = (ConstraintLayout)findViewById(R.id.activity_main);

        Button bMostrar =(Button)findViewById(R.id.button);

        Toast.makeText(getApplicationContext(),"Este Es Un Mensaje con Toast",Toast.LENGTH_LONG).show();

        bMostrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar snackbar = Snackbar.make(layout,"Tutorial de Snackbar",Snackbar.LENGTH_LONG);
                snackbar.show();
            }
        });
    }
}
