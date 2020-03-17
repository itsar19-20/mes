package com.example.ecommerce2;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final int VERSIONE_DATABASE = 1;
    private static final String NOME_DATABASE = "Ecommerce.db";
    private static final String CREA_DATABASE = "create table utente (_id integer primary key autoincrement, nome text not null, cognome text not null, dataDiNascita text not null, indirizzo text not null, email text not null, password text not null, numeroCarta text not null, cvv text not null )";

    public DatabaseHelper(Context context){
        super(context, NOME_DATABASE,null,VERSIONE_DATABASE);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREA_DATABASE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS utente");
        onCreate(db);
    }
}
