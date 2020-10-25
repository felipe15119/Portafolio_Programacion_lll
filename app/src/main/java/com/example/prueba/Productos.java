package com.example.prueba;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Productos extends AppCompatActivity {
    String resp, accion, id, rev;
    ImageView imgPicture;
    String urlCompletaImg;
    Button btnProductos;
    Intent takePictureIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productos);
        btnProductos = findViewById(R.id.btnMostarProducto);
        btnProductos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });
        guardar();
        mostrarDatos();
        tomarFoto();
    }

    void tomarFoto() {
        imgPicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                    //guardando la imagen
                    File photoFile = null;
                    try {
                        photoFile = createImageFile();
                    } catch (Exception ex) {
                    }
                    if (photoFile != null) {
                        try {
                            Uri photoURI = FileProvider.getUriForFile(Productos.this, "com.example.prueba.fileprovider", photoFile);
                            takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                            startActivityForResult(takePictureIntent, 1);
                        } catch (Exception ex) {
                            Toast.makeText(getApplicationContext(), "Error Toma Foto: " + ex.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                }
            }
        });
    }

    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "imagen_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        if( storageDir.exists()==false ){
            storageDir.mkdirs();
        }
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );
        // Save a file: path for use with ACTION_VIEW intents
        urlCompletaImg = image.getAbsolutePath();
        return image;
    }

    void mostrarDatos(){
        try {
            Bundle recibirParametros = getIntent().getExtras();
            accion = recibirParametros.getString("accion");
            if (accion.equals("modificar")){
                JSONObject dataProducto = new JSONObject(recibirParametros.getString("dataProducto")).getJSONObject("value");

                TextView tempVal = (TextView)findViewById(R.id.codigo);
                tempVal.setText(dataProducto.getString("codigo"));

                tempVal = (TextView)findViewById(R.id.producto);
                tempVal.setText(dataProducto.getString("producto"));

                tempVal = (TextView)findViewById(R.id.marca);
                tempVal.setText(dataProducto.getString("marca"));

                tempVal = (TextView)findViewById(R.id.presentacion);
                tempVal.setText(dataProducto.getString("presentacion"));

                tempVal = (TextView)findViewById(R.id.descripcion);
                tempVal.setText(dataProducto.getString("descripcion"));

                tempVal = (TextView)findViewById(R.id.precio);
                tempVal.setText(dataProducto.getString("precio"));

                id = dataProducto.getString("_id");
                rev = dataProducto.getString("_rev");
            }
        }catch (Exception ex){
            ///
        }
    }

    private void mostrar(){
        Intent mostrarProductos = new Intent(Productos.this, MainActivity.class);
        startActivity(mostrarProductos);
    }

    private void guardar(){
        TextView tempVal = findViewById(R.id.codigo);
        String codigo = tempVal.getText().toString();

        tempVal = findViewById(R.id.producto);
        String producto = tempVal.getText().toString();

        tempVal = findViewById(R.id.marca);
        String marca = tempVal.getText().toString();

        tempVal = findViewById(R.id.presentacion);
        String presentacion = tempVal.getText().toString();

        tempVal = findViewById(R.id.descripcion);
        String descripcion = tempVal.getText().toString();

        tempVal = findViewById(R.id.precio);
        String precio = tempVal.getText().toString();
        try {
            JSONObject data = new JSONObject();
            if (accion.equals("modificar")){
                data.put("_id",id);
                data.put("_rev",rev);
            }
            data.put("codigo", codigo);
            data.put("producto", producto);
            data.put("marca", marca);
            data.put("presentacion", presentacion);
            data.put("descripcion", descripcion);
            data.put("precio", precio);
            enviarDatos objGuardar = new enviarDatos();
            objGuardar.execute(data.toString());
        }catch (Exception ex){
            Toast.makeText(getApplicationContext(), "Error: "+ ex.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    private class enviarDatos extends AsyncTask<String,String, String> {
        HttpURLConnection urlConnection;
        @Override
        protected String doInBackground(String... parametros) {
            StringBuilder stringBuilder = new StringBuilder();
            String jsonResponse = null;
            String jsonDatos = parametros[0];
            BufferedReader reader;
            try {
                URL url = new URL("");
                urlConnection = (HttpURLConnection)url.openConnection();
                urlConnection.setDoInput(true);
                urlConnection.setDoOutput(true);
                urlConnection.setRequestMethod("POST");
                urlConnection.setRequestProperty("Content-Type","application/json");
                urlConnection.setRequestProperty("Accept","application/json");
                Writer writer = new BufferedWriter(new OutputStreamWriter(urlConnection.getOutputStream(), "UTF-8"));
                writer.write(jsonDatos);
                writer.close();
                InputStream inputStream = urlConnection.getInputStream();
                if(inputStream==null){
                    return null;
                }
                reader = new BufferedReader(new InputStreamReader(inputStream));
                resp = reader.toString();
                String inputLine;
                StringBuffer stringBuffer = new StringBuffer();
                while ((inputLine=reader.readLine())!= null){
                    stringBuffer.append(inputLine+"\n");
                }
                if(stringBuffer.length()==0){
                    return null;
                }
                jsonResponse = stringBuffer.toString();
                return jsonResponse;
            }catch (Exception ex){
                //
            }
            return null;
        }
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            try{
                JSONObject jsonObject = new JSONObject(s);
                if(jsonObject.getBoolean("ok")){
                    Toast.makeText(getApplicationContext(), "DATO GUARDADO CON EXITO", Toast.LENGTH_SHORT).show();
                    mostrar();
                } else {
                    Toast.makeText(getApplicationContext(), "ERROR AL INTENTAR GUARDAR LOS DATOS", Toast.LENGTH_SHORT).show();
                }
            }catch (Exception e){
                Toast.makeText(getApplicationContext(), "ERROR AL GUARDAR "+e.getMessage(), Toast.LENGTH_LONG).show();
            }
        }
    }
}
