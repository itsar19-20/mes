package com.example.ecommerce2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.accessibility.AccessibilityManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
Button registrati,accedi;
EditText email,password;
DatabaseHelper databaseHelper;
DatabaseAdapter databaseAdapter;
Cursor utente;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        registrati = findViewById(R.id.registrati);
        accedi = findViewById(R.id.accedi);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);

        databaseAdapter = new DatabaseAdapter(this);
        databaseAdapter.open();
        databaseHelper = new DatabaseHelper(this);

        registrati.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });

        accedi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoginActivity.this,MainActivity.class);
                if (email.getText().toString().isEmpty() || password.getText().toString().isEmpty()){
                    Toast.makeText(LoginActivity.this,"Attenzione, compila tutti i campi prima di procedere",Toast.LENGTH_SHORT).show();

                } else {
                    String passwordLog = password.getText().toString();
                    try{
                        utente = databaseAdapter.cercaConEmail(email.getText().toString());
                        utente.moveToFirst();
                        if (utente.getString(1).contentEquals(passwordLog)){
                            Toast.makeText(LoginActivity.this,"Login effettuato con successo!", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(LoginActivity.this,"Password errata",Toast.LENGTH_SHORT).show();
                        }
                    } catch (Exception e){

                    }
                    startActivity(i);
                }
            }
        });

    }
}
