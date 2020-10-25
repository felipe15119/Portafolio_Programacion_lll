package com.example.prueba;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    Common_Utilities CU;
    Connection Con;
    JSONArray datosJSON;
    JSONObject jsonObject;
    Integer positionn;
    ArrayList<String> arrayList = new ArrayList<String>();
    ArrayList<String> copyStringArrayList = new ArrayList<String>();
    ArrayAdapter<String> stringArrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Con = new Connection(getApplicationContext());
        if (Con.Connection_Detected()) {
            Server_Connected objObtener = new Server_Connected();
            objObtener.execute(CU.url_query, "GET");
        } else {
            Toast.makeText(getApplicationContext(), "NO HAY CONEXION", Toast.LENGTH_LONG).show();
        }
        obtenerDatos objObtenerProductos = new obtenerDatos();
        objObtenerProductos.execute();
        FloatingActionButton btnAgregar = findViewById(R.id.btnAgregar);
        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                agregarNuevos("nuevo", jsonObject);
            }
        });
        buscarProductos();
    } //termina el OnCreate

    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu, menu);
        try {
            AdapterView.AdapterContextMenuInfo adapterContextMenuInfo = (AdapterView.AdapterContextMenuInfo) menuInfo;
            positionn = adapterContextMenuInfo.position;
            menu.setHeaderTitle(datosJSON.getJSONObject(positionn).getString("codigo"));
        }catch (Exception ex){

        }
    }

    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.mnxAgregar:
                agregarNuevos("nuevo", jsonObject);
                return true;
            case R.id.mnxModificar:
                try {
                    agregarNuevos("modificar", datosJSON.getJSONObject(positionn));
                }catch (Exception ex){}
                return true;
            case R.id.mnxEliminar:
                AlertDialog eliminarFriend =  eliminarProducto();
                eliminarFriend.show();
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

    private class Server_Connected extends AsyncTask<String, String, String> {
        HttpURLConnection urlConnection;

        @Override
        protected String doInBackground(String... parametros) {
            StringBuilder result = new StringBuilder();
            try {
                String Uri = parametros[0];
                String Method = parametros[1];
                URL url = new URL(Uri);
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod(Method);
                InputStream in = new BufferedInputStream(urlConnection.getInputStream());
                BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                String read;
                while ((read = reader.readLine()) != null) {
                    result.append(read);
                }
            } catch (Exception ex) {
                //
            }
            return result.toString();
        }
    }

    private class obtenerDatos extends AsyncTask<Void, Void, String> {
        HttpURLConnection urlConnection;

        @Override
        protected String doInBackground(Void... voids) {
            StringBuilder result = new StringBuilder();
            try {
                URL url = new URL("");
                urlConnection = (HttpURLConnection) url.openConnection();//conectando al servidor
                urlConnection.setRequestMethod("GET");
                InputStream in = new BufferedInputStream(urlConnection.getInputStream());//obtencion de los datos
                BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                String read; //para leer los datos
                while ((read = reader.readLine()) != null) {
                    result.append(read);
                }
            } catch (Exception ex) {
                //
            }
            return result.toString();
        }

        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            try {
                jsonObject = new JSONObject(s);
                datosJSON = jsonObject.getJSONArray("rows");
                mostrarDatos();
            } catch (Exception ex) {
                Toast.makeText(MainActivity.this, "ERROR AL HACER EL PARSER: " + ex.getMessage(), Toast.LENGTH_LONG).show();
            }
        }
    }

    private void mostrarDatos() {
        ListView ltsProductos = findViewById(R.id.ltsProductos);
        try {
            arrayList.clear();
            stringArrayAdapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, arrayList);
            ltsProductos.setAdapter(stringArrayAdapter);
            for (int i = 0; i < datosJSON.length(); i++) {
                stringArrayAdapter.add(datosJSON.getJSONObject(i).getJSONObject("value").getString("codigo"));
            }
            copyStringArrayList.clear();
            copyStringArrayList.addAll(arrayList);
            stringArrayAdapter.notifyDataSetChanged();
            registerForContextMenu(ltsProductos);
        } catch (Exception ex) {
            Toast.makeText(MainActivity.this, "ERROR AL MOSTRAR LOS DATOS: " + ex.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    public void agregarNuevos(String accion, JSONObject jsonObject) {
        try {
            Bundle enviarParametros = new Bundle();
            enviarParametros.putString("accion", accion);
            enviarParametros.putString("dataProducto", jsonObject.toString());
            Intent agregarProducto = new Intent(MainActivity.this, Productos.class);
            agregarProducto.putExtras(enviarParametros);
            startActivity(agregarProducto);
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "ERROR AL AGREGAR: " + e.toString(), Toast.LENGTH_LONG).show();
        }
    }

    AlertDialog eliminarProducto() {
        AlertDialog.Builder confirmacion = new AlertDialog.Builder(MainActivity.this);
        try {
            confirmacion.setTitle(datosJSON.getJSONObject(positionn).getJSONObject("value").getString("codigo"));
            confirmacion.setMessage("¿ESTÁ SEGURO DE ELIMINAR?");
            confirmacion.setPositiveButton("SI", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    try {
                        Server_Connected objEliminarProducto = new Server_Connected();
                        objEliminarProducto.execute(CU.url_mto +
                                datosJSON.getJSONObject(positionn).getJSONObject("value").getString("_id") + "?rev=" +
                                datosJSON.getJSONObject(positionn).getJSONObject("value").getString("_rev"), "DELETE");
                    } catch (Exception ex) {
                        Toast.makeText(getApplicationContext(), "ERROR AL ELIMINAR EL PRODUCTO: " + ex.getMessage(), Toast.LENGTH_LONG).show();
                    }
                    dialogInterface.dismiss();
                }
            });
            confirmacion.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    Toast.makeText(getApplicationContext(), "LA ELIMINACIÓN HA SIDO CANCELADA", Toast.LENGTH_SHORT).show();
                    dialogInterface.dismiss();
                }
            });
        } catch (Exception ex) {
            Toast.makeText(getApplicationContext(), "ERROR: " + ex.getMessage(), Toast.LENGTH_LONG).show();
        }
        return confirmacion.create();
    }

    void buscarProductos() {
        final TextView tempVal = (TextView) findViewById(R.id.txtBuscarProducto);
        tempVal.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                arrayList.clear();
                if (tempVal.getText().toString().trim().length() < 1) {
                    arrayList.addAll(copyStringArrayList);
                } else {
                    for (String Producto : copyStringArrayList) {
                        if (Producto.toLowerCase().contains(tempVal.getText().toString().trim().toLowerCase())) {
                            arrayList.add(Producto);
                        }
                    }
                }
                stringArrayAdapter.notifyDataSetChanged();
            }
            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
    }
}

class Tiendita{
String id;
String producto;
String marca;
String presentacion;
String descripcion;
String precio;
String UrImage;

public Tiendita(String id, String producto, String marca, String presentacion, String descripcion, String precio, String UrImage){
    this.id = id;
    this.producto = producto;
    this.marca = marca;
    this.presentacion = presentacion;
    this.descripcion = descripcion;
    this.precio = precio;
    this.UrImage = UrImage;
    }

    public String getId(){
    return id;
    }
    public void setId(){
    this.id = id;
    }

    public  String getProducto(){
    return producto;
    }
    public void setProducto(String producto) {
        this.producto = producto;
    }

    public String getMarca() {
        return marca;
    }
    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getPresentacion() {
        return presentacion;
    }
    public void setPresentacion(String presentacion) {
        this.presentacion = presentacion;
    }

    public String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getPrecio() {
        return precio;
    }
    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public String getUrImage() {
        return UrImage;
    }

    public void setUrImage(String urImage) {
        UrImage = urImage;
    }
}





