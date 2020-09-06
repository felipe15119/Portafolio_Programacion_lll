package com.example.prueba;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu mimenu) {
        getMenuInflater().inflate(R.menu.menu_principal, mimenu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem opcion_menu) {
        int id = opcion_menu.getItemId();
        if (id == R.id.configuracion){
            Toast.makeText(this,"Opcion de Configuracion", Toast.LENGTH_LONG).show();
            return true;
        }
        if (id== R.id.informacion){
            Toast.makeText(this, "Opcion de Informacion",Toast.LENGTH_LONG).show();
            return true;
        }
        return super.onOptionsItemSelected(opcion_menu);

    }
}