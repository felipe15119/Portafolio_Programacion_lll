package com.example.prueba;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;


public class SignUps extends AppCompatActivity implements View.OnClickListener {

    private EditText TextEmail;
    private EditText TextPassword;
    private Button btnRegistrar, btniniciarsesion;
    private ProgressDialog progressDialog;

    private FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_ups);


//inicializamos el objeto firebaseAuth
        firebaseAuth = FirebaseAuth.getInstance();

        //Referenciamos los views
        TextEmail = (EditText) findViewById(R.id.txtEmail2);
        TextPassword = (EditText) findViewById(R.id.txtContraseña2);

        btnRegistrar = (Button) findViewById(R.id.crear_cuenta);
        btniniciarsesion = (Button) findViewById(R.id.iniciar_sesion1);
        progressDialog = new ProgressDialog(this);

        //attaching listener to button
        btnRegistrar.setOnClickListener(this);
        btniniciarsesion.setOnClickListener(this);

    }



    private void registrarUsuario(){

        //Obtenemos el email y la contraseña desde las cajas de texto---------------------------------------------------------------------------------------------------------------
        String email = TextEmail.getText().toString().trim();
        String password  = TextPassword.getText().toString().trim();

        //Verificamos que las cajas de texto no esten vacías---------------------------------------------------------------------------------------------------------------
        if(TextUtils.isEmpty(email)){
            Toast.makeText(this,"Se debe ingresar un email",Toast.LENGTH_LONG).show();
            return;
        }
        if(TextUtils.isEmpty(password)){
            Toast.makeText(this,"Falta ingresar la contraseña",Toast.LENGTH_LONG).show();
            return;
        }
        progressDialog.setMessage("Realizando registro en linea...");
        progressDialog.show();

        //Registrar Nuevo Usuario---------------------------------------------------------------------------------------------------------------
        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        //checking if success
                        if(task.isSuccessful()){

                            Toast.makeText(SignUps.this, "Se ha registrado el usuario con el email: " + TextEmail.getText(), Toast.LENGTH_LONG).show();
                        } else {
                            if (task.getException() instanceof FirebaseAuthUserCollisionException) {//si se presenta una colisión
                                Toast.makeText(SignUps.this, "Este usuario ya existe ", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(SignUps.this, "No se pudo registrar el usuario ", Toast.LENGTH_LONG).show();
                            }
                        }
                        progressDialog.dismiss();
                    }
                });

    }

    private void loginUsuario(){
        //Obtenemos el email y la contraseña desde las cajas de texto---------------------------------------------------------------------------------------------------------------
        String email = TextEmail.getText().toString().trim();
        String password  = TextPassword.getText().toString().trim();

        //Verificamos que las cajas de texto no esten vacías---------------------------------------------------------------------------------------------------------------
        if(TextUtils.isEmpty(email)){
            Toast.makeText(this,"Se debe ingresar un email",Toast.LENGTH_LONG).show();
            return;
        }
        if(TextUtils.isEmpty(password)){
            Toast.makeText(this,"Falta ingresar la contraseña",Toast.LENGTH_LONG).show();
            return;
        }

        progressDialog.setMessage("Iniciando Sesion...");
        progressDialog.show();

        //Iniciar Sesion
        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        //checking if success
                        if(task.isSuccessful()){//Si el inicio de Sesion es Un Exito---------------------------------------------------------------------------------------------------------------
                            Toast.makeText(SignUps.this, "Bienvenid@: " + TextEmail.getText(), Toast.LENGTH_LONG).show();
                            Intent intencion = new Intent(getApplication(),MainActivity.class);
                            startActivity(intencion);
                        } else {
                            if (task.getException() instanceof FirebaseAuthInvalidUserException) {//Si el Usiario No existe---------------------------------------------------------------------------------------------------------------
                                Toast.makeText(SignUps.this, "El Usuario: "+ TextEmail.getText()+" No Existe...", Toast.LENGTH_SHORT).show();
                            } else {   //Si la contraseña es Incorrecta---------------------------------------------------------------------------------------------------------------
                                Toast.makeText(SignUps.this, "Contraseña Incorrecta", Toast.LENGTH_LONG).show();
                            }
                        }
                        progressDialog.dismiss();
                    }
                });
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.crear_cuenta:
            //Invocamos al método:---------------------------------------------------------------------------------------------------------------
            registrarUsuario();
            break;
            case R.id.iniciar_sesion1:
                //Invocamos al método:---------------------------------------------------------------------------------------------------------------
               loginUsuario();

        }

    }
}