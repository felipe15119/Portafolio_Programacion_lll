package com.example.prueba;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;


import com.example.prueba.esquemaSqlite.ConexionSqliteHelper;

public class Menu extends AppCompatActivity {
    ConexionSqliteHelper con ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        con = new ConexionSqliteHelper(Menu.this);
    }

    public void Cliente(View view) {
        Intent cliente = new Intent(this, Cliente.class);
        startActivity(cliente);
    }

    public void Mensaje(View view) {
    }

    public void Producto(View view) {
        Intent producto = new Intent(this, Producto.class);
        startActivity(producto);
    }

    public void Ventas(View view) {
        Intent venta = new Intent(this, Venta.class);
        startActivity(venta);
    }

    public void Historial(View view) {
        Intent historial = new Intent(this, VentaHistorial.class);
        startActivity(historial);
    }

    public boolean onCreateOptionsMenu(android.view.Menu menu) {
        //inflamos el menu, para incluir el menu personalizado
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

}