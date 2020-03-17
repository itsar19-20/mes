package com.example.ecommerce2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Calendar;

public class RegisterActivity extends AppCompatActivity {
    EditText nome, cognome, indirizzo, email, password, numeroCarta, cvv;
    RadioButton mastercard, visa;
    Button registrati;
    RadioGroup rg;
    TextView data;
    static final String TAG = "RegisterActivity";
    DatePickerDialog.OnDateSetListener dataListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        final DatabaseAdapter databaseAdapter = new DatabaseAdapter(this);
        nome = findViewById(R.id.nome);
        cognome = findViewById(R.id.cognome);
        data = findViewById(R.id.data);
        indirizzo = findViewById(R.id.indirizzo);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        numeroCarta = findViewById(R.id.numeroCarta);
        cvv = findViewById(R.id.cvv);
        mastercard = findViewById(R.id.mastercard);
        visa = findViewById(R.id.visa);
        registrati = findViewById(R.id.registrati);
        rg = findViewById(R.id.rg);

        data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int anno = cal.get(Calendar.YEAR);
                int mese = cal.get(Calendar.MONTH);
                int giorno = cal.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog dialog = new DatePickerDialog(RegisterActivity.this, android.R.style.Theme_Black,dataListener,anno,mese,giorno);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        dataListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month+1;
                Log.d(TAG,"OnDateSet : dd/mm/yyyy : " + dayOfMonth + "/" + month + "/" + year);
                String date = dayOfMonth + "/" + month + "/" + year;
                data.setText(date);
            }
        };




        registrati.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View v) {
                if (nome.getText().toString().isEmpty() || cognome.getText().toString().isEmpty() || data.getText().toString().isEmpty() || indirizzo.getText().toString().isEmpty() || email.getText().toString().isEmpty() || password.getText().toString().isEmpty() || numeroCarta.getText().toString().isEmpty() || cvv.getText().toString().isEmpty())
                {
                    Toast.makeText(RegisterActivity.this, "Attenzione, compila tutti i campi prima di procedere", Toast.LENGTH_SHORT).show();
                    if (numeroCarta.getText().toString().length() < 16 && numeroCarta.getText().toString().length() > 0 ){
                        Toast.makeText(RegisterActivity.this,"Attenzione, il campo NUMERO CARTA deve contenere 16 caratteri",Toast.LENGTH_SHORT).show();
                    }
                    if (cvv.getText().toString().length() < 3 && cvv.getText().toString().length()> 0){
                        Toast.makeText(RegisterActivity.this,"Attenzione, il campo CVV deve contenere 3 caratteri",Toast.LENGTH_SHORT).show();
                    }

                    if (visa.isChecked() == false && mastercard.isChecked() == false) {

                        Toast.makeText(getApplicationContext(), "Attenzione, seleziona un metodo di pagamento", Toast.LENGTH_SHORT).show();
                    }

                }else {

                    String nomeReg = nome.getText().toString();
                    String cognomeReg = cognome.getText().toString();
                    String dataReg = data.getText().toString();
                    String indirizzoReg = indirizzo.getText().toString();
                    String emailReg = email.getText().toString();
                    String passwordReg = password.getText().toString();
                    String numeroCartaReg = numeroCarta.getText().toString();
                    String cvvReg = cvv.getText().toString();
                    try {
                        databaseAdapter.open();
                        databaseAdapter.creaUtente(nomeReg, cognomeReg, dataReg, indirizzoReg, emailReg, passwordReg, numeroCartaReg, cvvReg);
                        databaseAdapter.close();
                        Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                        Toast.makeText(RegisterActivity.this, "Registrazione effettuata con successo!", Toast.LENGTH_SHORT).show();
                        startActivity(intent);
                    } catch(Exception e){

                    }

                }
            }
        });


    }
}

