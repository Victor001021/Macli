package com.vitor.alcan.appclima;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DbHelper extends SQLiteOpenHelper {

    public static int VERSION = 1;
    public static String NOME_DB = "CIDADES";
    public static String DB = "cidade";

    public DbHelper(Context context) {
        super(context, NOME_DB, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "CREATE TABLE IF NOT EXISTS " + DB +
                " (id INTEGER PRIMARY KEY AUTOINCREMENT, nome TEXT NOT NULL);";

        try {
            sqLiteDatabase.execSQL(sql);
            Log.i("DB: ", "Criado com sucesso!");
        } catch (Exception e) {
            Log.i("DB: ", "Erro ao criar" + e.getMessage());
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        String sql = "DROP TABLE IF EXISTS " + DB + " ;";

        try {
            sqLiteDatabase.execSQL(sql);
            onCreate(sqLiteDatabase);

            Log.i("DB: ", "Sucesso ao atualizar");
        } catch (Exception e) {
            Log.i("DB: ", "Erro ao atualizar" + e.getMessage());
        }

    }

}
