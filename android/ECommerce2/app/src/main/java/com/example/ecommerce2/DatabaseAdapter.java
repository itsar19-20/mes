package com.example.ecommerce2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.CursorIndexOutOfBoundsException;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class DatabaseAdapter {
    @SuppressWarnings("unused")
    private static final String LOG_TAG = DatabaseAdapter.class.getSimpleName();

    private Context context;
    private SQLiteDatabase db;
    private DatabaseHelper dbHelper;

    private static final String TABELLA_DATABASE = "utente";

    public static final String KEY_USERID = "_id";
    public static final String KEY_NOME = "nome";
    public static final String KEY_COGNOME = "cognome";
    public static final String KEY_DATADINASCITA = "data_di_nascita";
    public static final String KEY_INDIRIZZO = "indirizzo";
    public static final String KEY_EMAIL = "email";
    public static final String KEY_PASSWORD = "password";
    public static final String KEY_NUMEROCARTA = "numero_carta";
    public static final String KEY_CVV = "cvv";

    public DatabaseAdapter(Context context){
        this.context = context;
    }

    public DatabaseAdapter open() throws SQLException{
        dbHelper = new DatabaseHelper(context);
        db = dbHelper.getWritableDatabase();
        return this;
    }
    public void close(){
        dbHelper.close();
    }

    private ContentValues contentValues(String nome, String cognome, String data_di_nascita,String indirizzo, String email, String password,String numero_carta, String cvv) {
        ContentValues values = new ContentValues();
        values.put(KEY_NOME, nome);
        values.put(KEY_COGNOME, cognome);
        values.put(KEY_DATADINASCITA, data_di_nascita);
        values.put(KEY_INDIRIZZO, indirizzo);
        values.put(KEY_EMAIL, email);
        values.put(KEY_PASSWORD, password);
        values.put(KEY_NUMEROCARTA, numero_carta);
        values.put(KEY_CVV, cvv);
        return values;
    }

    public long creaUtente(String nome, String cognome, String data_di_nascita,String indirizzo, String email, String password,String numero_carta, String cvv){
        ContentValues valoreIniziale = contentValues(nome, cognome, data_di_nascita, indirizzo, email, password, numero_carta, cvv);
        return db.insertOrThrow(DatabaseAdapter.TABELLA_DATABASE,null,valoreIniziale);
    }

    public boolean aggiornaUtente(long id, String nome, String cognome, String data_di_nascita,String indirizzo, String email, String password,String numero_carta, String cvv){
        ContentValues aggiornaValore = contentValues(nome, cognome, data_di_nascita, indirizzo, email, password, numero_carta, cvv);
        return db.update(TABELLA_DATABASE,aggiornaValore,KEY_USERID + "=" + id,null) > 0;
    }

    public boolean cancellaUtente(long id){
        return db.delete(TABELLA_DATABASE,KEY_USERID + "=" + id,null) > 0;
    }

    public Cursor prendiTuttiGliUtenti(){
        return db.query(TABELLA_DATABASE,new String[]{KEY_USERID,KEY_NOME,KEY_COGNOME,KEY_DATADINASCITA,KEY_INDIRIZZO,KEY_EMAIL,KEY_PASSWORD,KEY_NUMEROCARTA,KEY_CVV},null,null,null,null,null);
    }

    public Cursor filtraUtenti(String filtro){
        Cursor cursor = db.query(true, TABELLA_DATABASE,new String[]{KEY_USERID,KEY_NOME,KEY_COGNOME,KEY_DATADINASCITA,KEY_INDIRIZZO,KEY_EMAIL,KEY_PASSWORD,KEY_NUMEROCARTA,KEY_CVV}, KEY_NOME + "like '%",null,null,null,null,null);
        return cursor;
    }

    public Cursor cercaConEmail(String email){
        Cursor cursor = db.query(true,TABELLA_DATABASE,new String[]{KEY_EMAIL,KEY_PASSWORD},KEY_EMAIL + "=" + email + "'",null,null,null,null,null);
        return cursor;
    }
}
