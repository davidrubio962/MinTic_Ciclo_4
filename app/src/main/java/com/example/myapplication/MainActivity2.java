package com.example.myapplication;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity2 extends AppCompatActivity {
    Button acceder, registrar;
    TextInputEditText correo ,contraseña;
    FirebaseAnalytics analytics;
    FirebaseAuth auth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        init();
        setup();
        events();


    }

    private void setup() {
        analytics=FirebaseAnalytics.getInstance(this);
        auth=FirebaseAuth.getInstance();

    }

    private void juan(String provider) {
        Bundle bundle= new Bundle();
        bundle.putString(FirebaseAnalytics.Param.METHOD,provider);
        analytics.logEvent(FirebaseAnalytics.Event.LOGIN,bundle);

    }

    private void events() {
        acceder.setOnClickListener(v -> {
            juan("email_and_password");

        });
        registrar.setOnClickListener(v -> {
            //registrarnos
            auth.createUserWithEmailAndPassword(correo.getText().toString(),contraseña.getText().toString()).addOnCompleteListener(this,task->{
                if(!task.isSuccessful()){
                    Toast.makeText(MainActivity2.this, "Error de registro", Toast.LENGTH_SHORT).show();
                    return;
                }
                FirebaseUser user = auth.getCurrentUser();
            });


        });
    }

    public void init()
    {
        acceder=findViewById(R.id.accesofinal);
        registrar=findViewById(R.id.registro);
        correo=findViewById(R.id.Login_email);
        contraseña=findViewById(R.id.ramiro);
    }

    @Override
    protected void onStart()
    {
        super.onStart();
        FirebaseUser user = auth.getCurrentUser();
        if(user!=null)
        {
            Intent nav = new Intent(this,MainActivity.class);
            nav.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(nav);
        }
    }
}